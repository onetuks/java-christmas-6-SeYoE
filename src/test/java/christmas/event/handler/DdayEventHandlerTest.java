package christmas.event.handler;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Order;
import christmas.order.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DdayEventHandlerTest {

    private DdayEventHandler ddayEventHandler;

    @BeforeEach
    void setUp() {
        ddayEventHandler = new DdayEventHandler();
    }

    @Test
    @DisplayName("총 주문 금액이 만 원이 넘지 않은 주문으로는 이벤트 적용되지 않는다.")
    void supportMinimumPrice_LessThanMinimumPrice_False() {
        // Given
        final Order order = new Order("타파스-1, 제로콜라-1");

        // When
        final boolean result = ddayEventHandler.supportMinimumPrice(order);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("총주문 금액이 만 원 이상인 주문은 이벤트 적용된다.")
    void supportMinimumPrice_GreaterThanMinimumPrice_True() {
        // Given
        final Order order = new Order("타파스-3, 제로콜라-1");

        // When
        final boolean result = ddayEventHandler.supportMinimumPrice(order);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 1~25 사이라면 이벤트 적용된다.")
    void supportVisitDate_BetweenOneAndTwentyFive_True() {
        // Given
        final VisitDate visitDate = new VisitDate("3");

        // When
        final boolean result = ddayEventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 25일 넘어라면 이벤트 적용되지 않는다.")
    void supportVisitDate_LaterThanChristmas_False() {
        // Given
        final VisitDate visitDate = new VisitDate("26");

        // When
        final boolean result = ddayEventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("방문 날짜가 크리스마스 전이고, 총주문 금액이 만 원 이상이면 이벤트 적용된다.")
    void supports_BeforeChristmasAndGreaterThanMinimumPrice_True() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("타파스-3, 제로콜라-1");

        // When
        final boolean result = ddayEventHandler.supports(order, visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트 적용 시 천원 이상의 할인 금액만큼 할인된다.")
    void apply_ApplicableOrderAndVisitDate_DiscountMoreThan1K() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("타파스-3, 제로콜라-1");

        // When
        final int result = ddayEventHandler.apply(order, visitDate);

        // Then
        assertThat(result).isLessThan(-1_000);
    }

}
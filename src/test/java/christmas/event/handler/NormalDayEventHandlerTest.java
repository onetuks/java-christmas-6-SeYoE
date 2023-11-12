package christmas.event.handler;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Order;
import christmas.order.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

class NormalDayEventHandlerTest {

    private NormalDayEventHandler eventHandler;

    @BeforeEach
    void setUp() {
        eventHandler = new NormalDayEventHandler();
    }

    @Test
    @DisplayName("총 주문 금액이 만 원이 넘지 않은 주문으로는 이벤트 적용되지 않는다.")
    void supportMinimumPrice_LessThanMinimumPrice_False() {
        // Given
        final Order order = new Order("아이스크림-1");

        // When
        final boolean result = eventHandler.supportMinimumPrice(order);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("총주문 금액이 만 원 이상인 주문은 이벤트 적용된다.")
    void supportMinimumPrice_GreaterThanMinimumPrice_True() {
        // Given
        final Order order = new Order("아이스크림-3, 초코케이크-1");

        // When
        final boolean result = eventHandler.supportMinimumPrice(order);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 평일이 아니라면 이벤트 적용된다.")
    void supportVisitDate_NotNormalDay_False() {
        // Given
        final VisitDate visitDate = new VisitDate("2");

        // When
        final boolean result = eventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("방문 날짜가 평일이면 이벤트 적용된다.")
    void supportVisitDate_NormalDay_True() {
        // Given
        final VisitDate visitDate = new VisitDate("3");

        // When
        final boolean result = eventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 평일이고, 총주문 금액이 만 원 이상이면 이벤트 적용된다.")
    void supports_BeforeChristmasAndGreaterThanMinimumPrice_True() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("아이스크림-3, 초코케이크-1");

        // When
        final boolean result = eventHandler.supports(order, visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트 적용 시 디저트 메뉴가 없다면 할인된 금액 없다.")
    void apply_DesertNotInOrder_NotDiscount() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("티본스테이크-1");

        // When
        final int result = eventHandler.apply(order, visitDate);

        // Then
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("이벤트 적용 시 디저트 메뉴 1개 당 2,023원 할인 된다.")
    void apply_DesertInOrder_Discount() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("아이스크림-3, 초코케이크-1");

        // When
        final int result = eventHandler.apply(order, visitDate);

        // Then
        assertThat(result).isEqualTo(-2_023 * 4);
    }

}
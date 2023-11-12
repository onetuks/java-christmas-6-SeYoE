package christmas.benefit.handler;

import static christmas.order.vo.Menu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveAwayEventHandlerTest {

    private GiveAwayEventHandler eventHandler;

    @BeforeEach
    void setUp() {
        eventHandler = new GiveAwayEventHandler();
    }

    @Test
    @DisplayName("총 주문 금액이 12만 원이 넘지 않은 주문으로는 이벤트 적용되지 않는다.")
    void supportMinimumPrice_LessThanMinimumPrice_False() {
        // Given
        final OrderHistory orderHistory = new OrderHistory("아이스크림-1");

        // When
        final boolean result = eventHandler.supportMinimumPrice(orderHistory);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("총주문 금액이 12만 원 이상인 주문은 이벤트 적용된다.")
    void supportMinimumPrice_GreaterThanMinimumPrice_True() {
        // Given
        final OrderHistory orderHistory = new OrderHistory("아이스크림-3, 티본스테이크-10");

        // When
        final boolean result = eventHandler.supportMinimumPrice(orderHistory);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 12월 중이면 이벤트 적용된다.")
    void supportVisitDate_NormalDay_True() {
        // Given
        final VisitDate visitDate = new VisitDate("3");

        // When
        final boolean result = eventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 12월 중이고, 총주문 금액이 12만 원 이상이면 이벤트 적용된다.")
    void supports_BeforeChristmasAndGreaterThanMinimumPrice_True() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final OrderHistory orderHistory = new OrderHistory("아이스크림-3, 티본스테이크-10");

        final Order order = new Order(visitDate, orderHistory);

        // When
        final boolean result = eventHandler.supports(order);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트 적용 시 샴페인 가격만큼 할인된다.")
    void apply_ApplicableOrderAndVisitDate_DiscountChampagnePrice() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final OrderHistory orderHistory = new OrderHistory("아이스크림-3, 티본스테이크-10");

        final Order order = new Order(visitDate, orderHistory);

        // When
        final int result = eventHandler.apply(order);

        // Then
        assertThat(result).isEqualTo(-CHAMPAGNE.getMenuPrice());
    }

}
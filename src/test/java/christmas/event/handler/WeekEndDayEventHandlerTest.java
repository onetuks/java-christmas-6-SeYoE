package christmas.event.handler;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekEndDayEventHandlerTest {

    private WeekEndDayEventHandler eventHandler;

    @BeforeEach
    void setUp() {
        eventHandler = new WeekEndDayEventHandler();
    }

    @Test
    @DisplayName("총 주문 금액이 만 원이 넘지 않은 주문으로는 이벤트 적용되지 않는다.")
    void supportMinimumPrice_LessThanMinimumPrice_False() {
        // Given
        final OrderHistory orderHistory = new OrderHistory("양송이수프-1");

        // When
        final boolean result = eventHandler.supportMinimumPrice(orderHistory);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("총주문 금액이 만 원 이상인 주문은 이벤트 적용된다.")
    void supportMinimumPrice_GreaterThanMinimumPrice_True() {
        // Given
        final OrderHistory orderHistory = new OrderHistory("티본스테이크-3, 초코케이크-1");

        // When
        final boolean result = eventHandler.supportMinimumPrice(orderHistory);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 주말이 아니라면 이벤트 적용된다.")
    void supportVisitDate_NotWeekEndVisitDate_False() {
        // Given
        final VisitDate visitDate = new VisitDate("4");

        // When
        final boolean result = eventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("방문 날짜가 주말이라면 이벤트 적용된다.")
    void supportVisitDate_WeekEndVisitDate_True() {
        // Given
        final VisitDate visitDate = new VisitDate("2");

        // When
        final boolean result = eventHandler.supportVisitDate(visitDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("방문 날짜가 주말이고, 총주문 금액이 만 원 이상이면 이벤트 적용된다.")
    void supports_BeforeChristmasAndGreaterThanMinimumPrice_True() {
        // Given
        final VisitDate visitDate = new VisitDate("2");
        final OrderHistory orderHistory = new OrderHistory("티본스테이크-3, 초코케이크-1");

        final Order order = new Order(visitDate, orderHistory);

        // When
        final boolean result = eventHandler.supports(order);

        // Then
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("이벤트 적용 시 메인 메뉴가 없다면 할인된 금액 없다.")
    void apply_MainMenuNotInOrder_NotDiscount() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final OrderHistory orderHistory = new OrderHistory("아이스크림-10");

        final Order order = new Order(visitDate, orderHistory);

        // When
        final int result = eventHandler.apply(order);

        // Then
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("이벤트 적용 시 메인 메뉴 1개 당 2,023원 할인된다.")
    void apply_MainMenuInOrder_Discount() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final OrderHistory orderHistory = new OrderHistory("티본스테이크-3, 초코케이크-1");

        final Order order = new Order(visitDate, orderHistory);

        // When
        final int result = eventHandler.apply(order);

        // Then
        assertThat(result).isEqualTo(-2_023 * 3);
    }

}
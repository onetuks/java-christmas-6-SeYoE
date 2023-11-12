package christmas.event;

import static christmas.order.Menu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Order;
import christmas.order.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBenefitTest {

    @Test
    @DisplayName("총주문 금액이 12만원 이상인 경우 증정 메뉴는 샴페인 1개다.")
    void getGiveAwayMenu_GreaterThan120K_GiveOneChampagne() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("티본스테이크-10");

        final EventBenefit eventBenefit = new EventBenefit(order, visitDate);

        // When
        final String result = eventBenefit.getGiveAwayMenu();

        // Then
        assertThat(result).contains(CHAMPAGNE.getMenuName());
    }

    @Test
    @DisplayName("총주문 금액이 12만원 미만인 경우 증정 메뉴는 없음이다.")
    void getGiveAwayMenu_LessThan120K_GiveOneChampagne() {
        // Given
        final VisitDate visitDate = new VisitDate("4");
        final Order order = new Order("티본스테이크-1");

        final EventBenefit eventBenefit = new EventBenefit(order, visitDate);

        // When
        final String result = eventBenefit.getGiveAwayMenu();

        // Then
        assertThat(result).contains("없음");
    }

    @Test
    @DisplayName("이벤트 덕으로 혜택 받은 금액을 반환한다.")
    void getTotalBenefitPrice_ReturnBenefitPrice() {
        // Given
        final VisitDate visitDate = new VisitDate("25");
        final Order order = new Order("티본스테이크-1, 초코케이크-3, 제로콜라-2");

        final EventBenefit eventBenefit = new EventBenefit(order, visitDate);

        // When
        final int result = eventBenefit.getTotalBenefitPrice();

        // Then
        assertThat(result).isEqualTo(-10_469);
    }

}
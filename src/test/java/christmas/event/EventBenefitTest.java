package christmas.event;

import static christmas.event.DecemberEvent.GIVE_AWAY_EVENT;
import static christmas.order.Menu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.order.Menu;
import christmas.order.Order;
import christmas.order.VisitDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
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

}
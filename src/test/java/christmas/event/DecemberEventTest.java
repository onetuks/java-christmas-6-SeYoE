package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.order.Order;
import christmas.order.VisitDate;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DecemberEventTest {

    @Test
    @DisplayName("방문 날짜와 주문에 따라 이벤트가 중복되어 적용된다.")
    void applyEvents_VisitDateAndOrder_Map() {
        // Given
        final VisitDate visitDate = new VisitDate("25");
        final Order order = new Order("초코케이크-2, 티본스테이크-3, 제로콜라-3");

        // When
        Map<String, Integer> result = DecemberEvent.applyEvents(order, visitDate);

        // Then
        assertThat(result.keySet()).hasSize(4);
    }

    @Test
    @DisplayName("방문 날짜와 주문에 따라 아무런 이벤트가 적용되지 않을 수 있다.")
    void applyEvents_VisitDateAndOrder_EmptyMap() {
        // Given
        final VisitDate visitDate = new VisitDate("26");
        final Order order = new Order("아이스크림-1");

        // When
        Map<String, Integer> result = DecemberEvent.applyEvents(order, visitDate);

        // Then
        assertThat(result.keySet()).isEmpty();
    }

}
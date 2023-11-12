package christmas.order;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.order.vo.OrderAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderHistoryAmountTest {

    @Test
    @DisplayName("숫자가 아닌 값으로 수량 생성 실패한다.")
    void createOrderAmount_NotDigitValue_Fail() {
        // Given
        final String amount = "가";

        // When & Then
        assertThatThrownBy(() -> new OrderAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("0보다 작거나 같은 값으로 수량 생성 실패한다.")
    void createOrderAmount_LessThanAndEqualToZero_Fail() {
        // Given
        final String amount = "0";

        // When & Then
        assertThatThrownBy(() -> new OrderAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("0보다 크고 숫자인 값으로 수량 생성 성공한다.")
    void createOrderAmount_DigitAndGreaterThanZero_Success() {
        // Given
        final String amount = "1";

        // When
        final OrderAmount result = new OrderAmount(amount);

        // Then
        assertThat(result.getAmount()).isEqualTo(Integer.parseInt(amount));
    }

}
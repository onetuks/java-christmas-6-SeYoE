package christmas.order;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderHistoryTest {

    @Test
    @DisplayName("중복되지 않은 메뉴 주문 시 주문 메뉴 생성 성공한다.")
    void createOrder_NotDuplicatedMenu_Success() {
        // Given
        final String inputOrder = "해산물파스타-2, 레드와인-1, 초코케이크-1";

        // When
        final OrderHistory result = new OrderHistory(inputOrder);

        // Then
        assertThat(result.getTotalPrice()).isGreaterThan(1_000);
    }

    @Test
    @DisplayName("중복되는 메뉴 주문 시 예외 던진다.")
    void createOrder_DuplicatedMenu_Fail() {
        // Given
        final String inputOrder = "시저샐러드-1, 시저샐러드-1";

        // When & Then
        assertThatThrownBy(() -> new OrderHistory(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴를 한 번에 20개 넘게 시키면 예외를 던진다.")
    void createOrder_GreaterThanUpperMenuLimit_Exception() {
        // Given
        final String inputOrder = "해산물파스타-17, 레드와인-3, 초코케이크-1";

        // When & Then
        assertThatThrownBy(() -> new OrderHistory(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("음료만 주문하면 예외를 던진다.")
    void createOrder_OnlyBeverage_Exception() {
        // Given
        final String inputOrder = "레드와인-3";

        // When & Then
        assertThatThrownBy(() -> new OrderHistory(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

}
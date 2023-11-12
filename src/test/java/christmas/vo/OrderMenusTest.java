package christmas.vo;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.order.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    @Test
    @DisplayName("중복되지 않은 메뉴 주문 시 주문 메뉴 생성 성공한다.")
    void createOrderMenus_NotDuplicatedMenu_Success() {
        // Given
        final String inputOrder = "해산물파스타-2, 레드와인-1, 초코케이크-1";

        // When
        final OrderMenus result = new OrderMenus(inputOrder);

        // Then
        assertThat(result.getTotalPrice()).isGreaterThan(1_000);
    }

    @Test
    @DisplayName("중복되는 메뉴 주문 시 주문 메뉴 생성 실패한다.")
    void createOrderMenus_DuplicatedMenu_Fail() {
        // Given
        final String inputOrder = "시저샐러드-1, 시저샐러드-1";

        // When & Then
        assertThatThrownBy(() -> new OrderMenus(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

}
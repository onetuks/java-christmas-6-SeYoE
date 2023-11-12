package christmas.vo;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static christmas.order.Menu.CHOCO_CAKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("메뉴에 있는 메뉴 이름으로 매칭되는 메뉴를 구한다.")
    void nameOf_NameInMenu_getMenu() {
        // Given
        final String menuName = "초코케이크";

        // When
        final Menu result = Menu.nameOf(menuName);

        // Then
        assertThat(result).isEqualTo(CHOCO_CAKE);
    }

    @Test
    @DisplayName("메뉴에 없는 메뉴 이름으로는 예외를 던진다.")
    void nameOf_NameNotInMenu_Exception() {
        // Given
        final String menuName = "미역국";

        // When & Then
        assertThatThrownBy(() -> Menu.nameOf(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MENU_ORDER.getMessage());
    }

}
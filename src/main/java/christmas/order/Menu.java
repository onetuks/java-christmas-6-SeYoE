package christmas.order;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static christmas.order.MenuType.APPETIZER;
import static christmas.order.MenuType.BEVERAGE;
import static christmas.order.MenuType.DESERT;
import static christmas.order.MenuType.MAIN_DISH;

import java.util.Arrays;
import java.util.Objects;

public enum Menu {
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STAKE(MAIN_DISH, "티본스테이크", 55_000),
    BARBECUE_RIBS(MAIN_DISH, "바비큐립", 54_000),
    SEA_FOOD_PASTA(MAIN_DISH, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_DISH, "크라스마스파스타", 25_000),

    CHOCO_CAKE(DESERT, "초코케이크", 15_000),
    ICE_CREAM(DESERT, "아이스크림", 5_000),

    ZERO_COKE(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샹페인", 25_000);

    private final MenuType menuType;
    private final String menuName;
    private final int menuPrice;

    Menu(final MenuType menuType, final String menuName, final int menuPrice) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public static Menu nameOf(final String value) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Objects.equals(menu.menuName, value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MENU_ORDER.getMessage()));
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

}

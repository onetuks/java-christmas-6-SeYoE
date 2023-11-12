package christmas.order;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static christmas.order.vo.MenuType.BEVERAGE;

import christmas.order.vo.Menu;
import christmas.order.vo.MenuType;
import christmas.order.vo.OrderAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class OrderHistory {

    private static final String COMMA_SEPARATOR = ",";
    private static final String DASH_SEPARATOR = "-";

    private static final int MENU_COLUMN_INDEX = 0;
    private static final int AMOUNT_COLUMN_INDEX = 1;

    private static final int ORDER_SPLIT_COLUMN_COUNT = 2;
    private static final int ORDER_MENU_AMOUNT_LIMIT = 20;
    private static final int ONE_MENU_TYPE = 1;

    private final Map<Menu, OrderAmount> history;

    public OrderHistory(final String orderLine) {
        final List<String> orderLines = parseOrderLines(orderLine);
        final Map<Menu, OrderAmount> orders = getOrderMenus(orderLines);

        validateAmountLimit(orders);
        validateOnlyBeverage(orders);

        this.history = orders;
    }

    public int getTotalPrice() {
        return history.entrySet()
                .stream()
                .mapToInt(entry -> {
                    final int menuPrice = entry.getKey().getMenuPrice();
                    final int amount = entry.getValue().getAmount();

                    return menuPrice * amount;
                })
                .sum();
    }

    public Map<Menu, OrderAmount> getHistory() {
        return history;
    }

    private List<String> parseOrderLines(final String inputOrder) {
        final String[] orders = inputOrder.split(COMMA_SEPARATOR);

        return Arrays.stream(orders)
                .map(String::trim)
                .toList();
    }

    private Map<Menu, OrderAmount> getOrderMenus(final List<String> orders) {
        return orders.stream()
                .map(this::splitOrderToMenuAndAmount)
                .collect(Collectors.toMap(
                        orderParts -> Menu.nameOf(orderParts[MENU_COLUMN_INDEX]),
                        orderParts -> new OrderAmount(orderParts[AMOUNT_COLUMN_INDEX]),
                        throwExceptionIfExistingMenu()
                ));
    }

    private BinaryOperator<OrderAmount> throwExceptionIfExistingMenu() {
        return (existing, replacement) -> {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        };
    }

    private String[] splitOrderToMenuAndAmount(final String order) {
        final String[] split = order.split(DASH_SEPARATOR);

        if (split.length != ORDER_SPLIT_COLUMN_COUNT) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }

        return split;
    }

    private void validateAmountLimit(final Map<Menu, OrderAmount> orders) {
        final int totalAmount = orders.values()
                .stream()
                .mapToInt(OrderAmount::getAmount)
                .sum();

        if (totalAmount > ORDER_MENU_AMOUNT_LIMIT) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateOnlyBeverage(final Map<Menu, OrderAmount> orders) {
        final Set<MenuType> menuTypes = orders.keySet()
                .stream()
                .map(Menu::getMenuType)
                .collect(Collectors.toSet());

        if (menuTypes.size() == ONE_MENU_TYPE && menuTypes.contains(BEVERAGE)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

}

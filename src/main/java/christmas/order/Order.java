package christmas.order;

import static christmas.error.ErrorMessage.MORE_THAN_MENU_AMOUNT_LIMIT;
import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;
import static christmas.error.ErrorMessage.NOT_ONLY_BEVERAGE_ORDER;
import static christmas.order.MenuType.BEVERAGE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {

    private static final int ORDER_SPLIT_COLUMN_COUNT = 2;
    private static final int ORDER_MENU_AMOUNT_LIMIT = 20;

    private final Map<Menu, OrderAmount> orderHistory;

    public Order(final String orderLine) {
        final List<String> orderLines = parseOrderLines(orderLine);
        final Map<Menu, OrderAmount> orders = getOrderMenus(orderLines);

        validateAmountLimit(orders);
        validateOnlyBeverage(orders);

        this.orderHistory = orders;
    }

    public int getTotalPrice() {
        return orderHistory.entrySet()
                .stream()
                .mapToInt(entry -> {
                    final int menuPrice = entry.getKey().getMenuPrice();
                    final int amount = entry.getValue().getAmount();

                    return menuPrice * amount;
                })
                .sum();
    }

    public Map<Menu, OrderAmount> getOrderHistory() {
        return orderHistory;
    }

    private List<String> parseOrderLines(final String inputOrder) {
        final String[] orders = inputOrder.split(",");

        return Arrays.stream(orders)
                .map(String::trim)
                .toList();
    }

    private Map<Menu, OrderAmount> getOrderMenus(final List<String> orders) {
        return orders.stream()
                .map(this::splitOrderToMenuAndAmount)
                .collect(Collectors.toMap(
                        orderParts -> Menu.nameOf(orderParts[0]),
                        orderParts -> new OrderAmount(orderParts[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
                        }
                ));
    }

    private String[] splitOrderToMenuAndAmount(final String order) {
        final String[] split = order.split("-");

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
            throw new IllegalArgumentException(MORE_THAN_MENU_AMOUNT_LIMIT.getMessage());
        }
    }

    private void validateOnlyBeverage(final Map<Menu, OrderAmount> orders) {
        final Set<MenuType> menuTypes = orders.keySet()
                .stream()
                .map(Menu::getMenuType)
                .collect(Collectors.toSet());

        if (menuTypes.size() == 1 && menuTypes.contains(BEVERAGE)) {
            throw new IllegalArgumentException(NOT_ONLY_BEVERAGE_ORDER.getMessage());
        }
    }

}

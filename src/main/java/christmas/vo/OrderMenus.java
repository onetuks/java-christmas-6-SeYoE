package christmas.vo;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenus {

    private final Map<Menu, OrderAmount> menus;

    public OrderMenus(final String inputOrder) {
        final List<String> orders = parseOrders(inputOrder);

        this.menus = getOrderMenus(orders);
    }

    public int getTotalPrice() {
        return menus.entrySet()
                .stream()
                .mapToInt(entry -> {
                    final int menuPrice = entry.getKey().getMenuPrice();
                    final int amount = entry.getValue().getAmount();

                    return menuPrice * amount;
                })
                .sum();
    }

    public boolean isEmpty() {
        return this.menus.isEmpty();
    }

    public Map<Menu, OrderAmount> getMenus() {
        return menus;
    }

    private List<String> parseOrders(final String inputOrder) {
        final String[] orders = inputOrder.split(",");

        return Arrays.stream(orders)
                .map(String::trim)
                .toList();
    }

    private Map<Menu, OrderAmount> getOrderMenus(final List<String> orders) {
        return orders.stream()
                .map(order -> {
                    String[] split = order.split("-");
                    if (split.length != 2) {
                        throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
                    }
                    return split;
                })
                .collect(Collectors.toMap(
                        orderParts -> Menu.nameOf(orderParts[0]),
                        orderParts -> new OrderAmount(orderParts[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
                        }
                ));
    }

}

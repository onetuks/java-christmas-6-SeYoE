package christmas.event.handler;

import static christmas.order.vo.MenuType.DESERT;

import christmas.event.EventHandler;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import java.util.List;

public class NormalDayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 10_000;

    private static final int DISCOUNT_PRICE = 2_023;

    private static final List<Integer> EVENT_DAYS = List.of(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31
    );

    @Override
    public boolean supports(final Order order) {
        final boolean validVisitDate = supportVisitDate(order.visitDate());
        final boolean validPriceLimit = supportMinimumPrice(order.orderHistory());

        return validVisitDate && validPriceLimit;
    }

    @Override
    public boolean supportMinimumPrice(final OrderHistory orderHistory) {
        return orderHistory.getTotalPrice() >= MINIMUM_PRICE;
    }

    @Override
    public boolean supportVisitDate(final VisitDate visitDate) {
        return EVENT_DAYS.contains(visitDate.getDate());
    }

    @Override
    public int apply(final Order order) {
        return -order.orderHistory()
                .getHistory()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getMenuType() == DESERT)
                .mapToInt(entry -> entry.getValue().getAmount() * DISCOUNT_PRICE)
                .sum();
    }

}

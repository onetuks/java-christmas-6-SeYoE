package christmas.event.handler;

import static christmas.order.vo.MenuType.MAIN_DISH;

import christmas.event.EventHandler;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import java.util.List;

public class WeekEndDayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 10_000;

    private static final int DISCOUNT_PRICE = 2_023;

    private static final List<Integer> EVENT_DAYS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

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
                .filter(entry -> entry.getKey().getMenuType() == MAIN_DISH)
                .mapToInt(entry -> entry.getValue().getAmount() * DISCOUNT_PRICE)
                .sum();
    }
}

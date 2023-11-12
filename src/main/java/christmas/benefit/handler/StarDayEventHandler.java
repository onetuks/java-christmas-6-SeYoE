package christmas.benefit.handler;

import christmas.benefit.EventHandler;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import java.util.List;

public class StarDayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 10_000;

    private static final int DISCOUNT_PRICE = 1_000;

    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);

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
        return STAR_DAYS.contains(visitDate.getDate());
    }

    @Override
    public int apply(final Order order) {
        return -DISCOUNT_PRICE;
    }

}

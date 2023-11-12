package christmas.event.handler;

import christmas.event.EventHandler;
import christmas.order.Order;
import christmas.order.VisitDate;
import java.util.List;

public class StarDayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 10_000;

    private static final int DISCOUNT_PRICE = 1_000;

    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);

    @Override
    public boolean supports(final Order order, final VisitDate visitDate) {
        return supportMinimumPrice(order) && supportVisitDate(visitDate);
    }

    @Override
    public boolean supportMinimumPrice(final Order order) {
        return order.getTotalPrice() >= MINIMUM_PRICE;
    }

    @Override
    public boolean supportVisitDate(final VisitDate visitDate) {
        return STAR_DAYS.contains(visitDate.getDate());
    }

    @Override
    public int apply(final Order order, final VisitDate visitDate) {
        return -DISCOUNT_PRICE;
    }

}

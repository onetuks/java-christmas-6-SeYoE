package christmas.benefit.handler;

import christmas.benefit.EventHandler;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import java.util.List;
import java.util.stream.IntStream;

public class DdayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 10_000;

    private static final int DISCOUNT_DEFAULT_PRICE = 1_000;
    private static final int DISCOUNT_PRICE = 100;

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 25;
    private static final List<Integer> EVENT_DAYS = IntStream.rangeClosed(EVENT_START_DATE, EVENT_END_DATE)
            .boxed()
            .toList();

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
        final int currentDate = order.visitDate().getDate();

        return -(DISCOUNT_DEFAULT_PRICE + DISCOUNT_PRICE * (currentDate - EVENT_START_DATE));
    }

}

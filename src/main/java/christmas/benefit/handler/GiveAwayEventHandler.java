package christmas.benefit.handler;

import static christmas.order.vo.Menu.CHAMPAGNE;

import christmas.benefit.EventHandler;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import java.util.List;
import java.util.stream.IntStream;

public class GiveAwayEventHandler implements EventHandler {

    private static final int MINIMUM_PRICE = 120_000;

    private static final int EVENT_START_DAY = 1;
    private static final int EVENT_END_DAY = 31;
    private static final List<Integer> EVENT_DAYS = IntStream.rangeClosed(EVENT_START_DAY, EVENT_END_DAY)
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
        return -CHAMPAGNE.getMenuPrice();
    }
}

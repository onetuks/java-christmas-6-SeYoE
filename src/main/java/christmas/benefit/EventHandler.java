package christmas.benefit;

import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;

public interface EventHandler {

    boolean supports(final Order order);

    boolean supportMinimumPrice(final OrderHistory orderHistory);

    boolean supportVisitDate(final VisitDate visitDate);

    int apply(final Order order);

}

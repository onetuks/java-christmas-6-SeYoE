package christmas.event;

import christmas.order.Order;
import christmas.order.VisitDate;

public interface EventHandler {

    boolean supports(final Order order, final VisitDate visitDate);

    boolean supportMinimumPrice(final Order order);

    boolean supportVisitDate(final VisitDate visitDate);

    int apply(final Order order, final VisitDate visitDate);

}

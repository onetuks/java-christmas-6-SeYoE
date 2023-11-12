package christmas;

import christmas.benefit.EventBenefit;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.order.Order;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import christmas.result.EventResult;

public class EventPlanner {

    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final Order order = eventOrder();

        final EventBenefit eventBenefit = eventBenefit(order);

        eventResult(order, eventBenefit);
    }

    private void eventResult(final Order order, final EventBenefit eventBenefit) {
        final int benefitPrice = eventBenefit.getTotalBenefitPrice();
        final int originPrice = order.orderHistory()
                .getTotalPrice();

        final EventResult eventResult = new EventResult(originPrice, benefitPrice);

        outputView.printPaymentPrice(eventResult);
        outputView.printEventBadge(eventResult);
    }

    private EventBenefit eventBenefit(final Order order) {
        final EventBenefit eventBenefit = new EventBenefit(order);

        outputView.printGiveAwayMenu(eventBenefit);
        outputView.printEventBenefitPrices(eventBenefit);
        outputView.printTotalBenefitPrice(eventBenefit);

        return eventBenefit;
    }

    private Order eventOrder() {
        outputView.printWelcome();

        final VisitDate visitDate = inputView.inputVisitDate();
        final OrderHistory orderHistory = inputView.inputOrderHistory();

        outputView.printEventDetails(visitDate);
        outputView.printOrderMenu(orderHistory);
        outputView.printTotalPrice(orderHistory);

        return new Order(visitDate, orderHistory);
    }
}

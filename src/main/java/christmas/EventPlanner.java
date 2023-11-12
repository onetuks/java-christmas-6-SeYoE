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

        outputView.outputPaymentPrice(eventResult);
        outputView.outputEventBadge(eventResult);
    }

    private EventBenefit eventBenefit(final Order order) {
        final EventBenefit eventBenefit = new EventBenefit(order);

        outputView.outputGiveAwayMenu(eventBenefit);
        outputView.outputEventBenefitPrices(eventBenefit);
        outputView.outputTotalBenefitPrice(eventBenefit);

        return eventBenefit;
    }

    private Order eventOrder() {
        outputView.outputWelcome();

        final VisitDate visitDate = inputView.inputVisitDate();
        final OrderHistory orderHistory = inputView.inputMenuOrder();

        outputView.outputEventDetails(visitDate);
        outputView.outputOrderMenu(orderHistory);
        outputView.outputTotalPrice(orderHistory);

        return new Order(visitDate, orderHistory);
    }
}

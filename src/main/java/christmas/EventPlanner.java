package christmas;

import christmas.event.EventBenefit;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.order.Order;
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







        outputView.outputEventBenefitPrices(eventBenefit);
        outputView.outputTotalBenefitPrice(eventBenefit);


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

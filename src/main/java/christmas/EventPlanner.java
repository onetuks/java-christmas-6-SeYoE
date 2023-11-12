package christmas;

import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.order.Order;
import christmas.order.OrderMenus;
import christmas.order.VisitDate;

public class EventPlanner {

    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.outputWelcome();

        final VisitDate visitDate = inputView.inputVisitDate();
        final OrderMenus orderMenus = inputView.inputMenuOrder();

        final Order order = new Order(visitDate, orderMenus);


        outputView.outputEventDetails(visitDate);

        outputView.outputOrderMenu(orderMenus);

        outputView.outputTotalPrice(orderMenus);
    }
}

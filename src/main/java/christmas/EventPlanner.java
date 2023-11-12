package christmas;

import christmas.event.DecemberEvent;
import christmas.event.EventBenefit;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.order.Order;
import christmas.order.VisitDate;
import java.util.Map;

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
        final Order order = inputView.inputMenuOrder();

        outputView.outputEventDetails(visitDate);

        outputView.outputOrderMenu(order);

        outputView.outputTotalPrice(order);

        EventBenefit eventBenefit = new EventBenefit(order, visitDate);

        outputView.outputGiveAwayMenu(eventBenefit);

        outputView.outputEventBenefits(eventBenefit);
    }
}

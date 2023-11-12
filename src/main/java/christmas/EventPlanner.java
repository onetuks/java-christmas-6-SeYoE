package christmas;

import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.vo.OrderMenus;
import christmas.vo.VisitDate;

public class EventPlanner {

    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.outputWelcome();

        VisitDate visitDate = inputView.inputVisitDate();

        OrderMenus orderMenus = inputView.inputMenuOrder();

        outputView.outputEventDetails(visitDate);


    }
}

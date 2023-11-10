package christmas;

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
    }
}

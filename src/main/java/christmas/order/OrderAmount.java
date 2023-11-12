package christmas.order;

import static christmas.error.ErrorMessage.NEGATIVE_MENU_AMOUNT;
import static christmas.error.ErrorMessage.NOT_DIGIT_MENU_AMOUNT;

public class OrderAmount {

    private static final int AMOUNT_LOWER_BOUND = 0;

    private final int amount;

    public OrderAmount(final String amount) {
        final int parsedAmount = parseInt(amount);

        validatePositiveAmount(parsedAmount);

        this.amount = parsedAmount;
    }

    public int getAmount() {
        return amount;
    }

    private void validatePositiveAmount(final int parsedAmount) {
        if (parsedAmount <= AMOUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(NEGATIVE_MENU_AMOUNT.getMessage());
        }
    }

    private int parseInt(final String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_DIGIT_MENU_AMOUNT.getMessage());
        }
    }

}

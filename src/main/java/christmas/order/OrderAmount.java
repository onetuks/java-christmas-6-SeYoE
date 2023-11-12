package christmas.order;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;

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
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private int parseInt(final String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

}

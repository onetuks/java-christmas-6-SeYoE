package christmas.order.vo;

import static christmas.error.ErrorMessage.INVALID_MENU_ORDER;

import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderAmount that = (OrderAmount) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderAmount.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .toString();
    }

}

package christmas.result;

import static christmas.error.ErrorMessage.INVALID_BENEFIT_PRICE;

public class EventResult {

    private final int totalOriginPrice;
    private final int totalBenefitPrice;

    public EventResult(final int totalOriginPrice, final int totalBenefitPrice) {
        validatePrice(totalOriginPrice, totalBenefitPrice);

        this.totalOriginPrice = totalOriginPrice;
        this.totalBenefitPrice = totalBenefitPrice;
    }

    public int getPaymentPrice() {
        return totalOriginPrice - totalBenefitPrice;
    }

    public String getEventBadgeName() {
        return EventBadge.priceOf(totalBenefitPrice)
                .getBadgeName();
    }

    private void validatePrice(final int totalOriginPrice, final int totalBenefitPrice) {
        if (totalOriginPrice < totalBenefitPrice) {
            throw new IllegalArgumentException(INVALID_BENEFIT_PRICE.getMessage());
        }
    }

}

package christmas.result;

import java.util.Arrays;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String badgeName;
    private final int boundPrice;

    EventBadge(
            final String badgeName,
            final int boundPrice
    ) {
        this.badgeName = badgeName;
        this.boundPrice = boundPrice;
    }

    public static EventBadge priceOf(final int price) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.getBoundPrice() <= price)
                .findFirst()
                .orElse(NONE);
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getBoundPrice() {
        return boundPrice;
    }
}

package christmas.event;

import static christmas.event.DecemberEvent.GIVE_AWAY_EVENT;
import static christmas.order.Menu.CHAMPAGNE;

import christmas.order.Menu;
import christmas.order.Order;
import christmas.order.VisitDate;
import java.text.MessageFormat;
import java.util.Map;

public class EventBenefit {

    private static final String NONE = "없음";
    private static final String GIVE_AWAY_MENU = MessageFormat.format("{0} 1개", CHAMPAGNE.getMenuName());

    private final Map<DecemberEvent, Integer> benefits;

    public EventBenefit(final Order order, final VisitDate visitDate) {
        this.benefits = DecemberEvent.applyEvents(order, visitDate);
    }

    public String getGiveAwayMenu() {
        if (benefits.containsKey(GIVE_AWAY_EVENT)) {
            return GIVE_AWAY_MENU;
        }

        return NONE;
    }

    public int getTotalBenefitPrice() {
        return benefits.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public boolean isEmpty() {
        return benefits.isEmpty();
    }

    public Map<DecemberEvent, Integer> getBenefits() {
        return benefits;
    }
}

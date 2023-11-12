package christmas.benefit;

import christmas.benefit.handler.DdayEventHandler;
import christmas.benefit.handler.GiveAwayEventHandler;
import christmas.benefit.handler.NormalDayEventHandler;
import christmas.benefit.handler.StarDayEventHandler;
import christmas.benefit.handler.WeekEndDayEventHandler;
import christmas.order.Order;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum DecemberEvent {
    D_DAY_EVENT("크리스마스 디데이 할인", new DdayEventHandler()),
    NORMAL_DAY_EVENT("평일 할인", new NormalDayEventHandler()),
    WEEKEND_DAY_EVENT("주말 할인", new WeekEndDayEventHandler()),
    STAR_DAY_EVENT("특별 할인", new StarDayEventHandler()),
    GIVE_AWAY_EVENT("증정 이벤트", new GiveAwayEventHandler());

    private final String eventName;
    private final EventHandler eventHandler;

    DecemberEvent(final String eventName, final EventHandler eventHandler) {
        this.eventName = eventName;
        this.eventHandler = eventHandler;
    }

    public static Map<DecemberEvent, Integer> applyEvents(final Order order) {
        return Arrays.stream(DecemberEvent.values())
                .filter(decemberEvent -> decemberEvent.eventHandler
                        .supports(order))
                .collect(Collectors.toMap(
                        decemberEvent -> decemberEvent,
                        decemberEvent -> applyEvent(order, decemberEvent)
                ));
    }

    public String getEventName() {
        return eventName;
    }

    private static int applyEvent(final Order order, final DecemberEvent decemberEvent) {
        return decemberEvent.eventHandler.apply(order);
    }

}


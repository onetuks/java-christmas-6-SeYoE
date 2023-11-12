package christmas.event;

import christmas.event.handler.DdayEventHandler;
import christmas.event.handler.GiveAwayEventHandler;
import christmas.event.handler.NormalDayEventHandler;
import christmas.event.handler.StarDayEventHandler;
import christmas.event.handler.WeekEndDayEventHandler;
import christmas.order.Order;
import christmas.order.VisitDate;
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

    DecemberEvent(String eventName, EventHandler eventHandler) {
        this.eventName = eventName;
        this.eventHandler = eventHandler;
    }

    public static Map<DecemberEvent, Integer> applyEvents(final Order order, final VisitDate visitDate) {
        return Arrays.stream(DecemberEvent.values())
                .filter(decemberEvent -> decemberEvent.eventHandler
                        .supports(order, visitDate))
                .collect(Collectors.toMap(
                        decemberEvent -> decemberEvent,
                        decemberEvent -> decemberEvent.eventHandler
                                .apply(order, visitDate)
                ));
    }

    public String getEventName() {
        return eventName;
    }
}


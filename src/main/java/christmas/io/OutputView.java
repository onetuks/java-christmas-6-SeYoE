package christmas.io;

import christmas.benefit.EventBenefit;
import christmas.order.OrderHistory;
import christmas.order.VisitDate;
import christmas.result.EventResult;
import java.text.DecimalFormat;

public class OutputView {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###원");
    private static final String NONE = "없음";

    private static final String WELCOME_TEXT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ORDER_MENU_TITLE = "\n<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "\n<할인 전 총주문 금액>";
    private static final String GIVEAWAY_MENU_TITLE = "\n<증정 메뉴>";
    private static final String BENEFIT_DETAILS_TITLE = "\n<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_TITLE = "\n<총혜택 금액>";
    private static final String PAYMENT_PRICE_TITLE = "\n<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "\n<12월 이벤트 배지>";

    private static final String EVENT_DETAILS_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String EVENT_BENEFIT_FORMAT = "%s: -%s";

    public void printWelcome() {
        System.out.println(WELCOME_TEXT);
    }

    public void printEventDetails(final VisitDate visitDate) {
        final String message = String.format(EVENT_DETAILS_FORMAT, visitDate.getDate());

        System.out.println(message);
    }

    public void printOrderMenu(final OrderHistory orderHistory) {
        System.out.println(ORDER_MENU_TITLE);

        orderHistory.getHistory()
                .forEach((menu, orderAmount) -> {
                    final String menuName = menu.getMenuName();
                    final int amount = orderAmount.getAmount();
                    final String format = String.format(ORDER_MENU_FORMAT, menuName, amount);

                    System.out.println(format);
                });
    }

    public void printTotalPrice(final OrderHistory orderHistory) {
        System.out.println(TOTAL_PRICE_TITLE);

        final String totalPrice = decimalFormat.format(orderHistory.getTotalPrice());

        System.out.println(totalPrice);
    }

    public void printGiveAwayMenu(final EventBenefit eventBenefit) {
        System.out.println(GIVEAWAY_MENU_TITLE);

        System.out.println(eventBenefit.getGiveAwayMenu());
    }

    public void printEventBenefitPrices(final EventBenefit eventBenefit) {
        System.out.println(BENEFIT_DETAILS_TITLE);

        if (eventBenefit.isEmpty()) {
            System.out.println(NONE);
        }

        eventBenefit.getBenefits()
                .forEach((key, value) -> {
                    final String eventName = key.getEventName();
                    final String benefit = decimalFormat.format(value);

                    final String format = String.format(EVENT_BENEFIT_FORMAT, eventName, benefit);

                    System.out.println(format);
                });
    }

    public void printTotalBenefitPrice(final EventBenefit eventBenefit) {
        System.out.println(TOTAL_BENEFIT_PRICE_TITLE);

        final String totalBenefitPrice = decimalFormat.format(eventBenefit.getTotalBenefitPrice());

        System.out.println(totalBenefitPrice);
    }

    public void printPaymentPrice(final EventResult eventResult) {
        System.out.println(PAYMENT_PRICE_TITLE);

        final String format = decimalFormat.format(eventResult.getPaymentPrice());

        System.out.println(format);
    }

    public void printEventBadge(final EventResult eventResult) {
        System.out.println(EVENT_BADGE_TITLE);

        final String eventBadgeName = eventResult.getEventBadgeName();

        System.out.println(eventBadgeName);
    }
}

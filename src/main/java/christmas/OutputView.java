package christmas;

public class OutputView {

    private static final String WELCOME_TEXT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_DETAILS_TITLE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_MENU_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_TITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_TITLE = "<총혜택 금액>";
    private static final String PAYMENT_PRICE_TITLE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";

    public void outputWelcome() {
        System.out.println(WELCOME_TEXT);
    }

}

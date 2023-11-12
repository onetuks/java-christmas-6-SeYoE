package christmas.error;

import java.text.MessageFormat;

public enum ErrorMessage {
    NOT_DIGIT_DATE("[ERROR] 날짜는 숫자만 입력해야 합니다."),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다."),

    INVALID_MENU_ORDER("[ERROR] 유효하지 않은 주문입니다."),
    NOT_DIGIT_MENU_AMOUNT("[ERROR] 메뉴 수량은 숫자여야 합니다."),
    NEGATIVE_MENU_AMOUNT("[ERROR] 메뉴 수량은 0보다 많아야 합니다."),
    MORE_THAN_MENU_AMOUNT_LIMIT("[ERROR] 한 번에 20개 이상의 메뉴는 주문할 수 없습니다."),
    NOT_ONLY_BEVERAGE_ORDER("[ERROR] 음료만 주문할 수 없습니다.")
    ;

    private static final String RETRY_MESSAGE = "다시 입력해 주세요.";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return MessageFormat.format("{0} {1}", message, RETRY_MESSAGE);
    }

}

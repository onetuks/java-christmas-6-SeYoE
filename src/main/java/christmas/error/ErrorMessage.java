package christmas.error;

import java.text.MessageFormat;

public enum ErrorMessage {
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다."),
    INVALID_MENU_ORDER("[ERROR] 유효하지 않은 주문입니다."),
    INVALID_BENEFIT_PRICE("[ERROR] 혜택 금액이 원래 구매 금액보다 큽니다.");

    private static final String RETRY_MESSAGE = "다시 입력해 주세요.";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return MessageFormat.format("{0} {1}", message, RETRY_MESSAGE);
    }

}

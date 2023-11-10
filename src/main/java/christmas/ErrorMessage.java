package christmas;

import java.text.MessageFormat;

public enum ErrorMessage {
    NOT_DIGIT_DATE("[ERROR] 날짜는 숫자만 입력해야 합니다."),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다.");

    private static final String RETRY_MESSAGE = "다시 입력해 주세요.";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return MessageFormat.format("{0} {1}", message, RETRY_MESSAGE);
    }

}

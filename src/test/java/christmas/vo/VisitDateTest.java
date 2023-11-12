package christmas.vo;

import static christmas.error.ErrorMessage.INVALID_DATE;
import static christmas.error.ErrorMessage.NOT_DIGIT_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisitDateTest {

    @Test
    @DisplayName("숫자가 아닌 값으로 방문 날짜 생성하면 실패한다.")
    void createVisitDate_ByNonDigitValue_Fail() {
        // Given
        final String dateValue = "가나다";

        // When & Then
        assertThatThrownBy(() -> new VisitDate(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_DIGIT_DATE.getMessage());
    }

    @Test
    @DisplayName("1보다 작은 값으로 방문 날짜 생성하면 실패한다.")
    void createVisitDate_ByLessThanFirstDate_Fail() {
        // Given
        final String dateValue = "0";

        // When & Then
        assertThatThrownBy(() -> new VisitDate(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE.getMessage());
    }

    @Test
    @DisplayName("31보다 큰 값으로 방문 날짜 생성하면 실패한다.")
    void createVisitDate_ByGreatherThanLastDate_Fail() {
        // Given
        final String dateValue = "32";

        // When & Then
        assertThatThrownBy(() -> new VisitDate(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE.getMessage());
    }

    @Test
    @DisplayName("숫자이며 1~31 사이의 값으로 방문 날짜를 생성한다.")
    void createVisitDate_ByDigitInBoundValue_Success() {
        // Given
        final String dateValue = "3";

        // When
        final VisitDate visitDate = new VisitDate(dateValue);

        // Then
        final String result = String.valueOf(visitDate.getDate());

        assertThat(result).isEqualTo(dateValue);
    }

}
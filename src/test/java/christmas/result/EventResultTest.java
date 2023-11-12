package christmas.result;

import static christmas.error.ErrorMessage.TOO_BIG_BENEFIT_PRICE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultTest {

    @Test
    @DisplayName("혜택 금액이 원래 구매 금액보다 크면 예외를 던진다.")
    void createEventResult_TooBigBenefitPrice_Exception() {
        // Given
        final int originPrice = 1_000;
        final int benefitPrice = 2_000;

        // When & Then
        assertThatThrownBy(() -> new EventResult(originPrice, benefitPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(TOO_BIG_BENEFIT_PRICE.getMessage());
    }

}
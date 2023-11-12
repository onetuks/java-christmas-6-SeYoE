package christmas.result;

import static christmas.result.vo.EventBadge.NONE;
import static christmas.result.vo.EventBadge.SANTA;
import static christmas.result.vo.EventBadge.STAR;
import static christmas.result.vo.EventBadge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.result.vo.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @Test
    @DisplayName("총혜택 금액이 2만원 이상이면 산타 배지를 얻는다.")
    void priceOf_TotalBenefitPriceGreaterThan20K_SantaBadge() {
        // Given
        final int price = 20_000;

        // When
        final EventBadge result = EventBadge.priceOf(price);

        // Then
        assertThat(result).isEqualTo(SANTA);
    }

    @Test
    @DisplayName("총혜택 금액이 만 원 이상이면 트리 배지를 얻는다.")
    void priceOf_TotalBenefitPriceGreaterThan10K_TreeBadge() {
        // Given
        final int price = 10_000;

        // When
        final EventBadge result = EventBadge.priceOf(price);

        // Then
        assertThat(result).isEqualTo(TREE);
    }

    @Test
    @DisplayName("총혜택 금액이 오천원 이상이면 별 배지를 얻는다.")
    void priceOf_TotalBenefitPriceGreaterThan5K_StarBadge() {
        // Given
        final int price = 5_000;

        // When
        final EventBadge result = EventBadge.priceOf(price);

        // Then
        assertThat(result).isEqualTo(STAR);
    }

    @Test
    @DisplayName("총혜택 금액이 오천원 미만이면 아무런 배지도 얻지 못한다.")
    void priceOf_TotalBenefitPriceLessThan5K_None() {
        // Given
        final int price = 4_999;

        // When
        final EventBadge result = EventBadge.priceOf(price);

        // Then
        assertThat(result).isEqualTo(NONE);
    }

}
package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import lotto.domain.Rank;
import lotto.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @DisplayName("getYieldPercent: 총 상금 5,000원 / 구입금액 8,000원 → 62.5(둘째 자리 반올림 유지)")
    @Test
    void yield_percent_rounding_two_decimals() {
        Result stats = new Result();
        stats.add(Rank.FIFTH);

        LottoResult lottoResult = new LottoResult(List.of(), stats, stats.getTotalPrize());

        double pct = lottoResult.getYieldPercent(8_000);
        assertThat(pct).isEqualTo(62.5);
    }

    @DisplayName("getYieldPercent: 구입금액이 0 또는 음수면 0.0 반환")
    @Test
    void yield_percent_non_positive_purchase() {
        Result stats = new Result();
        stats.add(Rank.FIFTH); // +5,000
        LottoResult lottoResult = new LottoResult(List.of(), stats, stats.getTotalPrize());

        assertThat(lottoResult.getYieldPercent(0)).isZero();
        assertThat(lottoResult.getYieldPercent(-1000)).isZero();
    }

    @DisplayName("getYieldPercent: 둘째 자리 반올림 확인 (예: 33.333… → 33.33)")
    @Test
    void yield_percent_rounding_behavior() {
        Result stats = new Result();
        stats.add(Rank.FIFTH);
        stats.add(Rank.FIFTH);
        LottoResult lottoResult = new LottoResult(List.of(), stats, stats.getTotalPrize());

        double pct = lottoResult.getYieldPercent(30_000);
        assertThat(pct).isEqualTo(33.33);
    }

    @DisplayName("1등 당첨 시 수익률: 2,000,000,000원 / 8,000원 = 25,000,000.00%")
    @Test
    void yield_percent_first_rank_two_decimals() {
        Result stats = new Result();
        stats.add(Rank.FIRST);

        LottoResult lottoResult = new LottoResult(List.of(), stats, stats.getTotalPrize());

        double pct = lottoResult.getYieldPercent(8_000);

        assertThat(pct).isEqualTo(25_000_000.00);

        String formatted = String.format("%.2f", pct);
        assertThat(formatted).isEqualTo("25000000.00");
    }

    @DisplayName("1등 당첨이면서 딱 나눠떨어지지 않는 경우도 둘째 자리 반올림 확인 (예: / 3,000원 → 66,666,666.67%)")
    @Test
    void yield_percent_first_rank_rounding_case() {

        Result stats = new Result();
        stats.add(Rank.FIRST);

        LottoResult lottoResult = new LottoResult(List.of(), stats, stats.getTotalPrize());

        double pct = lottoResult.getYieldPercent(3_000);

        assertThat(pct).isEqualTo(66_666_666.67);
        assertThat(String.format("%.2f", pct)).isEqualTo("66666666.67");
    }
}

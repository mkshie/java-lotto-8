package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("매칭 개수/보너스 여부로 Rank.of 판정")
    @Test
    void of_mapping() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);

        // 그 외는 미당첨
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.of(1, true)).isEqualTo(Rank.MISS);
    }

    @DisplayName("등수별 상금 확인")
    @Test
    void prize_amount() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000L);
        assertThat(Rank.THIRD.getPrize()).isEqualTo(1_500_000L);
        assertThat(Rank.FOURTH.getPrize()).isEqualTo(50_000L);
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000L);
        assertThat(Rank.MISS.getPrize()).isZero();
    }

}

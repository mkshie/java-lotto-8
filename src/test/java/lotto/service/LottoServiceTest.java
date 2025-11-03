package lotto.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @DisplayName("generateLotto: 요청 개수만큼 생성 + 각 티켓 6개 고유 번호 + 1~45 범위 보장")
    @Test
    void generateLotto_properties() {
        LottoService service = new LottoService();

        int count = 8;
        List<Lotto> tickets = service.generateLotto(count);

        assertThat(tickets).hasSize(count);
        for (Lotto t : tickets) {
            List<Integer> nums = t.getNumbers();
            assertThat(nums).hasSize(6);
            assertThat(nums).allMatch(n -> 1 <= n && n <= 45);
            assertThat(new HashSet<>(nums).size()).isEqualTo(6);
        }
    }

    @DisplayName("playLotto: 6, 5+보너스, 5, 4, 3, 그 외(MISS) 각각 1장씩 → 등수 집계/총상금 검증")
    @Test
    void playLotto_rank_aggregation() {
        LottoResult result = getLottoResult();

        Result stats = result.getStats();
        Map<Rank, Integer> c = stats.counts();

        assertThat(c.get(Rank.FIRST)).isEqualTo(1);
        assertThat(c.get(Rank.SECOND)).isEqualTo(1);
        assertThat(c.get(Rank.THIRD)).isEqualTo(1);
        assertThat(c.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(c.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(c.get(Rank.MISS)).isEqualTo(1);

        long expectedTotal =
                2_000_000_000L + 30_000_000L + 1_500_000L + 50_000L + 5_000L;
        assertThat(stats.getTotalPrize()).isEqualTo(expectedTotal);
    }

    private static LottoResult getLottoResult() {
        LottoService service = new LottoService();

        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;

        Lotto first = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Lotto fourth = new Lotto(List.of(1, 2, 3, 4, 44, 45));
        Lotto fifth = new Lotto(List.of(1, 2, 3, 44, 45, 8));
        Lotto miss = new Lotto(List.of(10, 20, 30, 40, 41, 42));

        List<Lotto> tickets = List.of(first, second, third, fourth, fifth, miss);

        return service.playLotto(winning, bonus, tickets);
    }
}

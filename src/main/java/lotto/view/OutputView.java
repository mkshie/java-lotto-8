package lotto.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.service.LottoResult;

public class OutputView {

    private static final DecimalFormat MONEY_FMT = new DecimalFormat("#,###");

    public void printBuyMessage(int purchaseCount) {
        System.out.println(purchaseCount + "개를 구매했습니다.");
    }

    public void printTickets(List<Lotto> tickets) {
        for (Lotto t : tickets) {
            System.out.println(formatTicket(t));
        }
        System.out.println();
    }


    public void printStatisticsAndYield(LottoResult lottoResult, long purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---");

        Result stats = lottoResult.getStats();
        Map<Rank, Integer> c = stats.counts();

        printlnStat("3개 일치", 5_000, c.getOrDefault(Rank.FIFTH, 0));
        printlnStat("4개 일치", 50_000, c.getOrDefault(Rank.FOURTH, 0));
        printlnStat("5개 일치", 1_500_000, c.getOrDefault(Rank.THIRD, 0));
        printlnStat("5개 일치, 보너스 볼 일치", 30_000_000, c.getOrDefault(Rank.SECOND, 0));
        printlnStat("6개 일치", 2_000_000_000L, c.getOrDefault(Rank.FIRST, 0));

        double yieldPercent = lottoResult.getYieldPercent(purchaseMoney);
        String PercentText = BigDecimal.valueOf(yieldPercent).stripTrailingZeros().toPlainString();
        System.out.println("총 수익률은 " + PercentText + "%입니다.");
    }

    private void printlnStat(String label, long money, int count) {
        System.out.printf("%s (%s원) - %d개%n", label, MONEY_FMT.format(money), count);
    }

    private String formatTicket(Lotto lotto) {
        List<Integer> nums = new ArrayList<>(lotto.getNumbers());
        Collections.sort(nums);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < nums.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(nums.get(i));
        }
        sb.append(']');
        return sb.toString();
    }


}

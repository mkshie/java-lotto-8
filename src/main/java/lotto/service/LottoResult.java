package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Result;

public class LottoResult {

    private final List<Lotto> tickets;
    private final Result stats;
    private final double totalPrize;

    public LottoResult(List<Lotto> tickets, Result stats, double totalPrize) {
        this.tickets = List.copyOf(tickets);
        this.stats = stats;
        this.totalPrize = totalPrize;
    }

    public List<Lotto> getTickets() {
        return List.copyOf(tickets);
    }

    public Result getStats() {
        return stats;
    }

    public double getYieldPercent(long purchaseMoney) {
        if (purchaseMoney <= 0) {
            return 0.0;
        }
        double percent = (totalPrize * 100.0) / purchaseMoney;
        return Math.round(percent * 100.0) / 100.0;
    }
}

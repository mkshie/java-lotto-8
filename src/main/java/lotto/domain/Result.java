package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
    private long totalPrize = 0L;

    public Result() {
        for (Rank r : Rank.values()) {
            counts.put(r, 0);
        }
    }

    public void add(Rank rank) {
        counts.put(rank, counts.get(rank) + 1);
        totalPrize += rank.getPrize();
    }

    public Map<Rank, Integer> counts() {
        return Map.copyOf(counts);
    }

    public long getTotalPrize() {
        return totalPrize;
    }
}

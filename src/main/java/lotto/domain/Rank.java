package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean bonusRequired;
    private final long prize;


    Rank(int matchCount, boolean bonusRequired, long prize) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
    }

    public static Rank of(int MatchCount, boolean bonusRequired) {
        if (MatchCount == 6) {
            return FIRST;
        }
        if (MatchCount == 5 && bonusRequired) {
            return SECOND;
        }
        if (MatchCount == 5) {
            return THIRD;
        }
        if (MatchCount == 4) {
            return FOURTH;
        }
        if (MatchCount == 3) {
            return FIFTH;
        }

        return MISS;
    }

    public long getPrize() {
        return prize;
    }
}

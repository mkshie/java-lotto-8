package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;

public class LottoService {
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 45;
    private final int COUNT = 6;

    public LottoResult playLotto(List<Integer> WinningNumbers, int bonusNumber, List<Lotto> lottos) {

        Result result = new Result();

        for (Lotto lotto : lottos) {
            int matchNumber = matchCount(lotto, WinningNumbers);
            boolean bonusMatched = matchBonusNumber(lotto.getNumbers(), bonusNumber);
            Rank rank = Rank.of(matchNumber, bonusMatched);
            result.add(rank);
        }

        return new LottoResult(lottos, result, result.getTotalPrize());

    }

    private int matchCount(Lotto lotto, List<Integer> WinningNumbers) {
        int cnt = 0;
        List<Integer> numbers = lotto.getNumbers();
        for (int number : numbers) {
            if (WinningNumbers.contains(number)) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean matchBonusNumber(List<Integer> numbers, int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Lotto> generateLotto(int possibleAttempts) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < possibleAttempts; i++) {
            lottos.add(new Lotto(pickLottoNumbers()));
        }

        return lottos;
    }

    private List<Integer> pickLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, COUNT);
    }
}

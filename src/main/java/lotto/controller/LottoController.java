package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoResult;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int money = inputView.readMoney();

        outputView.printBuyMessage(money / 1000);
        List<Lotto> lottos = lottoService.generateLotto(money / 1000);
        outputView.printTickets(lottos);

        List<Integer> winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber(winningNumbers);

        LottoResult lottoResult = lottoService.playLotto(winningNumbers, bonusNumber, lottos);
        outputView.printStatisticsAndYield(lottoResult, money);
    }
}

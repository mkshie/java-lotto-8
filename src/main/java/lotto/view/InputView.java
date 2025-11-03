package lotto.view;

import static lotto.validator.Validator.parseBonusNumber;
import static lotto.validator.Validator.parseMoney;
import static lotto.validator.Validator.parseWiningNumber;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    public int readMoney() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String moneyInput = Console.readLine().trim();
                return parseMoney(moneyInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> readWinningNumbers() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String winingNumberInput = Console.readLine().trim();
                return parseWiningNumber(winingNumberInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumberInput = Console.readLine().trim();
                return parseBonusNumber(bonusNumberInput, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

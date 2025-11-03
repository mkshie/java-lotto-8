package lotto.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.enums.ErrorMessageEnum;

public class Validator {

    static final int MONEY_MOD = 1000;
    static final int MAX_NUMBER = 45;

    public static int parseMoney(String moneyInput) {
        if (moneyInput == null || moneyInput.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageEnum.MONEY_EMPTY.getMessage());
        }
        int money;
        try {
            money = Integer.parseInt(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessageEnum.MONEY_NOT_NUMBER.getMessage());
        }
        if (money <= 0) {
            throw new IllegalArgumentException(ErrorMessageEnum.MONEY_NOT_POSITIVE.getMessage());
        }
        if (money % MONEY_MOD != 0) {
            throw new IllegalArgumentException(ErrorMessageEnum.MONEY_NOT_UNIT.getMessage());
        }
        return money;
    }

    public static List<Integer> parseWiningNumber(String winingNumberInput) {

        if (winingNumberInput == null || winingNumberInput.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageEnum.WINNING_EMPTY.getMessage());
        }
        List<Integer> winingNumbers = new ArrayList<>();
        String[] numbers = winingNumberInput.split(",");
        if (numbers.length != 6) {
            throw new IllegalArgumentException(ErrorMessageEnum.WINNING_COUNT.getMessage());
        }
        for (String number : numbers) {
            int numberCheck;
            try {
                numberCheck = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ErrorMessageEnum.WINNING_NOT_NUMBER.getMessage());
            }
            if (numberCheck <= 0 || numberCheck > MAX_NUMBER) {
                throw new IllegalArgumentException(ErrorMessageEnum.NUMBER_OUT_OF_RANGE.getMessage());
            }
            winingNumbers.add(numberCheck);
        }
        Set<Integer> set = new HashSet<>(winingNumbers);

        if (winingNumbers.size() != set.size()) {
            throw new IllegalArgumentException(ErrorMessageEnum.WINNING_DUPLICATED.getMessage());
        }

        return winingNumbers;
    }

    public static int parseBonusNumber(String bonusNumberInput, List<Integer> winingNumbers) {
        if (bonusNumberInput == null || bonusNumberInput.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageEnum.BONUS_EMPTY.getMessage());
        }
        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(bonusNumberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessageEnum.BONUS_NOT_NUMBER.getMessage());
        }
        if (bonusNumber <= 0 || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessageEnum.BONUS_OUT_OF_RANGE.getMessage());
        }
        if (winingNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessageEnum.BONUS_DUPLICATED.getMessage());
        }

        return bonusNumber;
    }

}

package lotto.enums;

public enum ErrorMessageEnum {

    MONEY_NOT_NUMBER("구입 금액은 숫자여야 합니다."),
    MONEY_NOT_POSITIVE("구입 금액은 0보다 큰 값이어야 합니다."),
    MONEY_NOT_UNIT("구입 금액은 1,000원 단위여야 합니다."),
    MONEY_EMPTY("구입 금액이 비어있습니다."),

    WINNING_EMPTY("당첨 번호가 비어있습니다."),
    WINNING_COUNT("당첨 번호는 쉼표(,)로 구분된 6개여야 합니다."),
    WINNING_NOT_NUMBER("당첨 번호는 숫자여야 합니다."),
    WINNING_DUPLICATED("당첨 번호에 중복이 존재합니다."),

    BONUS_EMPTY("보너스 번호가 비어있습니다."),
    BONUS_NOT_NUMBER("보너스 번호는 숫자여야 합니다."),
    BONUS_OUT_OF_RANGE("보너스 번호는 1부터 45 사이여야 합니다."),
    BONUS_DUPLICATED("보너스 번호는 당첨 번호와 중복될 수 없습니다."),

    LOTTO_DUPLICATED("로또 번호에 중복이 존재합니다."),

    NUMBER_OUT_OF_RANGE("번호는 1부터 45 사이여야 합니다."),
    ;
    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessageEnum(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}

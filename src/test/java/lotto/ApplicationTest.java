package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("구입금액 잘못 입력 → [ERROR] 출력 후 같은 단계 재입력")
    @Test
    void 구입금액_재입력_시나리오() {
        assertSimpleTest(() -> {
            run(
                    "abc",     // 숫자 아님
                    "-1000",   // 음수
                    "1500",    // 1000단위 아님
                    "8000",
                    "1,2,3,4,5,6",
                    "7"
            );
            String out = output();
            assertThat(out).contains(ERROR_MESSAGE);
            assertThat(out).contains("8개를 구매했습니다.");
            assertThat(out).contains("당첨 통계");
        });
    }

    @DisplayName("당첨번호 잘못 입력(개수/범위/중복) → [ERROR] 후 같은 단계 재입력")
    @Test
    void 당첨번호_재입력_시나리오() {
        assertSimpleTest(() -> {
            run(
                    "8000",
                    "1,2,3",         // 개수 부족
                    "1,2,3,4,5,46",  // 범위 초과
                    "1,1,2,3,4,5",   // 중복
                    "1,2,3,4,5,6",
                    "7"
            );
            String out = output();
            assertThat(out).contains(ERROR_MESSAGE);
            assertThat(out).contains("8개를 구매했습니다.");
            assertThat(out).contains("당첨 통계");
        });
    }

    @DisplayName("보너스 번호 잘못 입력(문자/범위/중복) → [ERROR] 후 같은 단계 재입력")
    @Test
    void 보너스번호_재입력_시나리오() {
        assertSimpleTest(() -> {
            run(
                    "8000",
                    "1,2,3,4,5,6",
                    "abc",
                    "0",
                    "6",
                    "7"
            );
            String out = output();
            assertThat(out).contains(ERROR_MESSAGE);
            assertThat(out).contains("당첨 통계");
        });
    }

    @DisplayName("1등 당첨 시 수익률 출력은 과학적 표기(E 표기) 없이 인쇄되어야 한다")
    @Test
    void 수익률_과학적표기_방지() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run(
                            "1000",
                            "1,2,3,4,5,6",
                            "7"
                    );
                    String out = output();
                    assertThat(out).contains("1개를 구매했습니다.");
                    assertThat(out).contains("당첨 통계");
                    assertThat(out).doesNotContain("E");
                    assertThat(out).contains("%");
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

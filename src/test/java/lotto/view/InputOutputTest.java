package lotto.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputOutputTest extends NsTest {


    @DisplayName("구입 금액이 잘못 입력되면 [ERROR] 출력 후 같은 단계부터 재입력")
    @Test
    void money_retry_loop() {
        assertSimpleTest(() -> {
            run(
                    "abc",
                    "-1000",
                    "1500",
                    "8000",
                    "1,2,3,4,5,6",
                    "7"
            );
            String out = output();
            assertThat(out).contains("[ERROR]");
            assertThat(out).contains("8개를 구매했습니다.");
            assertThat(out).contains("당첨 통계");
            assertThat(out).contains("---");
        });
    }

    @DisplayName("당첨 번호가 잘못 입력되면 [ERROR] 출력 후 같은 단계부터 재입력")
    @Test
    void winning_numbers_retry_loop() {
        assertSimpleTest(() -> {
            run(
                    "8000",
                    "1,2,3",
                    "1,2,3,4,5,46",
                    "1,1,2,3,4,5",
                    "1,2,3,4,5,6",
                    "7"
            );
            String out = output();
            assertThat(out).contains("8개를 구매했습니다.");
            assertThat(out).contains("[ERROR]");
            assertThat(out).contains("당첨 통계");
        });
    }

    @DisplayName("보너스 번호가 잘못 입력되면 [ERROR] 출력 후 같은 단계부터 재입력")
    @Test
    void bonus_retry_loop() {
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
            assertThat(out).contains("8개를 구매했습니다.");
            assertThat(out).contains("[ERROR]");
            assertThat(out).contains("당첨 통계");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

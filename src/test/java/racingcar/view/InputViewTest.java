package racingcar.view;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import camp.nextstep.edu.missionutils.Console;

import java.io.ByteArrayInputStream;
import java.util.List;

class InputViewTest {

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @Test
    @DisplayName("자동차 이름을 입력받는다")
    void readCarNames() {
        String input = "pobi,woni,jun\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        assertThat(carNames).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("단일 자동차 이름을 입력받는다")
    void readSingleCarName() {
        String input = "pobi\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        assertThat(carNames).containsExactly("pobi");
    }

    @Test
    @DisplayName("자동차 이름의 앞뒤 공백을 제거한다")
    void trimCarNames() {
        String input = " pobi , woni , jun \n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        assertThat(carNames).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("공백만 있는 이름도 포함하여 반환한다")
    void parseCarNamesWithOnlySpaces() {
        String input = "pobi,  ,woni\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        assertThat(carNames).containsExactly("pobi", "", "woni");
    }

    @Test
    @DisplayName("많은 자동차 이름을 입력받는다")
    void readManyCarNames() {
        String input = "pobi,woni,jun,brown,sally\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        assertThat(carNames).hasSize(5);
        assertThat(carNames).containsExactly("pobi", "woni", "jun", "brown", "sally");
    }

    @Test
    @DisplayName("시도 횟수를 입력받는다")
    void readAttemptCount() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();
        int attemptCount = inputView.readAttemptCount();

        assertThat(attemptCount).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100", "999"})
    @DisplayName("다양한 시도 횟수를 입력받는다")
    void readVariousAttemptCounts(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));

        InputView inputView = new InputView();
        int attemptCount = inputView.readAttemptCount();

        assertThat(attemptCount).isEqualTo(Integer.parseInt(input));
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외 발생")
    void throwExceptionWhenAttemptCountIsZero() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readAttemptCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외 발생")
    void throwExceptionWhenAttemptCountIsNegative() {
        String input = "-5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readAttemptCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아니면 예외 발생")
    void throwExceptionWhenAttemptCountIsNotNumber() {
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readAttemptCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    @Test
    @DisplayName("시도 횟수가 빈 문자열이면 예외 발생")
    void throwExceptionWhenAttemptCountIsEmpty() {
        String input = "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readAttemptCount())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("시도 횟수에 공백이 포함되어 있으면 예외 발생")
    void throwExceptionWhenAttemptCountHasSpaces() {
        String input = "5 3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readAttemptCount())
                .isInstanceOf(IllegalArgumentException.class);
    }
}

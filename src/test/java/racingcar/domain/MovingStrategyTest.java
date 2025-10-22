package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MovingStrategyTest {
    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, false",
            "2, false",
            "3, false",
            "4, true",
            "5, true",
            "6, true",
            "7, true",
            "8, true",
            "9, true"
    })
    @DisplayName("무작위 값에 따라 이동 여부를 결정한다")
    void shouldMoveBasedOnRandomNumber(int randomNumber, boolean expected) {
        boolean result = MovingStrategy.shouldMove(randomNumber);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10})
    @DisplayName("0 미만의 값은 예외 발생")
    void throwExceptionWhenRandomNumberIsNegative(int randomNumber) {
        assertThatThrownBy(() -> MovingStrategy.shouldMove(randomNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0~9 사이");
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 15, 100})
    @DisplayName("9 초과의 값은 예외 발생")
    void throwExceptionWhenRandomNumberExceeds9(int randomNumber) {
        assertThatThrownBy(() -> MovingStrategy.shouldMove(randomNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0~9 사이");
    }
}

package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @Test
    @DisplayName("이름을 생성한다")
    void createName() {
        Name name = new Name("pobi");
        assertThat(name.getValue()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("이름이 null이면 예외 발생")
    void throwExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 빈 문자열이면 예외 발생")
    void throwExceptionWhenNameIsEmpty() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 공백만 있으면 예외 발생")
    void throwExceptionWhenNameIsBlank() {
        assertThatThrownBy(() -> new Name("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 빈 문자열일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    @DisplayName("이름이 1~5자면 생성 가능")
    void createNameWithValidLength(String value) {
        assertThatCode(() -> new Name(value))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "pobi123", "verylongname"})
    @DisplayName("이름이 5자를 초과하면 예외 발생")
    void throwExceptionWhenNameExceeds5Characters(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자를 초과");
    }

    @Test
    @DisplayName("같은 이름이면 동일한 객체")
    void equalsWithSameName() {
        Name name1 = new Name("pobi");
        Name name2 = new Name("pobi");

        assertThat(name1).isEqualTo(name2);
    }
}

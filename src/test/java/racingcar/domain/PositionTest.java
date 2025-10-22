package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    @DisplayName("초기 위치는 0이다")
    void initialPosition() {
        Position position = new Position();
        assertThat(position.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("위치를 1 증가시킨다")
    void increasePosition() {
        Position position = new Position();
        position.increase();

        assertThat(position.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("위치를 여러 번 증가시킨다")
    void increasePositionMultipleTimes() {
        Position position = new Position();
        position.increase();
        position.increase();
        position.increase();

        assertThat(position.getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("위치를 '-' 문자열로 표시한다")
    void displayPosition() {
        Position position = new Position();
        position.increase();
        position.increase();

        assertThat(position.toDisplay()).isEqualTo("--");
    }

    @Test
    @DisplayName("위치가 0이면 빈 문자열을 표시한다")
    void displayZeroPosition() {
        Position position = new Position();
        assertThat(position.toDisplay()).isEqualTo("");
    }

    @Test
    @DisplayName("같은 위치인지 확인한다")
    void isSamePosition() {
        Position position1 = new Position();
        Position position2 = new Position();
        position1.increase();
        position2.increase();

        assertThat(position1.isSamePosition(position2)).isTrue();
    }
}

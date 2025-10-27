package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    @DisplayName("자동차를 생성한다")
    void createCar() {
        Car car = new Car("pobi");
        assertThat(car.getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("자동차 생성 시 초기 위치는 0이다")
    void initialPosition() {
        Car car = new Car("pobi");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("무작위 값이 4 이상이면 전진한다")
    void moveWhenRandomNumberIsGreaterThanOrEqualTo4() {
        Car car = new Car("pobi");
        car.move(4);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("무작위 값이 4 미만이면 멈춘다")
    void stopWhenRandomNumberIsLessThan4() {
        Car car = new Car("pobi");
        car.move(3);

        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("여러 번 전진할 수 있다")
    void moveMultipleTimes() {
        Car car = new Car("pobi");
        car.move(4);
        car.move(5);
        car.move(9);

        assertThat(car.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("전진과 멈춤을 반복할 수 있다")
    void moveAndStop() {
        Car car = new Car("pobi");
        car.move(4);  // 전진
        car.move(3);  // 멈춤
        car.move(5);  // 전진

        assertThat(car.getPosition()).isEqualTo(2);
    }

    @Test
    @DisplayName("위치를 문자열로 표시한다")
    void getPositionDisplay() {
        Car car = new Car("pobi");
        car.move(4);
        car.move(5);

        assertThat(car.getPositionDisplay()).isEqualTo("--");
    }
}

package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CarsTest {
    @Test
    @DisplayName("자동차 목록을 생성한다")
    void createCars() {
        List<String> names = List.of("pobi", "woni", "jun");
        Cars cars = new Cars(names);

        assertThat(cars.getCars()).hasSize(3);
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외 발생")
    void throwExceptionWhenDuplicateNames() {
        List<String> names = List.of("pobi", "woni", "pobi");

        assertThatThrownBy(() -> new Cars(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("모든 자동차를 이동시킨다")
    void moveAllCars() {
        List<String> names = List.of("pobi", "woni");
        Cars cars = new Cars(names);
        RandomNumberGenerator generator = () -> 4;  // 항상 전진

        cars.moveAll(generator);

        assertThat(cars.getCars())
                .allMatch(car -> car.getPosition() == 1);
    }

    @Test
    @DisplayName("단독 우승자를 찾는다")
    void findSingleWinner() {
        List<String> names = List.of("pobi", "woni", "jun");
        Cars cars = new Cars(names);

        // pobi만 전진
        cars.getCars().get(0).move(4);

        Winners winners = cars.findWinners();
        assertThat(winners.names()).containsExactly("pobi");
    }

    @Test
    @DisplayName("공동 우승자를 찾는다")
    void findMultipleWinners() {
        List<String> names = List.of("pobi", "woni", "jun");
        Cars cars = new Cars(names);

        // pobi, woni 전진
        cars.getCars().get(0).move(4);
        cars.getCars().get(1).move(5);

        Winners winners = cars.findWinners();
        assertThat(winners.names()).containsExactly("pobi", "woni");
    }

    @Test
    @DisplayName("모두 같은 위치면 모두 우승자")
    void findAllWinnersWhenAllSamePosition() {
        List<String> names = List.of("pobi", "woni", "jun");
        Cars cars = new Cars(names);

        Winners winners = cars.findWinners();
        assertThat(winners.names()).containsExactly("pobi", "woni", "jun");
    }
}

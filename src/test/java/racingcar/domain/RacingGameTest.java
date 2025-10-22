package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RacingGameTest {

    @Test
    @DisplayName("경주 게임을 생성한다")
    void createRacingGame() {
        Cars cars = new Cars(List.of("pobi", "woni"));
        RandomNumberGenerator generator = () -> 4;

        assertThatCode(() -> new RacingGame(cars, generator))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("한 라운드를 진행한다")
    void playOneRound() {
        Cars cars = new Cars(List.of("pobi", "woni"));
        RandomNumberGenerator generator = () -> 4;  // 항상 전진
        RacingGame game = new RacingGame(cars, generator);

        game.playRound();

        assertThat(game.getCars().getCars())
                .allMatch(car -> car.getPosition() == 1);
    }

    @Test
    @DisplayName("여러 라운드를 진행한다")
    void playMultipleRounds() {
        Cars cars = new Cars(List.of("pobi", "woni"));
        RandomNumberGenerator generator = () -> 4;  // 항상 전진
        RacingGame game = new RacingGame(cars, generator);

        game.playRound();
        game.playRound();
        game.playRound();

        assertThat(game.getCars().getCars())
                .allMatch(car -> car.getPosition() == 3);
    }

    @Test
    @DisplayName("우승자를 찾는다")
    void findWinners() {
        Cars cars = new Cars(List.of("pobi", "woni"));
        RandomNumberGenerator generator = () -> 4;
        RacingGame game = new RacingGame(cars, generator);

        game.playRound();

        Winners winners = game.findWinners();
        assertThat(winners.getNames()).hasSize(2);
    }
}
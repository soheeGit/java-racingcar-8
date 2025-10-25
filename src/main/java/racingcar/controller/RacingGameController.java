package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.Winners;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumberGenerator generator;

    public RacingGameController(InputView inputView, OutputView outputView, RandomNumberGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();

        int attemptCount = inputView.readAttemptCount();

        Cars cars = new Cars(carNames);

        RacingGame game = new RacingGame(cars, generator);

        outputView.printResultHeader();

        for (int i = 0; i < attemptCount; i++) {
            game.playRound();
            outputView.printRoundResult(game.getCars());
        }

        Winners winners = game.findWinners();
        outputView.printWinners(winners);
    }
}

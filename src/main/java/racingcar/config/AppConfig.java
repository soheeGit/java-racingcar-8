package racingcar.config;

import racingcar.controller.RacingGameController;
import racingcar.domain.RandomMovingNumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    public static RacingGameController createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomNumberGenerator generator = new RandomMovingNumberGenerator();

        return new RacingGameController(inputView, outputView, generator);
    }
}

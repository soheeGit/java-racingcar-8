package racingcar.domain;

import racingcar.validator.Validator;

public class MovingStrategy {
    private static final int MOVE_THRESHOLD = 4;

    public static boolean shouldMove(int randomNumber) {
        Validator.validateRange(randomNumber);
        return randomNumber >= MOVE_THRESHOLD;
    }
}

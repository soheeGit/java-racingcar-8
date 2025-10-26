package racingcar.domain;

public class MovingStrategy {
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 9;
    private static final int MOVE_THRESHOLD = 4;

    public static boolean shouldMove(int randomNumber) {
        validateRange(randomNumber);
        return randomNumber >= MOVE_THRESHOLD;
    }

    private static void validateRange(int randomNumber) {
        if (randomNumber < MIN_NUMBER || randomNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("무작위 값은 0~9 사이여야 합니다.");
        }
    }
}

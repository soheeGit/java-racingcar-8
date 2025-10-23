package racingcar.domain;

public class AttemptCount {
    private final int value;

    public AttemptCount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("시도 횟수는 양수여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}

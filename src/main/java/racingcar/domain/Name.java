package racingcar.domain;

import racingcar.exception.ErrorMessage;

public record Name(String value) {
    private static final int MAX_NAME_LENGTH = 5;

    public Name {
        validateName(value);
    }

    private void validateName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME);
        }
        if (value.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH);
        }
    }
}
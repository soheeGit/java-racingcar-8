package racingcar.validator;

import racingcar.exception.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 9;

    public static void validateName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME);
        }
        if (value.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH);
        }
    }

    public static void validateDuplication(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NAME);
        }
    }

    public static void validateAttemptCount(String input) {
        try {
            int number = Integer.parseInt(input);
            if(number <= MIN_NUMBER) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ATTEMPT_COUNT);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }

    public static void validateRange(int randomNumber) {
        if (randomNumber < MIN_NUMBER || randomNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANDOM_NUMBER);
        }
    }
}

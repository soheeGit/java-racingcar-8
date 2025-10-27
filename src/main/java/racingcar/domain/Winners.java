package racingcar.domain;

import java.util.List;

public record Winners(List<String> names) {
    private static final String WINNER_NAME_DELIMITER = ", ";

    @Override
    public String toString() {
        return String.join(WINNER_NAME_DELIMITER, names);
    }
}

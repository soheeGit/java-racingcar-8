package racingcar.domain;

import java.util.List;

public record Winners(List<String> names) {

    @Override
    public String toString() {
        return String.join(", ", names);
    }
}

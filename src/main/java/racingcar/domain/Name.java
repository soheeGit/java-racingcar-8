package racingcar.domain;

import racingcar.validator.Validator;

public record Name(String value) {
    public Name {
        Validator.validateName(value);
    }
}

package racingcar.domain;

import racingcar.validator.Validator;

public record Name(String value) {
    public Name {
        Validator.validateName(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equals(name.value);
    }
}

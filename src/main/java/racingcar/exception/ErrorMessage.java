package racingcar.exception;

public class ErrorMessage {
    public static final String EMPTY_NAME = "자동차 이름은 빈 문자열일 수 없습니다.";
    public static final String INVALID_NAME_LENGTH = "자동차 이름은 5자를 초과할 수 없습니다.";
    public static final String DUPLICATE_NAME = "중복된 자동차 이름이 존재합니다.";

    public static final String INVALID_ATTEMPT_COUNT = "시도 횟수는 양수여야 합니다.";
    public static final String INVALID_NUMBER_FORMAT = "시도 횟수는 숫자여야 합니다.";

    public static final String INVALID_RANDOM_NUMBER = "무작위 값은 0~9 사이여야 합니다.";

    private ErrorMessage() {
    }
}

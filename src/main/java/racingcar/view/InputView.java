package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private static final String COMMA_DELIMITER = ",";
    private static final int MIN_ATTEMPT_COUNT = 1;
    
    public List<String> readCarNames() {
        System.out.println(CAR_NAMES_INPUT_MESSAGE);
        String input = Console.readLine();
        return parseCarNames(input);
    }
    
    private List<String> parseCarNames(String input) {
        return Arrays.stream(input.split(COMMA_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
    
    public int readAttemptCount() {
        System.out.println(ATTEMPT_COUNT_INPUT_MESSAGE);
        String input = Console.readLine();
        validateAttemptCount(input);
        return Integer.parseInt(input);
    }

    private void validateAttemptCount(String input) {
        try {
            int number = Integer.parseInt(input);
            if(number < MIN_ATTEMPT_COUNT) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ATTEMPT_COUNT);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }
}
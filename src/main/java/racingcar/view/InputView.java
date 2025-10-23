package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private static final String COMMA_DELIMITER = ",";
    
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
        return parseAttemptCount(input);
    }
    
    private int parseAttemptCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }
}

package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;

public class OutputView {

    private static final String RESULT_HEADER_MESSAGE = "실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자";
    private static final String LABEL_CONTENT_DELIMITER = " : ";

    public void printResultHeader() {
        System.out.println();
        System.out.println(RESULT_HEADER_MESSAGE);
    }
    
    public void printRoundResult(Cars cars) {
        for (Car car : cars.getCars()) {
            System.out.println(car.getName() + LABEL_CONTENT_DELIMITER + car.getPositionDisplay());
        }
        System.out.println();
    }
    
    public void printWinners(Winners winners) {
        System.out.println(WINNER_PREFIX + LABEL_CONTENT_DELIMITER + winners);
    }
}

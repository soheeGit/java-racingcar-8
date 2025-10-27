package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;

public class OutputView {

    public void printResultHeader() {
        System.out.println();
        System.out.println("실행 결과");
    }
    
    public void printRoundResult(Cars cars) {
        for (Car car : cars.getCars()) {
            System.out.println(car.getName() + " : " + car.getPositionDisplay());
        }
        System.out.println();
    }
    
    public void printWinners(Winners winners) {
        System.out.println("최종 우승자 : " + winners);
    }
}

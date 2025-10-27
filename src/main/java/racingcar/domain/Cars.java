package racingcar.domain;

import racingcar.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;
    
    public Cars(List<String> names) {
        Validator.validateDuplication(names);
        this.cars = names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void moveAll(RandomNumberGenerator generator) {
        for (Car car : cars) {
            car.move(generator.generate());
        }
    }
    
    public List<Car> getCars() {
        return cars;
    }
    
    public Winners findWinners() {
        int maxPosition = findMaxPosition();
        List<String> winnerNames = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());

        return new Winners(winnerNames);
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }
}

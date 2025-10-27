package racingcar.domain;

import racingcar.exception.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;
    
    public Cars(List<String> names) {
        validateDuplication(names);
        this.cars = names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validateDuplication(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NAME);
        }
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

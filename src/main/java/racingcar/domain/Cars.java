package racingcar.domain;

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
            throw new IllegalArgumentException("중복된 자동차 이름이 존재합니다.");
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

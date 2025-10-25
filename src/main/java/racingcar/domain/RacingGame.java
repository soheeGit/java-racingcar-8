package racingcar.domain;

public class RacingGame {
    private final Cars cars;
    private final RandomNumberGenerator generator;
    
    public RacingGame(Cars cars, RandomNumberGenerator generator) {
        this.cars = cars;
        this.generator = generator;
    }
    
    public void playRound() {
        cars.moveAll(generator);
    }
    
    public Cars getCars() {
        return cars;
    }
    
    public Winners findWinners() {
        return cars.findWinners();
    }
}

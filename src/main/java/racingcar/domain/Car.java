package racingcar.domain;

public class Car {
    private final Name name;
    private final Position position;
    
    public Car(String name) {
        this.name = new Name(name);
        this.position = new Position();
    }
    
    public void move(int randomNumber) {
        if (MovingStrategy.shouldMove(randomNumber)) {
            position.increase();
        }
    }
    
    public String getName() {
        return name.getValue();
    }
    
    public int getPosition() {
        return position.getValue();
    }
    
    public String getPositionDisplay() {
        return position.toDisplay();
    }
    
    public boolean isSamePosition(Car other) {
        return this.position.isSamePosition(other.position);
    }
}

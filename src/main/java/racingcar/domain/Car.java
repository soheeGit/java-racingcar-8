package racingcar.domain;

public class Car {
    private static final int MOVE_THRESHOLD = 4;

    private final Name name;
    private final Position position;
    
    public Car(String name) {
        this.name = new Name(name);
        this.position = new Position();
    }
    
    public void move(int randomNumber) {
        if (canMove(randomNumber)) {
            position.increase();
        }
    }

    private boolean canMove(int randomNumber) {
        return randomNumber >= MOVE_THRESHOLD;
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

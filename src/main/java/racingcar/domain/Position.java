package racingcar.domain;

public class Position {
    private static final int INITIAL_POSITION = 0;
    private static final String POSITION_MARK = "-";

    private int value;
    
    public Position() {
        this.value = INITIAL_POSITION;
    }
    
    public void increase() {
        value++;
    }
    
    public int getValue() {
        return value;
    }
    
    public String toDisplay() {
        return POSITION_MARK.repeat(value);
    }
}

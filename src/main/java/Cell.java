import java.util.HashMap;
import java.util.Map;

public class Cell {
    private int state;
    private final Map<Integer, Command> stateRules;

    public Cell(int state) {
        this.state = state;
        this.stateRules = new HashMap<>();
        this.stateRules.put(3, () -> this.state = State.ALIVE);
        this.stateRules.put(2, () -> { /* nothing to do */ });
    }

    public int getState() {
        return state;
    }

    public void updateState(int aliveNeighbours) {
        this.stateRules.getOrDefault(aliveNeighbours, () -> this.state = State.DEAD).execute();
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state + "";
    }
}

package cloud.happydev.treasurehunter;

import java.util.Arrays;
import java.util.LinkedList;

public class Adventurer {
    private final String name;
    private int column;
    private int row;
    private Orientation orientation;
    private int treasureCount;
    private final LinkedList<Action> actions;

    public Adventurer(String name, int column, int row, Orientation orientation, String actions) {
        this.name = name;
        this.column = column;
        this.row = row;
        this.orientation = orientation;
        this.treasureCount = 0;
        this.actions = new LinkedList<>();
        if (!actions.isEmpty()) {
            Arrays.stream(actions.split("")).map(Action::valueOf).forEach(this.actions::addLast);
        }
    }

    public String getName() {
        return name;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public LinkedList<Action> getActions() {
        return actions;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void addAction(Action action) {
        actions.addFirst(action);
    }

    public void addTreasures(int count) {
        treasureCount += count;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String toArrow() {
        return name.charAt(0) + switch (orientation) {
            case N:
                yield "↑";
            case E:
                yield "→";
            case S:
                yield "↓";
            case O:
                yield "←";
        };
    }

    public Action nextAction() {
        return !actions.isEmpty()
                ? actions.pollFirst()
                : Action.W;
    }
}

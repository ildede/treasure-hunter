package cloud.happydev.treasurehunter;

import java.util.ArrayList;
import java.util.List;

import static cloud.happydev.treasurehunter.MapToken.BLANK;
import static cloud.happydev.treasurehunter.MapToken.MOUNTAIN;

public class TreasureMap {

    private final int width;
    private final int height;
    private final String[][] table;
    private final List<Adventurer> adventurers;

    public TreasureMap(int width, int height) {
        this.width = width;
        this.height = height;

        this.table = new String[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                table[y][x] = "  ";
            }
        }
        this.adventurers = new ArrayList<>();
    }

    public Object[][] getTable() {
        return table;
    }

    public void addTreasure(int column, int row, int count) {
        table[row - 1][column - 1] = String.valueOf(count);
    }

    public void addMountain(int column, int row) {
        table[row - 1][column - 1] = MOUNTAIN;
    }

    public void addAdventurer(int column, int row, Orientation orientation, String name, String actions) {
        Adventurer adventurer = new Adventurer(name, column, row, orientation, actions);
        adventurers.add(adventurer);
        table[row - 1][column - 1] = adventurer.toArrow();
    }

    public void moveForward(Adventurer a) {
        int advX = a.getColumn() - 1;
        int advY = a.getRow() - 1;
        switch (a.getOrientation()) {
            case N -> {
                if (advY > 0) {
                    int targetY = advY - 1;
                    checkAndMoveTo(a, targetY, advX);
                }
            }
            case E -> {
                if (advX < width - 1) {
                    int targetX = advX + 1;
                    checkAndMoveTo(a, advY, targetX);
                }
            }
            case S -> {
                if (advY < height - 1) {
                    int targetY = advY + 1;
                    checkAndMoveTo(a, targetY, advX);
                }
            }
            case O -> {
                if (advX > 0) {
                    int targetX = advX - 1;
                    checkAndMoveTo(a, advY, targetX);
                }
            }
        }
    }

    private void checkAndMoveTo(Adventurer a, int targetY, int targetX) {
        String target = table[targetY][targetX];
        if (BLANK.equals(target) || isTreasure(target)) {
            if (!BLANK.equals(target)) {
                a.addTreasures(Integer.parseInt(target));
                a.addAction(Action.W);
            }
            moveTo(a, targetX, targetY);
        }
    }

    private boolean isTreasure(String target) {
        try {
            Integer.parseInt(target);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void turnRight(Adventurer a) {
        switch (a.getOrientation()) {
            case N -> changeOrientationTo(a, Orientation.E);
            case E -> changeOrientationTo(a, Orientation.S);
            case S -> changeOrientationTo(a, Orientation.O);
            case O -> changeOrientationTo(a, Orientation.N);
        }
    }

    private void changeOrientationTo(Adventurer a, Orientation o) {
        a.setOrientation(o);
        table[a.getRow() - 1][a.getColumn() - 1] = a.toArrow();
    }

    private void moveTo(Adventurer a, int x, int y) {
        table[a.getRow() - 1][a.getColumn() - 1] = BLANK;
        a.setColumn(x + 1);
        a.setRow(y + 1);
        table[y][x] = a.toArrow();
    }

    public void turnLeft(Adventurer a) {
        switch (a.getOrientation()) {
            case N -> changeOrientationTo(a, Orientation.O);
            case E -> changeOrientationTo(a, Orientation.N);
            case S -> changeOrientationTo(a, Orientation.E);
            case O -> changeOrientationTo(a, Orientation.S);
        }
    }

    public void executeAdventurerAction() {
        adventurers
                .forEach(a -> {
                    switch (a.nextAction()) {
                        case A -> moveForward(a);
                        case D -> turnRight(a);
                        case G -> turnLeft(a);
                        case W -> {
                        }
                    }
                });
    }

    public boolean hasActionsLeft() {
        return adventurers.stream().anyMatch(a -> !a.getActions().isEmpty());
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }
}

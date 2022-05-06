package cloud.happydev.treasurehunter;

import org.springframework.stereotype.Service;

@Service
public class GameStatus {

    private TreasureMap treasureMap;

    public void startNewGame(int width, int height) {
        treasureMap = new TreasureMap(width, height);
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }

    public void addTreasure(int column, int row, int count) {
        treasureMap.addTreasure(column, row, count);
    }

    public void addMountain(int column, int row) {
        treasureMap.addMountain(column, row);
    }

    public void addAdventurer(int column, int row, Orientation orientation, String name, String actions) {
        treasureMap.addAdventurer(column, row, orientation, name, actions);
    }

    public boolean isValid() {
        return treasureMap != null;
    }

    public boolean needsUpdate() {
        return isValid() && treasureMap.hasActionsLeft();
    }

    public void update() {
        treasureMap.executeAdventurerAction();
    }
}

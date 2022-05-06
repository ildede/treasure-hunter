package cloud.happydev.treasurehunter;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ShellComponent
public class GameCommands {

    private final TerminalWriter writer;
    private final GameStatus gameStatus;

    public GameCommands(TerminalWriter shellHelper, GameStatus gameStatus) {
        this.writer = shellHelper;
        this.gameStatus = gameStatus;
    }

    @ShellMethod(value = "Create new treasure map, ex: C 6 5", key = "C")
    public void newGame(@Min(1) int width, @Min(1) int height) {

        gameStatus.startNewGame(width, height);
        writer.print(gameStatus.getTreasureMap());
    }

    @ShellMethod(value = "Add treasure, ex: T 1-4 3", key = "T")
    @ShellMethodAvailability("treasureMapCreated")
    public void addTreasure(String position, int count) {
        String[] split = position.split("-");
        int column = Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]);

        gameStatus.addTreasure(column, row, count);
        writer.print(gameStatus.getTreasureMap());
    }

    @ShellMethod(value = "Add mountain, ex: M 5-3", key = "M")
    @ShellMethodAvailability("treasureMapCreated")
    public void addMountain(String position) {
        String[] split = position.split("-");
        int column = Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]);

        gameStatus.addMountain(column, row);
        writer.print(gameStatus.getTreasureMap());
    }

    @ShellMethod(value = "Place adventurer, ex: John 1-1 N")
    @ShellMethodAvailability("treasureMapCreated")
    public void newAdventurer(@NotNull String name, String position, Orientation orientation, String actions) {
        String[] split = position.split("-");
        int column = Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]);

        gameStatus.addAdventurer(column, row, orientation, name, actions);
        writer.print(gameStatus.getTreasureMap());
    }

    public Availability treasureMapCreated() {
        return gameStatus.isValid()
                ? Availability.available()
                : Availability.unavailable("< No treasure map >");
    }
}

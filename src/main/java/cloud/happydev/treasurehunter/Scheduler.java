package cloud.happydev.treasurehunter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    TerminalWriter terminalWriter;
    GameStatus gameStatus;

    public Scheduler(TerminalWriter terminalWriter, GameStatus gameStatus) {
        this.terminalWriter = terminalWriter;
        this.gameStatus = gameStatus;
    }

    @Scheduled(fixedRate = 1000)
    public void gameTick() {
        if (gameStatus.needsUpdate()) {
            gameStatus.update();
            terminalWriter.print(gameStatus.getTreasureMap());
        }
    }
}

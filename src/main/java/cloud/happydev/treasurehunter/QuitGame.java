package cloud.happydev.treasurehunter;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class QuitGame implements Quit.Command {

    @ShellMethod("Quit the game.")
    public void quit() {
        System.exit(0);
    }
}

package cloud.happydev.treasurehunter;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Component;

@Component
public class TerminalWriter {

    private final Terminal terminal;

    public TerminalWriter(@Lazy Terminal terminal) {
        this.terminal = terminal;
    }

    public String getColored(String message, int color) {
        return new AttributedStringBuilder()
                .append(message, AttributedStyle.DEFAULT.foreground(color))
                .toAnsi();
    }

    public void print(TreasureMap treasureMap) {
        terminal.puts(InfoCmp.Capability.clear_screen);

        treasureMap.getAdventurers()
                .forEach(a -> print(a.getName() + ", treasures found: " + a.getTreasureCount(), AttributedStyle.MAGENTA));

        printTable(treasureMap.getTable());

        if (!treasureMap.hasActionsLeft()) {
            print("No more actions left, type \"help\" to see available commands", AttributedStyle.BLUE);
        }
    }

    private void printTable(Object[][] table) {
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        print(tableBuilder.build().render(80), AttributedStyle.WHITE);
    }

    private void print(String message, int color) {
        terminal.writer()
                .println(getColored(message, color));
        terminal.flush();
    }
}

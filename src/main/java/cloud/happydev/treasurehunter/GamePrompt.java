package cloud.happydev.treasurehunter;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class GamePrompt implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(
                "TREASURE-HUNTER (help for list of commands) > ",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)
        );
    }
}

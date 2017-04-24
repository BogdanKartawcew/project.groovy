package commands;

import classes.Console;
import exceptions.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by MyWORID on 13.04.2017.
 */
public class Language implements Command {
    ResourceBundle resLanguage = ResourceBundle.getBundle("chooseLanguage");
    public static volatile String language = "en";

    @Override
    public void execute() throws InterruptOperationException {
        Console.showMessage(resLanguage.getString("choose.language"));
        while (true) {
            String tryLanguage = Console.read();
            if (Console.checkLanguage(tryLanguage)) {
                language = tryLanguage;
                break;
            } else Console.showMessage(resLanguage.getString("error.language"));
        }
    }
}

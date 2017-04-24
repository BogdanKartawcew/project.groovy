package commands;

import classes.Console;
import classes.Operations;
import exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
class Exit implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        Locale locale = new Locale(Language.language);
        ResourceBundle resExit = ResourceBundle.getBundle("exit", locale);
        Console.showMessage(resExit.getString("exit.question"));
        if (Console.read().equals(resExit.getString("yes"))) {
            Console.showMessage(resExit.getString("thank.message"));
            System.exit(1);
        }
        else {
            Executor.execute(Operations.GoTo);
        }
    }
}

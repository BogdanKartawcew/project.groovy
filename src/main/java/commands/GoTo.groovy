package commands;

import classes.Console;
import classes.Operations;
import exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class GoTo implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        Locale locale = new Locale(Language.language);
        ResourceBundle resGoTo = ResourceBundle.getBundle("common", locale);
        Operations operation;
        while (true) {
            Console.showMessage(resGoTo.getString("choose.operation") + " \n" +
                    resGoTo.getString("operation.INFO") + ": 1;\n" +
                    resGoTo.getString("operation.DEPOSIT") + ": 2;\n" +
                    resGoTo.getString("operation.WITHDRAW") + ": 3;\n" +
                    resGoTo.getString("operation.EXIT") + ": 4");

            operation = Console.askOperation();
            Executor.execute(operation);
        }
    }
}

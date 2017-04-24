package classes;

import commands.Executor;
import commands.Language;
import exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class Bankomat{

    public static void main(String[] args) throws InterruptOperationException {
        Executor.execute(Operations.LANGUAGE);
        Executor.execute(Operations.LOGIN);
        Executor.execute(Operations.GoTo);
    }
}

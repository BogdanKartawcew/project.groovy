package commands;

import classes.Operations;
import exceptions.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class Executor {
    private static Map<Operations, Command> map = new HashMap<>();

    static {

        map.put(Operations.LOGIN, new Login());
        map.put(Operations.INFO, new Info());
        map.put(Operations.DEPOSIT, new Deposit());
        map.put(Operations.WITHDRAW, new Withdraw());
        map.put(Operations.EXIT, new Exit());
        map.put(Operations.LANGUAGE, new Language());
        map.put(Operations.GoTo, new GoTo());
    }

    public static final void execute(Operations operation) throws InterruptOperationException {
        map.get(operation).execute();
    }

    public Executor() {
    }
}

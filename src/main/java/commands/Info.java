package commands;

import classes.Console;
import classes.CurrencyChanger;
import classes.Factory;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
class Info implements Command {
    @Override
    public void execute() {
        Locale locale = new Locale(Language.language);
        ResourceBundle resInfo = ResourceBundle.getBundle("info", locale);
        Console.showMessage(resInfo.getString("information"));
        if (CurrencyChanger.hasMoney())
        for (CurrencyChanger cur : Factory.getAllCurrencyManipulators()) {
            Console.showMessage(cur.getCurrencyName() + ": " + cur.getTotalAmount());
        }
        else Console.showMessage(resInfo.getString("no.money"));
    }
}

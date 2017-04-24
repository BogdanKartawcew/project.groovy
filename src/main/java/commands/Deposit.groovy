package commands;

import classes.Console;
import classes.CurrencyChanger;
import classes.Factory;
import exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
class Deposit implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        Locale locale = new Locale(Language.language);
        ResourceBundle resDeposit = ResourceBundle.getBundle("deposit", locale);
        Console.showMessage(resDeposit.getString("depositing"));

        String currencyCode = Console.askCurrencyName();
        CurrencyChanger currencyManipulator = Factory.getChangerCurrencyName(currencyCode);
        try
        {
            String[] moneyAndAmount = Console.getCashDigits(currencyCode);
            int k = Integer.parseInt(moneyAndAmount[0]);
            int l = Integer.parseInt(moneyAndAmount[1]);
            currencyManipulator.addAmount(k, l);
            Console.showMessage(String.format(resDeposit.getString("success.deposited"), k * l, currencyCode));
        }
        catch (NumberFormatException e)
        {
            Console.showMessage(resDeposit.getString("error.data"));
        }
    }
}

package commands;

import classes.Console;
import classes.CurrencyChanger;
import classes.Factory;
import exceptions.InterruptOperationException;
import exceptions.NotEnoughMoneyException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
class Withdraw implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        Locale locale = new Locale(Language.language);
        ResourceBundle resWithdraw = ResourceBundle.getBundle("withdraw", locale);
        Console.showMessage(resWithdraw.getString("withdrawing"));
        String currencyCode = Console.askCurrencyName();
        CurrencyChanger currencyManipulator = Factory.getChangerCurrencyName(currencyCode);
        int sum;
        while (true)
        {
            Console.showMessage(resWithdraw.getString("specify.amount"));
            String s = Console.read();
            try
            {
                sum = Integer.parseInt(s);
            }
            catch (NumberFormatException e)
            {
                Console.showMessage(resWithdraw.getString("specify.amount.error"));
                continue;
            }
            if (sum < 1)
            {
                Console.showMessage(resWithdraw.getString("specify.not.empty.amount"));
                continue;
            }
            if (!currencyManipulator.isAmountAvailable(sum))
            {
                Console.showMessage(resWithdraw.getString("not.enough.money"));
                continue;
            }
            try
            {
                currencyManipulator.withdrawAmount(sum);
            }
            catch (NotEnoughMoneyException e)
            {
                Console.showMessage(resWithdraw.getString("exact.amount.not.available"));
                continue;
            }
            Console.showMessage(String.format(resWithdraw.getString("dont.forget"), sum, currencyCode));
            break;
        }
    }
}


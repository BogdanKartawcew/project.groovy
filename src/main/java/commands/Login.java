package commands;

import classes.Console;
import exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class Login implements Command
{
    ResourceBundle validCreditCards = ResourceBundle.getBundle("verifiedCards");

    @Override
    public void execute() throws InterruptOperationException
    {
        Locale locale = new Locale(Language.language);
        ResourceBundle resLogin = ResourceBundle.getBundle("login", locale);
        Console.showMessage(resLogin.getString("logging"));
        while (true)
        {
            Console.showMessage(resLogin.getString("specify.data"));
            String s1 = Console.read();
            String s2 = Console.read();
            if (validCreditCards.containsKey(s1))
            {
                if (validCreditCards.getString(s1).equals(s2))
                    Console.showMessage(String.format(resLogin.getString("success.verification"), s1));
                else
                {
                    Console.showMessage(String.format(resLogin.getString("not.verified.format"), s1));
                    Console.showMessage(resLogin.getString("try.again.or.exit"));
                    continue;
                }
            }
            else
            {
                Console.showMessage(String.format(resLogin.getString("not.verified.format"), s1));
                Console.showMessage(resLogin.getString("try.again.with.details"));
                continue;
            }
            break;
        }
    }
}


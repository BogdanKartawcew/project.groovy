package classes;

import commands.Executor;
import commands.Language;
import exceptions.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class Console {

    private static ResourceBundle resConsole = ResourceBundle.getBundle("common_" + Language.language);

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static String read() throws InterruptOperationException {
        String message = "";
        try {
            message = reader.readLine();
            if (message.equalsIgnoreCase(resConsole.getString("operation.EXIT")))
                Executor.execute(Operations.EXIT);
        } catch (IOException ignored) {
        }
        return message;
    }

    public static String askCurrencyName() throws InterruptOperationException {
        ResourceBundle resConsole = ResourceBundle.getBundle("common_" + Language.language);
        String currency;
        showMessage(resConsole.getString("choose.currency.name"));
        while (true) {
            currency = read();
            if (currency.length() == 3)
                break;
            else
                showMessage(resConsole.getString("error.data"));

        }
        return currency.toUpperCase();
    }

    public static String[] getCashDigits(String currency) throws InterruptOperationException {
        ResourceBundle resConsole = ResourceBundle.getBundle("common_" + Language.language);
        showMessage(String.format(resConsole.getString("choose.denomination.and.count.format"), currency));
        String s;
        while (true) {
            s = read();
            if (s.matches("\\d+ \\d+"))
                break;
            else showMessage(resConsole.getString("error.data"));
        }
        return s.split(" ");
    }

    public static Operations askOperation() throws InterruptOperationException {
        ResourceBundle resConsole = ResourceBundle.getBundle("common_" + Language.language);
        while (true) {
            try {
                int line = Integer.parseInt(read());
                if (checkCommand(line))
                    return Operations.getOperationByOrdinal(line);
                else
                    showMessage(resConsole.getString("error.data"));
            } catch (NumberFormatException e) {
                showMessage(resConsole.getString("error.data"));
            }
        }
    }

    /*public static boolean checkCommand (int commandNumber) {
        Pattern p = Pattern.compile("^[1-4]$");
        Matcher m = p.matcher(commandNumber);
        return m.matches();
    }*/

    public static boolean checkCommand(int commandNumber) {
        return (1 <= commandNumber && commandNumber <= 4);
    }

    public static boolean checkLanguage(String language) {
        return (language.equalsIgnoreCase("en") ||
                language.equalsIgnoreCase("ru") ||
                language.equalsIgnoreCase("pl") ||
                language.equalsIgnoreCase("ua"));
    }
}

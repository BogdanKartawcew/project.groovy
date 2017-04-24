package classes;

import commands.Language;
import exceptions.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class CurrencyChanger {

    public String currencyName;
    private static Map<Integer, Integer> cash = new HashMap<>();

   /* static {
        cash.put(1, 6);
        cash.put(2, 6);
        cash.put(5, 6);
        cash.put(10, 6);
        cash.put(20, 6);
        cash.put(50, 6);
        cash.put(100, 6);
        cash.put(200, 6);
    }*/

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public CurrencyChanger(String currency) {
        this.currencyName = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void addAmount(int nominationInt, int count) {
        if (cash.containsKey(nominationInt)) cash.put(nominationInt, cash.get(nominationInt) + count);
        else cash.put(nominationInt, count);
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : cash.entrySet()) result += pair.getKey() * pair.getValue();
        return result;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Locale locale = new Locale(Language.language);
        ResourceBundle resWithdraw = ResourceBundle.getBundle("withdraw", locale);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> ourmap : cash.entrySet()) {
            for (int i = 0; i < ourmap.getValue(); i++)
                linkedList.add(ourmap.getKey());
        }
        Collections.sort(linkedList);
        Collections.reverse(linkedList);
        Map<Integer, Integer> withdrawAmount = new TreeMap<>(Collections.<Integer>reverseOrder());
        List<Integer> list = new ArrayList<>();
        int sum = expectedAmount;

        for (int k = 0; k < linkedList.size(); k++) {
            if (sum >= 0) {
                if (linkedList.get(k) <= sum) {
                    list.add(linkedList.get(k));
                    sum -= linkedList.get(k);
                    linkedList.remove(linkedList.indexOf(linkedList.get(k)));
                    k--;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            withdrawAmount.put(list.get(i), withdrawAmount.containsKey(list.get(i)) ? withdrawAmount.get(list.get(i)) + 1 : 1);
        }

        if (sum != 0)
            throw new NotEnoughMoneyException();

        else {
            cash.clear();

            for (int i = 0; i < linkedList.size(); i++) {
                cash.put(linkedList.get(i), cash.containsKey(linkedList.get(i)) ? cash.get(linkedList.get(i)) + 1 : 1);
            }
            Console.showMessage(resWithdraw.getString("success.withdrawing"));
            for (Map.Entry<Integer, Integer> pair : withdrawAmount.entrySet())
                Console.showMessage("\t" + pair.getKey() + " - " + pair.getValue());
        }
        return withdrawAmount;
    }

    public static boolean hasMoney() {
        return !cash.isEmpty();
    }
}

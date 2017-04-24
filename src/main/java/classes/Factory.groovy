package classes;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by MyWORID on 12.04.2017.
 */
public class Factory {
    private static HashMap<String, CurrencyChanger> map = new HashMap<>();

    public static CurrencyChanger getChangerCurrencyName(String currencyName)
    {
        CurrencyChanger current;

        if (map.containsKey(currencyName))
            current= map.get(currencyName);
        else
        {
            current = new CurrencyChanger(currencyName);
            map.put(currencyName, current);
        }
        return current;
    }

    private Factory()
    {
    }

    public static Collection<CurrencyChanger> getAllCurrencyManipulators()
    {
        return map.values();
    }
}

package classes;

/**
 * Created by MyWORID on 12.04.2017.
 */
public enum Operations {
    LANGUAGE,
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT,
    GoTo;

    public static Operations getOperationByOrdinal(Integer i)
    {
        switch (i)
        {
            case 0:
                throw new IllegalArgumentException();
            case 1:
                return INFO;
            case 2:
                return DEPOSIT;
            case 3:
                return WITHDRAW;
            case 4:
                return EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}

package commands;

import exceptions.InterruptOperationException;

/**
 * Created by MyWORID on 12.04.2017.
 */
public interface Command
{
    void execute() throws InterruptOperationException;
}

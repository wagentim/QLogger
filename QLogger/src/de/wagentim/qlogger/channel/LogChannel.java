package de.wagentim.qlogger.channel;

import de.wagentim.qlogger.console.Console;

/**
 * Different Channels can be used to sort log message
 *
 * @author root
 *
 */
public interface LogChannel
{
	Console getConsole();
	void log(final int level, final String text);
	void log(final int level, final String text, final String...parameters);
	void print();
}

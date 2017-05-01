package de.wagentim.qlogger.channel;

import de.wagentim.qlogger.console.Console;
import de.wagentim.qlogger.console.SysoutConsole;

public class DefaultChannel extends AbstractLogChannel
{

	public DefaultChannel()
	{
		this(null);
	}

	public DefaultChannel(final String channelName)
	{
		super(channelName);
	}

	@Override
	public Console getConsole()
	{
		if( null == console )
		{
			console = new SysoutConsole();
		}
		return console;
	}
}

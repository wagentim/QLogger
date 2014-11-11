package de.wagentim.qlogger.channel;

public class DefaultChannel extends AbstractLogChannel{

	public DefaultChannel() {
		this(null);
	}

	public DefaultChannel(final String channelName)
	{
		super(channelName);
	}
}

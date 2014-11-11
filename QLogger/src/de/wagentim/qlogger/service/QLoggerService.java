package de.wagentim.qlogger.service;

import java.util.TreeMap;

import de.wagentim.qlogger.channel.LogChannel;
import de.wagentim.qlogger.utils.Counter;

/**
 * Quick Logger
 * 
 * @author root
 *
 */
public class QLoggerService {
	
	private static TreeMap<Integer, LogChannel> channelMap = null;
	
	private static Counter counter = null;
	
	static
	{
	
		counter = new Counter();
		counter.setReuseReleasedNumber(false);
		channelMap = new TreeMap<Integer, LogChannel>();
	}
	
	public static synchronized int addChannel( final LogChannel channel )
	{
		if( null == channel )
		{
			return -1;
		}
		
		int availableNumber = counter.getAvailableNumber();
		
		channelMap.put(availableNumber, channel);
		
		return availableNumber;
	}
	
	public static LogChannel getChannel( final int key )
	{
		return channelMap.get(key);
	}
	
	
}

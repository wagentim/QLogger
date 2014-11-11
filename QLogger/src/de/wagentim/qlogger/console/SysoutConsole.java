package de.wagentim.qlogger.console;

import java.sql.Timestamp;
import java.util.List;

import de.wagentim.qlogger.logger.Log;

public class SysoutConsole implements Console{

	@Override
	public void print(List<Log> logs, final String channelName) {
		
		if( null == logs || logs.isEmpty() )
		{
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// construct channel identification
		sb.append(getChannelIdentification(channelName));
		
		for( Log log : logs )
		{
			sb.append(getTimeStamp(log));
			sb.append(getLevel(log));
			sb.append("\n");
			sb.append(log.getText());
			System.out.println(sb.toString());
		}
	}

	private Object getLevel(Log log) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("[ ");
		
		switch(log.getLevel())
		{
			case Log.LEVEL_CRITICAL_ERROR:
				sb.append("CError");
				break;
			
			case Log.LEVEL_ERROR:
				sb.append("Error");
				break;
			
			case Log.LEVEL_INFO:
				sb.append("Info");
				break;
				
			case Log.LEVEL_WARN:
				sb.append("Warn");
				break;
		}
		
		
		sb.append(" ]");
		
		return sb.toString();
	}

	private String getTimeStamp(Log log) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("[ ");
		sb.append(new Timestamp(log.getTimeStamp()).toString());
		sb.append(" ]");
		
		return sb.toString();
	}

	private String getChannelIdentification(String channelName) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("[ ");
		sb.append(channelName);
		sb.append(" ]");
		
		return sb.toString();
	}

}

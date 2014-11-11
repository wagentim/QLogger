package de.wagentim.qlogger.test;

import de.wagentim.qlogger.channel.DefaultChannel;
import de.wagentim.qlogger.channel.LogChannel;
import de.wagentim.qlogger.logger.Log;
import de.wagentim.qlogger.service.QLoggerService;

public class Test {
	
	public static void main(String[] args)
	{
		int id = QLoggerService.addChannel(new DefaultChannel());
		
		LogChannel log = QLoggerService.getChannel(id);
		
		log.log(Log.LEVEL_INFO, "Hello");
		
		log.print();
	}
}

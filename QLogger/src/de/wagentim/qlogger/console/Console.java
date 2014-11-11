package de.wagentim.qlogger.console;

import java.util.List;

import de.wagentim.qlogger.logger.Log;

public interface Console {
	
	void print(List<Log> logs, String channelName);
}

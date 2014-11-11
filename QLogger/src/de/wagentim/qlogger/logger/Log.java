package de.wagentim.qlogger.logger;

public class Log {
	
	public static final int LEVEL_ERROR = 0;
	public static final int LEVEL_WARN = 1;
	public static final int LEVEL_CRITICAL_ERROR = 2;
	public static final int LEVEL_INFO = 3;
	
	private String text = "";
	private int level = Log.LEVEL_INFO;
	private Long timeStamp = 0L;
	
	public Log(final int level, final String text) {
		
		setText(text);
		setLevel(level);
		setTimeStamp(System.currentTimeMillis());
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	private void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getLevel() {
		return level;
	}
	private void setLevel(int level) {
		this.level = level;
	}
	public String getText() {
		return text;
	}
	private void setText(String text) {
		this.text = text;
	}
	

}

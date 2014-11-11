package de.wagentim.qlogger.channel;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.wagentim.basicutils.BasicUtils;
import cn.wagentim.basicutils.StringConstants;
import de.wagentim.qlogger.console.Console;
import de.wagentim.qlogger.console.SysoutConsole;
import de.wagentim.qlogger.logger.Log;
import de.wagentim.qlogger.utils.Utilities;

public abstract class AbstractLogChannel implements LogChannel
{

	private final SortList logList = new SortList();

	private static final int DEFAULT_MAX_LOG_SIZE = 1;

	private int maxSize = DEFAULT_MAX_LOG_SIZE;

	private final String DEFAULT_CHANNEL_NAME = "Default";

	private Console console = null;

	private String name = StringConstants.EMPTY_STRING;

	public AbstractLogChannel(final String name)
	{
		if (null == name || name.isEmpty())
		{
			this.name = DEFAULT_CHANNEL_NAME;
		} else
		{
			this.name = name;
		}
	}

	public String getName()
	{
		return name;
	}

	@Override
	public Console getConsole()
	{

		if (null == this.console)
		{
			this.console = new SysoutConsole();
		}

		return console;
	}

	public int getMaxLogCacheSize()
	{
		return this.maxSize;
	}

	public void setConsole(Console console)
	{
		this.console = console;
	}

	public void setMaxLogCacheSize(int maxSize)
	{
		if (maxSize > 0)
		{
			this.maxSize = maxSize;
		}
	}

	@Override
	public synchronized void log(final int level, final String text)
	{
		addLog(level, text);

	}

	private void addLog(final int level, final String text)
	{
		if( BasicUtils.isNullOrEmpty(text) )
		{
			return;
		}

		logList.add(new Log(level, text));

		if (logList.size() == maxSize)
		{
			print();

			logList.clear();
		}
	}

	@Override
	public synchronized void log(final int level, final String text, final String...parameters)
	{
		String replacedInfo = Utilities.textReplace(text, parameters);

		addLog(level, replacedInfo);
	}

	@Override
	public void print()
	{
		getConsole().print(logList, this.name);
	}

	private class SortList extends AbstractList<Log>
	{

		private ArrayList<Log> internalList = new ArrayList<Log>();

		@Override
		public Log get(int index)
		{
			return internalList.get(index);
		}

		@Override
		public int size()
		{
			return internalList.size();
		}

		public void clear()
		{
			internalList.clear();
		}

		@Override
		public void add(int position, Log e)
		{

			if (maxSize > 0 && internalList.size() == maxSize)
			{
				console.print(internalList, name);
				internalList.clear();
			}

			internalList.add(e);
			Collections.sort(internalList, new Comparator<Log>()
			{

				@Override
				public int compare(Log o1, Log o2)
				{
					return o1.getTimeStamp().compareTo(o2.getTimeStamp());
				}
			});
		}
	}
}

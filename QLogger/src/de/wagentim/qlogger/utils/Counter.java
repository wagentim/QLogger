package de.wagentim.qlogger.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Counter is used to generate an available and smallest Integer number
 * <br>
 * The number is start with 1. 
 * 
 * @author root
 *
 */
public class Counter {

	private boolean useReleasedNumber = true;
	
	private SortList usedNumber = null;
	
	public Counter()
	{
		usedNumber = new SortList();
	}
	
	public synchronized int getAvailableNumber()
	{
		if( usedNumber.isEmpty() )
		{
			usedNumber.add(1);
			
			return 1;
		}
		
		if( useReleasedNumber )
		{
			return getNextFreeNumber();
		}else
		{
			return getNextBiggestNumber();
		}
	}
	
	private int getNextBiggestNumber() {
		
		int result = usedNumber.get( usedNumber.size() - 1 ) + 1;
		
		usedNumber.add(result);
		
		return result;
	}

	private int getNextFreeNumber() {
		
		int value = 1;
		
		while( findNumber(value) )
		{
			value++;
		}
		
		usedNumber.add(value);
		
		return value;
	}

	private boolean findNumber(int value) {
		
		return Collections.binarySearch(usedNumber, value) == -1 ? false : true;
	}

	public synchronized void setReuseReleasedNumber(final boolean value)
	{
		this.useReleasedNumber = value;
	}
	
	private class SortList extends AbstractList<Integer>
	{
		
		private ArrayList<Integer> internalList = new ArrayList<Integer>();

		@Override
		public Integer get(int index) {
			return internalList.get(index);
		}

		@Override
		public int size() {
			return internalList.size();
		}
		
		@Override 
	    public void add(int position, Integer e) {
	        internalList.add(e);
	        Collections.sort(internalList, null);
	    }
	}
}

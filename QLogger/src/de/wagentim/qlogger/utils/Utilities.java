package de.wagentim.qlogger.utils;

import cn.wagentim.basicutils.StringConstants;
import cn.wagentim.basicutils.Validator;

public class Utilities
{

	/**
	 * Replace the text in the placeholder
	 *
	 * @param originalText
	 * @param replaceTexts
	 * @return
	 */
	public static final String textReplace(final String originalText, final String...replaceTexts)
	{
		if( Validator.isNullOrEmpty(originalText) || null == replaceTexts )
		{
			return StringConstants.EMPTY_STRING;
		}

		StringBuffer sb = new StringBuffer(originalText);
		
		int replaceIndex = -1;

		while( (replaceIndex = sb.lastIndexOf(StringConstants.SYMBOL_PERCENT)) >= 0 )
		{
			StringBuffer tmp = new StringBuffer();
			int subNumberIndex = 1;
			int index;
			
			while( ( (index = (replaceIndex + subNumberIndex)) < sb.length()) && Character.isDigit(sb.charAt(index)) )
			{
				tmp.append(sb.charAt(index));
				subNumberIndex++;
			}

			if( tmp.length() > 0 )
			{
				String text = replaceTexts[Integer.valueOf(tmp.toString()) - 1];
				sb.replace(replaceIndex, replaceIndex + tmp.length() + 1, null == text ? StringConstants.EMPTY_STRING : text );
			}
		}

		return sb.toString();
	}
}

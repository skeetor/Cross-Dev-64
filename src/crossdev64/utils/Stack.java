package crossdev64.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Stack
{
	public static int getLineNumber()
	{
	    return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	public static String getFileName()
	{
	    return Thread.currentThread().getStackTrace()[2].getFileName();
	}

	public static String getClassName()
	{
	    return Thread.currentThread().getStackTrace()[2].getClassName();
	}

	/**
	 * Return the source position from the calling method.
	 * 
	 * @return
	 */
	public static String getSourcePosition()
	{
		return getDebugString(3);
	}

	public static String getDebugString(int nStackIndex)
	{
		StackTraceElement[] el = Thread.currentThread().getStackTrace();
		if(el == null || nStackIndex < 0 || nStackIndex >= el.length)
			return "";

		return getDebugString(el[nStackIndex]);
	}

	public static String getDebugString(StackTraceElement oElement)
	{
		String n = oElement.getClassName();
		String[] s = n.split("[.]");
		n = "";
		for(int i = 0; i < s.length; i++)
			n += s[i]+".";

		return n+oElement.getMethodName()+"("+oElement.getFileName()+":"+oElement.getLineNumber()+")";
	}

	public static String printStackTrace(Throwable e)
	{
		StackTraceElement[] el = null;

		if(e == null)
			el = Thread.currentThread().getStackTrace();
		else
			el = e.getStackTrace();

		String rc = "";

		if(el == null)
			return "";

		for(int i = 0; i < el.length; i++)
			rc += getDebugString(el[i])+"\n";

		return rc;
	}

	public static String getTimestamp()
	{
		return Stack.getTimestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static String getTimestamp(long nTimestamp)
	{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Timestamp ts = new Timestamp(nTimestamp);

		return sd.format(ts);
	}
}

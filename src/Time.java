package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time 
{
	public Time()
	{
	
	
	
	}
	
	public void saveTime(String filename)
	{
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateStr = dateFormat.format(cal.getTime());
	try {
		PrintWriter out = new PrintWriter(filename);
		out.println(dateStr);
		out.close();
		} 
	catch (Exception e) {e.printStackTrace();}
	}

	public Date loadTime(String filename)
	{
		File f = new File(filename);
		if(f.exists())
		{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        try {
				while((line = reader.readLine()) != null) {
				    stringBuilder.append(line);
				    stringBuilder.append(ls);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        String date = stringBuilder.toString();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
				return dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    } finally {
	        try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
		return null;
	}
}

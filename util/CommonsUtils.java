package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class CommonsUtils {

	
	
	public static boolean isEmpty(String str) {
		return str==null || "".equals(str) ? true : false;
	}
	
	public static boolean isEmpty(Integer it) {
		return it==null ? true : false;
	}
	
	
	public static String pareDate(String parrtner,Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(parrtner);
		return dateFormat.format(date);
	}
}

package epf.domethic.ouroboros.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.actionbarsherlock.app.SherlockFragment;
import android.support.v4.app.FragmentTransaction;

public abstract class Utils {
	
	
	public static String formaterDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static Date parserDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(date);
	}
}

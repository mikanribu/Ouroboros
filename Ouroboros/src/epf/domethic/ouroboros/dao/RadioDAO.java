package epf.domethic.ouroboros.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import epf.domethic.ouroboros.activity.Utils;
import epf.domethic.ouroboros.data.PatientDBOpenHelper;
import epf.domethic.ouroboros.data.RadioDBOpenHelper;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.PatientColumns;

public class RadioDAO {

	private final static String TAG = RadioDAO.class.getSimpleName();

	private Context context;
	
	private SQLiteDatabase database;
	
	private RadioDBOpenHelper helper;
	
	public RadioDAO(Context context) {
		this.context = context;
		this.helper = new RadioDBOpenHelper(context);   
		database = helper.getWritableDatabase();
	}
	
	public void close() {
		helper.close();
	}
	
	public RadioDAO open() throws SQLException {
		helper = new RadioDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	public Cursor getRadiosCursor(){
		String[] columns = 
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM,
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN,
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		return	database.query(RadioDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, DocumentColumns.KEY_DATE, null);
	}
	
	public Cursor getRadiosCursor(String like){
		String[] columns = 
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		String where = PatientColumns.KEY_NOM + " like '%" + like + "%'";
		Log.v("YO", "GetRadio :"+ where);
		String[] whereArgs = new String[]{like};
		return	database.query(RadioDBOpenHelper.TABLE_PATIENT,columns, "nom = 'Thorax'", null, null, null, null);
	}
	
	public Cursor getRadiosCursor(int like){
		String[] columns = 
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		String where = PatientColumns._ID + " like '%" + like + "%'";
		int whereArgs = like;
		return	database.query(RadioDBOpenHelper.TABLE_PATIENT,columns, where, null, null, null, null);
	}
	
	
	public void ajouterRadio(ArrayList<String> arrayRadios){
		ContentValues values = new ContentValues();

		values.put(DocumentColumns.KEY_ID_PATIENT, arrayRadios.get(0));
		values.put(DocumentColumns.KEY_NOM, arrayRadios.get(1));
		values.put(DocumentColumns.KEY_RADIO, arrayRadios.get(2));
		values.put(DocumentColumns.KEY_CAUSE, arrayRadios.get(3));	
		values.put(DocumentColumns.KEY_DATE, arrayRadios.get(4));
		values.put(DocumentColumns.KEY_MEDECIN, arrayRadios.get(5));
		values.put(DocumentColumns.KEY_DESCRIPTION, arrayRadios.get(6));
		values.put(DocumentColumns.KEY_INTERPRETATION, arrayRadios.get(7));
		

		database.insert(RadioDBOpenHelper.TABLE_PATIENT, null, values);
	}
	
	public boolean dbIsEmpty () {
		//Savoir si la base de données est vide
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ RadioDBOpenHelper.TABLE_PATIENT, null);
		
		if (cur != null) {
			Log.v("TAG","DANS focntion DBEMPTY !!!!!");
		    cur.moveToFirst();                       // Always one row returned.
		    if (cur.getInt (0) == 0) {               // Zero count means empty table.
		    	Log.v("TAG","EMPTY !!!!!");
		    	return true;
		    }
		}
		Log.v("TAG","NO EMPTY !!!!!");
		return false;
	}
}

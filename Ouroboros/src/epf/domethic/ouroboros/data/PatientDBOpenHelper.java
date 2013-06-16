package epf.domethic.ouroboros.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PatientDBOpenHelper extends SQLiteOpenHelper {

	private final static String TAG = PatientDBOpenHelper.class.getSimpleName(); 
	
	private static final String DATABASE_NAME = "patients.db";
	
	private static final int DATABASE_VERSION = 2;
	
	public static final String TABLE_PATIENT = "Patient";   
    
    
	private static final String DATABASE_CREATE = 
			"CREATE TABLE " + TABLE_PATIENT + "(" + PatientColumns.KEY_ID + " integer primary key autoincrement," +
					PatientColumns.KEY_NOM + " text not null," + PatientColumns.KEY_PRENOM + " text not null," + PatientColumns.KEY_SEXE + " text not null," +
					PatientColumns.KEY_DATE_NAISSANCE + " text not null," + PatientColumns.KEY_LIEU_NAISSANCE + " text not null," +
					PatientColumns.KEY_ADRESSE + " text not null,"+ PatientColumns.KEY_VILLE + " text not null," + PatientColumns.KEY_CODE_POSTAL + " text not null," +
					PatientColumns.KEY_PAYS + " text not null," + PatientColumns.KEY_NATIONALITE + " text not null," + PatientColumns.KEY_TELEPHONE + " text not null," +
					PatientColumns.KEY_NUMSS + " text not null," + PatientColumns.KEY_MEDECIN_TRAITANT + " text not null,"+ PatientColumns.KEY_HOSPITALISE + " text not null)";

	public PatientDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, 
				"Création de la base : [version " + db.getVersion() + "]");
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, 
				"Mise à jour de la base [" + DATABASE_NAME + "] : [" + oldVersion + " --> " + newVersion + "]");

	}

}

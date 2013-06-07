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
	
	public static final String KEY_ID ="id";
	public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_SEXE = "sexe";
    public static final String KEY_DATE_NAISSANCE ="date_naissance" ;
    public static final String KEY_LIEU_NAISSANCE = "lieu_naissance";
    public static final String KEY_ADRESSE = "adresse";
    public static final String KEY_VILLE = "ville";
    public static final String KEY_CODE_POSTAL = "code_postal";
    public static final String KEY_PAYS = "pays";
    public static final String KEY_NATIONALITE = "nationalite";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_NUMSS = "num_ss";
    public static final String KEY_MEDECIN_TRAITANT = "medecin_traitant";
    public static final String KEY_HOSPITALISE = "hospitalise";
    
    
    
	private static final String DATABASE_CREATE = 
			"CREATE TABLE " + TABLE_PATIENT + "(" + KEY_ID + " integer primary key autoincrement," +
					KEY_NOM + " text not null," + KEY_PRENOM + " text not null," + KEY_SEXE + " text not null," +
					KEY_DATE_NAISSANCE + " text not null," + KEY_LIEU_NAISSANCE + " text not null," +
					KEY_ADRESSE + " text not null,"+ KEY_VILLE + " text not null," + KEY_CODE_POSTAL + " text not null," +
					KEY_PAYS + " text not null," + KEY_NATIONALITE + " text not null," +KEY_TELEPHONE + " text not null," +
					KEY_NUMSS + " text not null," + KEY_MEDECIN_TRAITANT + " text not null,"+ KEY_HOSPITALISE + " text not null)";
	

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

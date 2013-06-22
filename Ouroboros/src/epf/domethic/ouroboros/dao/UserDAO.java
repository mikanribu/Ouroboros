package epf.domethic.ouroboros.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import epf.domethic.ouroboros.activity.Utils;
import epf.domethic.ouroboros.data.PatientDBOpenHelper;
import epf.domethic.ouroboros.data.RadioDBOpenHelper;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.PatientColumns;

public class UserDAO {

	private final static String TAG = PatientDAO.class.getSimpleName();

	private Context context;
	
	private SQLiteDatabase database;
	
	private PatientDBOpenHelper helper;
	
	public UserDAO(Context context) {
		this.context = context;
		this.helper = new PatientDBOpenHelper(context);
		database = helper.getWritableDatabase();
	}
	
	public void close() {
		helper.close();
	}
	
	public UserDAO open() throws SQLException {
		helper = new PatientDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	public Cursor getUsersCursor(){
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_PSEUDO, PatientColumns.KEY_MDP,
				PatientColumns.KEY_NOM, PatientColumns.KEY_PRENOM, PatientColumns.KEY_MAIL,
				PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_SERVICE, PatientColumns.KEY_FONCTION};
		return	database.query(PatientDBOpenHelper.TABLE_USER,columns, null, null, null, null, null, null);
	}
	
	//Fonction qui permet de récupérer l'utilisateur selon son pseudo 
	public Cursor getUsersCursor(String pseudo){
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_PSEUDO, PatientColumns.KEY_MDP,
				PatientColumns.KEY_NOM, PatientColumns.KEY_PRENOM, PatientColumns.KEY_MAIL,
				PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_SERVICE, PatientColumns.KEY_FONCTION};
		String where = PatientColumns.KEY_PSEUDO + " like '%" + pseudo + "%'";
		String[] whereArgs = new String[]{pseudo};
		return	database.query(PatientDBOpenHelper.TABLE_USER,columns, where, null, null, null, null);
	}
	
	//Fonction qui permet de récupérer l'utilisateur selon son pseudo & mot de passe
	public Cursor getUsersCursor(String pseudo, String mdp){
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_PSEUDO, PatientColumns.KEY_MDP,
				PatientColumns.KEY_NOM, PatientColumns.KEY_PRENOM, PatientColumns.KEY_MAIL,
				PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_SERVICE, PatientColumns.KEY_FONCTION};
		String where = PatientColumns.KEY_PSEUDO + "='" + pseudo + "' and " + 
				PatientColumns.KEY_MDP + "='" + mdp + "'";
		String[] whereArgs = new String[]{pseudo};
		return	database.query(PatientDBOpenHelper.TABLE_USER,columns, where, null, null, null, null);
	}
	
	
	public void ajouterUser(ArrayList<String> arrayUsers){
		ContentValues values = new ContentValues();
		// ajout de toutes les valeurs dans le champ de la BDD correspondant
		//values.put(DocumentColumns.KEY_ID_PATIENT, arrayUsers.get(0));
		values.put(PatientColumns.KEY_PSEUDO, arrayUsers.get(1));
		values.put(PatientColumns.KEY_MDP, arrayUsers.get(2));
		values.put(PatientColumns.KEY_NOM, arrayUsers.get(3));	
		values.put(PatientColumns.KEY_PRENOM, arrayUsers.get(4));
		values.put(PatientColumns.KEY_MAIL, arrayUsers.get(5));
		values.put(PatientColumns.KEY_TELEPHONE, arrayUsers.get(6));
		values.put(PatientColumns.KEY_SERVICE, arrayUsers.get(7));
		values.put(PatientColumns.KEY_FONCTION, arrayUsers.get(8));

		database.insert(PatientDBOpenHelper.TABLE_USER, null, values); //Insert les données dans la BDD
	}

	//Fonction permettant de savoir si la Base de Données est vide
	public boolean dbIsEmpty () {
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ PatientDBOpenHelper.TABLE_USER, null);
		
		if (cur != null) {							//Si le curseur n'est pas null
		    cur.moveToFirst();                      // On pointe le curseur sur le premier élément
		    if (cur.getInt (0) == 0) {               // Zero signifie que la table est vide
		    	return true;
		    }
		}
		return false;					//On retourne false si la table n'est pas vide
	}
}

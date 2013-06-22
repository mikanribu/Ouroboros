package epf.domethic.ouroboros.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import epf.domethic.ouroboros.data.RadioDBOpenHelper;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.PatientColumns;

public class RadioDAO {

	/* ----------	Déclaration des variables	---------- */
	private Context context;
	
	private SQLiteDatabase database;	//Base SQLite
	
	private RadioDBOpenHelper helper;
	
	
	/* ----------	Déclaration des fonctions	---------- */
	
	public RadioDAO(Context context) {
		this.context = context;
		this.helper = new RadioDBOpenHelper(context);   
		database = helper.getWritableDatabase();
	}
	//Fonction permettant de fermer la BDD Radio
	public void close() {
		helper.close();
	}
	//Fonction permettant l'ouverture de la BDD Radio
	public RadioDAO open() throws SQLException {
		helper = new RadioDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	/* Focntion permettant de récupérer toutes les tables de la BDD Radio	  
	 */
	public Cursor getRadiosCursor(){
		String[] columns = 			//Colonnes à récupérer
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM,
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN,
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		//Retourne toutes les tables de la BDD Radio trié dans l'ordre alphabétique
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, null, null, null, null, DocumentColumns.KEY_DATE, null);
	}
	
	/*Fonction qui permet de récupérer les tables de la BDD selon un critère
	 like représente un nom qui devra être similaire au nom de la radio	 */
	public Cursor getRadiosCursor(String like){
		String[] columns =						//Colonnes à récupérer 
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};


		String where = PatientColumns.KEY_NOM + " like '%" + like + "%'";			//Clause où le nom de la radio correspond à la chaine de caractère like	
		String[] whereArgs = new String[]{like};
		//Retourne un curseur sur les tables qui correspondent au critère de sélection
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, where, null, null, null, null);

	}
	
	/*Fonction qui permet de récupérer les tables de la BDD selon un critère
	 like représente un entier qui devra être similaire à l'id de la radio	 */
	public Cursor getRadiosCursor(int like){
		String[] columns = 					//Colonnes à récupérer
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		String where = PatientColumns._ID + " like '%" + like + "%'";		 		//Si l'id de la radio est comme l'entier 'like'
		int whereArgs = like;
		//Retourne un curseur sur les tables qui correspondent au critère de sélection
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, where, null, null, null, null); 
	}
	
	/*Fonction qui permet d'ajouter une radio dans la base de données
	arrayRadios correspond à la liste des attributs d'une radio*/
	public void ajouterRadio(ArrayList<String> arrayRadios){
		ContentValues values = new ContentValues();
		// ajout de toutes les valeurs dans le champ de la BDD correspondant
		values.put(DocumentColumns.KEY_ID_PATIENT, arrayRadios.get(0));
		values.put(DocumentColumns.KEY_NOM, arrayRadios.get(1));
		values.put(DocumentColumns.KEY_RADIO, arrayRadios.get(2));
		values.put(DocumentColumns.KEY_CAUSE, arrayRadios.get(3));	
		values.put(DocumentColumns.KEY_DATE, arrayRadios.get(4));
		values.put(DocumentColumns.KEY_MEDECIN, arrayRadios.get(5));
		values.put(DocumentColumns.KEY_DESCRIPTION, arrayRadios.get(6));
		values.put(DocumentColumns.KEY_INTERPRETATION, arrayRadios.get(7));

		database.insert(RadioDBOpenHelper.TABLE_RADIO, null, values); //Insert les données dans la BDD
	}
	
	//Fonction permettant de savoir si la Base de Données est vide
	public boolean dbIsEmpty () {
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ RadioDBOpenHelper.TABLE_RADIO, null);
		
		if (cur != null) {							 //Si le curseur n'est pas null
		    cur.moveToFirst();                       // On pointe le curseur sur le premier élément
		    if (cur.getInt (0) == 0) {               // Zero signifie que la table est vide
		    	return true;
		    }
		}
		return false;					//On retourne false si la table n'est pas vide
	}
	
	public Radio getRadio(Cursor cursor){
		Radio radio = new Radio();
		radio.setTitre(cursor.getString(0));
		radio.setNomRadio(cursor.getString(1));
		radio.setCause(cursor.getString(2));
		radio.setDate(cursor.getString(3));
		radio.setMedecin(cursor.getString(4));
		radio.setDescription(cursor.getString(5));
		radio.setInterpretation(cursor.getString(6));
		
		return radio;
	}
	
}

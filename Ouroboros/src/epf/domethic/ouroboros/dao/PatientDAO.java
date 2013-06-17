package epf.domethic.ouroboros.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import epf.domethic.ouroboros.activity.Utils;
import epf.domethic.ouroboros.data.PatientDBOpenHelper;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
import epf.domethic.ouroboros.outils.PatientColumns;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PatientDAO {
	
	private final static String TAG = PatientDAO.class.getSimpleName();

	private Context context;
	
	private SQLiteDatabase database;
	
	private PatientDBOpenHelper helper;
	
	public PatientDAO(Context context) {
		this.context = context;
		this.helper = new PatientDBOpenHelper(context);
		database = helper.getWritableDatabase();
	}
	
	public void close() {
		helper.close();
	}
	
	public PatientDAO open() throws SQLException {
		helper = new PatientDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	public Cursor getPatientsCursor(){
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_NOM, PatientColumns.KEY_PRENOM,
				PatientColumns.KEY_SEXE, PatientColumns.KEY_DATE_NAISSANCE,PatientColumns.KEY_LIEU_NAISSANCE, 
				PatientColumns.KEY_ADRESSE, PatientColumns.KEY_VILLE, PatientColumns.KEY_CODE_POSTAL, PatientColumns.KEY_PAYS, 
				PatientColumns.KEY_NATIONALITE, PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_NUMSS, 
				PatientColumns.KEY_MEDECIN_TRAITANT, PatientColumns.KEY_HOSPITALISE};
		return	database.query(PatientDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, PatientColumns.KEY_NOM, null);
	}
	
	public Cursor getPatientsCursor(String like){
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_NOM,	PatientColumns.KEY_PRENOM,
				PatientColumns.KEY_SEXE, PatientColumns.KEY_DATE_NAISSANCE,PatientColumns.KEY_LIEU_NAISSANCE, 
				PatientColumns.KEY_ADRESSE, PatientColumns.KEY_VILLE, PatientColumns.KEY_CODE_POSTAL, PatientColumns.KEY_PAYS, 
				PatientColumns.KEY_NATIONALITE, PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_NUMSS, 
				PatientColumns.KEY_MEDECIN_TRAITANT, PatientColumns.KEY_HOSPITALISE};
		String where = PatientColumns.KEY_NOM + " like '%" + like + "%'";
		String[] whereArgs = new String[]{like};
		return	database.query(PatientDBOpenHelper.TABLE_PATIENT,columns, where, null, null, null, null);
	}
	
	public Patient getPatient(Cursor cursor){
		Patient patient = new Patient();
		patient.setId(cursor.getInt(0));
		patient.setNom(cursor.getString(1));
		patient.setPrenom(cursor.getString(2));
		Sexe sexe = Sexe.valueOf(cursor.getString(3));
		patient.setSexe(sexe);
		try {
			Date dateNaissance = Utils.parserDate(cursor.getString(4));
			patient.setDateNaissance(dateNaissance);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient.setLieuNaissance(cursor.getString(5));
		patient.setAdresse(cursor.getString(6));
		patient.setVille(cursor.getString(7));
		patient.setCodePostal(cursor.getString(8));
		patient.setPays(cursor.getString(9));
		patient.setNationalite(cursor.getString(10));
		patient.setTelephone(cursor.getString(11));
		patient.setNumSS(cursor.getString(12));
		patient.setMedecinTraitant(cursor.getString(13));
		patient.setHospitalise(cursor.getInt(14) == 1);  // Récupérer la valeur en tant que boolean
		
		return patient;
	}
	
	public List<Patient> getPatients(){
		
		List<Patient> patients = new ArrayList<Patient>();
		String[] columns = 
				new String[]{PatientColumns._ID, PatientColumns.KEY_NOM,	PatientColumns.KEY_PRENOM,
				PatientColumns.KEY_SEXE, PatientColumns.KEY_DATE_NAISSANCE,PatientColumns.KEY_LIEU_NAISSANCE, 
				PatientColumns.KEY_ADRESSE, PatientColumns.KEY_VILLE, PatientColumns.KEY_CODE_POSTAL, PatientColumns.KEY_PAYS, 
				PatientColumns.KEY_NATIONALITE, PatientColumns.KEY_TELEPHONE, PatientColumns.KEY_NUMSS, 
				PatientColumns.KEY_MEDECIN_TRAITANT, PatientColumns.KEY_HOSPITALISE};
		Cursor cursor = 
				database.query(PatientDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Patient patient = new Patient();
			patient.setId(cursor.getInt(0));
			patient.setNom(cursor.getString(1));
			patient.setPrenom(cursor.getString(2));
			Sexe sexe = Sexe.valueOf(cursor.getString(3));
			patient.setSexe(sexe);
			try {
				Date dateNaissance = Utils.parserDate(cursor.getString(4));
				patient.setDateNaissance(dateNaissance);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			patient.setLieuNaissance(cursor.getString(5));
			patient.setAdresse(cursor.getString(6));
			patient.setVille(cursor.getString(7));
			patient.setCodePostal(cursor.getString(8));
			patient.setPays(cursor.getString(9));
			patient.setNationalite(cursor.getString(10));
			patient.setTelephone(cursor.getString(11));
			patient.setNumSS(cursor.getString(12));
			patient.setMedecinTraitant(cursor.getString(13));
			patient.setHospitalise(cursor.getInt(14) == 1);  // Récupérer la valeur en tant que boolean
			
			patients.add(patient);
			cursor.moveToNext();
		}
		cursor.close();
		return patients;
	}
	
	public void ajouterPatient(Patient patient){
		ContentValues values = new ContentValues();

		values.put(PatientColumns.KEY_NOM, patient.getNom());
		values.put(PatientColumns.KEY_PRENOM,patient.getPrenom());
		values.put(PatientColumns.KEY_SEXE, patient.getSexe().name());	
		values.put(PatientColumns.KEY_DATE_NAISSANCE, Utils.formaterDate(patient.getDateNaissance()));
		values.put(PatientColumns.KEY_NOM, patient.getNom());
		values.put(PatientColumns.KEY_LIEU_NAISSANCE, patient.getLieuNaissance());
		values.put(PatientColumns.KEY_ADRESSE, patient.getAdresse());
		values.put(PatientColumns.KEY_VILLE, patient.getVille());
		values.put(PatientColumns.KEY_CODE_POSTAL, patient.getCodePostal());
		values.put(PatientColumns.KEY_PAYS, patient.getPays());
		values.put(PatientColumns.KEY_NATIONALITE, patient.getNationalite());
		values.put(PatientColumns.KEY_TELEPHONE, patient.getTelephone());
		values.put(PatientColumns.KEY_NUMSS, patient.getNumSS());
		values.put(PatientColumns.KEY_MEDECIN_TRAITANT, patient.getMedecinTraitant());
		values.put(PatientColumns.KEY_HOSPITALISE, patient.isHospitalise());
		database.insert(PatientDBOpenHelper.TABLE_PATIENT, null, values);
	}
	
	public boolean dbIsEmpty () {
		//Savoir si la base de données est vide
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ PatientDBOpenHelper.TABLE_PATIENT, null);
		
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

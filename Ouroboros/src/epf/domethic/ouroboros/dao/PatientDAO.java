package epf.domethic.ouroboros.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import epf.domethic.ouroboros.activity.Utils;
import epf.domethic.ouroboros.data.PatientDBOpenHelper;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
	
	public Cursor getPatientsCursor(){
		String[] columns = 
				new String[]{helper.KEY_ID, helper.KEY_NOM,	helper.KEY_PRENOM,
				helper.KEY_SEXE, helper.KEY_DATE_NAISSANCE,helper.KEY_LIEU_NAISSANCE, 
				helper.KEY_ADRESSE, helper.KEY_VILLE, helper.KEY_CODE_POSTAL, helper.KEY_PAYS, 
				helper.KEY_NATIONALITE, helper.KEY_TELEPHONE, helper.KEY_NUMSS, 
				helper.KEY_MEDECIN_TRAITANT, helper.KEY_HOSPITALISE};
		return	database.query(PatientDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, null);
	}
	
	public Cursor getPatientsCursor(String like){
		String[] columns = 
				new String[]{helper.KEY_ID, helper.KEY_NOM,	helper.KEY_PRENOM,
				helper.KEY_SEXE, helper.KEY_DATE_NAISSANCE,helper.KEY_LIEU_NAISSANCE, 
				helper.KEY_ADRESSE, helper.KEY_VILLE, helper.KEY_CODE_POSTAL, helper.KEY_PAYS, 
				helper.KEY_NATIONALITE, helper.KEY_TELEPHONE, helper.KEY_NUMSS, 
				helper.KEY_MEDECIN_TRAITANT, helper.KEY_HOSPITALISE};
		String where = helper.KEY_NOM + " like '%" + like + "%'";
		String[] whereArgs = new String[]{like};
		return	database.query(PatientDBOpenHelper.TABLE_PATIENT,columns, where, null, null, null, null);
	}
	
	public Patient getEtudiant(Cursor cursor){
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
	
	public List<Patient> getEtudiants(){
		
		List<Patient> patients = new ArrayList<Patient>();
		String[] columns = 
				new String[]{helper.KEY_ID, helper.KEY_NOM,	helper.KEY_PRENOM,
				helper.KEY_SEXE, helper.KEY_DATE_NAISSANCE,helper.KEY_LIEU_NAISSANCE, 
				helper.KEY_ADRESSE, helper.KEY_VILLE, helper.KEY_CODE_POSTAL, helper.KEY_PAYS, 
				helper.KEY_NATIONALITE, helper.KEY_TELEPHONE, helper.KEY_NUMSS, 
				helper.KEY_MEDECIN_TRAITANT, helper.KEY_HOSPITALISE};
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
	
}

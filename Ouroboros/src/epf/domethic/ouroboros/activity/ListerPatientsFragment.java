package epf.domethic.ouroboros.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.dao.PatientDAO;

import epf.domethic.ouroboros.adapter.PatientCursorAdapter;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.PatientColumns;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListerPatientsFragment extends ListFragment {

	public interface OnPatientSelectedListener {
		public void onPatientSelected(int position, Patient patient);
	}

	// url contenant le fichier json des patients de l'application
	static String url = "http://raw.github.com/Mikanribu/Ouroboros/master/json_patients";

	private OnPatientSelectedListener listener;

	private ListView patientListView;

	private PatientDAO dao;
	private Patient patient; // Création d'un patient de la classe Patient

	private final static String TAG = ListerPatientsFragment.class.getSimpleName();
	private PatientCursorAdapter adapter; // Création du Cursor pour les
											// patients via la Base de Données

	JSONArray patients = null; // Objet JSON récupéré de la classe ParserJSON
	List<Patient> patientList = new ArrayList<Patient>();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// Récupération de la listview créée dans le fichier main.xml
		patientListView = getListView();

		patientList = (ArrayList<Patient>) Patient.ALL;
		
		//Choix du mode de sélection : un seul élément de la liste peut être sélectionné
		patientListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		patientListView.setAdapter(adapter);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.dao = new PatientDAO(getActivity());

		// On vérifie si la base de données est vide ou non
		if (dao.dbIsEmpty() == true) {
			RecuperationJSON(); // Dans le cas où elle est vide on récupère les
								// données du fichier JSON
		}
		
		Cursor cursor = dao.getPatientsCursor(); // Création du Cursor qui va
													// nous permettre de se
													// déplacer dans la BDD

		adapter = new PatientCursorAdapter(getActivity(),
				dao.getPatientsCursor(), true); // Définition de l'adapter

		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_patients_list,
				container, false);

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (OnPatientSelectedListener) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

	public void update() {
		// adapter.notifyDataSetChanged();
		Cursor cursor = dao.getPatientsCursor();
		adapter.changeCursor(cursor);
	}

	public void update(String like) {
		Cursor cursor = dao.getPatientsCursor(like);
		adapter.changeCursor(cursor);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null) {
			
			Cursor cursor = (Cursor) getListAdapter().getItem(position);
			patient = dao.getPatient(cursor);
			listener.onPatientSelected(position, patient);

		}
	}

	public Patient getPatientSelected() {
		return patient;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		adapter.notifyDataSetChanged();
	}

	// Fonction qui permet de récupérer l'objet JSON via l'url
	// Qui le parse et ajoute les informations dans la BDD
	public void RecuperationJSON() {
		// Permet d'accéder à internet sans erreurs "Network access" dû à
		// l'accès à internet dans le thread principal

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// Creation d'une instance ParserJSON
		ParserJSON jParser = new ParserJSON();
		// On récupère JSON string à partir de l'URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			patients = json.getJSONArray("patients");

			// Boucle sur tous les patients du fichier JSON
			for (int i = 0; i < patients.length(); i++) {
				JSONObject c = patients.getJSONObject(i);

				// On récupère toutes les données qu'on stocke dans une variable
				String nom = c.getString(PatientColumns.KEY_NOM);
				String prenom = c.getString(PatientColumns.KEY_PRENOM);
				Sexe sexe = Sexe.valueOf(c.getString(PatientColumns.KEY_SEXE));
				Date dateNaissance = null;
				// Récupère la date de naissance du format Date
				try {
					dateNaissance = Utils.parserDate(c
							.getString(PatientColumns.KEY_DATE_NAISSANCE));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String lieuNaissance = c
						.getString(PatientColumns.KEY_LIEU_NAISSANCE);
				String adresse = c.getString(PatientColumns.KEY_ADRESSE);
				String ville = c.getString(PatientColumns.KEY_VILLE);
				String codePostal = c.getString(PatientColumns.KEY_CODE_POSTAL);
				String pays = c.getString(PatientColumns.KEY_PAYS);
				String nationalite = c
						.getString(PatientColumns.KEY_NATIONALITE);
				String telephone = c.getString(PatientColumns.KEY_TELEPHONE);
				String numSS = c.getString(PatientColumns.KEY_NUMSS);
				String medecinTraitant = c
						.getString(PatientColumns.KEY_MEDECIN_TRAITANT);
				boolean hospitalise = c.getInt(PatientColumns.KEY_HOSPITALISE) == 1;
				// Création d'un patient avec les données
				Patient p = new Patient(nom, prenom, sexe, dateNaissance,
						lieuNaissance, adresse, ville, codePostal, pays,
						nationalite, telephone, numSS, medecinTraitant,
						hospitalise);

				PatientDAO dao = new PatientDAO(this.getActivity());
				dao.ajouterPatient(p); // Ajoute un patient dans la BDD
				dao.close();
				// patientList.add(p);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

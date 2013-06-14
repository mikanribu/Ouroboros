package epf.domethic.ouroboros.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import epf.domethic.ouroboros.data.PatientDBOpenHelper;
import epf.domethic.ouroboros.adapter.PatientAdapter;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.comparator.NameComparator;
import epf.domethic.ouroboros.data.ParserJSON;
import epf.domethic.ouroboros.model.Patient.Sexe;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListerPatientsFragment extends SherlockListFragment {

	public interface OnPatientSelectedListener {
		public void onPatientSelected(int position);
	}
	
	static String url = "http://raw.github.com/Mikanribu/Ouroboros/master/json_patients";
	static String urlTest ="http://api.androidhive.info/contacts/";

	private OnPatientSelectedListener listener;
	private PatientDBOpenHelper helper;
	private ListerPatientsFragment liste;

	private final static String TAG = ListerPatientsFragment.class.getSimpleName();
	private PatientAdapter adapter;

    JSONArray patients = null;
    List<Patient> patientList = new ArrayList<Patient>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		
		// Creating JSON Parser instance
        ParserJSON jParser = new ParserJSON();
        
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);        
        
        try {

            // Getting Array of Contacts
            patients = json.getJSONArray("patients");
            
             
            // looping through All Contacts
            for(int i = 0; i < patients.length(); i++){
                JSONObject c = patients.getJSONObject(i);
                 
                // Storing each json item in variable
                int id = c.getInt("id");
                String nom = c.getString("nom");
                String prenom = c.getString("prenom");
                Sexe sexe = Sexe.valueOf(c.getString("sexe"));
                Date dateNaissance = null;
				try {
					dateNaissance = Utils.parserDate(c.getString("date_naissance"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String lieuNaissance = c.getString("lieu_naissance");
                String adresse = c.getString("adresse");
                String ville = c.getString("ville");
                String codePostal = c.getString("code_postal");
                String pays = c.getString("pays");
                String nationalite = c.getString("nationalite");
                String telephone = c.getString("telephone");
                String numSS = c.getString("num_ss");
                String medecinTraitant = c.getString("medecin_traitant");
                boolean hospitalise = c.getInt("hospitalise") ==1;
                 
                Patient p = new Patient (id, nom, prenom, sexe, dateNaissance, lieuNaissance,
                					adresse, ville, codePostal, pays, nationalite, telephone, numSS,
                					medecinTraitant, hospitalise);
 
                // adding patient to ArrayList
                patientList.add(p);
                Log.v("TAG","PATIENT LIST" + patientList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
		//List<Patient> patient = Patient.ALL;
        
        //Tri des noms des patients par ordre alphabétique
        Collections.sort(patientList, new NameComparator());
		adapter = new PatientAdapter(getActivity(), patientList);
		
		setListAdapter(adapter);

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
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null) {
			listener.onPatientSelected(position);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		adapter.notifyDataSetChanged();
	}
}

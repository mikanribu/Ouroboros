package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
 
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.adapter.MenuGaucheHospiDMPAdapter;
import epf.domethic.ouroboros.dao.PatientDAO;
import epf.domethic.ouroboros.data.ParserJSON;
import epf.domethic.ouroboros.data.PatientColumns;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
 
public class ListeGaucheHospiDMPFragment extends SherlockFragment {
    private ExpandableListView mExpandableList;
    
    static String url ="https://raw.github.com/Mikanribu/Ouroboros/master/json_radios";
    JSONArray radios = null;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
    	View view = inflater.inflate(R.layout.fragment_liste_hospi_dmp,container, false);
 
        mExpandableList = (ExpandableListView)view.findViewById(R.id.menu_gauche_hospi);
        
        //Méthode temporaire pour avoir un menu propre en attendant d'aller chercher dans la bdd
        ArrayList<String> arrayNomParents = new ArrayList<String>();
        arrayNomParents.add("Analyses"); //indice 0
        arrayNomParents.add("Examens"); // indice 1
        arrayNomParents.add("Compte-rendus"); // indice 2
        arrayNomParents.add("Radiographies"); // indice 3
        arrayNomParents.add("Fiches de suivi"); // indice 4
        arrayNomParents.add("Consultations"); // indice 5
        
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();
 
        //here we set the parents and the children
        for (int i = 0; i < arrayNomParents.size(); i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent = new Parent();
            parent.setTitle(arrayNomParents.get(i));
             
            //Récupérer ici les enfants
            arrayChildren = new ArrayList<String>();
            for (int j = 0; j < 3; j++) {
                arrayChildren.add("Child " + j);
            }
            parent.setArrayChildren(arrayChildren);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MenuGaucheHospiDMPAdapter(getSherlockActivity(),arrayParents));
        
        return view;
 
    }
    
    public class Parent {
        private String mTitle;
        private ArrayList<String> mArrayChildren;
     
        public String getTitle() {
            return mTitle;
        }
     
        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
     
        public ArrayList<String> getArrayChildren() {
            return mArrayChildren;
        }
     
        public void setArrayChildren(ArrayList<String> mArrayChildren) {
            this.mArrayChildren = mArrayChildren;
        }
    }
    
	public void RecuperationJSON() {
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		// Creation d'une instance ParserJSON
        ParserJSON jParser = new ParserJSON();    
        //On récupère JSON string à partir de l'URL
        JSONObject json = jParser.getJSONFromUrl(url);        
        
        try {
            radios = json.getJSONArray("radios");
             
            // Boucle sur tous les patients du fichier JSON
            for(int i = 0; i < radios.length(); i++){
                JSONObject c = radios.getJSONObject(i);
                 
                // On récupère toutes les données qu'on stocke dans une variable
                String nom = c.getString(PatientColumns.KEY_NOM);
                String prenom = c.getString(PatientColumns.KEY_PRENOM);
                Sexe sexe = Sexe.valueOf(c.getString(PatientColumns.KEY_SEXE));
                Date dateNaissance = null;
				try {
					dateNaissance = Utils.parserDate(c.getString(PatientColumns.KEY_DATE_NAISSANCE));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String lieuNaissance = c.getString(PatientColumns.KEY_LIEU_NAISSANCE);
                String adresse = c.getString(PatientColumns.KEY_ADRESSE);
                String ville = c.getString(PatientColumns.KEY_VILLE);
                String codePostal = c.getString(PatientColumns.KEY_CODE_POSTAL);
                String pays = c.getString(PatientColumns.KEY_PAYS);
                String nationalite = c.getString(PatientColumns.KEY_NATIONALITE);
                String telephone = c.getString(PatientColumns.KEY_TELEPHONE);
                String numSS = c.getString(PatientColumns.KEY_NUMSS);
                String medecinTraitant = c.getString(PatientColumns.KEY_MEDECIN_TRAITANT);
                boolean hospitalise = c.getInt(PatientColumns.KEY_HOSPITALISE) ==1;
                 
                Patient p = new Patient (nom, prenom, sexe, dateNaissance, lieuNaissance,
                					adresse, ville, codePostal, pays, nationalite, telephone, numSS,
                					medecinTraitant, hospitalise);
                
                PatientDAO dao = new PatientDAO(this.getActivity());
                dao.ajouterPatient(p);
                dao.close();
               // patientList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Tri des noms des patients par ordre alphabétique
       // Collections.sort(patientList, new NameComparator());
	}
}
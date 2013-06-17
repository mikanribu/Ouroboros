package epf.domethic.ouroboros.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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
import epf.domethic.ouroboros.dao.RadioDAO;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.PatientColumns;
 
public class ListeGaucheHospiDMPFragment extends SherlockFragment {
    private ExpandableListView mExpandableList;
    
    static String url2 ="http://raw.github.com/Mikanribu/Ouroboros/master/json_radios";
    static String url = "http://raw.github.com/Mikanribu/Ouroboros/master/json_patients";
    JSONArray radios = null;
    
	private final static String TAG = ListerPatientsFragment.class.getSimpleName();
    
    RadioDAO dao =null;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
    	View view = inflater.inflate(R.layout.fragment_liste_hospi_dmp,container, false);
       
    	this.dao = new RadioDAO(getActivity());
 
        mExpandableList = (ExpandableListView)view.findViewById(R.id.menu_gauche_hospi);
        
        if(dao.dbIsEmpty() == true) {
			Log.v("TAG","DANS LE IF!!!!!");
			RecuperationJSON();
		}
        
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
            
            if(arrayNomParents.get(i) == "Radiographies"){
            	Cursor cursor = dao.getRadiosCursor();
            	cursor.moveToFirst();

        		while (!cursor.isAfterLast()) {
        			arrayChildren.add(cursor.getString(2));
        			cursor.moveToNext();
        		}
            }
            else {
	            for (int j = 0; j < 3; j++) {
	                arrayChildren.add("Child " + j);
	            }
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
        JSONObject json = jParser.getJSONFromUrl(url2);
        
        try {
            radios = json.getJSONArray("radios");
             
            // Boucle sur toutes les radios du fichier JSON
            for(int i = 0; i < radios.length(); i++){
                JSONObject c = radios.getJSONObject(i);
                 
                // On récupère toutes les données qu'on stocke dans une variable
                String idPatient = c.getString(DocumentColumns.KEY_ID_PATIENT);
                String nom = c.getString(DocumentColumns.KEY_NOM);
                String radio = c.getString(DocumentColumns.KEY_RADIO);
                String cause = c.getString(DocumentColumns.KEY_CAUSE);
                String d = c.getString(DocumentColumns.KEY_DATE);
               /* Date date = null;
				try {
					date = Utils.parserDate(c.getString(DocumentColumns.KEY_DATE));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				String medecin = c.getString(DocumentColumns.KEY_MEDECIN);
				String description = c.getString(DocumentColumns.KEY_DESCRIPTION);
				String interpretation = c.getString(DocumentColumns.KEY_INTERPRETATION);

                ArrayList <String> r = new ArrayList<String>();
                r.add(idPatient);
                r.add(nom);
                r.add(radio);
                r.add(cause);
                r.add(d);
                r.add(medecin);
                r.add(description);
                r.add(interpretation);
                
                dao = new RadioDAO(this.getActivity());	
                dao.ajouterRadio(r);

               // patientList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Tri des noms des patients par ordre alphabétique
       // Collections.sort(patientList, new NameComparator());
        //dao.close();
	}
}
package epf.domethic.ouroboros.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.actionbarsherlock.app.SherlockFragment;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.adapter.MenuGaucheHospiDMPAdapter;
import epf.domethic.ouroboros.dao.RadioDAO;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.ParserJSON;

public class ListeGaucheHospiDMPFragment extends SherlockFragment {
	
	//interface du listener de la radio sélectionnée
	public interface OnRadioSelectedListener {
		public void onRadioSelected(int position, Radio radio);
	}
	
	//Variables utilisées dans le fragment
	private ExpandableListView mExpandableList;
	private ListView lvlistNewDoc;
	private OnRadioSelectedListener listener;
	private Radio radio;
	private Patient patient;

	//URL pour accéder au JSON des radios et des patients
	static String url2 = "http://raw.github.com/Mikanribu/Ouroboros/master/json_radios";
	JSONArray radios = null;
	
	//Déclaration du fragment
	AfficherRadioFragment fragment_afficher_radio = new AfficherRadioFragment();
    
    RadioDAO dao =null;

    //Création de la vue
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_liste_hospi_dmp,container, false);

		//on récupère la bdd de l'activité
		this.dao = new RadioDAO(getActivity());
		mExpandableList = (ExpandableListView) view.findViewById(R.id.menu_gauche_hospi);
		
		//si l'activité est vide on récupère le JSON
		if (dao.dbIsEmpty() == true) {
			RecuperationJSON();
		}

		// Méthode temporaire pour avoir un menu propre en attendant d'aller chercher dans la bdd
		ArrayList<String> arrayNomParents = new ArrayList<String>();
		arrayNomParents.add("Analyses"); // indice 0
		arrayNomParents.add("Examens"); // indice 1
		arrayNomParents.add("Compte-rendus"); // indice 2
		arrayNomParents.add("Radiographies"); // indice 3
		arrayNomParents.add("Fiches de suivi"); // indice 4
		arrayNomParents.add("Consultations"); // indice 5

		//Tableaux des noms des parents et de leurs enfants
		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<String> arrayChildren = new ArrayList<String>();

		// Implémentation des parents et leurs enfants
		for (int i = 0; i < arrayNomParents.size(); i++) {
			
			Parent parent = new Parent();
			parent.setTitle(arrayNomParents.get(i)); //titre associé aux parents

			// Récupérer ici les enfants
			arrayChildren = new ArrayList<String>();

			//depuis la bdd récupère les enfants
			if (arrayNomParents.get(i) == "Radiographies" ) {
				Cursor cursor = dao.getRadiosCursor();
				cursor.moveToFirst();
				
				if (cursor.getInt(1) == patient.getId() ) {
					while (!cursor.isAfterLast()) {
						arrayChildren.add(cursor.getString(2));
						
						cursor.moveToNext();
					}
				}
			} 
			parent.setArrayChildren(arrayChildren);
			arrayParents.add(parent);

		}
		
		// set l'adapter
		mExpandableList.setAdapter(new MenuGaucheHospiDMPAdapter(getSherlockActivity(), arrayParents));

		//listener de l'expandable liste
		mExpandableList.setOnChildClickListener(new OnChildClickListener() {
			@Override
			//sur click d'un enfant
			public boolean onChildClick(ExpandableListView parent, View v,int group_position, int child_position, long id) {

				//sélection de la radio du thorax 
				if (group_position == 3 && child_position == 1) {
					Cursor cursor = dao.getRadiosCursor(child_position);
					cursor.moveToFirst();
					radio = dao.getRadio(cursor);
					listener.onRadioSelected(child_position, radio);
					
				}
				return false;
			}
		});
		
		//listener des parents
		mExpandableList.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            //action après un click sur un parent
            public boolean onGroupClick(ExpandableListView parent, View v, int group_position, long id) {
                //Log.d("group click", mTag);
            	
                return false;
            }
        });

		//instancie la list view contenant l'élément nouveau document
		lvlistNewDoc = (ListView)view.findViewById(R.id.liste_new_doc);
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        map = new HashMap<String, String>();
        map.put("titre", "Nouveau Document");
        map.put("img", String.valueOf(R.drawable.logo_new_doc));
        listItem.add(map);
        
      //Création d'un SimpleAdapter qui se chargera de mettre les items présents dans notre liste (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getBaseContext(), listItem, R.layout.menu_infos_dmp_elements,
               new String[] {"img", "titre", "description"}, new int[] {R.id.ivImage, R.id.tvTitre});
 
        //On attribue à notre listView l'adapter que l'on vient de créer
        lvlistNewDoc.setAdapter(mSchedule);
 
        //Enfin on met un listener sur notre listView
        lvlistNewDoc.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) lvlistNewDoc.getItemAtPosition(position);
        	}
         });
		return view;

	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (OnRadioSelectedListener) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

	public void RecuperationJSON() {

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// Creation d'une instance ParserJSON
		ParserJSON jParser = new ParserJSON();
		// On récupère JSON string à partir de l'URL
		JSONObject json = jParser.getJSONFromUrl(url2);

		try {
			radios = json.getJSONArray("radios");

			// Boucle sur toutes les radios du fichier JSON
			for (int i = 0; i < radios.length(); i++) {
				JSONObject c = radios.getJSONObject(i);

				// On récupère toutes les données qu'on stocke dans une variable
				String idPatient = c.getString(DocumentColumns.KEY_ID_PATIENT);
				String nom = c.getString(DocumentColumns.KEY_NOM);
				String radio = c.getString(DocumentColumns.KEY_RADIO);
				String cause = c.getString(DocumentColumns.KEY_CAUSE);
				String d = c.getString(DocumentColumns.KEY_DATE);
				/*
				 * Date date = null; try { date =
				 * Utils.parserDate(c.getString(DocumentColumns.KEY_DATE)); }
				 * catch (ParseException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 */
				String medecin = c.getString(DocumentColumns.KEY_MEDECIN);
				String description = c
						.getString(DocumentColumns.KEY_DESCRIPTION);
				String interpretation = c
						.getString(DocumentColumns.KEY_INTERPRETATION);

				ArrayList<String> r = new ArrayList<String>();
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
			}
			dao.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
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
	
	public Patient getPatient(Patient patient){
		this.patient=patient;
		return patient;
	}
}
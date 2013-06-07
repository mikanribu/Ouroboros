package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DMPFragment extends Fragment{

	//Lors de la création du fragment
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	     Context appContext = getActivity().getApplicationContext();
	     ActionBar actionBar = getActivity().getActionBar();
	     
	     //-------------------------Initialisation des onglets----------------------------
	    
	     //Création des fragments associés aux onglets
	     //Création des onglets et des écouteurs associés
	     
	     //Informations générales
		 InformationsGeneralesFragment infoFragment = new InformationsGeneralesFragment(); 
		 ActionBar.Tab infoTab = actionBar.newTab().setText("Informations Générales");
		 infoTab.setTabListener(new MyTabsListener(infoFragment));
		 
		 //Historique
		 HistoriqueFragment histoFragment = new HistoriqueFragment(); 
		 ActionBar.Tab histoTab = actionBar.newTab().setText("Historique");
		 histoTab.setTabListener(new MyTabsListener(histoFragment));
		 
		 //Hospitalisation en cours
		 HospitalisationEnCoursFragment hospiFragment = new HospitalisationEnCoursFragment(); 
		 ActionBar.Tab hospiTab = actionBar.newTab().setText("Hospitalisation en Cours");
		 hospiTab.setTabListener(new MyTabsListener(hospiFragment));
		 
		 //Codification
		 CodificationFragment codiFragment = new CodificationFragment(); 
		 ActionBar.Tab codiTab = actionBar.newTab().setText("Codification");
		 codiTab.setTabListener(new MyTabsListener(codiFragment));
		 
		 actionBar.addTab(infoTab);
		 actionBar.addTab(histoTab);
		 actionBar.addTab(hospiTab);
		 actionBar.addTab(codiTab);

	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.removeAllTabs();
		
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_vue_ensemble_dmp,container, false);
		 
		 
		return view;
	 }
	
	
}

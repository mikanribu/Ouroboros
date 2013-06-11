package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.listener.MyTabsListener;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InformationsGeneralesFragment extends Fragment{
	
	HospitalisationEnCoursFragment fragment_hospi_en_cours = new HospitalisationEnCoursFragment();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();

		// --------------------Initialisation des onglets----------------------------

		// Création des fragments associés aux onglets
		// Création des onglets et des écouteurs associés

		// Informations
		InformationsGeneralesFragment infosGFragment = new InformationsGeneralesFragment();
		ActionBar.Tab infosGTab = actionBar.newTab().setText("Informations");
		infosGTab.setTabListener(new MyTabsListener(infosGFragment));

		// Hospitalisation
		HospitalisationEnCoursFragment hospiFragment = new HospitalisationEnCoursFragment();
		ActionBar.Tab hospiTab = actionBar.newTab().setText("Hospitalisation");
		hospiTab.setTabListener(new MyTabsListener(hospiFragment));

		// Historique
		HistoriqueFragment historiqueFragment = new HistoriqueFragment();
		ActionBar.Tab historiqueTab = actionBar.newTab().setText("Historique");
		historiqueTab.setTabListener(new MyTabsListener(historiqueFragment));

		// Codification
		CodificationFragment codiFragment = new CodificationFragment();
		ActionBar.Tab codiTab = actionBar.newTab().setText("Codification");
		codiTab.setTabListener(new MyTabsListener(codiFragment));

		// Ajout des onglets à la barre d'action
		actionBar.addTab(infosGTab);
		actionBar.addTab(hospiTab);
		actionBar.addTab(historiqueTab);
		actionBar.addTab(codiTab);
		
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.removeAllTabs();

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_infos_g,container, false);
		return view;
	}
	
}



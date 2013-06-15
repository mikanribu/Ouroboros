package epf.domethic.ouroboros.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import epf.domethic.ouroboros.R;

public class RechercheGeneraleFragment extends Fragment {

//	@Override
//	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//	super.onCreate(savedInstanceState);
	//	ActionBar actionBar = getActivity().getActionBar();

//		// --------------------Initialisation des onglets----------------------------
//
//		// Création des fragments associés aux onglets
//		// Création des onglets et des écouteurs associés
//
//		// Recherche générale
//		RechercheGeneraleFragment rechercheGFragment = new RechercheGeneraleFragment();
//		ActionBar.Tab rechercheGTab = actionBar.newTab().setText("Recherche Générale");
//		rechercheGTab.setTabListener(new MyTabsListener());
//
//		// Recherche médicale
//		RechercheMedicaleFragment rechercheMedicaleFragment = new RechercheMedicaleFragment();
//		ActionBar.Tab rechercheMedTab = actionBar.newTab().setText("Recherche Médicale");
//		rechercheMedTab.setTabListener(new MyTabsListener());
//
//		// Dossiers Transférés
//		DossiersTransferesFragment dossiersTransfereFragment = new DossiersTransferesFragment();
//		ActionBar.Tab dossTransTab = actionBar.newTab().setText("Dossiers Transférés");
//		dossTransTab.setTabListener(new MyTabsListener());
//
//		// Codification
//		CodificationFragment codiFragment = new CodificationFragment();
//		ActionBar.Tab codiTab = actionBar.newTab().setText("Codification");
//		codiTab.setTabListener(new MyTabsListener(codiFragment));
//
//		// Ajout des onglets à la barre d'action
//		actionBar.addTab(rechercheGTab);
//		actionBar.addTab(rechercheMedTab);
//		actionBar.addTab(dossTransTab);
//	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.removeAllTabs();

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_recherche_patients,container, false);
		return view;
	}
	
}

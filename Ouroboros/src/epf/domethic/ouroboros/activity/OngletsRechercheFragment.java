package epf.domethic.ouroboros.activity;

import java.util.ArrayList;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.CodificationFragment;
import epf.domethic.ouroboros.activity.HistoriqueFragment;
import epf.domethic.ouroboros.activity.HospitalisationEnCoursFragment;
import epf.domethic.ouroboros.activity.InformationsGeneralesFragment;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment;
import epf.domethic.ouroboros.activity.ListeGaucheInfosDMPFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;


public class OngletsRechercheFragment extends SherlockFragment implements ActionBar.TabListener {
	
	RechercheGeneraleFragment recherche_g = new RechercheGeneraleFragment();
	RechercheMedicaleFragment recherche_med = new RechercheMedicaleFragment();
	DossiersTransferesFragment dossiers_transf = new DossiersTransferesFragment();
	private ArrayList<SherlockFragment> listeOnglets = new ArrayList<SherlockFragment>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
		ActionBar.Tab tab_rech_g = getSherlockActivity().getSupportActionBar().newTab();
		tab_rech_g.setText("Recherche générale");
		tab_rech_g.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_rech_g);
	    
	    ActionBar.Tab tab_rech_med = getSherlockActivity().getSupportActionBar().newTab();
	    tab_rech_med.setText("Recherche Médicale");
	    tab_rech_med.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_rech_med);
	    
	    ActionBar.Tab tab_dossiers = getSherlockActivity().getSupportActionBar().newTab();
	    tab_dossiers.setText("Dossiers transférés");
	    tab_dossiers.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_dossiers);
	    
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
		
		listeOnglets.clear();
		int position =tab.getPosition();
		switch(position)
    	{  
			case 1:
				fragmentTransaction.replace(R.id.tiers, recherche_med);
				listeOnglets.add(recherche_med);
				break;  
				
			case 2:
				fragmentTransaction.replace(R.id.tiers, dossiers_transf);
				listeOnglets.add(dossiers_transf);
				break;  

			default:
				fragmentTransaction.replace(R.id.tiers, recherche_g);
				listeOnglets.add(recherche_g);
				break;            
    	}
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
	}
	
	public void onDetach() {
		// TODO Auto-generated method stub
		getSherlockActivity().getSupportActionBar().removeAllTabs();
		
		FragmentManager manager = getSherlockActivity().getSupportFragmentManager();  
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        
        for(int i=0; i<listeOnglets.size(); i++)
        	fragmentTransaction.remove(listeOnglets.get(i));
        
        fragmentTransaction.commit();
		super.onDetach();
		
	}
	
	
}
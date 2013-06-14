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


public class OngletsDMPFragment extends SherlockFragment implements ActionBar.TabListener {
	
	InformationsGeneralesFragment infos_g = new InformationsGeneralesFragment();
	HospitalisationEnCoursFragment hospitalisation = new HospitalisationEnCoursFragment();
	HistoriqueFragment historique = new HistoriqueFragment();
	CodificationFragment codification = new CodificationFragment();
	ListeGaucheHospiDMPFragment menu_hospi = new ListeGaucheHospiDMPFragment();	
	ListeGaucheInfosDMPFragment menu_infos = new ListeGaucheInfosDMPFragment();
	private ArrayList<SherlockFragment> listeOnglets = new ArrayList<SherlockFragment>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
		ActionBar.Tab tab_infos = getSherlockActivity().getSupportActionBar().newTab();
		tab_infos.setText("Informations");
		tab_infos.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_infos);
	    
	    ActionBar.Tab tab_hospi = getSherlockActivity().getSupportActionBar().newTab();
	    tab_hospi.setText("Hospitalisation");
	    tab_hospi.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_hospi);
	    
	    ActionBar.Tab tab_histo = getSherlockActivity().getSupportActionBar().newTab();
	    tab_histo.setText("Historique");
	    tab_histo.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_histo);
	    
	    ActionBar.Tab tab_code = getSherlockActivity().getSupportActionBar().newTab();
	    tab_code.setText("Codification");
	    tab_code.setTabListener(this);
	    getSherlockActivity().getSupportActionBar().addTab(tab_code);      
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
		
		int position =tab.getPosition();
		switch(position)
    	{  
			case 1:
				listeOnglets.clear();
				fragmentTransaction.replace(R.id.deuxTiers, hospitalisation);
				listeOnglets.add(hospitalisation);
				fragmentTransaction.replace(R.id.tiers, menu_hospi);
				listeOnglets.add(menu_hospi);
				break;  
				
			case 2:
				for(int i=0; i<listeOnglets.size(); i++)
					fragmentTransaction.remove(listeOnglets.get(i));
				listeOnglets.clear();
				fragmentTransaction.replace(R.id.deuxTiers, historique);
				listeOnglets.add(historique);
				break;
				
			case 3:
				for(int i=0; i<listeOnglets.size(); i++)
					fragmentTransaction.remove(listeOnglets.get(i));
				listeOnglets.clear();
				fragmentTransaction.replace(R.id.deuxTiers, codification);
				listeOnglets.add(codification);
				break;
				
			default:
				fragmentTransaction.replace(R.id.deuxTiers, infos_g);
				listeOnglets.add(infos_g);
				fragmentTransaction.replace(R.id.tiers, menu_infos);
				listeOnglets.add(menu_infos);				
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
		
		FragmentTransaction fragmentTransaction = getSherlockActivity().getSupportFragmentManager().beginTransaction();
		for(int i=0; i<listeOnglets.size(); i++)
			fragmentTransaction.remove(listeOnglets.get(i));		 
		fragmentTransaction.commit();
        listeOnglets.clear();
       
		super.onDetach();
	}
	
}
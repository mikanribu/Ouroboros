package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.CodificationFragment;
import epf.domethic.ouroboros.activity.HistoriqueFragment;
import epf.domethic.ouroboros.activity.HospitalisationEnCoursFragment;
import epf.domethic.ouroboros.activity.InformationsGeneralesFragment;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment;
import epf.domethic.ouroboros.activity.ListeGaucheInfosDMPFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;


public class MenuDMPFragment extends SherlockFragment implements ActionBar.TabListener {
	
	InformationsGeneralesFragment fragment_infos = new InformationsGeneralesFragment();
	HospitalisationEnCoursFragment fragment_hospi = new HospitalisationEnCoursFragment();
	HistoriqueFragment fragment_histo = new HistoriqueFragment();
	CodificationFragment fragment_code = new CodificationFragment();
	ListeGaucheHospiDMPFragment fragment_menu_hospi = new ListeGaucheHospiDMPFragment();	
	ListeGaucheInfosDMPFragment fragment_menu_infos = new ListeGaucheInfosDMPFragment();
	
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
				fragmentTransaction.replace(R.id.deuxTiers, fragment_hospi);
				fragmentTransaction.replace(R.id.tiers, fragment_menu_hospi);
				break;       

			default:
				fragmentTransaction.replace(R.id.deuxTiers, fragment_infos);
				fragmentTransaction.replace(R.id.tiers, fragment_menu_infos);
				break;            
    	}
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	//	ft.remove(mFragment); //
		
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
	}
	
	public void onDetach() {
		// TODO Auto-generated method stub
		getSherlockActivity().getSupportActionBar().removeAllTabs();
		super.onDetach();
		
	}
	
	
}
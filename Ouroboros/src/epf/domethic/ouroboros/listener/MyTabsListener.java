package epf.domethic.ouroboros.listener;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.CodificationFragment;
import epf.domethic.ouroboros.activity.HistoriqueFragment;
import epf.domethic.ouroboros.activity.HospitalisationEnCoursFragment;
import epf.domethic.ouroboros.activity.InformationsGeneralesFragment;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment;
import epf.domethic.ouroboros.activity.ListeGaucheInfosDMPFragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;


public class MyTabsListener implements ActionBar.TabListener {
	
	InformationsGeneralesFragment fragment_infos = new InformationsGeneralesFragment();
	HospitalisationEnCoursFragment fragment_hospi = new HospitalisationEnCoursFragment();
	HistoriqueFragment fragment_histo = new HistoriqueFragment();
	CodificationFragment fragment_code = new CodificationFragment();
	ListeGaucheHospiDMPFragment fragment_menu_hospi = new ListeGaucheHospiDMPFragment();	
	ListeGaucheInfosDMPFragment fragment_menu_infos = new ListeGaucheInfosDMPFragment();
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
		//popBackStack();
		switch(tab.getPosition())
    	{
//			case 0:
//			    	fragmentTransaction.replace(R.id.deuxTiers, fragment_infos);
//			    	fragmentTransaction.replace(R.id.tiers, fragment_menu_infos);
//	 	    break;       
		
			case 1:
					fragmentTransaction.replace(R.id.deuxTiers, fragment_hospi);
					fragmentTransaction.replace(R.id.tiers, fragment_menu_hospi);
    	    break;       

			default:
    	    /*Action*/;            
    	}
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
}
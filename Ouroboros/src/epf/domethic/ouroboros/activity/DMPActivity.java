package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import epf.domethic.ouroboros.R;

public class DMPActivity extends SherlockFragmentActivity implements TabListener {
	
	InformationsGeneralesFragment infos = new InformationsGeneralesFragment();
	HospitalisationEnCoursFragment hospitalisation = new HospitalisationEnCoursFragment();
	HistoriqueFragment historique = new HistoriqueFragment();
	CodificationFragment codification = new CodificationFragment();
	ListeGaucheHospiDMPFragment menu_hospi = new ListeGaucheHospiDMPFragment();	
	ListeGaucheInfosDMPFragment menu_infos = new ListeGaucheInfosDMPFragment();
	private ArrayList<SherlockFragment> listeOnglets = new ArrayList<SherlockFragment>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		setContentView(R.layout.activity_dmp);
		
		Resources r = getResources();
		Drawable myDrawable = r.getDrawable(R.drawable.barre_haut);	
			
		ActionBar.Tab tab_infos = getSupportActionBar().newTab();
		tab_infos.setText("Informations");
		tab_infos.setTabListener(this);
	    getSupportActionBar().addTab(tab_infos);
	    
	    ActionBar.Tab tab_hospi = getSupportActionBar().newTab();
	    tab_hospi.setText("Hospitalisation");
	    tab_hospi.setTabListener(this);
	    getSupportActionBar().addTab(tab_hospi);
	    
	    ActionBar.Tab tab_histo = getSupportActionBar().newTab();
	    tab_histo.setText("Historique");
	    tab_histo.setTabListener(this);
	    getSupportActionBar().addTab(tab_histo);
	    
	    ActionBar.Tab tab_code = getSupportActionBar().newTab();
	    tab_code.setText("Codification");
	    tab_code.setTabListener(this);
	    getSupportActionBar().addTab(tab_code);
	   
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub
		
		int position =tab.getPosition();
		switch(position)
    	{  
			case 1:
				listeOnglets.clear();
				fragmentTransaction.replace(R.id.detail, hospitalisation);
				listeOnglets.add(hospitalisation);
				fragmentTransaction.replace(R.id.menu_gauche, menu_hospi);
				listeOnglets.add(menu_hospi);
				break;
				
			case 2:
				for(int i=0; i<listeOnglets.size(); i++){
		        	fragmentTransaction.remove(listeOnglets.get(i));
		        }
				listeOnglets.clear();
				fragmentTransaction.add(R.id.menu_gauche, historique);
				listeOnglets.add(historique);
				break;
				
			case 3:
				for(int i=0; i<listeOnglets.size(); i++){
		        	fragmentTransaction.remove(listeOnglets.get(i));
		        }
				listeOnglets.clear();
				fragmentTransaction.add(R.id.menu_gauche, codification);
				listeOnglets.add(codification);
				break;

			default:
				listeOnglets.clear();
				fragmentTransaction.replace(R.id.detail, infos);
				listeOnglets.add(infos);
				fragmentTransaction.replace(R.id.menu_gauche, menu_infos);
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
	

}

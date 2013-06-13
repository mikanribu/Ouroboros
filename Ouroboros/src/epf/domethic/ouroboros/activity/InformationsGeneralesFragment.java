package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import epf.domethic.ouroboros.listener.MyTabsListener;
import epf.domethic.ouroboros.R;

public class InformationsGeneralesFragment extends SherlockFragment{
	
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
   		View view = inflater.inflate(R.layout.fragment_infos_g,container, false);
   		
   		getSherlockActivity().getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

   		ActionBar.Tab tab_infos = getSherlockActivity().getSupportActionBar().newTab();
   		tab_infos.setText("Informations");
   		tab_infos.setTabListener(new MyTabsListener());
        getSherlockActivity().getSupportActionBar().addTab(tab_infos);
        
        ActionBar.Tab tab_hospi = getSherlockActivity().getSupportActionBar().newTab();
        tab_hospi.setText("Hospitalisation");
        tab_hospi.setTabListener(new MyTabsListener());
        getSherlockActivity().getSupportActionBar().addTab(tab_hospi);
        
        ActionBar.Tab tab_histo = getSherlockActivity().getSupportActionBar().newTab();
        tab_histo.setText("Historique");
        tab_histo.setTabListener(new MyTabsListener());
        getSherlockActivity().getSupportActionBar().addTab(tab_histo);
        
        ActionBar.Tab tab_code = getSherlockActivity().getSupportActionBar().newTab();
        tab_code.setText("Codification");
        tab_code.setTabListener(new MyTabsListener());
        getSherlockActivity().getSupportActionBar().addTab(tab_code);        
        
   		return view;
       }


}

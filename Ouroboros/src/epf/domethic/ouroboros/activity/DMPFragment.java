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

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_vue_ensemble_dmp,container, false);
		 
	     Context appContext = getActivity().getApplicationContext();
	     ActionBar actionBar = getActivity().getActionBar();
		 RecherchePatientFragment RechercheFragment = new RecherchePatientFragment(); 
		 ActionBar.Tab RechercheTab = actionBar.newTab().setText("Recherche");
		 RechercheTab.setTabListener(new MyTabsListener(RechercheFragment));
		 actionBar.addTab(RechercheTab);
		return view;
	 }
	
	class MyTabsListener implements ActionBar.TabListener {
		public android.support.v4.app.Fragment fragment;
		
		public MyTabsListener(android.support.v4.app.Fragment fragment) {
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	}
}

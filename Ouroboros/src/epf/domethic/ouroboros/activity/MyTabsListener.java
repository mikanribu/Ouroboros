package epf.domethic.ouroboros.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;

public class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;
	
	public MyTabsListener(Fragment fragment) {
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
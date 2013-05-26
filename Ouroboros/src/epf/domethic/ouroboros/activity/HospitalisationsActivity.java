package epf.domethic.ouroboros.activity;

import java.util.List;


import epf.domethic.ouroboros.widget.AnimationLayout;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.adapter.PatientAdapter;
import epf.domethic.ouroboros.model.Patient;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

public class HospitalisationsActivity extends Activity implements
		AnimationLayout.Listener {
	/** Called when the activity is first created. */

	// The menu configuration
	private int screenWidth;// The size of the screen

	public final static String TAG = "Demo";

	protected LinearLayout mList;
	protected AnimationLayout mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospitalisations);

		ColorDrawable colorDrawable = new ColorDrawable();
		ActionBar actionBar = getActionBar();
		colorDrawable.setColor(0xff7184fa);
		actionBar.setBackgroundDrawable(colorDrawable);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true); // The icone_launcher will not go
												// back automatically to home (API min 14)

		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);
		mList = (LinearLayout) findViewById(R.id.slideMenu);
		
		List<Patient> patients = Patient.ALL; 
/*		PatientAdapter aa = new PatientAdapter(this, patients); 
	    setListAdapter(aa);*/
		
		ListView liste = (ListView) findViewById(R.id.list);
		PatientAdapter aa = new PatientAdapter(this, patients); 
	    liste.setAdapter(aa);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the options menu from XML
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.accueil, menu);

		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(
				R.id.action_recherche).getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false); // Do not iconify the widget;
													// expand it by default

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if(menuItem.getItemId()== android.R.id.home){
			mLayout.toggleSidebar();
		}
		return (super.onOptionsItemSelected(menuItem));
	}

	public void onClickContentButton(View v) {
		mLayout.toggleSidebar();
	}

	@Override
	public void onBackPressed() {
		if (mLayout.isOpening()) {
			mLayout.closeSidebar();
		} else {
			finish();
		}
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarOpened() {
		Log.d(TAG, "opened");
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarClosed() {
		Log.d(TAG, "closed");
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public boolean onContentTouchedWhenOpening() {
		// the content area is touched when sidebar opening, close sidebar
		Log.d(TAG, "going to close sidebar");
		mLayout.closeSidebar();
		return true;
	}



}

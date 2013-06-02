package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.activity.ListerPatientsFragment.OnPatientSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.widget.AnimationLayout;
import epf.domethic.ouroboros.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class HospitalisationsActivity extends FragmentActivity implements
		AnimationLayout.Listener, OnPatientSelectedListener {
	/** Called when the activity is first created. */

	private int position;
	
	
	public final static String TAG = "Demo";

	protected LinearLayout mList;
	protected AnimationLayout mLayout;
	private TextView tvRecherche;
	private TextView tvArchives;

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

		mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);
		
		/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		   ListerPatientsFragment listFragment = new ListerPatientsFragment();
		   ft.add(R.id.patient_list, listFragment, "List_Fragment");
		   ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		   ft.commit();	*/

	
	    tvRecherche = (TextView) findViewById(R.id.bRecherche);	
	    tvRecherche.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		//Intent intent_recherche = new Intent(HospitalisationsActivity.this, RecherchePatientActivity.class);
	    		//startActivity(intent_recherche);
	    	/*	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	    		ft.remove(fpatient);
	    		ft.commit();
	    		
	    		FragmentManager fragmentManager = getFragmentManager()
	    				// Or: FragmentManager fragmentManager = getSupportFragmentManager()
	    		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    				ExampleFragment fragment = new ExampleFragment();
	    				fragmentTransaction.add(R.id.fragment_container, fragment);
	    				fragmentTransaction.commit();*/
	    	    		
	    	}
	    });
	    
	    tvArchives = (TextView) findViewById(R.id.bArchives);
		
	    tvArchives.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		Intent intent_archives = new Intent(HospitalisationsActivity.this, ArchivesActivity.class);
	    		startActivity(intent_archives);
	    	}
	    });
	}

	@Override
	public void onPatientSelected(int position) {
		this.position = position;
		AfficherPatientFragment detailFragment = (AfficherPatientFragment)getSupportFragmentManager().findFragmentById(R.id.patient_detail);
		Patient patient = Patient.ALL.get(position);
		detailFragment.afficherPatient(patient);

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

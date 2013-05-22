package epf.domethic.ouroboros.activity;

import java.util.List;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.adapter.PatientAdapter;
import epf.domethic.ouroboros.animations.CollapseAnimation;
import epf.domethic.ouroboros.animations.ExpandAnimation;
import epf.domethic.ouroboros.model.Patient;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

public class HospitalisationsActivity extends Activity {
	/** Called when the activity is first created. */

	// The menu configuration
	private LinearLayout MenuList; // The layout where the menu is written in
									// activity_main.xml
	private int screenWidth;// The size of the screen
	private boolean isExpanded;// The configuration of the menu: collapsed or
								// expanded

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
												// back automatically to home.
												// (API min 14)

		MenuList = (LinearLayout) findViewById(R.id.slideMenu);// rely the menu
																// to the
																// corresponding
																// layout

		// get screen size
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth = metrics.widthPixels;// Get the horizontal size of the
											// screen who uses the application

		List<Patient> patients = Patient.ALL;
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
		switch (menuItem.getItemId()) {
		case android.R.id.home:// When the user click on the icone_launcher
			if (isExpanded) {// If the menu is visible
				isExpanded = false;
				// 0,12 corresponds to the size of the menu compare to the
				// screen
				// Move MenuList from 12% of the screenwidth to 0 in 20.
				MenuList.startAnimation(new CollapseAnimation(MenuList, 0,
						(int) (screenWidth * 0.12), 20));
			} else {// If the menu is invisible
				isExpanded = true;
				// Move MenuList from 0 to 12% of the screenwidth in 20.
				MenuList.startAnimation(new ExpandAnimation(MenuList, 0,
						(int) (screenWidth * 0.12), 20));
			}
			return true;
		}
		return (super.onOptionsItemSelected(menuItem));
	}

}

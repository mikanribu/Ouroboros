package epf.domethic.ouroboros.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
<<<<<<< HEAD
=======
import com.actionbarsherlock.view.SubMenu;

>>>>>>> b994974a71c279809e6bdaae9114f41fac4db962
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListerPatientsFragment.OnPatientSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.widget.AnimationLayout;

public class HospitalisationsActivity extends SherlockFragmentActivity implements
		AnimationLayout.Listener, OnPatientSelectedListener {
	/** Called when the activity is first created. */

	private int position;
	public static Context appContext;

	public final static String TAG = "Demo";
	protected LinearLayout mList;
	protected AnimationLayout mLayout;
	private TextView tvDeconnexion;
	private TextView tvRecherche;
	private TextView tvArchives;
	private TextView tvHospitalisation;
	ListerPatientsFragment liste_patients = new ListerPatientsFragment();
	AfficherPatientFragment detail_patient = new AfficherPatientFragment();
	OngletsRechercheFragment recherche = new OngletsRechercheFragment();
	AfficherRadioFragment radio = new AfficherRadioFragment();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospitalisations);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Resources r = getResources();
		Drawable myDrawable = r.getDrawable(R.drawable.barre_haut_bleue);
		getSupportActionBar().setBackgroundDrawable(myDrawable);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().setHomeButtonEnabled(true); // The icone_launcher will not go
												// back automatically to home
												// (API min 14)

		// Pas d'affichage du nom de l'application dans la barre d'action
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);

		FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = manager.beginTransaction();
		
		fragmentTransaction.add(R.id.tiers, liste_patients);
		fragmentTransaction.add(R.id.deuxTiers, detail_patient);
		fragmentTransaction.addToBackStack("liste_hospitalisations");
		fragmentTransaction.commit();
		
		tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
		final Intent intent_connexion = new Intent(HospitalisationsActivity.this, ConnexionActivity.class);
		tvDeconnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_connexion);
			}
		});

		tvRecherche = (TextView) findViewById(R.id.tvRecherche);
		tvRecherche.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
				String str = manager.getBackStackEntryAt(0).getName();

				if (str != "recherche") {
					FragmentTransaction fragmentTransaction = manager.beginTransaction();
					manager.popBackStackImmediate();
					fragmentTransaction.replace(R.id.tiers, recherche);
					fragmentTransaction.addToBackStack("recherche");
					fragmentTransaction.commit();
				}
				
			}
		});

		tvHospitalisation = (TextView) findViewById(R.id.tvHospitalisation);
		tvHospitalisation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
				String str = manager.getBackStackEntryAt(0).getName();
				
				if (str != "liste_hospitalisations") {
					FragmentTransaction fragmentTransaction = manager.beginTransaction();
					manager.popBackStackImmediate();
					fragmentTransaction.replace(R.id.tiers, liste_patients);
					fragmentTransaction.replace(R.id.deuxTiers, detail_patient);					
					fragmentTransaction.addToBackStack("liste_hospitalisations");
					fragmentTransaction.commit();
				}
			}
		});

		tvArchives = (TextView) findViewById(R.id.tvArchives);
		tvArchives.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
				String str = manager.getBackStackEntryAt(0).getName();

				if (str != "fragment_radio") {
					FragmentTransaction fragmentTransaction = manager.beginTransaction();
					manager.popBackStackImmediate();
					fragmentTransaction.replace(R.id.deuxTiers,radio);
					fragmentTransaction.addToBackStack("fragment_radio");
					fragmentTransaction.commit();
				}

			}
		});
	}

	@Override
	public void onPatientSelected(int position) {
		this.position = position;
		Patient patient = liste_patients.patientList.get(position);
		detail_patient.afficherPatient(patient);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the options menu from XML
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.accueil, menu);		
		
		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_recherche).getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false); // Do not iconify the widget;
													// expand it by default

		return true;
	}
<<<<<<< HEAD
=======
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
        builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }

>>>>>>> b994974a71c279809e6bdaae9114f41fac4db962
					

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getItemId() == android.R.id.home) {
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

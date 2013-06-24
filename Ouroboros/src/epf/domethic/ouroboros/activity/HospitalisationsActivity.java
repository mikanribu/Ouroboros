package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListerPatientsFragment.OnPatientSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.widget.AnimationLayout;

public class HospitalisationsActivity extends FragmentActivity implements AnimationLayout.Listener, OnPatientSelectedListener {
	/** Called when the activity is first created. */

	private int position;
	public static Context appContext;

	public final static String TAG = "Demo";
	protected LinearLayout mList;
	protected AnimationLayout mLayout;

	// Contenu du menu
	private TextView tvFonction;
	private TextView tvDeconnexion;
	private TextView tvRecherche;
	private TextView tvCreationDossier;
	private TextView tvAjoutDoc;
	private TextView tvTransfert;
	private TextView tvCodification;
	private TextView tvArchives;
	private TextView tvMonCompte;
	private TextView tvHospitalisation;	

	// Boîte de dialogue pour les fonctions non implémentées
	AlertDialog.Builder boite;

	//Déclaration des fragments
	AfficherPatientFragment detail_patient = new AfficherPatientFragment();
	ListerPatientsFragment liste_patients = new ListerPatientsFragment();
	
	public int fonction = 0;
	
	JSONArray personnes = null;

	// url ou l'on peut accéder au JSON des sécrétaires médicales.
	static String urlsecMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_secretaires_med";

	// url ou l'on peut accéder au JSON des médecins.
	static String urlMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_medecins";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospitalisations);
		
		ActionBar actionBar = getActionBar();
		Resources r = getResources();
		Drawable myDrawable;

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true); // The icone_launcher will not
												// go
												// back automatically to
												// home
												// (API min 14)

		// Pas d'affichage du nom de l'application dans la barre d'action
		actionBar.setDisplayShowTitleEnabled(false);

		mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);

		
		// Création de la boîte de dialogue qui sera affichée lorsque l'utilisateur cliquera sur des boutons du menu non développés
		ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.ThemeHoloDialog );
		boite = new AlertDialog.Builder(ctw);
		boite.setTitle("La fonction n'est pas encore implémentée!");
		boite.setIcon(R.drawable.travaux);
		boite.setMessage("Cette fonction n'a pas été développée dans cette version.");
		boite.setNegativeButton("Retour", null);

		// Déconnecter l'utilisateur
		tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
		final Intent intent_connexion = new Intent(
				HospitalisationsActivity.this, ConnexionActivity.class);
		tvDeconnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_connexion);
			}
		});

		if(fonction == 0){
			Intent intent = getIntent ();
			
			// Récuperer le pseudo entré en connexion
			String nom = intent.getStringExtra("nom");
			String prenom = intent.getStringExtra("prenom");
									
			if(intent.getStringExtra("fonction")!=null){
				fonction = Integer.parseInt(intent.getStringExtra("fonction"));
			}
			Log.v(TAG, nom + prenom + fonction);
				   
			// Si l'utilisateur est un médecin.
			if (fonction == 1) {
				setContentView(R.layout.activity_hospitalisations);
	
				myDrawable = r.getDrawable(R.drawable.barre_haut);
				actionBar.setBackgroundDrawable(myDrawable);
				
				// Fonction Recherche non implémentée: renvoie vers la boite de dialogue
				tvRecherche = (TextView) findViewById(R.id.tvRecherche);
				tvRecherche.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boite.show();
					}
				});
	
				// Fonction Création dossier non implémentée: renvoie vers la boite de dialogue
				tvCreationDossier = (TextView) findViewById(R.id.tvCreationDossier);
				tvCreationDossier.setOnClickListener(new View.OnClickListener() {
	
					@Override
					public void onClick(View v) {
						boite.show();
					}
				});
	
				// Fonction Ajout document non implémentée: renvoie vers la boite de dialogue
				tvAjoutDoc = (TextView) findViewById(R.id.tvAjoutDocument);
				tvAjoutDoc.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boite.show();
					}
				});
	
				// Fonction Transfert non implémentée: renvoie vers la boite de dialogue
				tvTransfert = (TextView) findViewById(R.id.tvTransfert);
				tvTransfert.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						boite.show();
					}
				});
	
				// Fonction A codifier non implémentée: renvoie vers la boite de dialogue
				tvCodification = (TextView) findViewById(R.id.tvACodifier);
				tvCodification.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boite.show();
					}
	
				});
				
				// Fonction Archives non implémentée: renvoie vers la boite de dialogue
				tvArchives = (TextView) findViewById(R.id.tvArchives);
				tvArchives.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boite.show();
					}
				});
	
				// Fonction Mon compte non implémentée: renvoie vers la boite de dialogue
				tvMonCompte = (TextView) findViewById(R.id.tvMonCompte);
				tvMonCompte.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
						boite.show();
					}
				});
	
				// Affiche dans le menu le nom du médecin connecté et sa fonction
				tvFonction = (TextView) findViewById(R.id.tvNomMedecin);
				tvFonction.setText(prenom + " " + nom);
			}
			
			// Si l'utilisateur est une secrétaire médicale
			else if (fonction == 2) {
				setContentView(R.layout.menu_secretaire_medicale);
	
				myDrawable = r.getDrawable(R.drawable.barre_hautsecadm);
				actionBar.setBackgroundDrawable(myDrawable);
		
				// Affiche dans le menu le nom de la secrétaire médicale connectée et sa fonction
				tvFonction = (TextView) findViewById(R.id.tvNomSecretaireMedicale);
				tvFonction.setText(prenom + " " + nom);
			}
			
			// Si l'utilisateur n'est ni médecin, ni secrétaire médicale.
			else {
				Toast.makeText(getApplicationContext(),"Ce type d'utilisateur n'a pas encore été implémenté.",Toast.LENGTH_SHORT).show();
			}
		}
		
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.add(R.id.tiers, liste_patients);
		fragmentTransaction.add(R.id.deuxTiers, detail_patient);
		fragmentTransaction.commit();
	}

	@Override
	public void onPatientSelected(int position, Patient patient) {
		this.position = position;
		detail_patient.afficherPatient(patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the options menu from XML
		MenuInflater inflater = getMenuInflater();
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

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getItemId() == android.R.id.home) {
			mLayout.toggleSidebar();
		}
		return (super.onOptionsItemSelected(menuItem));
	}

	//Lorsqu'on clique à un endroit du layout de contenu, le menu slide se rétracte
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

	@Override
	public void onSidebarOpened() {
		Log.d(TAG, "opened");
	}

	@Override
	public void onSidebarClosed() {
		Log.d(TAG, "closed");
	}

	
	@Override
	public boolean onContentTouchedWhenOpening() {
		Log.d(TAG, "going to close sidebar");
		mLayout.closeSidebar();
		return true;
	}
	
}

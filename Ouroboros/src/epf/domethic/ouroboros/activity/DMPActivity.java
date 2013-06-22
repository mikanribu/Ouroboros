package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment.OnRadioSelectedListener;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.medecinColumns;
import epf.domethic.ouroboros.widget.AnimationLayout;

public class DMPActivity extends SherlockFragmentActivity implements
		TabListener, AnimationLayout.Listener, OnRadioSelectedListener {

	InformationsGeneralesFragment infos = new InformationsGeneralesFragment();
	HospitalisationEnCoursFragment hospitalisation = new HospitalisationEnCoursFragment();
	HistoriqueFragment historique = new HistoriqueFragment();
	CodificationFragment codification = new CodificationFragment();
	ListeGaucheHospiDMPFragment menu_hospi = new ListeGaucheHospiDMPFragment();
	ListeGaucheInfosDMPFragment menu_infos = new ListeGaucheInfosDMPFragment();
	private ArrayList<SherlockFragment> listeOnglets = new ArrayList<SherlockFragment>();
	AfficherRadioFragment radio_fragment = new AfficherRadioFragment();
	private int position;
	public final static String TAG = "Demo";
	protected AnimationLayout mLayout;
	protected LinearLayout mList;

	// Les boutons du menu
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

	// Boîte de dialogue pour les fonctions non encore implémentée
	AlertDialog.Builder boite;

	// Fragments
	ListerPatientsFragment liste_patients = new ListerPatientsFragment();
	AfficherPatientFragment detail_patient = new AfficherPatientFragment();
	RechercheGeneraleFragment recherche = new RechercheGeneraleFragment();

	JSONArray personnes = null;

	// url ou l'on peut accéder au JSON des sécrétaires médicales.
	static String urlsecMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_secretaires_med";

	// url ou l'on peut accéder au JSON des médecins.
	static String urlMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_medecins";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		setContentView(R.layout.activity_dmp);
		
		Resources resources = getResources();
		Drawable drawable;
		drawable = resources.getDrawable(R.drawable.barre_haut);

		getSupportActionBar().setBackgroundDrawable(drawable);
		getSupportActionBar().setHomeButtonEnabled(true); // The icone_launcher
															// will not go back
															// automatically to
															// home (API min 14)

		// Pas d'affichage du nom de l'application dans la barre d'action
		getSupportActionBar().setDisplayShowTitleEnabled(false);

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

		mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);

		// Création de la boîte de dialogue qui sera affichée lorsque
		// l'utilisateur cliquera sur des boutons pas développé
		boite = new AlertDialog.Builder(this);
		boite.setTitle("La fonction n'est pas encore implémentée!");
		boite.setIcon(R.drawable.en_travaux);
		boite.setMessage("Cette fonction n'a pas été développée dans cette version.");
		boite.setNegativeButton("Retour", null);

		// Déconnecter l'utilisateur
		tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
		final Intent intent_connexion = new Intent(DMPActivity.this,
				ConnexionActivity.class);
		tvDeconnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_connexion);
			}
		});

		// Amener l'utilisateur sur la page recherche d'une personne
		tvRecherche = (TextView) findViewById(R.id.tvRecherche);
		final Intent intent_hospi = new Intent(DMPActivity.this,
			HospitalisationsActivity.class);
		tvRecherche.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent_hospi.putExtra("destination", "recherche");
				startActivity(intent_hospi);
				
			}
		});

		// Amener l'utilisateru sur la liste de totues les hospitalisations
		// auquel il a accès
		tvHospitalisation = (TextView) findViewById(R.id.tvHospitalisation);
		final Intent intent_liste = new Intent(DMPActivity.this,
				HospitalisationsActivity.class);
		tvHospitalisation.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					intent_liste.putExtra("destination", "liste_patients");
					startActivity(intent_hospi);
					
				}
		});

		// Fonction non implémentée: renvoit vers la boite de dialogue
		tvCreationDossier = (TextView) findViewById(R.id.tvCreationDossier);
		tvCreationDossier.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boite.show();
			}
		});

		// Fonction non implémentée: renvoit vers la boite de dialogue
		tvAjoutDoc = (TextView) findViewById(R.id.tvAjoutDocument);
		tvAjoutDoc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boite.show();
			}
		});

		// Fonction non implémentée: renvoit vers la boite de dialogue
		tvTransfert = (TextView) findViewById(R.id.tvTransfert);
		tvTransfert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boite.show();
			}
		});

		tvCodification = (TextView) findViewById(R.id.tvACodifier);
		tvCodification.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boite.show();
			}

		});

		// Fonction non implémentée: renvoit vers la boite de dialogue
		tvArchives = (TextView) findViewById(R.id.tvArchives);
		tvArchives.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boite.show();
			}
		});

		// Fonction non implémentée: renvoit vers la boite de dialogue
		tvMonCompte = (TextView) findViewById(R.id.tvMonCompte);
		tvMonCompte.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boite.show();
			}
		});

		/*
		 * // Affiche dans le menu le nom du médecin connecté et sa fonction
		 * tvFonction = (TextView) findViewById(R.id.tvNomMedecin);
		 * RecuperationJSON(pseudo, fonction);
		 */
		// }

		/*
		 * // Si l'utilisateru est une secrétaire médicale else if (fonction ==
		 * 2) { Log.v(TAG, "la est le probleme?");
		 * setContentView(R.layout.menu_secretaire_medicale);
		 * 
		 * android.app.ActionBar actionBar = getActionBar(); Resources resources
		 * = getResources(); Drawable drawable;
		 * 
		 * drawable = resources.getDrawable(R.drawable.barre_hautsecadm);
		 * Log.v(TAG, "nn");
		 * 
		 * actionBar.setBackgroundDrawable(drawable);
		 * actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		 * actionBar.setHomeButtonEnabled(true); // The icone_launcher will not
		 * // go // back automatically to // home // (API min 14)
		 * 
		 * // Pas d'affichage du nom de l'application dans la barre d'action
		 * actionBar.setDisplayShowTitleEnabled(false);
		 * 
		 * mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		 * mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		 * mLayout.setListener(this);
		 * 
		 * // Gère la déconnexion de l'utilisateur tvDeconnexion = (TextView)
		 * findViewById(R.id.tvDeconnexion); final Intent intent_connexion = new
		 * Intent( DMPActivity.this, ConnexionActivity.class);
		 * 
		 * tvDeconnexion.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * startActivity(intent_connexion); } }); tvFonction = (TextView)
		 * findViewById(R.id.tvNomSecretaireMedicale); RecuperationJSON(pseudo,
		 * fonction); } // Si l'utilisateur n'est ni médecin, ni secrétaire
		 * médicale. else { Toast.makeText(getApplicationContext(),
		 * "Ce type d'utilisateur n'a pas encore été implémenté.",
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 */

	}

	public void RecuperationJSON(String pseudo, int fonction) {
		// Cette fonction a pour but de renvoyer le nom et le prénom de
		// l'utilisateru a partir de son pseudo.
		// Il prend en argument le pseudo et la fonction (médecin, secrétaire)
		// de l'utilisateur si celui ci existe bien.
		// Il affiche dans le menu le nom et prénom.

		Log.d(TAG, "entered in recuperationJSON");
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// Creation d'une instance ParserJSON
		ParserJSON jParser = new ParserJSON();

		// Cas d'un médecin
		if (fonction == 1) {
			// On récupère JSON string à partir de l'URL
			JSONObject json = jParser.getJSONFromUrl(urlMed);

			try {
				personnes = json.getJSONArray("medecin");

				Log.d(TAG, "JSON get array working");
				String pseudonyme = "";
				String nom = "";
				String prenom = "";

				// Boucle sur tous les membres de Ouroboros inscrit dans le JSON
				for (int i = 0; i < personnes.length(); i++) {
					JSONObject c = personnes.getJSONObject(i);

					// On récupère toutes les données qu'on stocke dans une
					// variable

					pseudonyme = c.getString(medecinColumns.KEY_PSEUDO);
					nom = c.getString(medecinColumns.KEY_NOM);
					prenom = c.getString(medecinColumns.KEY_PRENOM);

					Log.v(TAG, "pseudo" + pseudonyme + "bli");
					Log.v(TAG, "bli" + pseudo + "bli");

					// Comparaison du pseudo et mdp avec ceux rentré par
					// l'utilisateur
					if (pseudonyme.compareTo(pseudo) == 0) {
						Log.d(TAG, "JSON person found");
						tvFonction.setText(prenom + " " + nom); // On écrit le
																// nom et prénom
																// dans le menu
					}

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// Cas d'une secrétaire médicale
		else if (fonction == 2) {
			// On récupère JSON string à partir de l'URL
			JSONObject json = jParser.getJSONFromUrl(urlsecMed);

			try {
				personnes = json.getJSONArray("secretaire_medicale");

				Log.d(TAG, "JSON get array working");
				String pseudonyme = "";
				String nom = "";
				String prenom = "";

				// Boucle sur tous les membres de Ouroboros inscrit dans le JSON
				for (int i = 0; i < personnes.length(); i++) {
					JSONObject c = personnes.getJSONObject(i);

					// On récupère toutes les données qu'on stocke dans une
					// variable

					pseudonyme = c.getString(medecinColumns.KEY_PSEUDO);
					nom = c.getString(medecinColumns.KEY_NOM);
					prenom = c.getString(medecinColumns.KEY_PRENOM);

					Log.v(TAG, "pseudo" + pseudonyme + "bli");
					Log.v(TAG, "bli" + pseudo + "bli");

					// Comparaison du pseudo et mdp avec ceux rentré par
					// l'utilisateur
					if (pseudonyme.compareTo(pseudo) == 0) {
						Log.d(TAG, "JSON person found");
						tvFonction.setText(prenom + " " + nom);
					}

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {

		int position = tab.getPosition();
		switch (position) {
		case 1:
			listeOnglets.clear();
			fragmentTransaction.replace(R.id.detail, hospitalisation);
			listeOnglets.add(hospitalisation);
			fragmentTransaction.replace(R.id.menu_gauche, menu_hospi);
			listeOnglets.add(menu_hospi);
			break;

		case 2:
			for (int i = 0; i < listeOnglets.size(); i++) {
				fragmentTransaction.remove(listeOnglets.get(i));
			}
			listeOnglets.clear();
			fragmentTransaction.add(R.id.menu_gauche, historique);
			listeOnglets.add(historique);
			break;

		case 3:
			for (int i = 0; i < listeOnglets.size(); i++) {
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

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

	}

	@Override
	public void onRadioSelected(int position, Radio radio) {
		this.position = position;
		radio_fragment.afficherRadio(radio);

	}

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

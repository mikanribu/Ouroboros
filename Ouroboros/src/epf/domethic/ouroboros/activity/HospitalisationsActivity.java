package epf.domethic.ouroboros.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListerPatientsFragment.OnPatientSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.PersonnelConnexionColumns;
import epf.domethic.ouroboros.outils.medecinColumns;
import epf.domethic.ouroboros.widget.AnimationLayout;

public class HospitalisationsActivity extends SherlockFragmentActivity implements
		AnimationLayout.Listener, OnPatientSelectedListener {
	/** Called when the activity is first created. */

	private int position;
	public static Context appContext;

	public final static String TAG = "Demo";
	protected LinearLayout mList;
	protected AnimationLayout mLayout;
	
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
	ListerPatientsFragment fragment_liste = new ListerPatientsFragment();
	AfficherPatientFragment fragment_detail = new AfficherPatientFragment();
	RechercheGeneraleFragment fragment_recherche_g = new RechercheGeneraleFragment();
	AfficherRadioFragment fragment_radio = new AfficherRadioFragment();
	
	JSONArray personnes = null;
	
	//url ou l'on peut accéder au JSON des sécrétaires médicales.
	static String urlsecMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_secretaires_med";
	
	//url ou l'on peut accéder au JSON des médecins.
	static String urlMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_medecins";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		
		//Récuperer le pseudo entré en connection
		String pseudo = i.getStringExtra("pseudo"); 
	    int fonction = Integer.parseInt(i.getStringExtra("fonction")); 
	    Log.v(TAG, pseudo + fonction);
	    
	    
	   // Si l'utilisateur est un médecin.
	    if(fonction==1){
			setContentView(R.layout.activity_hospitalisations);	
			
	
			ActionBar actionBar = getActionBar();
			
			Resources r = getResources();
			
			Drawable myDrawable;
			
			myDrawable = r.getDrawable(R.drawable.barre_haut);		
			
			actionBar.setBackgroundDrawable(myDrawable);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setHomeButtonEnabled(true); // The icone_launcher will not go
													// back automatically to home
													// (API min 14)
	
			// Pas d'affichage du nom de l'application dans la barre d'action
			actionBar.setDisplayShowTitleEnabled(false);
	
			mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
			mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
			mLayout.setListener(this);
	
			FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = manager.beginTransaction();
			
			fragmentTransaction.add(R.id.tiers, fragment_liste);
			fragmentTransaction.add(R.id.deuxTiers, fragment_detail);
			fragmentTransaction.addToBackStack("vers_hospi");
			fragmentTransaction.commit();
			
			// Création de la boîte de dialogue qui sera affichée lorsque l'utilisateur cliquera sur des boutons pas développé
	        boite = new AlertDialog.Builder(this);
	        boite.setTitle("La fonction n'est pas encore implémentée!");
	        boite.setIcon(R.drawable.en_travaux);
	        boite.setMessage("Cette fonction n'a pas été développée dans cette version.");
	        boite.setNegativeButton("Retour", null);
			
	        // Déconnecter l'utilisateur
			tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
			final Intent intent_connexion = new Intent(HospitalisationsActivity.this, ConnexionActivity.class);
			tvDeconnexion.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(intent_connexion);
				}
			});
	
			// Amener l'utilisateur sur la page recherche d'une personne
			tvRecherche = (TextView) findViewById(R.id.tvRecherche);
			tvRecherche.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
					String str = manager.getBackStackEntryAt(0).getName();
	
					if (str != "fragment_recherche_g") {
						FragmentTransaction fragmentTransaction = manager.beginTransaction();
						manager.popBackStack();
						fragmentTransaction.replace(R.id.tiers,fragment_recherche_g);
						fragmentTransaction.addToBackStack("vers_recherche");
						fragmentTransaction.commit();
					}
					
				}
			});
	
			// Amener l'utilisateru sur la liste de totues les hospitalisations auquel il a accès
			tvHospitalisation = (TextView) findViewById(R.id.tvHospitalisation);
			tvHospitalisation.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					//removeMenuDMP();
					
					FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
					String str = manager.getBackStackEntryAt(0).getName();
					
					if (str != "vers_hospi") {
						FragmentTransaction fragmentTransaction = manager.beginTransaction();
						manager.popBackStack();
						fragmentTransaction.replace(R.id.tiers, fragment_liste);
						fragmentTransaction.replace(R.id.deuxTiers, fragment_detail);					
						fragmentTransaction.addToBackStack("vers_hospi");
						fragmentTransaction.commit();
					}
				}
			});
			
			// Fonction non implémentée: renvoit vers la boite de dialogue
			tvCreationDossier= (TextView) findViewById(R.id.tvCreationDossier);
			tvCreationDossier.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
	                boite.show();
					
				}
			});
			
			// Fonction non implémentée: renvoit vers la boite de dialogue
			tvAjoutDoc= (TextView) findViewById(R.id.tvAjoutDocument);
			tvAjoutDoc.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					boite.show();
				}
			});
			
			// Fonction non implémentée: renvoit vers la boite de dialogue
			tvTransfert= (TextView) findViewById(R.id.tvTransfert);
			tvTransfert.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					boite.show();
				}
			});
			
			tvCodification= (TextView) findViewById(R.id.tvACodifier);
			tvCodification.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					boite.show();
				}
			});
	
			// Fonction non implémentée: renvoit vers la boite de dialogue
			tvArchives = (TextView) findViewById(R.id.tvArchives);
			tvArchives.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
//					FragmentManager manager = HospitalisationsActivity.this.getSupportFragmentManager();
//					String str = manager.getBackStackEntryAt(0).getName();
//	
//					if (str != "fragment_radio") {
//						FragmentTransaction fragmentTransaction = manager.beginTransaction();
//						manager.popBackStack();
//						fragmentTransaction.replace(R.id.deuxTiers,fragment_radio);
//						fragmentTransaction.addToBackStack("fragment_radio");
//						fragmentTransaction.commit();
//					}
//	
//				}
					boite.show();
				}
			});
			
			// Fonction non implémentée: renvoit vers la boite de dialogue
			tvMonCompte= (TextView) findViewById(R.id.tvMonCompte);
			tvMonCompte.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					boite.show();
				}
			});
				
			// Affiche dans le menu le nom du médecin connecté et sa fonction
			tvFonction = (TextView) findViewById(R.id.tvNomMedecin);
			RecuperationJSON(pseudo, fonction);
	    }
	    // Si l'utilisateru est une secrétaire médicale
		else if (fonction==2){
			Log.v(TAG, "la est le probleme?");
			setContentView(R.layout.menu_secretaire_medicale);
			
			ActionBar actionBar = getActionBar();

			Resources r = getResources();
			Drawable myDrawable;
			myDrawable = r.getDrawable(R.drawable.barre_hautsecadm);
			Log.v(TAG, "nn");
			
			actionBar.setBackgroundDrawable(myDrawable);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setHomeButtonEnabled(true); // The icone_launcher will not go
													// back automatically to home
													// (API min 14)

			// Pas d'affichage du nom de l'application dans la barre d'action
			actionBar.setDisplayShowTitleEnabled(false);
			
			mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
			mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
			mLayout.setListener(this);
			
			//Gère la déconnexion de l'utilisateur
			tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
			final Intent intent_connexion = new Intent(HospitalisationsActivity.this, ConnexionActivity.class);
			
			tvDeconnexion.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(intent_connexion);
				}
			});
			tvFonction = (TextView) findViewById(R.id.tvNomSecretaireMedicale);
			RecuperationJSON(pseudo, fonction);
		}
	    //Si l'utilisateur n'est ni médecin, ni secrétaire médicale.
		else{
			Toast.makeText(getApplicationContext(), "Ce type d'utilisateur n'a pas encore été implémenté.", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	public void RecuperationJSON(String pseudo, int fonction) {
		// Cette fonction a pour but de renvoyer le nom et le prénom de l'utilisateru a partir de son pseudo.
		// Il prend en argument le pseudo et la fonction (médecin, secrétaire) de l'utilisateur si celui ci existe bien.
		// Il affiche dans le menu le nom et prénom.
		
		Log.d(TAG, "entered in recuperationJSON");
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		// Creation d'une instance ParserJSON
        ParserJSON jParser = new ParserJSON(); 
        
        // Cas d'un médecin
        if(fonction==1){
	        //On récupère JSON string à partir de l'URL
	        JSONObject json = jParser.getJSONFromUrl(urlMed);
	        
	        try {
	            personnes = json.getJSONArray("medecin");
	             
	            Log.d(TAG, "JSON get array working"); 
	            String pseudonyme="";
	            String nom ="";
	            String prenom ="";
	            
	            // Boucle sur tous les membres de Ouroboros inscrit dans le JSON
	            for(int i = 0; i < personnes.length(); i++){
	                JSONObject c = personnes.getJSONObject(i);
	                 
	                // On récupère toutes les données qu'on stocke dans une variable
	                
	                pseudonyme = c.getString(medecinColumns.KEY_PSEUDO);
	                nom = c.getString(medecinColumns.KEY_NOM);
	                prenom = c.getString(medecinColumns.KEY_PRENOM);
	               
	                Log.v(TAG, "pseudo"+pseudonyme+"bli"); 
	                Log.v(TAG, "bli"+pseudo+"bli"); 
	                
	                //Comparaison du pseudo et mdp avec ceux rentré par l'utilisateur
	                if(pseudonyme.compareTo(pseudo)==0){
	                	Log.d(TAG, "JSON person found"); 
	                	tvFonction.setText(prenom+" "+nom); //On écrit le nom et prénom dans le menu
	                }
	                     
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
        }
        // Cas d'une secrétaire médicale
	    else if(fonction==2){
	    	//On récupère JSON string à partir de l'URL
	        JSONObject json = jParser.getJSONFromUrl(urlsecMed);
	        
	        try {
	            personnes = json.getJSONArray("secretaire_medicale");
	             
	            Log.d(TAG, "JSON get array working"); 
	            String pseudonyme="";
	            String nom ="";
	            String prenom ="";
	            
	            // Boucle sur tous les membres de Ouroboros inscrit dans le JSON
	            for(int i = 0; i < personnes.length(); i++){
	                JSONObject c = personnes.getJSONObject(i);
	                 
	                // On récupère toutes les données qu'on stocke dans une variable
	                
	                pseudonyme = c.getString(medecinColumns.KEY_PSEUDO);
	                nom = c.getString(medecinColumns.KEY_NOM);
	                prenom = c.getString(medecinColumns.KEY_PRENOM);
	               
	                Log.v(TAG, "pseudo"+pseudonyme+"bli"); 
	                Log.v(TAG, "bli"+pseudo+"bli"); 
	                
	                //Comparaison du pseudo et mdp avec ceux rentré par l'utilisateur
	                if(pseudonyme.compareTo(pseudo)==0){
	                	Log.d(TAG, "JSON person found"); 
	                	tvFonction.setText(prenom+ " "+ nom);
	                }
	                     
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        	
	    }
	}

	@Override
	public void onPatientSelected(int position, Patient patient) {
		this.position = position;
		fragment_detail.afficherPatient(patient);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the options menu from XML
		MenuInflater inflater = getSupportMenuInflater();
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

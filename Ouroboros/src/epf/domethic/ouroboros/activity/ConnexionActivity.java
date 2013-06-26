package epf.domethic.ouroboros.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.dao.UserDAO;
import epf.domethic.ouroboros.model.User;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.PersColumns;
import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends Activity {

	/*----------	Déclaration des variables	----------*/
	Button bConnexion;
	EditText etPswd;
	EditText etPseudo;
	
	private static final String TAG = "MyActivity";
	
	JSONArray lesutilisateurs = null;
	
	//url ou l'on peut accéder au JSON de connexion.
	static String url_user ="http://raw.github.com/Mikanribu/Ouroboros/master/json_utilisateurs";
	
	private UserDAO dao;
	List<User> userList = new ArrayList<User>();
	
	/*----------	Déclaration des fonctions	----------*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		
		this.dao = new UserDAO(this);
				
		etPseudo = (EditText)findViewById(R.id.etPseudo);		
		etPswd = (EditText) findViewById(R.id.etPswd);	
		bConnexion = (Button)findViewById(R.id.bConnexion);
		
		// Lorsque l'utilisateur clique sur le bouton de connexion
		bConnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// Si les champs pseudo et password sont remplis
				if ((etPseudo.getText().toString().trim().equals("")==false) && (etPswd.getText().toString().trim().equals("")==false))
				{
					String lepseudo = etPseudo.getText().toString().trim();
					String lepswd = etPswd.getText().toString().trim();
					
					if(isOnline() ==true) {
						if(dao.dbIsEmpty()==true){
							RecuperationJSON_DATABASE();					
						}
						Cursor cursor = dao.getUsersCursor(lepseudo, lepswd);
						connexion(cursor);		
					}
					else {
						 Toast.makeText(getApplicationContext(), "Attention, vous travaillez hors connexion!", Toast.LENGTH_LONG).show();

						if(dao.dbIsEmpty()==true){
							Toast.makeText(getApplicationContext(), "Vous ne vous êtes jamais connecté à Internet avec l'application." +
									"Nous sommes dans l'incapacité de vérifier la véracité des informations. Désolé.", Toast.LENGTH_LONG).show();	
						}
						else {
							Cursor cursor = dao.getUsersCursor(lepseudo, lepswd);						
							 connexion(cursor);
						} 
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Les deux champs sont vides!", Toast.LENGTH_SHORT).show();
				}				
			}
		});		
	}

	/*Cette fonction permet de récupérer les données JSON via l'url
	 * Et de stocker ces données dans la base de données prévue a cet effet
	 */
	public void RecuperationJSON_DATABASE(){
		Log.d(TAG, "entered in recuperation JSON_DATABASE");
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
				ParserJSON jParser = new ParserJSON();
				// On récupère JSON string à partir de l'URL
				JSONObject json = jParser.getJSONFromUrl(url_user);

				try {
					lesutilisateurs = json.getJSONArray("utilisateurs");

					// Boucle sur tous les patients du fichier JSON
					for (int i = 0; i < lesutilisateurs.length(); i++) {

						JSONObject c = lesutilisateurs.getJSONObject(i);

						// On récupère toutes les données qu'on stocke dans une variable
						int fonction = Integer.parseInt(c.getString(PersColumns.KEY_FONCTION));
		                String pseudo = c.getString(PersColumns.KEY_PSEUDO);
		                String password = c.getString(PersColumns.KEY_MDP);
						String nom = c.getString(PersColumns.KEY_NOM);
						String prenom = c.getString(PersColumns.KEY_PRENOM);
						String mail = c.getString(PersColumns.KEY_MAIL);		
						String telephone = c.getString(PersColumns.KEY_TELEPHONE);
						String service = c.getString(PersColumns.KEY_SERVICE);
						
						User u = new User(pseudo, password, nom, prenom, mail, telephone,
								service, fonction);

						UserDAO dao = new UserDAO(this);
						dao.ajouterUser(u); // Ajoute un patient dans la BDD
						dao.close();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
	/*Fonction qui permet la connexion de l'utilisateur 
	 * Permet de vérifier la véracité du couple pseudo/mot de passe
	 */
	public void connexion(Cursor cursor) {
		if(cursor.moveToFirst()==false){ //Si la correspondance pseudo / mot de passe est fausse
			 Toast.makeText(getApplicationContext(), "Mot de passe ou pseudonyme incorrect!", Toast.LENGTH_SHORT).show();
		 }
		else {
			 cursor.moveToFirst();
			 
			 final Intent intent_connexion = new Intent(ConnexionActivity.this, HospitalisationsActivity.class);
			 //Récupération des données de la base de données
			 String nom = cursor.getString(3);
			 String prenom = cursor.getString(4);
			 int fonction = Integer.parseInt(cursor.getString(8));
			 cursor.close();
	         intent_connexion.putExtra("fonction", String.valueOf(fonction));
			 intent_connexion.putExtra("nom", nom);
			 intent_connexion.putExtra("prenom", prenom);
			 startActivity(intent_connexion); 
		}
	}
	
	
	/*Fonction qui permet de vérifier si la tablette est connectée à internet 
	 * Retourne "true" s'il existe une connexion
	 * Retourne "false" si la tablette n'est pas connectée*/
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}

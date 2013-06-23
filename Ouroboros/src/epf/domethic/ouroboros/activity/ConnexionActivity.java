package epf.domethic.ouroboros.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import android.widget.ListView;
import android.widget.Toast;

public class ConnexionActivity extends Activity {

	Button bConnexion;
	EditText etPswd;
	EditText etPseudo;
	
	private static final String TAG = "MyActivity";
	
	JSONArray lesutilisateurs = null;
	
	//url ou l'on peut accéder au JSON de connexion.
	static String url_user ="http://raw.github.com/Mikanribu/Ouroboros/master/json_utilisateurs";
	
	private UserDAO dao;
	private User user;
	List<User> userList = new ArrayList<User>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		
		this.dao = new UserDAO(this);
		if(dao.dbIsEmpty()==true){
			Log.d(TAG, "dbEmpty");
			RecuperationJSON_DATABASE();
		}
				
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
					Log.d(TAG, "pseudo and password non null");
					String lepseudo = etPseudo.getText().toString().trim();
					String lepswd = etPswd.getText().toString().trim();
					
					if(isOnline() ==true) {
						Log.d(TAG, "user online");
//						int fonction = RecuperationJSON(lepseudo,lepswd);
//						
//					//Si un utilisateur a le même pseudo et mot de passe que ce qu'il a rentré
//						if(fonction != 0){
//							
//							// On créé l'utilisateur s'il a entré un pseudo et mot de passe correct
//							Log.v(TAG, "la fonction:"+fonction);
//							
//						// Il est renvoyé à la page d'accueil
							final Intent intent_connexion = new Intent(ConnexionActivity.this, HospitalisationsActivity.class);
							RecuperationJSON(lepseudo,lepswd, intent_connexion);
//						// On passe aussi la fonction de l'utilisateur et son pseudo à la page d'accueil
//							intent_connexion.putExtra("fonction", String.valueOf(fonction));
//							intent_connexion.putExtra("pseudo", lepseudo);
//							
//							startActivity(intent_connexion);
//						}
//					else{// Le pseudo et mdp entrés ne correspondent à rien!
//							Toast.makeText(getApplicationContext(), "Mot de passe ou pseudonyme incorrect!", Toast.LENGTH_SHORT).show();
//						}
//						
					}
					else {
						 Toast.makeText(getApplicationContext(), "Attention, vous travaillez hors connexion!", Toast.LENGTH_LONG).show();
						 Cursor cursor = dao.getUsersCursor(lepseudo, lepswd);

						 if(cursor==null){
							 Log.d(TAG, "cursor null");
							 Toast.makeText(getApplicationContext(), "Mot de passe ou pseudonyme incorrect!", Toast.LENGTH_SHORT).show();
						 }
						 else{
							 cursor.moveToFirst();
							 Log.d(TAG, "cursor non null");
							 final Intent intent_connexion = new Intent(ConnexionActivity.this, HospitalisationsActivity.class);
							 Log.d(TAG, "JSON person found"); 
							 
							 String nom = cursor.getString(3);
							 Log.v(TAG, nom);
							 String prenom = cursor.getString(4);
							 Log.v(TAG, prenom);
							 int fonction = Integer.parseInt(cursor.getString(8));
							 cursor.close();
			                 intent_connexion.putExtra("fonction", String.valueOf(fonction));
							 intent_connexion.putExtra("nom", nom);
							 intent_connexion.putExtra("prenom", prenom);
							 Log.v(TAG, fonction + nom + prenom); 
							 startActivity(intent_connexion); 
						 }
						 
						}
				}
				else{
					Toast.makeText(getApplicationContext(), "Les deux champs sont vides!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});		
	}
	
	public void RecuperationJSON(String pseudonyme, String motdepasse, Intent intent) {
		// Cette fonction a pour but de vérifier si les arguments entrés correspondent  bien a une session utilisateur.
		// Il prend en argument le pseudo et mot de passe entrés et renvoit la fonction (médecin, secrétaire) 
		// de l'utilisateur si celui ci existe bien.
		
		Log.d(TAG, "entered in recuperationJSON");
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		// Creation d'une instance ParserJSON
        ParserJSON jParser = new ParserJSON();    
        //On récupère JSON string à partir de l'URL
        JSONObject json = jParser.getJSONFromUrl(url_user);
        int fonction=0;
        String nom="";
        String prenom="";
        
        try {
            lesutilisateurs = json.getJSONArray("utilisateurs");
             
            Log.d(TAG, "JSON get array working"); 
            String pseudo;
            String password;
            
            // Boucle sur tous les membres de Ouroboros inscrit dans le JSON
            for(int i = 0; i < lesutilisateurs.length(); i++){
                JSONObject c = lesutilisateurs.getJSONObject(i);
                 
                // On récupère toutes les données qu'on stocke dans une variable
                
                fonction = Integer.parseInt(c.getString(PersColumns.KEY_FONCTION));
                pseudo = c.getString(PersColumns.KEY_PSEUDO);
                password = c.getString(PersColumns.KEY_MDP);
                nom=c.getString(PersColumns.KEY_NOM);
                prenom=c.getString(PersColumns.KEY_PRENOM);
                
                Log.v(TAG, "pseudo"+pseudonyme+"bli"); 
                Log.v(TAG, "bli"+pseudo+"bli"); 
                Log.v(TAG, "mdp"+motdepasse+"bli"); 
                Log.v(TAG, "bli"+password+"bli"); 
                
                //Comparaison du pseudo et mdp avec ceux rentré par l'utilisateur
                if(pseudonyme.compareTo(pseudo)==0 && motdepasse.compareTo(password)==0){
                	Log.d(TAG, "JSON person found"); 
                	intent.putExtra("fonction", String.valueOf(fonction));
					intent.putExtra("nom", nom);
					intent.putExtra("prenom", prenom);
					
					startActivity(intent); 
                }
                     
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } // Lorsque la fonction vaut 0, le couple (pseudo, mdp) n'existe pas.
        if(fonction==0){
        	Toast.makeText(getApplicationContext(), "Mot de passe ou pseudonyme incorrect!", Toast.LENGTH_SHORT).show();
        }
	}
	
	public void RecuperationJSON_DATABASE(){
		Log.d(TAG, "entered in recuperation JSON_DATABASE");
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
				ParserJSON jParser = new ParserJSON();
				Log.d(TAG, "1");
				// On récupère JSON string à partir de l'URL
				JSONObject json = jParser.getJSONFromUrl(url_user);
				Log.d(TAG, "1b");

				try {
					Log.d(TAG, "2a");
					lesutilisateurs = json.getJSONArray("utilisateurs");
					Log.d(TAG, "2");

					// Boucle sur tous les patients du fichier JSON
					for (int i = 0; i < lesutilisateurs.length(); i++) {
						Log.d(TAG, "3");

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
						// patientList.add(p);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
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

package epf.domethic.ouroboros.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.outils.ParserJSON;
import epf.domethic.ouroboros.outils.PersonnelConnexionColumns;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends SherlockActivity {

	Button bConnexion;
	EditText etPswd;
	EditText etPseudo;
	
	private static final String TAG = "MyActivity";
	
	JSONArray personnelConnexions = null;
	
	//url ou l'on peut accéder au JSON de connexion.
	static String url = "http://raw.github.com/Mikanribu/Ouroboros/master/json_personnelconnexion";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		
		
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
					
					//try {
						int fonction = RecuperationJSON(lepseudo,lepswd);
						
					//Si un utilisateur a le même pseudo et mot de passe que ce qu'il a rentré
						if(fonction != 0){
							
							// On crèe l'utilisateur s'il a entré un pseudo et mdp correct
							Log.v(TAG, "la fonction:"+fonction);
							
						// Il est renvoyé à la page d'accueil
							final Intent intent_connexion = new Intent(ConnexionActivity.this, HospitalisationsActivity.class);
						
						// On passe aussi la fonction de l'utilisateur et son pseudo à la page d'accueil
							intent_connexion.putExtra("fonction", String.valueOf(fonction));
							intent_connexion.putExtra("pseudo", lepseudo);
							
							startActivity(intent_connexion);
						}
					else{// Le pseudo et mdp entrés ne correspondent à rien!
							Toast.makeText(getApplicationContext(), "Mot de passe ou pseudonyme incorrect!", Toast.LENGTH_SHORT).show();
						}
						
					/*}
					catch (Exception e) {
						  Toast.makeText(ConnexionActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
						}*/
				}
				else{
					Toast.makeText(getApplicationContext(), "Les deux champs sont vides!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});		
	}
	
	public int RecuperationJSON(String pseudonyme, String motdepasse) {
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
        JSONObject json = jParser.getJSONFromUrl(url);
        int fonction=0;
        
        try {
            personnelConnexions = json.getJSONArray("personnelConnexion");
             
            Log.d(TAG, "JSON get array working"); 
            String pseudo;
            String password;
            
            // Boucle sur tous les membres de Ouroboros inscrit dans le JSON
            for(int i = 0; i < personnelConnexions.length(); i++){
                JSONObject c = personnelConnexions.getJSONObject(i);
                 
                // On récupère toutes les données qu'on stocke dans une variable
                
                fonction = Integer.parseInt(c.getString(PersonnelConnexionColumns.KEY_FONCTION));
                pseudo = c.getString(PersonnelConnexionColumns.KEY_PSEUDO);
                password = c.getString(PersonnelConnexionColumns.KEY_PASSWORD);
                Log.v(TAG, "pseudo"+pseudonyme+"bli"); 
                Log.v(TAG, "bli"+pseudo+"bli"); 
                Log.v(TAG, "mdp"+motdepasse+"bli"); 
                Log.v(TAG, "bli"+password+"bli"); 
                
                //Comparaison du pseudo et mdp avec ceux rentré par l'utilisateur
                if(pseudonyme.compareTo(pseudo)==0 && motdepasse.compareTo(password)==0){
                	Log.d(TAG, "JSON person found"); 
                	return fonction;
                }
                     
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0; // Lorsque la fonction vaut 0, le couple (pseudo, mdp) n'existe pas.
	}
}

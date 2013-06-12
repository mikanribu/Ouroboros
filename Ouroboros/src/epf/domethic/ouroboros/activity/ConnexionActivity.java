package epf.domethic.ouroboros.activity;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import epf.domethic.ouroboros.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConnexionActivity extends SherlockActivity {

	Button bConnexion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		
		bConnexion = (Button)findViewById(R.id.bouton_connexion);
		final Intent intent_connexion = new Intent(ConnexionActivity.this, HospitalisationsActivity.class);
		bConnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_connexion);
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.connexion, menu);
		return true;
	}

}

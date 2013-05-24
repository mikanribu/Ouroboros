package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ConnexionActivity extends Activity {
	
	private ImageButton bConnexion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		bConnexion = (ImageButton)findViewById(R.id.bouton_connexion);

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
		getMenuInflater().inflate(R.menu.connexion, menu);
		return true;
	}

}

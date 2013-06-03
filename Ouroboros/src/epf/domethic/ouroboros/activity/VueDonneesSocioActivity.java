package epf.domethic.ouroboros.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import epf.domethic.ouroboros.R;

public class VueDonneesSocioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vue_donnees_socio);
		ColorDrawable colorDrawable =new ColorDrawable();
	    ActionBar actionBar = getActionBar();
	    colorDrawable.setColor(0xff7184fa);
	    actionBar.setBackgroundDrawable(colorDrawable);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vue_donnees_socio, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem menu_item) {
		switch (menu_item.getItemId()) {
		case R.id.hospi_en_cours:
			Intent intent_hospi = new Intent(VueDonneesSocioActivity.this, HospiEnCoursActivity.class);
			startActivity(intent_hospi);
			return true;
		case R.id.archives:
			Intent intent_archives = new Intent(VueDonneesSocioActivity.this, ArchivesActivity.class);
			startActivity(intent_archives);
			return true;
		case R.id.codification:
			Intent intent_code = new Intent(VueDonneesSocioActivity.this, CodificationActivity.class);
			startActivity(intent_code);
			return true;
		default:
			return super.onOptionsItemSelected(menu_item);
		}
	}

}

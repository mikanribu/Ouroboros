package epf.domethic.ouroboros.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import epf.domethic.ouroboros.R;

public class CodificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_codification);
		ColorDrawable colorDrawable =new ColorDrawable();
	    ActionBar actionBar = getActionBar();
	    colorDrawable.setColor(0xff7184fa);
	    actionBar.setBackgroundDrawable(colorDrawable);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.codification, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem menu_item) {
		switch (menu_item.getItemId()) {
		case R.id.infos_generales:
			Intent intent_infos = new Intent(CodificationActivity.this, VueDonneesSocioActivity.class);
			startActivity(intent_infos);
			return true;
		case R.id.hospi_en_cours:
			Intent intent_hospi = new Intent(CodificationActivity.this, HospiEnCoursActivity.class);
			startActivity(intent_hospi);
			return true;
		case R.id.archives:
			Intent intent_archives = new Intent(CodificationActivity.this, ArchivesActivity.class);
			startActivity(intent_archives);
			return true;
		default:
			return super.onOptionsItemSelected(menu_item);
		}
	}

}
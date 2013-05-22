package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.activity.ArchivesActivity;
import epf.domethic.ouroboros.activity.CodificationActivity;
import epf.domethic.ouroboros.activity.VueDonneesSocioActivity;

import epf.domethic.ouroboros.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;

public class HospiEnCoursActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospi_en_cours);
		ColorDrawable colorDrawable =new ColorDrawable();
	    ActionBar actionBar = getActionBar();
	    colorDrawable.setColor(0xff7184fa);
	    actionBar.setBackgroundDrawable(colorDrawable);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hospi_en_cours, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem menu_item) {
		switch (menu_item.getItemId()) {
		case R.id.infos_generales:
			Intent intent_infos = new Intent(HospiEnCoursActivity.this, VueDonneesSocioActivity.class);
			startActivity(intent_infos);
			return true;
		case R.id.archives:
			Intent intent_archives = new Intent(HospiEnCoursActivity.this, ArchivesActivity.class);
			startActivity(intent_archives);
			return true;
		case R.id.codification:
			Intent intent_code = new Intent(HospiEnCoursActivity.this, CodificationActivity.class);
			startActivity(intent_code);
			return true;
		default:
			return super.onOptionsItemSelected(menu_item);
		}
	}


}

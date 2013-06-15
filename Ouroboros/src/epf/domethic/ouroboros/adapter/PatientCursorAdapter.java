package epf.domethic.ouroboros.adapter;

import java.text.SimpleDateFormat;

import epf.domethic.ouroboros.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PatientCursorAdapter extends CursorAdapter {

	public PatientCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TextView titre = (TextView)view.findViewById(R.id.tvNom);
		TextView date = (TextView) view.findViewById(R.id.tvDateNaiss);
		ImageView image = (ImageView) view.findViewById(R.id.ivSexe);
		
		String nom = cursor.getString(1);
		String prenom = cursor.getString(2);
		String date_naissance = cursor.getString(4);
		String sexe = cursor.getString(3);
		
		titre.setText(nom + " - " +prenom);
		date.setText(sdf.format(date_naissance));
		
		if(sexe == "Masculin")
			image.setImageResource(R.drawable.male);
		else
			image.setImageResource(R.drawable.female);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = 
				(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.patients_list_entry, parent, false);
		return view;
	}
}

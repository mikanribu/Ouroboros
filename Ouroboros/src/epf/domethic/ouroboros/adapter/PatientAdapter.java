package epf.domethic.ouroboros.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class PatientAdapter extends ArrayAdapter<Patient> {

	public PatientAdapter(Context context, List<Patient> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater
					.inflate(R.layout.patients_list_entry, parent, false);
		}

		Patient patient = getItem(position);
		TextView titre = (TextView) view.findViewById(R.id.tvNom);
		titre.setText(patient.getNom() + " - " + patient.getPrenom());
		//titre.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.male, 0);
		TextView date = (TextView) view.findViewById(R.id.tvDateNaiss);
		//nDateNaissance.setText(sdf.format(patient.getDateNaissance()));
		date.setText(sdf.format(patient.getDateNaissance()));
		
		ImageView image = (ImageView) view.findViewById(R.id.ivSexe);
		image.setImageResource(R.drawable.female);

		return view;
	}

}

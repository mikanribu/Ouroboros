package epf.domethic.ouroboros.adapter;

import java.util.List;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PatientAdapter extends ArrayAdapter<Patient>{

	public PatientAdapter(Context context, List<Patient> objects) {
		super(context,0, objects);
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {

	   if (view == null) {
	     LayoutInflater inflater = 
	(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     view = inflater.inflate(R.layout.patients_list_entry, parent, false);
	    }
	    Patient patient = getItem(position);
	    TextView titre = (TextView)view.findViewById(R.id.titreLivre);
	    titre.setText(patient.getNom() + " - " + patient.getPrenom());
	   return view;
	  }

	

}

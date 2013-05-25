package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AfficherPatientFragment extends Fragment {

	private TextView nNomPatient;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_visualiser_patients,container, false);
		return view;
	}
}

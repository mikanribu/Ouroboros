package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import epf.domethic.ouroboros.R;

public class RechercheGeneraleFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_recherche_patients,container, false);
		return view;
	}
	
}

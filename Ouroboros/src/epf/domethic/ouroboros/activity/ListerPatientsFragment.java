package epf.domethic.ouroboros.activity;

import java.util.List;

import epf.domethic.ouroboros.adapter.PatientAdapter;
import epf.domethic.ouroboros.model.Patient;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListerPatientsFragment extends ListFragment {
	
	public interface OnPatientSelectedListener {
		public void onPatientSelected(int position);
	}

	private OnPatientSelectedListener listener;

	private final static String TAG = ListerPatientsFragment.class
			.getSimpleName();
	private PatientAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Patient> patient = Patient.ALL;
		adapter = new PatientAdapter(getActivity(), patient);
		setListAdapter(adapter);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (OnPatientSelectedListener) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

	public void update() {
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null) {
			listener.onPatientSelected(position);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		adapter.notifyDataSetChanged();
	}
}

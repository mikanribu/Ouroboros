package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class AfficherPatientFragment extends Fragment {

	private TextView nNomPatient;
	//private TextView nPrenomPatient;
	private TextView nSexePatient;
	private TextView nLieuNaissance;
	private TextView nDateNaissance;
	private TextView nVille;
	
	private Patient patient;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_visualiser_patients,container, false);
		nNomPatient = (TextView)view.findViewById(R.id.tv_nom_patient);
		nSexePatient = (TextView)view.findViewById(R.id.textview_sexe_value);
		nLieuNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		nDateNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		
		return view;
	}
	
	
	public void afficherPatient (Patient patient) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.patient = patient;
		nNomPatient.setText(patient.getPrenom() + " " + patient.getNom());
		nSexePatient.setText(patient.getSexe().toString());
		nLieuNaissance.setText(patient.getLieuNaissance());
		nDateNaissance.setText(sdf.format(patient.getDateNaissance()));
		
	}
}

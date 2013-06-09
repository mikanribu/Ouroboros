package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class AfficherPatientFragment extends Fragment {

	private TextView nNomPatient;
	private TextView nSexePatient;
	private TextView nLieuNaissance;
	private TextView nDateNaissance;
	private TextView nVille;
	private Button bVisualiserDMP;
	private InformationsGeneralesFragment fragment_dmp_infos = new InformationsGeneralesFragment();
	
	private Patient patient;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_visualiser_patients,container, false);
		nNomPatient = (TextView)view.findViewById(R.id.tv_nom_patient);
		nSexePatient = (TextView)view.findViewById(R.id.textview_sexe_value);
		nLieuNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		nDateNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		
		bVisualiserDMP =(Button)view.findViewById(R.id.bVueDMP);
		bVisualiserDMP.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	FragmentManager manager = getActivity().getSupportFragmentManager();  
		        FragmentTransaction fragmentTransaction = manager.beginTransaction();
		        manager.popBackStack();	    	
		    	fragmentTransaction.replace(R.id.tiers, fragment_dmp_infos);
		    	fragmentTransaction.addToBackStack("vers_infos_dmp");
		    	fragmentTransaction.commit();
		    	}
	    	
		});		
		
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

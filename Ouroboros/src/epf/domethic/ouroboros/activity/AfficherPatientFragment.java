package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class AfficherPatientFragment extends SherlockFragment {

	private TextView nNomPatient;
	private TextView nSexePatient;
	private TextView nLieuNaissance;
	private TextView nDateNaissance;
	private TextView nVille;
	private TextView nAdresse;
	private TextView nCodePostal;
	private TextView nPays;
	private TextView nNationalite;
	private TextView nNumeroSS;
	private TextView nTelephone;
	private TextView nMedecinTraitant;
	private Button bVisualiserDMP;
	
	InformationsGeneralesFragment infos_dmp = new InformationsGeneralesFragment();
	ListeGaucheInfosDMPFragment menu_infos_dmp = new ListeGaucheInfosDMPFragment();
	OngletsDMPFragment onglets_dmp = new OngletsDMPFragment();
	
	private Patient patient;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_visualiser_patients,container, false);
		nNomPatient = (TextView)view.findViewById(R.id.tv_nom_patient);
		nSexePatient = (TextView)view.findViewById(R.id.textview_sexe_value);
		nLieuNaissance = (TextView)view.findViewById(R.id.textview_lieu_naissance_value);
		nDateNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		nVille = (TextView)view.findViewById(R.id.textview_ville_value);
		nAdresse =(TextView)view.findViewById(R.id.textview_adresse_value);
		nCodePostal = (TextView)view.findViewById(R.id.textview_code_postal_value);
		nPays = (TextView)view.findViewById(R.id.textview_pays_value);
		nNationalite = (TextView)view.findViewById(R.id.textview_nationalite_value);
		nNumeroSS = (TextView)view.findViewById(R.id.textview_ss_value);
		nTelephone = (TextView)view.findViewById(R.id.textview_telephone_value);
		nMedecinTraitant = (TextView)view.findViewById(R.id.textview_medecin_traitant_value);
		
		bVisualiserDMP =(Button)view.findViewById(R.id.bVueDMP);
		bVisualiserDMP.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	FragmentManager manager = getSherlockActivity().getSupportFragmentManager();  
		        FragmentTransaction fragmentTransaction = manager.beginTransaction();
		        manager.popBackStack();	    	
		    	fragmentTransaction.replace(R.id.deuxTiers, onglets_dmp);
		    	fragmentTransaction.addToBackStack("infos_dmp");
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
		nVille.setText(patient.getVille());
		nAdresse.setText(patient.getAdresse());;
		nCodePostal.setText(patient.getCodePostal());;
		nPays.setText(patient.getPays());;
		nNationalite.setText(patient.getNationalite());;
		nNumeroSS.setText(patient.getNumSS());;
		nTelephone.setText(patient.getTelephone());;
		nMedecinTraitant.setText(patient.getMedecinTraitant());;
		
		
		
	}
}

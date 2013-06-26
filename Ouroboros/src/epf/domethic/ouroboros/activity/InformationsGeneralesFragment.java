package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class InformationsGeneralesFragment extends SherlockFragment {

	/*----------	Déclaration des variables	----------*/
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
	
	private Button bModif;

	private Patient patient;

	
	/*----------	Déclaration des fonctions	----------*/
	
	//Création de la vue 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_infos_g,container, false);

		
		/*Récupération des variables de type du layout "fragment_visualiser_patients"
		 * Permet de récupérer les TextView afin de leur affilier une valeur		 */		
		nNomPatient = (TextView)view.findViewById(R.id.tvNomPatient);
		nSexePatient = (TextView)view.findViewById(R.id.tvSexeValuePatient);
		nLieuNaissance = (TextView)view.findViewById(R.id.tvLieuNaissanceValuePatient);
		nDateNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		nVille = (TextView)view.findViewById(R.id.tvVilleValuePatient);
		nAdresse =(TextView)view.findViewById(R.id.tvAdresseValuePatient);
		nCodePostal = (TextView)view.findViewById(R.id.tvCodePostalValuePatient);
		nPays = (TextView)view.findViewById(R.id.tvPaysValuePatient);
		nNationalite = (TextView)view.findViewById(R.id.tvNationaliteValuePatient);
		nNumeroSS = (TextView)view.findViewById(R.id.tvSSValuePatient);
		nTelephone = (TextView)view.findViewById(R.id.tvTelephoneValuePatient);
		nMedecinTraitant = (TextView)view.findViewById(R.id.tvMedecinTraitantValuePatient);
		
		afficherPatient(patient);
		
		bModif =(Button)view.findViewById(R.id.bModif);
		bModif.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    }
		});		
			
		return view;
	}
	
	// Fonction permettant d'affilier une valeur aux TextView
	// Permet d'afficher les données du patient dans le fragment 
	public void afficherPatient (Patient patient) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//On affilie chaque TextView à une valeur de la classe Patient
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
	
	public Patient getPatient(Patient patient){
		this.patient=patient;
		return patient;
	}
	
}

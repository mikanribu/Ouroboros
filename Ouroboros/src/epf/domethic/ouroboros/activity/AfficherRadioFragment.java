package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Radio;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AfficherRadioFragment extends Fragment {

	private TextView titre;
	private TextView nomRadio;
	private TextView cause;
	private TextView date;
	private TextView medecin;
	private TextView description;
	private TextView interpretation;
	private Radio radio = new Radio("Thorax - 22/05/13", "Radio du thorax",
			"Faiblesse pulmonaire", "22/05/13", "Dr House",
			"Fuerit toto in consulatu sine provincia, cui fuerit, antequam designatus est, decreta provincia. Sortietur an non? Nam et non sortiri absurdum est, et, quod sortitus sis, non habere. Proficiscetur paludatus? Quo? Quo pervenire ante certam diem non licebit. ianuario, Februario, provinciam non habebit; Kalendis ei denique Martiis nascetur repente provincia.",
			"Auxerunt haec vulgi sordidioris audaciam, quod cum ingravesceret penuria commeatuum, famis et furoris inpulsu Eubuli cuiusdam inter suos clari domum ambitiosam ignibus subditis inflammavit rectoremque ut sibi iudicio imperiali addictum calcibus incessens et pugnis conculcans seminecem laniatu miserando discerpsit. post cuius lacrimosum interitum in unius exitio quisque imaginem periculi sui considerans documento recenti similia formidabat.");

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_afficher_radio,
				container, false);
		titre = (TextView) view.findViewById(R.id.tv_titre);
		nomRadio = (TextView) view.findViewById(R.id.textview_radio_value);
		cause = (TextView) view.findViewById(R.id.textview_cause_value);
		date = (TextView) view.findViewById(R.id.textview_date_value);
		medecin = (TextView) view.findViewById(R.id.textview_medecin_value);
		description = (TextView) view
				.findViewById(R.id.textview_description_value);
		interpretation = (TextView) view
				.findViewById(R.id.textview_interpretation_value);
		
		afficherRadio(radio);
		return view;
	}

	public void afficherRadio(Radio radio) {

		titre.setText(radio.getTitre());
		nomRadio.setText(radio.getRadio());
		cause.setText(radio.getCause());
		date.setText(radio.getDate());
		medecin.setText(radio.getMedecin());
		description.setText(radio.getDescription());
		interpretation.setText(radio.getInterpretation());
	}
}

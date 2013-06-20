package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.dao.RadioDAO;
import epf.domethic.ouroboros.model.Radio;
import android.database.Cursor;
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
	
	RadioDAO dao = null;
	private Radio radio = new Radio(
			"Thorax - 22/05/13",
			"Radio du thorax",
			"Faiblesse pulmonaire",
			"22/05/13",
			"Dr House",
			"Fuerit toto in consulatu sine provincia, cui fuerit, antequam designatus est, decreta provincia. Sortietur an non? Nam et non sortiri absurdum est, et, quod sortitus sis, non habere. Proficiscetur paludatus? Quo? Quo pervenire ante certam diem non licebit. ianuario, Februario, provinciam non habebit; Kalendis ei denique Martiis nascetur repente provincia.",
			"Auxerunt haec vulgi sordidioris audaciam, quod cum ingravesceret penuria commeatuum, famis et furoris inpulsu Eubuli cuiusdam inter suos clari domum ambitiosam ignibus subditis inflammavit rectoremque ut sibi iudicio imperiali addictum calcibus incessens et pugnis conculcans seminecem laniatu miserando discerpsit. post cuius lacrimosum interitum in unius exitio quisque imaginem periculi sui considerans documento recenti similia formidabat.");

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_afficher_radio,
				container, false);
		titre = (TextView) view.findViewById(R.id.tvTitreRadio);
		nomRadio = (TextView) view.findViewById(R.id.tvRadioValue);
		cause = (TextView) view.findViewById(R.id.tvCauseValue);
		date = (TextView) view.findViewById(R.id.tvDateValue);
		medecin = (TextView) view.findViewById(R.id.tvMedecinValue);
		description = (TextView) view.findViewById(R.id.tvDescriptionValue);
		interpretation = (TextView) view
				.findViewById(R.id.tvInterpretationValue);

		afficherRadio();
	
		return view;
	}

	public void afficherRadio() {

		/*Cursor cursor = dao.getRadiosCursor(id);
		cursor.getString(2);
		cursor.getString(3);
		cursor.getString(4);
		cursor.getString(5);*/

		
		
		titre.setText(radio.getTitre());
		nomRadio.setText(radio.getNomRadio());
		cause.setText(radio.getCause());
		date.setText(radio.getDate());
		medecin.setText(radio.getMedecin());
		description.setText(radio.getDescription());
		interpretation.setText(radio.getInterpretation());
	}
}

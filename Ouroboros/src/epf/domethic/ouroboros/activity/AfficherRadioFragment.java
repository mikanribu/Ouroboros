package epf.domethic.ouroboros.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.actionbarsherlock.app.SherlockFragment;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Radio;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AfficherRadioFragment extends SherlockFragment {

	/*----------	Déclaration des variables	----------*/
	private TextView titre;
	private TextView nomRadio;
	private TextView cause;
	private TextView date;
	private TextView medecin;
	private TextView description;
	private TextView interpretation;
	private ImageView image;
	private String imageUrl ="http://m.goirand.free.fr/evaluation_anatomie/cliche_thorax.jpg";
	private Radio radio;

	/*----------	Déclaration des fonctions	----------*/
	//Création de la vue
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_afficher_radio,container, false);
		
		//Récupération des variables du layout fragment_afficher_radio pour leur affilier une valeur
		titre = (TextView) view.findViewById(R.id.tvTitreRadio);
		nomRadio = (TextView) view.findViewById(R.id.tvRadioValue);
		cause = (TextView) view.findViewById(R.id.tvCauseValue);
		date = (TextView) view.findViewById(R.id.tvDateValue);
		medecin = (TextView) view.findViewById(R.id.tvMedecinValue);
		description = (TextView) view.findViewById(R.id.tvDescriptionValue);
		interpretation = (TextView) view.findViewById(R.id.tvInterpretationValue);
		image = (ImageView) view.findViewById(R.id.ivRadio);

		afficherRadio(radio);
		
		//Quand on clique sur l'image, on appelle le fragment ZoomRadioFragment
		final Intent intent_zoom = new Intent(getSherlockActivity(), ZoomRadioActivity.class);
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_zoom);
			}
		});

		return view;
	}

	//Méthode d'affichage de la radio
	public void afficherRadio(Radio radio) {
		//On affilie chaque TextView à une valeur de la classe Radio
		titre.setText(radio.getTitre());
		nomRadio.setText(radio.getNomRadio());
		cause.setText(radio.getCause());
		date.setText(radio.getDate());
		medecin.setText(radio.getMedecin());
		description.setText(radio.getDescription());
		interpretation.setText(radio.getInterpretation());

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		//On cherche l'image via une URL
		try {
			Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
			image.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Radio getVueRadio(Radio radio){
		this.radio=radio;
		return radio;
	}
}

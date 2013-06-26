package epf.domethic.ouroboros.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.actionbarsherlock.app.SherlockFragment;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Radio;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
	//private String imageUrl = "https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg";
	private String imageUrl ="http://www.prisedevue.com/francais/chapitres/info_pratique/picsbio/radio.jpg";
	private Radio radio;
	
	//Déclaration du fragment
	ZoomRadioFragment fragment_zoom_radio = new ZoomRadioFragment();
	

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
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager manager = AfficherRadioFragment.this.getFragmentManager();
				FragmentTransaction fragmentTransaction = manager.beginTransaction();
				Log.v("YO", "je vais afficher la radio");
				manager.popBackStack();
				fragmentTransaction.replace(R.id.tiers, fragment_zoom_radio);
				fragmentTransaction.commit();
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
	
	public void onDetach(){
		super.onDetach();
	}
}

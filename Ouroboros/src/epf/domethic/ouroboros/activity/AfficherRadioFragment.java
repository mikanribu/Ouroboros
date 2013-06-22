package epf.domethic.ouroboros.activity;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.dao.RadioDAO;
import epf.domethic.ouroboros.model.Radio;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AfficherRadioFragment extends SherlockFragment {

	private TextView titre;
	private TextView nomRadio;
	private TextView cause;
	private TextView date;
	private TextView medecin;
	private TextView description;
	private TextView interpretation;
	private ImageView image;
	private String imageUrl="https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg";
 
	ZoomRadioFragment fragment_zoom_radio= new ZoomRadioFragment();

	// RadioDAO dao = null;
	private Radio radio;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_afficher_radio,container, false);

		titre = (TextView) view.findViewById(R.id.tvTitreRadio);
		nomRadio = (TextView) view.findViewById(R.id.tvRadioValue);
		cause = (TextView) view.findViewById(R.id.tvCauseValue);
		date = (TextView) view.findViewById(R.id.tvDateValue);
		medecin = (TextView) view.findViewById(R.id.tvMedecinValue);
		description = (TextView) view.findViewById(R.id.tvDescriptionValue);
		interpretation = (TextView) view
				.findViewById(R.id.tvInterpretationValue);
		
		try {
			  image = (ImageView)view.findViewById(R.id.ivRadio);
;
			Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
			  image.setImageBitmap(bitmap); 
			} catch (MalformedURLException e) {
			  e.printStackTrace();
			} catch (IOException e) {
			  e.printStackTrace();
			}
		
		
		//afficherRadio();
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager manager = AfficherRadioFragment.this.getFragmentManager();

					FragmentTransaction fragmentTransaction = manager.beginTransaction();
					fragmentTransaction.replace(R.id.deuxTiers,fragment_zoom_radio);
					fragmentTransaction.commit();
				}
			
		});

		return view;
	}

	public void afficherRadio(int id) {
		RadioDAO dao = null;
		Cursor cursor = dao.getRadiosCursor(id);

		titre.setText(cursor.getString(2));
		nomRadio.setText(cursor.getString(3));
		cause.setText(cursor.getString(4));
		date.setText(cursor.getString(5));
		medecin.setText(cursor.getString(6));
		description.setText(cursor.getString(7));
		interpretation.setText(cursor.getString(8));
	}

	public void afficherRadio() {
		titre.setText("Thorax test");
		nomRadio.setText("Radio du thorax");
		cause.setText("Faiblesse pulmonaire");
		date.setText("22/05/2013");
		medecin.setText("Dr House");
		description.setText("Eminuit autem inter humilia supergressa iam impotentia fines mediocrium delictorum nefanda Clematii.");
		interpretation.setText("Tempore quo primis auspiciis in mundanum fulgorem surgeret victura dum erunt homines Roma.");

	}

	public void afficherRadio(String nom, RadioDAO dao) {

		Cursor cursor = dao.getRadiosCursor("Thorax");
		cursor.moveToFirst();
		String test;
		if (cursor != null && cursor.getCount() > 0) {

			Log.v("TAG", "Cursor count " + cursor.getCount());
			Log.v("TAG", "cursor string " + cursor.getString(2));
			// Log.v("TAG","Cursor "+cursor.moveToFirst());
			test = cursor.getString(2);

			//titre.setText(cursor.getString(2));
			titre.setText("blablbalabldblebrgl");
			Log.v("TAG", "Cursor TEST " + test);
			Log.v("TAG", "TEST !!!!!!!!! " + radio.getTitre());

			//titre.setText("Radio du thorax test");
			// titre.setText(radio.getTitre());

			Log.v("TAG", "Cursor 1 " + titre);
		} else
			Log.v("TAG", "EMPTY ");
		// titre.setText(cursor.getString(2));
		Log.v("TAG", "Cursor 1 " + titre);
		// nomRadio.setText(cursor.getString(3));
		Log.v("TAG", "Cursor 2 " + nomRadio);
		// cause.setText(cursor.getString(4));
		Log.v("TAG", "Cursor 3 " + cause);

		// date.setText(cursor.getString(5));
		// medecin.setText(cursor.getString(6));
		// description.setText(cursor.getString(7));
		// interpretation.setText(cursor.getString(8));
	
	}

	
	
	public void afficherRadio(Radio radio) {
		titre.setText(radio.getTitre());
		nomRadio.setText(radio.getNomRadio());
		cause.setText(radio.getCause());
		date.setText(radio.getDate());
		medecin.setText(radio.getMedecin());
		description.setText(radio.getDescription());
		interpretation.setText(radio.getInterpretation());

	}

	public void onDetach(){
		super.onDetach();
	}
	
}

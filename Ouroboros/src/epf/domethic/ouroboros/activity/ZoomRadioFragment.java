package epf.domethic.ouroboros.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.actionbarsherlock.app.SherlockFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import epf.domethic.ouroboros.R;

public class ZoomRadioFragment extends SherlockFragment {
	
	private ImageView image;
	//private String imageUrl="https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg";
	private String imageUrl ="http://www.prisedevue.com/francais/chapitres/info_pratique/picsbio/radio.jpg";

	//Création de la vue
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_zoom_radio,container, false);
		Log.v("YO", "j'affiche la radio !!!!!!!!!!!");
		
		//On cherche l'image via l'URL
		try {
			  image = (ImageView)view.findViewById(R.id.ivRadioZoom);

			Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
			  image.setImageBitmap(bitmap); 
			} catch (MalformedURLException e) {
			  e.printStackTrace();
			} catch (IOException e) {
			  e.printStackTrace();
			}
		
		return view;
	}

}

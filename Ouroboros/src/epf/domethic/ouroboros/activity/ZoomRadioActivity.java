package epf.domethic.ouroboros.activity;
import com.actionbarsherlock.app.SherlockActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import epf.domethic.ouroboros.R;

public class ZoomRadioActivity extends SherlockActivity {

	//Positionnement de la webView et adresse url de l'image à afficher
	private WebView webView;
	private String imageUrl ="https://raw.github.com/Mikanribu/Ouroboros/master/cliche_thorax.jpg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zoom_radio); //affichage de la vue
		webView = (WebView) findViewById(R.id.wvDoc);

		// On affiche l'image via l'URL
		try {

			webView.loadUrl(imageUrl);
			WebSettings webSettings = webView.getSettings();
			webSettings.setBuiltInZoomControls(true);
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

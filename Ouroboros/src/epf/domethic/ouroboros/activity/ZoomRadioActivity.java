package epf.domethic.ouroboros.activity;



import com.actionbarsherlock.app.SherlockActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import epf.domethic.ouroboros.R;

public class ZoomRadioActivity extends SherlockActivity {

	private WebView webView;
	private String imageUrl ="http://m.goirand.free.fr/evaluation_anatomie/cliche_thorax.jpg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zoom_radio);
		webView = (WebView) findViewById(R.id.wvDoc);
		// On cherche l'image via l'URL
		try {

			webView.loadUrl(imageUrl);
			WebSettings settings = webView.getSettings();
			settings.setBuiltInZoomControls(true);
			settings.setDisplayZoomControls(false);
			settings.setUseWideViewPort(true);
			settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
			settings.setLoadWithOverviewMode(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

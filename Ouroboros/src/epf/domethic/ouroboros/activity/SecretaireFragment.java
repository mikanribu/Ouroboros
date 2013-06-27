package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;

public class SecretaireFragment extends SherlockFragment {
	
	@Override
	//affichage de la vue dans le cas d'une connexion d'une secrétaire médicale
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.secretaire_layout,container, false);
		return view;
	}
}

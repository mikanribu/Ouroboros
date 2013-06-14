package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;



public class HospitalisationEnCoursFragment extends SherlockFragment{
	
	 @Override
	   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
	   		View view = inflater.inflate(R.layout.fragment_afficher_radio,container, false);
	   		return view;
	 }
}

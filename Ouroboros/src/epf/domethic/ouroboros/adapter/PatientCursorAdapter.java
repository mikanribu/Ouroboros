package epf.domethic.ouroboros.adapter;

import epf.domethic.ouroboros.R;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* Classe Adapter permettant d'indiquer la mise en forme 
 * des éléments dans la listView  */
public class PatientCursorAdapter extends CursorAdapter {

	//Constructeur de la classe
	public PatientCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	//Récupération et mise en forme des élèments Adapter pour la listViex patient
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.patients_list_entry, null);
		}
		TextView titre = (TextView) view.findViewById(R.id.tvNom);			//On récupère le TextView titre du layout
		TextView date = (TextView) view.findViewById(R.id.tvDateNaiss);		//On récupère le TextView date du layout
		ImageView image = (ImageView) view.findViewById(R.id.ivSexe);		//On récupère l'ImageView image du layout
		
		//On récupère et on stocke dans des varaibles précédentes les valeurs voulues de la BDD
		String nom = cursor.getString(1);
		String prenom = cursor.getString(2);
		String date_naissance = cursor.getString(4);
		String sexe = cursor.getString(3);

		titre.setText(nom + " - " + prenom);		//Mise en forme du TextView titre et ajout dans le TextView
		date.setText(date_naissance);				//Ajout de la date de naissance dans le TextView
		//On compare si le sexe du patient est masculin ou féminin afin d'attribuer la bonne image "sexe" au patient
		if (sexe.compareTo("Masculin") == 0) {
			image.setImageResource(R.drawable.male);
		} 
		else image.setImageResource(R.drawable.female);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.patients_list_entry, parent,false);
		return view;
	}
}


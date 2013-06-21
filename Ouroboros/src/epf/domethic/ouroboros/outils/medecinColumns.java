package epf.domethic.ouroboros.outils;

import android.provider.BaseColumns;

//Classe permettant de récupérer le nom des colonnes de la table Medecin
public class medecinColumns implements BaseColumns{
	public static final String KEY_ID ="id";
	public static final String KEY_PSEUDO ="pseudo";
	public static final String KEY_NOM ="nom";
	public static final String KEY_PRENOM ="prenom";
	public static final String KEY_MAIL ="mail";
	public static final String KEY_TELEPHONE ="telephone";	
	public static final String KEY_SERVICE ="service";
	public static final String KEY_CHEF ="chef";
}

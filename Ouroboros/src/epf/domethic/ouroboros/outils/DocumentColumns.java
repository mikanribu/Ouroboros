package epf.domethic.ouroboros.outils;

import android.provider.BaseColumns;

//Classe permettant de récupérer le nom des colonnes de la table Radio
public class DocumentColumns implements BaseColumns{
	
	public static final String KEY_ID_PATIENT ="id_patient";
	public static final String KEY_NOM ="nom";
	public static final String KEY_RADIO ="radio";
	public static final String KEY_CAUSE ="cause";
	public static final String KEY_DATE ="date";
	public static final String KEY_MEDECIN ="medecin";
	public static final String KEY_DESCRIPTION ="description";
	public static final String KEY_INTERPRETATION ="interpretation";
}


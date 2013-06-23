package epf.domethic.ouroboros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import epf.domethic.ouroboros.data.PersDBOpenHelper;
import epf.domethic.ouroboros.model.User;
import epf.domethic.ouroboros.outils.PersColumns;

public class UserDAO {

	/* ----------	Déclaration des variables	---------- */
	private Context context;
	
	private SQLiteDatabase database;		//Base de données
	
	private PersDBOpenHelper helper;
	
	/* ----------	Déclaration des fonctions	---------- */
	
	//Constructeur
	public UserDAO(Context context) {
		this.context = context;
		this.helper = new PersDBOpenHelper(context);
		database = helper.getWritableDatabase();	//Autorise l'écriture dans la BDD	
	}
	
	//Fonction permettant de fermer la BDD 
	public void close() {
		helper.close();
	}
	
	//Fonction permettant l'ouverture de la BDD
	public UserDAO open() throws SQLException {
		helper = new PersDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	/* Focntion permettant de récupérer toutes les données de la table utilisateurs */
	public Cursor getUsersCursor(){
		String[] columns = 					//Colonnes à récupérer
				new String[]{PersColumns._ID, PersColumns.KEY_PSEUDO, PersColumns.KEY_MDP,
				PersColumns.KEY_NOM, PersColumns.KEY_PRENOM, PersColumns.KEY_MAIL,
				PersColumns.KEY_TELEPHONE, PersColumns.KEY_SERVICE, PersColumns.KEY_FONCTION};
		//Retourne toutes les données de la tables utilisateurs 
		return	database.query(PersDBOpenHelper.TABLE_USER,columns, null, null, null, null, null, null);
	}
	
	//Fonction qui permet de récupérer l'utilisateur selon son pseudo 
	public Cursor getUsersCursor(String pseudo){
		String[] columns = 
				new String[]{PersColumns._ID, PersColumns.KEY_PSEUDO, PersColumns.KEY_MDP,
				PersColumns.KEY_NOM, PersColumns.KEY_PRENOM, PersColumns.KEY_MAIL,
				PersColumns.KEY_TELEPHONE, PersColumns.KEY_SERVICE, PersColumns.KEY_FONCTION};
		//Clause où le pseudo de l'utilisateur correspond à la chaine de caractère 'like'
		String where = PersColumns.KEY_PSEUDO + " like '%" + pseudo + "%'";
		//Retourne un curseur sur les tables qui correspondent au critère de sélection
		return	database.query(PersDBOpenHelper.TABLE_USER,columns, where, null, null, null, null);
	}
	
	//Fonction qui permet de récupérer l'utilisateur selon son pseudo & mot de passe
	public Cursor getUsersCursor(String pseudo, String mdp){
		String[] columns = 
				new String[]{PersColumns._ID, PersColumns.KEY_PSEUDO, PersColumns.KEY_MDP,
				PersColumns.KEY_NOM, PersColumns.KEY_PRENOM, PersColumns.KEY_MAIL,
				PersColumns.KEY_TELEPHONE, PersColumns.KEY_SERVICE, PersColumns.KEY_FONCTION};
		//Clause concernant le pseudo ainsi que le mot de passe de l'utilisateur
		String where = PersColumns.KEY_PSEUDO + "='" + pseudo + "' and " + 
				PersColumns.KEY_MDP + "='" + mdp + "'";
		Cursor cursor=null;
		cursor=database.query(PersDBOpenHelper.TABLE_USER,columns, where, null, null, null, null);
		return cursor;
	}
	
	/*Fonction qui permet d'ajouter un utilisateur dans la base de données */
	public void ajouterUser(User user){
		ContentValues values = new ContentValues();
		// ajout de toutes les valeurs dans le champ de la BDD correspondant
		//values.put(DocumentColumns.KEY_ID_PATIENT, arrayUsers.get(0));
		values.put(PersColumns.KEY_PSEUDO, user.getPseudo());
		values.put(PersColumns.KEY_MDP, user.getPassword());
		values.put(PersColumns.KEY_NOM, user.getNom());	
		values.put(PersColumns.KEY_PRENOM, user.getPrenom());
		values.put(PersColumns.KEY_MAIL, user.getMail());
		values.put(PersColumns.KEY_TELEPHONE, user.getTelephone());
		values.put(PersColumns.KEY_SERVICE, user.getService());
		values.put(PersColumns.KEY_FONCTION, user.getFonction());

		database.insert(PersDBOpenHelper.TABLE_USER, null, values); 	//Insertion des données dans la BDD
	}

	//Fonction permettant de savoir si la Base de Données est vide
	public boolean dbIsEmpty () {
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ PersDBOpenHelper.TABLE_USER, null);
		
		if (cur != null) {							//Si le curseur n'est pas null
		    cur.moveToFirst();                      // On pointe le curseur sur le premier élément
		    if (cur.getInt (0) == 0) {              // Zero signifie que la table est vide
		    	return true;
		    }
		}
		return false;					//On retourne false si la table n'est pas vide
	}
}

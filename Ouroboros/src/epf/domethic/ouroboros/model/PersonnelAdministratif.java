package epf.domethic.ouroboros.model;

public class PersonnelAdministratif extends Personnel {
	//Classe permettant d'avoir les métiers administrateurs et secrétaires médicales
	
	public String metier;

	//Constructeur de la classe PersonnelAdministratif
	public PersonnelAdministratif(String metier) {
		super();
		this.metier = metier;
	}

	/* ----------	Déclaration des Mutateurs & Accesseurs	----------*/
	
	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}
	
}

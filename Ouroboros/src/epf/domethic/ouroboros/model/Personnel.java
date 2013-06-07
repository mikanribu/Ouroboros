package epf.domethic.ouroboros.model;

public class Personnel {
	//Classe de tous le personnel de l'hôpital médical+administratif
	
	public String nom;
	public String prenom;
	public String mail;
	public int telephone;
	
	public Personnel() {
		super();
	}
	
	public Personnel(String nom, String prenom, String mail, int telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
	}
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public int getTelephone() {
		return telephone;
	}


	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}


}

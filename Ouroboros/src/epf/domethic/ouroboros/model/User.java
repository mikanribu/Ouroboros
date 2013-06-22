package epf.domethic.ouroboros.model;

public class User {

	public String pseudo;
	public String password;
	public String nom;
	public String prenom;
	public String mail;
	public String telephone;
	public String service;
	public int fonction;
	
	
	public User(String pseudo, String password, String nom, String prenom,
			String mail, String telephone, String service, int fonction) {
		super();
		this.pseudo = pseudo;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.service = service;
		this.fonction = fonction;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getFonction() {
		return fonction;
	}
	public void setFonction(int fonction) {
		this.fonction = fonction;
	}
}

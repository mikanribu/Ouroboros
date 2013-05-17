package epf.domethic.ouroboros.model;

import java.util.Date;

public class Patient {
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private enum Sexe {
		HOMME,
		FEMME;
	}
	private Sexe sexe;
	
	public Patient (){
		
	}
	
	public Patient(String valNom, String valPrenom, Date valDate, Sexe valSexe){
		nom = valNom;
		prenom = valPrenom;
		dateNaissance = valDate;
		sexe = valSexe;
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}


}

package epf.domethic.ouroboros.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
	
	public enum Sexe {
		HOMME,
		FEMME;
	}
	
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private Sexe sexe;
	
	public static List<Patient> ALL;
	
	static{
		ALL = new ArrayList<Patient>();
		for(int i = 0; i < 20; i++){
			Patient p = new Patient("Nom" + i, "Prénom" + i, new Date(), Sexe.FEMME);
			ALL.add(p);		
		}
	}
	
	


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
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}


}

package epf.domethic.ouroboros.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
	
	/*	----------	Déclaration des attributs de la classe Patient	----------*/
	
	public enum Sexe {
		Masculin,
		Feminin;
	}
	
	private int id;
	
	private String nom;
	private String prenom;
	private Sexe sexe;
	private Date dateNaissance;
	private String lieuNaissance;
	private String adresse;
	private String ville;
	private String codePostal;
	private String pays;
	private String nationalite;
	private String telephone;
	private String numSS;
	private String medecinTraitant;
	private boolean hospitalise;

	public static List<Patient> ALL;
	
	static{
		ALL = new ArrayList<Patient>();
	}

	//Constructeur de la classe Patient sans paramètre
	public Patient (){
		
	}
	
	//Constructeur de la classe patient avec comme paramètre tous les attributs de la classe 
	public Patient( String nom, String prenom, Sexe sexe,
			Date dateNaissance, String lieuNaissance, String adresse,
			String ville, String code_postal, String pays, String nationalite,
			String telephone, String numSS, String medecinTraitant,
			boolean hospitalise) {
		super();
		//this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = code_postal;
		this.pays = pays;
		this.nationalite = nationalite;
		this.telephone = telephone;
		this.numSS = numSS;
		this.medecinTraitant = medecinTraitant;
		this.hospitalise = hospitalise;
	}
	
	/* ----------	Déclaration des Mutateurs & Accesseurs	----------*/
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String code_postal) {
		this.codePostal = code_postal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumSS() {
		return numSS;
	}

	public void setNumSS(String numSS) {
		this.numSS = numSS;
	}

	public String getMedecinTraitant() {
		return medecinTraitant;
	}

	public void setMedecinTraitant(String medecinTraitant) {
		this.medecinTraitant = medecinTraitant;
	}

	public boolean isHospitalise() {
		return hospitalise;
	}

	public void setHospitalise(boolean hospitalise) {
		this.hospitalise = hospitalise;
	}

}

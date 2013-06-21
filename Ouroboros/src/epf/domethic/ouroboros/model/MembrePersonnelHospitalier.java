package epf.domethic.ouroboros.model;

public class MembrePersonnelHospitalier extends PersonnelMedical{

	public int niveauAcces;

	//Constructeur de la classe MembrePersonnelHospitalier
	public MembrePersonnelHospitalier(String service, int niveauAcces) {
		super(service);
		this.niveauAcces = niveauAcces;
	}

	/* ----------	Déclaration des Mutateurs & Accesseurs	----------*/
	
	public int getNiveauAcces() {
		return niveauAcces;
	}

	public void setNiveauAcces(int niveauAcces) {
		this.niveauAcces = niveauAcces;
	}
	
	
}

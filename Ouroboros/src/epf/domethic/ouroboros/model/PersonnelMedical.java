package epf.domethic.ouroboros.model;

public class PersonnelMedical extends Personnel {
	//Classe personnel uniquement médical. Classe mère de Medecin, MermbrePersonnelHospitalier
	
	public String service;
	
	public PersonnelMedical(String service) {
		super();
		this.service = service;
	}
	
	public PersonnelMedical() {
		super();
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}


}

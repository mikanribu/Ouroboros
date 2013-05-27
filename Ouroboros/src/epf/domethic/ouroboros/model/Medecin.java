package epf.domethic.ouroboros.model;

public class Medecin extends PersonnelMedical {
	//Classe d'un médecin. On peut faire la différence entre le chef et le medecin "normal"
	
	public enum Chef {
		OUI,
		NON;
	}
	
	private Chef chef;

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Medecin(String service, Chef chef) {
		super(service);
		this.chef = chef;
	}
	
}

package epf.domethic.ouroboros.comparator;

import java.util.Comparator;

import epf.domethic.ouroboros.model.Patient;

//classe qui permet de comparer des éléments de type "Patient"
public class NameComparator implements Comparator<Patient>{
	
	//Méthode qui permet de comparer deux patients 
	//sur la base de leur nom de famille
	public int compare(Patient o1, Patient o2) 
	{
	    return o1.getNom().compareToIgnoreCase(o2.getNom());
	    
	}

	

}

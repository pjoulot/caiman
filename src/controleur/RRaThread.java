package controleur;

import java.util.List;
import modele.EtatEnvironnement;
import modele.Ressource;

public class RRaThread extends Thread {

	private Moniteur<EtatEnvironnement> moniteurRRaInput;
	private Moniteur<EtatEnvironnement> moniteurRRaOutput;
	private List<String> services;

	public RRaThread(Moniteur<EtatEnvironnement> mon1, Moniteur<EtatEnvironnement> mon2, List<String> services) {
		this.moniteurRRaInput = mon1;
		this.moniteurRRaOutput = mon2;
		this.services = services;
	}
	
	public void run() {
		EtatEnvironnement a;
		EtatEnvironnement result;
		while(true) {
			result = new EtatEnvironnement();
			a = moniteurRRaInput.cons();
			System.out.println("RRa Input : consommation");
			for ( Ressource s : a.getRessources()) {
				if(services.contains(s.getService())) {
					result.getRessources().add(s);
				}
			}
			moniteurRRaOutput.prod(result);
			System.out.println("RRa Output : production");
		}
	}
	
}

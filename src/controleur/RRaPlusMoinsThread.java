package controleur;

import java.util.Collection;
import java.util.List;

import modele.*;

public class RRaPlusMoinsThread extends Thread {
	
	private RRaPlusMoins plus;
	private RRaPlusMoins moins;
	private List<String> services;
	private Moniteur<EtatEnvironnement> moniteurRcInput;
	private Moniteur<CouplePlusMoins> moniteurRcOutput;
	
	
	public RRaPlusMoinsThread(Moniteur<EtatEnvironnement> mon1, Moniteur<CouplePlusMoins> mon2, List<String> f) {
		this.moniteurRcInput = mon1;
		this.moniteurRcOutput = mon2;
		this.services = f;
		this.plus = new RRaPlusMoins();
		this.moins = new RRaPlusMoins();
		
	}
	
	public void run() {
		EtatEnvironnement a;
		RRaPlusMoins temp;
		while(true) {
			temp = this.plus;
			a = this.moniteurRcInput.cons();
			System.out.println("Rc Input : consommation");
			
			//On calcule le nouveau RRa-
			temp.getRessources().getQ().removeAll(a.getRessources());
			this.moins = temp;
			
			//On calcule le nouveau RRa+
			a.getRessources().removeAll((Collection<?>) this.plus.getRessources().getQ());
			this.plus = new RRaPlusMoins(new Fifo<Ressource>(a.getRessources()));
			
			//Correspondance avec les services
			for ( Ressource s : this.plus.getRessources().getQ()) {
				if(!this.services.contains(s.getService())) {
					this.plus.getRessources().getQ().remove(s);
				}
			}
			for ( Ressource s : this.moins.getRessources().getQ()) {
				if(!this.services.contains(s.getService())) {
					this.moins.getRessources().getQ().remove(s);
				}
			}
			//Envoi du résultat (attention à l'ordre pour défiler dans i'Q)
			moniteurRcOutput.prod(new CouplePlusMoins(plus, moins, a.getT()));
			System.out.println("Rc Output : production");
		}
	}

}

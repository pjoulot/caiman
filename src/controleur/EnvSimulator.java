package controleur;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import modele.EtatEnvironnement;
import modele.Fifo;
import modele.Ressource;

public class EnvSimulator extends Thread {
	
	private Fifo<EtatEnvironnement> env;
	
	public void run() {
		Ressource a;
		String ressourceNoms[] = {"Orion", "Aurora", "Dédale", "Prométhée", "Korolev", "Phoenix"};
		String ressourceServices[] = {"bluetooth", "température", "luminosité", "tilt sensor"};
		
		Random random;
		int nbRs;
		int nbSer;
		double qualite;
		double distance;
		List<Ressource> l;
		
		while(true) {
			distance = (Math.random() * 19) + 1;
			random = new Random();
			qualite = Math.random();
			l = new LinkedList<Ressource>();
			for(int i=0; i < ((int)(Math.random() * 6) + 4); i++) {
				nbRs = (int)(Math.random() * ressourceNoms.length);
				nbSer = (int)(Math.random() * ressourceServices.length);
				a = new Ressource(ressourceNoms[nbRs], ressourceServices[nbSer], random.nextBoolean(), distance, qualite);
				l.add(a);
			}
			env.enqueue(new EtatEnvironnement(l));
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

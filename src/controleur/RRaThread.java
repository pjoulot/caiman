package controleur;

import modele.Fifo;
import modele.RRa;
import modele.Ressource;

public class RRaThread extends Thread {

	Fifo env;
	RRa rra;
	Fifo services;
	
	public RRaThread(RRa rra) {
		this.rra = rra;
	}
	
	public void run() {
		for(Object r : this.env.getQ()) {
			this.rra.add((Ressource)r);
		}
	}
	
}

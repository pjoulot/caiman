package controleur;

import java.util.LinkedList;

import modele.*;

public class RRaPlusMoinsThread extends Thread {
	
	RRaPlus plus;
	RRaMoins moins;
	Fifo env;
	RRa rra;
	Fifo services;
	
	
	public RRaPlusMoinsThread(RRaPlus plus, RRaMoins moins) {
		this.plus = plus;
		this.moins = moins;
	}
	
	public void run() {
		for(Object r : this.env.getQ()) {
			if( ! rra.getRessources().contains(r) ) {
				this.plus.add((Ressource)r);
			}
		}
		for(Object r : this.rra.getRessources().getQ()) {
			if( ! this.env.contains(r) ) {
				this.moins.add((Ressource)r);
			}
		}
	}

}

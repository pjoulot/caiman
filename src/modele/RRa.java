package modele;

import java.util.ArrayList;
import java.util.List;

public class RRa {
	private Fifo ressources;
	
	public RRa(Fifo services) {
		this.ressources = new Fifo();
	}
	
	public void add(Ressource r) {
		ressources.enqueue(r);
	}
	
	public Fifo getRessources() {
		return ressources;
	}

	public void setRessources(Fifo ressources) {
		this.ressources = ressources;
	}
}

package modele;

import java.util.LinkedList;
import java.util.List;

public class EtatEnvironnement {
	
	private List<Ressource> ressources;

	public EtatEnvironnement(List<Ressource> ressources) {
		this.ressources = ressources;
	}
	
	public EtatEnvironnement() {
		this.ressources = new  LinkedList<Ressource>();
	}

	public List<Ressource> getRessources() {
		return ressources;
	}

}

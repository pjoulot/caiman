package modele;

import java.util.List;

public class EtatEnvironnement {
	
	private List<Ressource> ressources;

	public EtatEnvironnement(List<Ressource> ressources) {
		this.ressources = ressources;
	}

	public List<Ressource> getRessources() {
		return ressources;
	}

}

package modele;

public class RRa {
	
	private Fifo<Ressource> ressources;
	
	public RRa(Fifo<Ressource> services) {
		this.ressources = new Fifo<Ressource>();
	}
	
	public void add(Ressource r) {
		ressources.enqueue(r);
	}
	
	public Fifo<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(Fifo<Ressource> ressources) {
		this.ressources = ressources;
	}
}

package modele;

public class RRaPlusMoins {

	private Fifo<Ressource> ressources;
	
	public RRaPlusMoins() {
		this.ressources = new Fifo<Ressource>();
	}
	
	public RRaPlusMoins(Fifo<Ressource> f) {
		this.ressources = f;
	}
	
	public void add(Ressource r) {
		ressources.enqueue(r);
	}
	
	public Fifo<Ressource> getRessources() {
		return ressources;
	}
}

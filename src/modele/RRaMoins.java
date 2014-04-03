package modele;

public class RRaMoins {
	private Fifo ressources;
	
	public RRaMoins() {
		this.ressources = new Fifo();
	}
	
	public void add(Ressource r) {
		ressources.enqueue(r);
	}
}

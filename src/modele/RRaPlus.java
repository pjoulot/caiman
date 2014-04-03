package modele;

public class RRaPlus {
	private Fifo ressources;
	
	public RRaPlus() {
		this.ressources = new Fifo();
	}
	
	public void add(Ressource r) {
		ressources.enqueue(r);
	}
}

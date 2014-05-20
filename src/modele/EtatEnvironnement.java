package modele;

import java.util.LinkedList;
import java.util.List;

public class EtatEnvironnement {
	
	private List<Ressource> ressources;
	private int t;

	public EtatEnvironnement(List<Ressource> ressources) {
		this.ressources = ressources;
		this.t = 0;
	}
	
	public void setT(int t) {
		this.t = t;
	}

	public EtatEnvironnement() {
		this.ressources = new  LinkedList<Ressource>();
		this.t = 0;
	}

	public int getT() {
		return t;
	}

	public List<Ressource> getRessources() {
		return ressources;
	}
	
	@Override 
	public String toString() {
		String s = "";
		s+="T="+t+" | ";
		s+="Ressources:";
		for (Ressource r: ressources) {
			s+=r.getNom()+" ("+r.getService()+")";
		}
		return s;
	}

}

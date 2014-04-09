package modele;

public class Ressource {
	
	private String service;
	private String nom;
	private boolean synchro;
	private double distance;
	private double qualite; 
	
	public Ressource(String nom, String service, boolean synchro, double distance, double qualite) {
		this.distance = distance;
		this.nom = nom;
		this.qualite = qualite;
		this.service = service;
		this.synchro = synchro;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isSynchro() {
		return synchro;
	}

	public void setSynchro(boolean synchro) {
		this.synchro = synchro;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public double getQualite() {
		return qualite;
	}

	public void setQualite(float qualite) {
		this.qualite = qualite;
	}
	
	
}

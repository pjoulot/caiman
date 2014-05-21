package controleur;

import java.util.LinkedList;
import java.util.List;

import modele.CouplePlusMoins;
import modele.EtatEnvironnement;
import modele.RRaPlusMoins;
import modele.Ressource;

public class SolutionThread extends Thread {

	private Moniteur<CouplePlusMoins> moniteurRcOutput;
	private Moniteur<EtatEnvironnement> moniteurRRaOutput;
	private Moniteur<EtatEnvironnement> bind;
	private List<String> services;
	private EtatEnvironnement iq;
	private EtatEnvironnement lastIq;

	public SolutionThread(Moniteur<CouplePlusMoins> mon1, Moniteur<EtatEnvironnement> mon2, List<String> services, Moniteur<EtatEnvironnement> bind) {
		this.moniteurRcOutput = mon1;
		this.moniteurRRaOutput = mon2;
		this.services = services;
		this.iq = new EtatEnvironnement();
		this.lastIq =  new EtatEnvironnement();
		this.bind = bind;
	}
	
	private EtatEnvironnement adapt(EtatEnvironnement rra,
			List<String> services2, EtatEnvironnement lastIq,
			RRaPlusMoins rRaMoins, RRaPlusMoins rRaPlus) {
		
		 EtatEnvironnement iqt = new EtatEnvironnement();
			
		for(Ressource r: rRaMoins.getRessources().getQ()) {
			if(rra.getRessources().contains(r)) {
				rra.getRessources().remove(r);
			}
		}
		for(Ressource r: rRaPlus.getRessources().getQ()) {
			if(!rra.getRessources().contains(r)) {
				rra.getRessources().add(r);
			}
		}
		
		for(Ressource r: iq.getRessources()) {
			if(!rRaMoins.getRessources().getQ().contains(r)) {
				iqt.getRessources().add(r);
			}
		}
		
		if(iqt.equals(iq)) {
			return iq;
		}
		
		List<String> servicesPrim = this.services;
		
		for(Ressource r: iqt.getRessources()) {
			if(servicesPrim.contains(r.getService())) {
				servicesPrim.remove(r.getService());
			}
		}
		
		iq = computeBestSolution(rra, servicesPrim);
		
		
		for(Ressource r: iqt.getRessources()) {
			if(!iq.getRessources().contains(r)) {
				iq.getRessources().add(r);
			}
		}
		
		return iq;
	}

	public void run() {
		EtatEnvironnement rra = new EtatEnvironnement();
		CouplePlusMoins rraplusmoins = new CouplePlusMoins(new RRaPlusMoins(), new RRaPlusMoins(), 0);
		long time = 0;
		
		/*******************************/
		int firstIteration = 1;
		
		/***************************/
		/* A chaque insertion dans moniteurRRaOutput ou moniteurRcOutput, on effectue le calcul de la solution */
		while(true) {
			
			if(time == 0) {
				rra = moniteurRRaOutput.cons();
				rraplusmoins = moniteurRcOutput.cons();
				time = rra.getT()+1;
				iq = computeBestSolution(rra, services);
				lastIq = iq;
				bind.prod(iq);
				System.out.println(bind.cons().toString());
			}
			else {
				System.out.println("passe"+rraplusmoins.getT());
				rraplusmoins = moniteurRcOutput.cons();
				time = rraplusmoins.getT()+1;
				if(rraplusmoins.getT() == 4) {
					time = 0;
				}
				iq = adapt(rra, services, lastIq, rraplusmoins.getRRaMoins(), rraplusmoins.getRRaPlus());
				if( iq != lastIq) {
					lastIq = iq;
					bind.prod(iq);
					System.out.println(bind.cons().toString());
				}
			}
		}
	}

	private EtatEnvironnement computeBestSolution(EtatEnvironnement a,
			List<String> services2) {
		
		for(String s: services2) {
			List<Ressource> ressourceSet = new LinkedList<Ressource>();
			for(Ressource r: a.getRessources()) {
				if(r.getService() == s) {
					ressourceSet.add(r);
				}
			}
			if(ressourceSet.size() != 0 ){
				Ressource solution = selectRessource(ressourceSet);
				if(solution != null) {
					this.iq.getRessources().add(solution);
				}
			}
		}
		return this.iq;
		
	}

	private Ressource selectRessource(List<Ressource> ressourceSet) {
		Ressource max = ressourceSet.get(0);
		for(Ressource r: ressourceSet) {
			if(max.getQualite() < r.getQualite()) {
				max = r;
			}
		}
		return max;
	}
	
}


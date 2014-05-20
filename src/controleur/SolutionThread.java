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
	
	public void run() {
		EtatEnvironnement rra = null;
		CouplePlusMoins rraplusmoins = null;
		long time = 0;
		int firstIteration = 1;
		
		/*******************************/
		/*
		rra = moniteurRRaOutput.cons();
		time = rra.getT();
		iq = computeBestSolution(rra, services);
		lastIq = iq;
		bind.prod(iq);
		System.out.println(bind.cons().toString());*/
		
		/***************************/
		/* A chaque insertion dans moniteurRRaOutput ou moniteurRcOutput, on effectue le calcul de la solution */
		while(true) {
			
			/*//Si on doit consommer dans RelRes
			if(time == 0  ) {
				rra = moniteurRRaOutput.cons();
				time = rra.getT();
				iq = computeBestSolution(rra, services);
				lastIq = iq;
				bind.prod(iq);
			}*/
			//Si on doit consommer dans DeltaRelRes
			//else {
			if(time != 0) {
				rraplusmoins = moniteurRcOutput.cons();
				time = rraplusmoins.getT();
				iq = adapt(rra, services, lastIq, rraplusmoins.getRRaMoins(), rraplusmoins.getRRaPlus());
				if( iq != lastIq) {
					lastIq = iq;
					bind.prod(iq);
				}
			}
			else {
				rra = moniteurRRaOutput.cons();
				if(firstIteration == 1) {
					firstIteration = 0;
					time = rra.getT()+1;
				}
				else {
					time = rra.getT();
				}
				iq = computeBestSolution(rra, services);
				lastIq = iq;
				bind.prod(iq);
			}
			System.out.println(time);
		}
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


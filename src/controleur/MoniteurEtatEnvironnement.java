package controleur;

import modele.EtatEnvironnement;

public class MoniteurEtatEnvironnement {
		EtatEnvironnement tampon; 
		boolean estVide= true;
		
		public MoniteurEtatEnvironnement() {
		}
		
		synchronized void prod(EtatEnvironnement mot)
		{	if (!estVide) 
			{
				try {wait();}catch (InterruptedException e){};
			}
			tampon = mot; 
			estVide = false; 
			notify();
		}
		
		synchronized EtatEnvironnement cons(){
			if (estVide) 
			{
				try {wait();} catch (InterruptedException e){};
			}
			EtatEnvironnement res = tampon; estVide = true; 
			notify();
			return res;
		}
}

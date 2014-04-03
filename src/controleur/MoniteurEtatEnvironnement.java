package controleur;

import modele.EtatEnvironnement;

public class MoniteurEtatEnvironnement {
		EtatEnvironnement tampon; 
		private final int limite;
		private int nb;
		
		public MoniteurEtatEnvironnement(int limite) {
				this.limite = limite;
				this.nb = limite;
		}
		
		synchronized void prod(EtatEnvironnement mot)
		{	if (nb == 0) 
			{
				try {wait();}catch (InterruptedException e){};
			}
			tampon = mot; 
			nb--;
			notify();
		}
		
		synchronized EtatEnvironnement cons(){
			if (nb == this.limite) 
			{
				try {wait();} catch (InterruptedException e){};
			}
			EtatEnvironnement res = tampon; 
			nb++;
			notify();
			return res;
		}
}

package controleur;

import modele.EtatEnvironnement;
import modele.Fifo;

public class MoniteurEtatEnvironnement {
		EtatEnvironnement tampon;
		private Fifo f;
		private final int limite;
		private int nb;
		
		public MoniteurEtatEnvironnement(int limite, Fifo f) {
				this.limite = limite;
				this.nb = limite;
				this.f = f;
		}
		
		synchronized void prod(EtatEnvironnement mot)
		{	if (this.nb == 0) 
			{
				try {wait();}catch (InterruptedException e){};
			}
			this.tampon = mot; 
			this.f.enqueue(tampon);
			this.nb--;
			notify();
		}
		
		synchronized EtatEnvironnement cons(){
			if (this.nb == this.limite) 
			{
				try {wait();} catch (InterruptedException e){};
			}
			EtatEnvironnement res; 
			this.nb++;
			res = (EtatEnvironnement) this.f.dequeue();
			notify();
			return res;
		}
}

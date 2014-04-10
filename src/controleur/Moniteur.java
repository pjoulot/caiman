package controleur;

import modele.Fifo;

public class Moniteur<T> {
		T tampon;
		private Fifo<T> f;
		private final int limite;
		private int nb;
		
		public Moniteur(int limite, Fifo<T> f) {
				this.limite = limite;
				this.nb = limite;
				this.f = f;
		}
		
		synchronized void prod(T mot)
		{	if (this.nb == 0) 
			{
				try {wait();}catch (InterruptedException e){};
			}
			this.tampon = mot; 
			this.f.enqueue(tampon);
			this.nb--;
			notify();
		}
		
		synchronized T cons(){
			if (this.nb == this.limite) 
			{
				try {wait();} catch (InterruptedException e){};
			}
			T res; 
			this.nb++;
			res = this.f.dequeue();
			notify();
			return res;
		}
}

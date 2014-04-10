package controleur;

import modele.EtatEnvironnement;
import modele.Fifo;

public class Dispatcher extends Thread {
	
	private Fifo<EtatEnvironnement> env;
	private Moniteur<EtatEnvironnement> moniteurRRa;
	private Moniteur<EtatEnvironnement> moniteurRc;

	public Dispatcher(Moniteur<EtatEnvironnement> mon1, Moniteur<EtatEnvironnement> mon2, Fifo<EtatEnvironnement> env) {
		this.env = env;
		this.moniteurRRa = mon1;
		this.moniteurRc = mon2;
	}
	
	public void run() {
		int tau = 5;
		int t = 0;
		while(true) {
			
			EtatEnvironnement a = (EtatEnvironnement) env.dequeue();
			System.out.println("ENV ==> Rc Input");
			moniteurRc.prod(a);
			if(t%tau == 0){
				System.out.println("ENV ==> RRa Input");
				moniteurRRa.prod(a);
				t = 0;
			}
			
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t++;
		}
	}
	
}

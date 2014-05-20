package controleur;

import modele.EtatEnvironnement;
import modele.Fifo;

public class Dispatcher extends Thread {
	
	private Fifo<EtatEnvironnement> env;
	private Moniteur<EtatEnvironnement> moniteurRRa;
	private Moniteur<EtatEnvironnement> moniteurRc;
	private int frequenceEnvA;
	private int frequenceEnvC;

	/* Utiliser le constructeur tel que freqEnvA > freqEnvC */
	public Dispatcher(Moniteur<EtatEnvironnement> mon1, Moniteur<EtatEnvironnement> mon2, Fifo<EtatEnvironnement> env, int freqEnvA, int freqEnvC) {
		this.env = env;
		this.moniteurRRa = mon1;
		this.moniteurRc = mon2;
		this.frequenceEnvA = freqEnvA;
		this.frequenceEnvC = freqEnvC;
	}
	
	public void run() {
		
		int t = 0;
		while(true) {
			
			EtatEnvironnement a = (EtatEnvironnement) env.dequeue();
			
			if(t % this.frequenceEnvC == 0){
				System.out.println("ENV ==> Rc Input");
				a.setT(t+1);
				moniteurRc.prod(a);
			}
			
			if(t % this.frequenceEnvA == 0){
				System.out.println("ENV ==> RRa Input");
				a.setT(t);
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

	public int getFrequenceEnvA() {
		return frequenceEnvA;
	}
	
	public int getFrequenceEnvC() {
		return frequenceEnvC;
	}

}

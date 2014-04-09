package controleur;

import modele.EtatEnvironnement;
import modele.Fifo;

public class Dispatcher extends Thread {
	
	private Fifo env;
	private MoniteurEtatEnvironnement moniteurRRa;
	private MoniteurEtatEnvironnement moniteurRc;

	public Dispatcher(MoniteurEtatEnvironnement mon1, MoniteurEtatEnvironnement mon2, Fifo env) {
		this.env = env;
		this.moniteurRRa = mon1;
		this.moniteurRc = mon2;
	}
	
	public void run() {
		int tau = 5;
		int t = 0;
		while(true) {
			
			EtatEnvironnement a = (EtatEnvironnement) env.dequeue();
			moniteurRc.prod(a);
			if(t%tau == 0){
				moniteurRRa.prod(a);
			}
			
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t++;
		}
	}
	
}

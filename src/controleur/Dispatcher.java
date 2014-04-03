package controleur;

import modele.EtatEnvironnement;
import modele.Fifo;

public class Dispatcher extends Thread {
	
	private Fifo env;
	private MoniteurEtatEnvironnement moniteurRRa;
	private MoniteurEtatEnvironnement moniteurRc;
	private Fifo inputRRa;
	private Fifo inputRc;

	public Dispatcher(MoniteurEtatEnvironnement mon1, MoniteurEtatEnvironnement mon2, Fifo env, Fifo inputRRa, Fifo inputRc) {
		this.env = env;
		this.moniteurRRa = mon1;
		this.moniteurRc = mon2;
		this.inputRRa = inputRRa;
		this.inputRc = inputRc;
	}
	
	public void run() {
		int tau = 5;
		int t = 0;
		while(true) {
			
			EtatEnvironnement a = (EtatEnvironnement) env.dequeue();
			inputRc.enqueue(a);
			if(t%tau == 0){
				inputRRa.enqueue(a);
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

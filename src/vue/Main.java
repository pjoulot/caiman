package vue;

import controleur.Dispatcher;
import controleur.Moniteur;
import controleur.RRaPlusMoinsThread;
import controleur.RRaThread;
import modele.*;

import java.util.LinkedList;
import java.util.List;

public class Main extends Thread{
	
	public static void main(String[] args) {
		
		Fifo<EtatEnvironnement> env = new Fifo<EtatEnvironnement>();
		Ressource a = new Ressource("Rs1", "blootooth", false, 2, 0.6);
		Ressource b = new Ressource("Rs2", "blootooth", false, 4, 0.7);
		List<Ressource> l = new LinkedList<Ressource>();
		l.add(a);
		l.add(b);
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		
		Fifo<EtatEnvironnement> inputRRa = new Fifo<EtatEnvironnement>();
		Fifo<EtatEnvironnement> inputRc = new Fifo<EtatEnvironnement>();
		Fifo<EtatEnvironnement> outputRRa = new Fifo<EtatEnvironnement>();
		Fifo<RRaPlusMoins> outputRc = new Fifo<RRaPlusMoins>();
		Fifo<Ressource> bind = new Fifo<Ressource>();
		
		List<String> services = new LinkedList<String>();
		services.add("bluetooth");
		services.add("température");
		
		Moniteur<EtatEnvironnement> moniteurRRa = new Moniteur<EtatEnvironnement>(10, inputRRa);
		Moniteur<EtatEnvironnement> moniteurRc = new Moniteur<EtatEnvironnement>(50, inputRc);
		
		Moniteur<EtatEnvironnement> moniteurRRaOutput = new Moniteur<EtatEnvironnement>(10, outputRRa);
		Moniteur<RRaPlusMoins> moniteurRcOutput = new Moniteur<RRaPlusMoins>(50, outputRc);
		
		Dispatcher dispatcher = new Dispatcher(moniteurRRa, moniteurRc, env);
		dispatcher.start();
		
		RRaThread rrathread = new RRaThread(moniteurRRa, moniteurRRaOutput, services);
		rrathread.start();
		
		RRaPlusMoinsThread rraplusmoinsthread = new RRaPlusMoinsThread(moniteurRc, moniteurRcOutput, services);
		rraplusmoinsthread.start();
		
		try {
			sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		
	}
}

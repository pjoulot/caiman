package vue;

import controleur.Dispatcher;
import controleur.Moniteur;
import controleur.RRaPlusMoinsThread;
import controleur.RRaThread;
import controleur.SolutionThread;
import modele.*;

import java.util.LinkedList;
import java.util.List;

public class Main extends Thread{
	
	public static void main(String[] args) {
		
		Fifo<EtatEnvironnement> env = new Fifo<EtatEnvironnement>();
		Ressource a = new Ressource("Rs1", "bluetooth", false, 2, 0.6);
		Ressource b = new Ressource("Rs2", "bluetooth", false, 4, 0.7);
		Ressource c = new Ressource("Rs3", "température", true, 4, 0.8);
		Ressource d = new Ressource("Rs4", "température", true, 4, 0.9);
		Ressource f = new Ressource("Rs2", "bluetooth", false, 4, 1);
		List<Ressource> l = new LinkedList<Ressource>();
		l.add(a);
		l.add(b);
		l.add(c);
		l.add(d);
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		l.add(f);
		env.enqueue(new EtatEnvironnement(l));
		
		Fifo<EtatEnvironnement> inputRRa = new Fifo<EtatEnvironnement>();
		Fifo<EtatEnvironnement> inputRc = new Fifo<EtatEnvironnement>();
		Fifo<EtatEnvironnement> outputRRa = new Fifo<EtatEnvironnement>();
		Fifo<CouplePlusMoins> outputRc = new Fifo<CouplePlusMoins>();
		Fifo<EtatEnvironnement> bind = new Fifo<EtatEnvironnement>();
		
		List<String> services = new LinkedList<String>();
		services.add("bluetooth");
		services.add("température");
		
		Moniteur<EtatEnvironnement> moniteurRRa = new Moniteur<EtatEnvironnement>(10, inputRRa);
		Moniteur<EtatEnvironnement> moniteurRc = new Moniteur<EtatEnvironnement>(50, inputRc);
		
		Moniteur<EtatEnvironnement> moniteurRRaOutput = new Moniteur<EtatEnvironnement>(10, outputRRa);
		Moniteur<CouplePlusMoins> moniteurRcOutput = new Moniteur<CouplePlusMoins>(50, outputRc);
		
		Moniteur<EtatEnvironnement> moniteurBind = new Moniteur<EtatEnvironnement>(50, bind);
		
		Dispatcher dispatcher = new Dispatcher(moniteurRRa, moniteurRc, env, 5, 1);
		dispatcher.start();
		
		RRaThread rrathread = new RRaThread(moniteurRRa, moniteurRRaOutput, services);
		rrathread.start();
		
		RRaPlusMoinsThread rraplusmoinsthread = new RRaPlusMoinsThread(moniteurRc, moniteurRcOutput, services);
		rraplusmoinsthread.start();
		
		SolutionThread solutionThread = new SolutionThread(moniteurRcOutput, moniteurRRaOutput, services, moniteurBind);
		solutionThread.start();
		
		try {
			sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		try {
			sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

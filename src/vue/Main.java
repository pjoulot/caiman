package vue;

import controleur.Dispatcher;
import controleur.MoniteurEtatEnvironnement;
import modele.*;
import java.util.LinkedList;
import java.util.List;

public class Main extends Thread{
	
	public static void main(String[] args) {
		
		Fifo env = new Fifo();
		Ressource a = new Ressource("Rs1", "blootooth", false, 2, 0.6);
		Ressource b = new Ressource("Rs2", "blootooth", false, 4, 0.7);
		List<Ressource> l = new LinkedList<Ressource>();
		l.add(a);
		l.add(b);
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		env.enqueue(new EtatEnvironnement(l));
		
		Fifo inputRRa = new Fifo();
		Fifo inputRc = new Fifo();
		Fifo outputRRa = new Fifo();
		Fifo outputRc = new Fifo();
		Fifo bind = new Fifo();
		Fifo services = new Fifo();
		
		MoniteurEtatEnvironnement moniteurRRa = new MoniteurEtatEnvironnement(10, inputRRa);
		MoniteurEtatEnvironnement moniteurRc = new MoniteurEtatEnvironnement(50, inputRc);
		
		Dispatcher dispatcher = new Dispatcher(moniteurRRa, moniteurRc, env);
		dispatcher.start();
		
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

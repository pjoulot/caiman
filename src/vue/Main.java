package vue;

import controleur.Dispatcher;
import controleur.MoniteurEtatEnvironnement;
import modele.*;

public class Main {
	
	public static void main() {
		MoniteurEtatEnvironnement moniteurRRa = new MoniteurEtatEnvironnement(10);
		MoniteurEtatEnvironnement moniteurRc = new MoniteurEtatEnvironnement(50);
		Fifo env = new Fifo();
		Fifo inputRRa = new Fifo();
		Fifo inputRc = new Fifo();
		Fifo outputRRa = new Fifo();
		Fifo outputRc = new Fifo();
		Fifo bind = new Fifo();
		Fifo services = new Fifo();
		
		Dispatcher dispatcher = new Dispatcher(moniteurRRa, moniteurRc, env, inputRRa, inputRc);
		dispatcher.start();
		
	}
}

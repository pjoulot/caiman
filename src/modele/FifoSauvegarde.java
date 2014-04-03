package modele;

import java.util.LinkedList;

public class FifoSauvegarde {
	    private LinkedList<Ressource> q ;
	    
	    public FifoSauvegarde() { 
	    	q = new LinkedList<Ressource> (); 
	    }
	    
	    public boolean isEmpty() { 
	    	return q.isEmpty() ;
	    }

	    public LinkedList<Ressource> getQ() {
			return q;
		}

		public synchronized void enqueue(Ressource x) {
	    	q.addLast(x);
	    	notify();
	    }

	    public synchronized Ressource dequeue() { 
	    	if(isEmpty()) {
	            try {
	                wait();
	            } catch(InterruptedException ie) {
	                ie.printStackTrace();
	            }
	        }
	    	Ressource n = q.removeFirst();
	    	return n;
	    }
	    
	    public boolean contains(Ressource r) { 
	    	return q.contains(r) ; 
	    }

	    public String toString() { 
	    	return q.toString() ; 
	    }
}

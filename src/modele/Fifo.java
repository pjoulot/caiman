package modele;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fifo {
	    private List q = Collections.synchronizedList(new LinkedList());
	    
	    public Fifo() { 
	    	q = new LinkedList<Object>(); 
	    }
	    
	    public boolean isEmpty() { 
	    	return q.isEmpty() ;
	    }

	    public List getQ() {
			return q;
		}

		public synchronized void enqueue(Object x) {
	    	((LinkedList) q).addLast(x);
	    	notify();
	    }

	    public synchronized Object dequeue() { 
	    	if(isEmpty()) {
	            try {
	                wait();
	            } catch(InterruptedException ie) {
	                ie.printStackTrace();
	            }
	        }
	    	Object n = ((LinkedList) q).removeFirst();
	    	return n;
	    }
	    
	    public boolean contains(Object r) { 
	    	return q.contains(r) ; 
	    }

	    public String toString() { 
	    	return q.toString() ; 
	    }
}

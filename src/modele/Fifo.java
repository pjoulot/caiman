package modele;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fifo<T> {
	    private List<T> q = Collections.synchronizedList(new LinkedList<T>());
	    
	    public Fifo() { 
	    	q = new LinkedList<T>();
	    }
	    
	    public Fifo(List<T> l) { 
	    	q = l;
	    }
	    
	    public boolean isEmpty() { 
	    	return q.isEmpty() ;
	    }

	    public List<T> getQ() {
			return q;
		}

		public synchronized void enqueue(T x) {
	    	((LinkedList<T>) q).addLast(x);
	    	notify();
	    }

	    public synchronized T dequeue() { 
	    	if(isEmpty()) {
	            try {
	                wait();
	            } catch(InterruptedException ie) {
	                ie.printStackTrace();
	            }
	        }
	    	T n = ((LinkedList<T>) q).removeFirst();
	    	return n;
	    }
	    
	    public boolean contains(Object r) { 
	    	return q.contains(r) ; 
	    }

	    public String toString() { 
	    	return q.toString() ; 
	    }
}

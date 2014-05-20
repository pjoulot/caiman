package modele;

public class CouplePlusMoins {
	
	private RRaPlusMoins RRaPlus;
	private RRaPlusMoins RRaMoins;
	private int t;

	public CouplePlusMoins(RRaPlusMoins rRaPlus, RRaPlusMoins rRaMoins, int t) {
		super();
		RRaPlus = rRaPlus;
		RRaMoins = rRaMoins;
		this.t = t;
	}

	public RRaPlusMoins getRRaPlus() {
		return RRaPlus;
	}

	public RRaPlusMoins getRRaMoins() {
		return RRaMoins;
	}
	
	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

}

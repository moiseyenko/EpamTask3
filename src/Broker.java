
public class Broker {

	private String name;
	private double byn;
	private double usd;
	private double eur;
	private double indexUsd = 2.0;
	private double indexEur = 3.0;

	

	public Broker(String name, double byn, double usd, double eur) {
		this.name = name;
		this.byn = byn;
		this.usd = usd;
		this.eur = eur;
	}
	
	public Broker (Broker another) {
		this.name = another.name;
		this.byn = another.byn;
		this.usd = another.usd;
		this.eur = another.eur;
		this.indexUsd = another.indexUsd;
		this.indexEur = another.indexEur;
	}

	public double getByn() {
		return byn;
	}

	public void setByn(double byn) {
		this.byn = byn;
	}

	public double getUsd() {
		return usd;
	}

	public void setUsd(double usd) {
		this.usd = usd;
	}

	public double getEur() {
		return eur;
	}

	public void setEur(double eur) {
		this.eur = eur;
	}
	public double getIndexUsd() {
		return indexUsd;
	}

	public void setIndexUsd(double indexUsd) {
		this.indexUsd = indexUsd;
	}

	public double getIndexEur() {
		return indexEur;
	}

	public void setIndexEur(double indexEur) {
		this.indexEur = indexEur;
	}

	@Override
	public String toString() {
		return name + ": (" + byn + " , " + usd + " , " + eur +" , "+indexUsd+ " , "+ indexEur+")";
	}
}

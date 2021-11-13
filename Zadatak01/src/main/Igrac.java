package main;

public class Igrac {
	String ime;
	Karta karta;

	public Igrac() {
		super();
		this.karta = new Karta();
	}

	public Igrac(String ime) {
		super();
		this.ime = ime;
	}

	public Igrac(String ime, Karta k) {
		super();
		this.ime = ime;
		this.karta = k;
	}

	public Karta getK() {
		return karta;
	}

	public void setK(Karta k) {
		this.karta = k;
	}

	@Override
	public String toString() {
		return "Igrac [ime=" + ime + "]";
	}

}

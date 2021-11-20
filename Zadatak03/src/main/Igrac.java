package main;

public class Igrac extends Thread {
	String ime;
	Karta karta;
	// referenca na spil
	Spil spil;
	// referenca na talon
	Talon talon;

	public Igrac() {
		super();
		this.karta = new Karta();
	}

	public Igrac(String ime, Spil s, Talon t) {
		super();
		this.ime = ime;
		this.spil = s;
		this.talon = t;
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

	@Override
	public void run() {
		System.out.println(this);

		this.setK(this.spil.uzmiOdGore());
		System.out.println(this + " uzeo sam kartu " + this.karta);
		this.talon.staviKartu(this.karta);
		System.out.println(this + " postavio sam kartu " + this.karta + " na talon");
		this.talon.cekajOstale(this);
		
		if (this.talon.jeNajjaca(this.karta)) {
			System.out.println(this + " pobedio sam!");
		}
	}

}

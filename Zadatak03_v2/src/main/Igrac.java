package main;

public class Igrac extends Thread {
	String ime;
	Karta karta;
	Spil s;
	Talon t;
	static Object o = new Object();

	public Igrac() {
		super();
		this.karta = new Karta();
	}

	public Igrac(String ime, Spil s, Talon t) {
		super();
		this.ime = ime;
		this.s = s;
		this.t = t;
	}

	public Karta getK() {
		return karta;
	}

	public void setK(Karta k) {
		this.karta = k;
	}

	@Override
	public void run() {

		this.karta = s.uzmiOdGore();
		System.out.println(this.ime + " izvukao je kartu: " + this.karta);

		// stavi kartu na talon
		t.staviKartu(this.karta);
//		System.out.println(this.ime + " stavio je svoju kartu. ====");

		if (t.jeNajjaca(karta)) {
			System.out.println(this.ime + " pobedio sam!");
		}
	}

	@Override
	public String toString() {
		return "Igrac [ime=" + ime + "]";
	}

}

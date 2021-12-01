package main;

public class Radnik extends Thread {
	private final int dnevniLimitPiva = 50;
	private Gajba g;
	private int popioPiva;
	private String ime;

	Radnik(String ime, Gajba g) {
		this.g = g;
		this.popioPiva = 0;
		this.ime = ime;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (popioPiva < dnevniLimitPiva) {

			try {
				Pivo p = g.uzmiPivo();
				p.ispij();
				popioPiva++;
				System.out.println(ime + " je popio " + popioPiva + " piva");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(ime + " ne moze da popije vise");
	}
}

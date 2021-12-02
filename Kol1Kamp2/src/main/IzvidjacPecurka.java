package main;

public class IzvidjacPecurka extends Thread {
	Kamp k;
	Suma s;
	private final int maxTrazenje = 20;

	public IzvidjacPecurka(Kamp k, Suma s) {
		super();
		this.k = k;
		this.s = s;
	}

	@Override
	public void run() {
		int skupljenePecurke = 0;
		for (int i = 0; i < maxTrazenje && !interrupted(); i++) {
			skupljenePecurke = s.traziPecurke();
		}
		k.donesiPecurke(skupljenePecurke);
		try {
			k.sacekajOstale();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " zavrsio rad");
	}
}

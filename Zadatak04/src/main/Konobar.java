package main;

import java.util.Random;

public class Konobar extends Thread {
	Namirnice namirnice;
	BrojacZarade br;

	public Konobar(Namirnice namirnice, BrojacZarade br) {
		super();
		this.namirnice = namirnice;
		this.br = br;
	}

	// Za vegetarijanski sendvic je od sastojaka potrebno: dva
	// parceta domaceg hleba, jedan komad grilovanog tofu sira i
	// 100 grama povrca.
	//
	// Za porciju potaza od bundeve je potrebno pola litre potaza
	// i jedno parce domaceg hleba.
	//
	// Za grilovani tofu sa povrcem je potreban jedan komad grilo-
	// vanog tofu sira i 300g povrca.
	private synchronized void napraviSendvic() throws InterruptedException {
		namirnice.proizvediSendvic();
		br.prodaj(Jelo.Sendvic.cena);
	}

	private synchronized void napraviPotaz() throws InterruptedException {
		namirnice.proizvediPotaz();
		br.prodaj(Jelo.PotazOdBundeve.cena);
	}

	private synchronized void napraviGrilovaniTofu() throws InterruptedException {
		namirnice.proizvediTofu();
		br.prodaj(Jelo.GrilovaniTofuSaPovrcem.cena);
	}

	@Override
	public void run() {
		Random r = new Random();
		while (true) {
			try {
				Konobar.sleep(10_000);
//				Konobar.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int f = r.nextInt(3);
			if (f == 0) {
				try {
					this.napraviSendvic();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (f == 1) {
				try {
					this.napraviPotaz();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					this.napraviGrilovaniTofu();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

package main;

public class PomocniKuvarZaPovrce extends Thread {
	Namirnice namirnice;

	public PomocniKuvarZaPovrce(Namirnice namirnice) {
		super();
		this.namirnice = namirnice;
	}

	@Override
	public void run() {
		while (true) {
			try {
				PomocniKuvarZaPovrce.sleep(90_000);
//				PomocniKuvarZaPovrce.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			namirnice.dodajPovrce(1000); // svakih 90 sec, isecka 1kg povrca
		}
	}
}

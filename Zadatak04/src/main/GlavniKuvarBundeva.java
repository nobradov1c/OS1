package main;

public class GlavniKuvarBundeva extends Thread {
	Namirnice namirnice;

	public GlavniKuvarBundeva(Namirnice namirnice) {
		super();
		this.namirnice = namirnice;
	}

	@Override
	public void run() {
		while (true) {
			try {
				GlavniKuvarBundeva.sleep(240_000);
//				GlavniKuvarBundeva.sleep(240);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			namirnice.dodajPotaz(10); // svakih 4 min, 10l potaza
		}
	}
}

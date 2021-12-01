package main;

public class RostiljMajstor extends Thread {
	Namirnice namirnice;

	public RostiljMajstor(Namirnice namirnice) {
		super();
		this.namirnice = namirnice;
	}

	@Override
	public void run() {
		while (true) {
			try {
				RostiljMajstor.sleep(60_000);
//				RostiljMajstor.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			namirnice.dodajSir(1); // svakih 1 min, 1 komad sira
		}
	}
}

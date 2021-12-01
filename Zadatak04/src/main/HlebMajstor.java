package main;

public class HlebMajstor extends Thread {
	Namirnice namirnice;

	public HlebMajstor(Namirnice namirnice) {
		super();
		this.namirnice = namirnice;
	}

	@Override
	public void run() {
		while (true) {
			try {
				HlebMajstor.sleep(60_000);
//				HlebMajstor.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			namirnice.dodajHleb(6); // svakih 1 min, 6 parcadi
		}
	}
}

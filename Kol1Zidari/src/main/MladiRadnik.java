package main;

public class MladiRadnik implements Runnable {
	private Gajba g;

	public MladiRadnik(String ime, Gajba g) {
		// TODO Auto-generated constructor stub
		this.g = g;
	}

	@Override
	public void run() {
		Pivo[] piva = null;
		// TODO Auto-generated method stub
		try {
			while (!Thread.interrupted()) {
				piva = Pivo.kupi();
				for (Pivo pivo : piva) {
					g.dodajPivo(pivo);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package main;

public class IzvidjacDrvo implements Runnable {
	Kamp k;
	Suma s;
	private final int maxTrazenje = 25;

	public IzvidjacDrvo(Kamp k, Suma s) {
		super();
		this.k = k;
		this.s = s;
	}

	@Override
	public void run() {
		int skupljenaDrva = 0;
		for (int i = 0; i < maxTrazenje; i++) {
			skupljenaDrva = s.traziDrva();
		}
		k.donesiDrva(skupljenaDrva);
		try {
			k.sacekajOstale();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " zavrsio rad");
	}

}

package main;

import java.util.ArrayList;
import java.util.List;

public class Gajba {
	private final int maxPiva = 12;

//	private int stanjePiva;
	List<Pivo> piva;

	Gajba() {
		piva = new ArrayList<Pivo>();
	}

	public synchronized void dodajPivo(Pivo x) throws InterruptedException {
		while (this.piva.size() + 1 > maxPiva) {
			wait();
		}
		this.piva.add(x);
		notifyAll();
	}

	public synchronized Pivo uzmiPivo() throws InterruptedException {
		while (this.piva.size() - 1 < 0) {
			wait();
		}
		Pivo p = this.piva.remove(0);
		notifyAll();
		return p;
	}

}

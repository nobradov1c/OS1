package main;

import java.util.ArrayList;
import java.util.List;

//void staviKartu(Karta)   - pomocu koje igrac stavlja kartu na talon
//*   void cekajOstale()       - blokira nit dok se na talon ne stavi 12 karata
//*                              ovaj metod baca InterruptedException ako neko
//*                              prekine nit u toku ovog cekanja
//*   boolean jeNajjaca(Karta) - utvrdjuje da li je prosledjena karta najjaca

public class Talon {
	List<Karta> karte = null;

	public Talon() {
		super();

		karte = new ArrayList<Karta>();
	}

	public synchronized void staviKartu(Karta k) {
		karte.add(k);
	}

	private synchronized boolean daLiSuSviStaviliKartu() {
		return karte.size() == 12;
	}

	public void cekajOstale(Igrac i) {
		while (!daLiSuSviStaviliKartu()) {
			// cekaj
		}
	}

	public synchronized boolean jeNajjaca(Karta k) {
		for (Karta karta : karte) {
			if (karta.jacinaKarte() > k.jacinaKarte()) {
				return false;
			}
		}

		return true;
	}

}

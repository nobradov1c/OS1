package main;

import java.util.ArrayList;
import java.util.List;

//* Implementirati klasu 'Talon' koja ima sledece metode i koristiti je za
//* sinhronizaciju igraca:
//* 
//*   void staviKartu(Karta)   - pomocu koje igrac stavlja kartu na talon
//*   void cekajOstale()       - blokira nit dok se na talon ne stavi 12 karata
//*                              ovaj metod baca InterruptedException ako neko
//*                              prekine nit u toku ovog cekanja
//*   boolean jeNajjaca(Karta) - utvrdjuje da li je prosledjena karta najjaca

public class Talon {
	List<Karta> karte;

	public Talon() {
		super();
		this.karte = new ArrayList<Karta>();
	}

	public synchronized void staviKartu(Karta k) {
		this.karte.add(k);

		if (this.karte.size() == 12) {
			notifyAll();
		} else {
			cekajOstale();
		}
	}

	public synchronized void cekajOstale() {

		while (this.karte.size() < 12) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean jeNajjaca(Karta k) {
		for (Karta karta : karte) {
			if (karta.jacinaKarte() > k.jacinaKarte()) {
				return false;
			}
		}
		return true;
	}

}

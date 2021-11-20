package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//* Implementirati klasu 'Spil' ciji konstruktor kreira nov spil koji sadrzi sve
//* 54 razlicite karte. Takodje, implementirati sledece operacije:
// 52+2 karte.
//* 
//*   int velicina()            - vraca broj karata trenutno u spilu
//*   Karta uzmiOdGore()        - ukljanja gornju kartu i vraca je kao rezultat
//*   Karta uzmiOdDole()        - ukljanja donju kartu i vraca je kao rezultat
//*   Karta uzmiIzSredine()     - ukljanja nasumicno izabranu kartu i vraca je
//*   void staviGore(Karta)     - dodaje kartu na vrh spila
//*   void staviDole(Karta)     - dodaje kartu na dno spila
//*   void staviUSredinu(Karta) - dodaje kartu na nasumicno izabrao mesto u spilu
//*   void promesaj()           - nasumicno rasporedjuje karte trenutno u spilu

public class Spil {
	List<Karta> karte;

	public Spil() {
		super();
		this.karte = new ArrayList<Karta>();

		// add 52 cards
		for (Rang r : Rang.values()) {
			if (r != Rang.DZOKER) {
				for (Boja b : Boja.values()) {
					if (b == Boja.KARO || b == Boja.HERC || b == Boja.PIK || b == Boja.TREF) {
						Karta k = new Karta(b, r);
						this.karte.add(k);
					}
				}
			}
		}

		// add jokers
		this.karte.add(new Karta(Boja.DZOKER_CRNI, Rang.DZOKER));
		this.karte.add(new Karta(Boja.DZOKER_OBOJENI, Rang.DZOKER));
	}

	public synchronized List<Karta> getKarte() {
		return karte;
	}

	public synchronized int velicina() {
		return this.karte.size();
	}

	// * Karta uzmiOdGore() - ukljanja gornju kartu i vraca je kao rezultat
	public synchronized Karta uzmiOdGore() {
		Karta k = this.karte.get(this.velicina() - 1);
		this.karte.remove(this.velicina() - 1);
		return k;
	}

	// * Karta uzmiOdDole() - ukljanja donju kartu i vraca je kao rezultat
	public synchronized Karta uzmiOdDole() {
		Karta k = this.karte.get(0);
		this.karte.remove(0);
		return k;
	}

	// * Karta uzmiIzSredine() - ukljanja nasumicno izabranu kartu i vraca je
	public synchronized Karta uzmiIzSredine() {
		Random r = new Random();
		int index = r.nextInt(this.velicina());

		Karta k = this.karte.get(index);
		this.karte.remove(index);
		return k;
	}

	// * void staviGore(Karta) - dodaje kartu na vrh spila
	public synchronized void staviGore(Karta k) {
		this.karte.add(k);
	}

	// * void staviDole(Karta) - dodaje kartu na dno spila
	public synchronized void staviDole(Karta k) {
		this.karte.add(0, k);
	}

	// * void staviUSredinu(Karta) - dodaje kartu na nasumicno izabrao mesto u spilu
	public synchronized void staviUSredinu(Karta k) {
		Random r = new Random();
		int index = r.nextInt(this.velicina());

		this.karte.add(index, k);
	}

	// * void promesaj() - nasumicno rasporedjuje karte trenutno u spilu
	public synchronized void promesaj() {
		Random r = new Random();
		for (int i = 0; i < this.velicina(); i++) {
			Karta tmp = this.karte.get(i);
			int swapIndex = r.nextInt(this.velicina());

			this.karte.set(i, this.karte.get(swapIndex));
			this.karte.set(swapIndex, tmp);
		}
	}

	@Override
	public synchronized String toString() {
		return "Spil [karte=" + karte + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Spil s = new Spil();

		System.out.println(s);
		s.promesaj();
		System.out.println(s);
	}

}

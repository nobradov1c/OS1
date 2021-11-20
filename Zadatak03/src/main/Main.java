package main;

import java.util.ArrayList;
import java.util.List;

/* Uzeti implementaciju klasa 'Karta' i 'Spil' iz prvog zadatka i adaptirati ih
 * tako da se mogu koristiti od strane vise procesa istovremeno.
 * 
 * Napraviti i pokrenuti 12 niti koje predstavljaju igrace. Svaka nit uzima
 * jednu kartu sa vrha spila i smesta je u svoje privatno polje. Potom tu kartu
 * stavlja na talon (videti ispod) i ceka da to urade i svi ostali igraci.
 * 
 * Kada su svi igraci stavili svoje karte na talon, nastavljaju izvrsavanje.
 * Svako samostalno proverava da li je imao najjacu kartu i stampa prigodnu
 * poruku o tome. Moze biti vise igraca sa najjacom kartom, gleda se samo rang
 * karte kao i u prethodnim zadacima.
 * 
 * Implementirati klasu 'Talon' koja ima sledece metode i koristiti je za
 * sinhronizaciju igraca:
 * 
 *   void staviKartu(Karta)   - pomocu koje igrac stavlja kartu na talon
 *   void cekajOstale()       - blokira nit dok se na talon ne stavi 12 karata
 *                              ovaj metod baca InterruptedException ako neko
 *                              prekine nit u toku ovog cekanja
 *   boolean jeNajjaca(Karta) - utvrdjuje da li je posledejna karta najjaca
 * 
 * Glavna nit kreira spil i talon, pokrece sve ostale niti, posle cega zavrsava
 * svoj rad.
 */

public class Main {

	public static void main(String[] args) {
		Spil s = new Spil();
		Talon t = new Talon();
		s.promesaj();
		s.promesaj();

		List<Igrac> igraci = new ArrayList<Igrac>();

		for (int i = 0; i < 12; i++) {
			igraci.add(new Igrac("Igrac " + i, s, t));
		}

		System.out.println("Igra pocinje.");

		// glavna nit pokrece sve ostale niti
		for (Igrac igrac : igraci) {
			igrac.start();
		}
	}
}

//public class Main {
//
//	public static void main(String[] args) {
//
//		System.out.println("Igra pocinje");
//		while (igraci.size() > 1) {
//			// svi igraci vuku kartu
//			for (Igrac i : igraci) {
//				i.setK(s.uzmiOdGore());
//				System.out.print(i + " " + i.getK() + " | ");
//			}
//			System.out.println();
//
//			// ostaju igraci samo sa najjacom kartom
//			int max = 0;
//			for (Igrac i : igraci) {
//				if (i.getK().jacinaKarte() > max)
//					max = i.getK().jacinaKarte();
//			}
//			System.out.println("najaca karta je: " + max);
//
//			// vrati karte u spil
//			for (Igrac i : igraci) {
//				s.staviDole(i.getK());
//			}
//
////			for (Igrac i : igraci) {
////				if (i.getK().jacinaKarte() < max) {
////					igraci.remove(i);
////				}
////			}
//
//			for (Iterator<Igrac> it = igraci.iterator(); it.hasNext();) {
//				if (it.next().getK().jacinaKarte() < max) {
//					it.remove();
//				}
//			}
//
////			final int maxim = max;
////			igraci = igraci.stream().filter(i -> i.getK().jacinaKarte() >= maxim).collect(Collectors.toList());
//
//			// promesaj spil
//			s.promesaj();
//
//		}
//
//		System.out.println("Pobedio je igrac " + igraci.get(0) + " sa kartom " + igraci.get(0).getK());
//
//	}
//
//}

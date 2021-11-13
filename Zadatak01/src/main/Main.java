package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.stream.Collectors;

/* Implementirati klasu 'Karta' sa osobinama 'boja' i 'rang' koje predstavljaju
 * standardne osobine karata klasicnog spila od 52+2 karte.
 * 
 * Potrebno je predstaviti sledece boje: pik, karo, herc i tref, dok su dozvo-
 * ljene vrednosti za rang poredjane po velicini: brojevi od 2 do 10, zandar,
 * kraljica, kralj i kec. Takodje je potrebno predstaviti i dva dzokera, jedan
 * u boji, jedan ne.
 * 
 * Implementirati klasu 'Spil' ciji konstruktor kreira nov spil koji sadrzi sve
 * 54 razlicite karte. Takodje, implementirati sledece operacije:
 * 
 *   int velicina()            - vraca broj karata trenutno u spilu
 *   Karta uzmiOdGore()        - ukljanja gornju kartu i vraca je kao rezultat
 *   Karta uzmiOdDole()        - ukljanja donju kartu i vraca je kao rezultat
 *   Karta uzmiIzSredine()     - ukljanja nasumicno izabranu kartu i vraca je
 *   void staviGore(Karta)     - dodaje kartu na vrh spila
 *   void staviDole(Karta)     - dodaje kartu na dno spila
 *   void staviUSredinu(Karta) - dodaje kartu na nasumicno izabrao mesto u spilu
 *   void promesaj()           - nasumicno rasporedjuje karte trenutno u spilu
 * 
 * Napisati program koji implementira sledecu igru za 12 igraca. Igraci redom
 * vuku po jednu kartu sa vrha spila i okrecu je. Program ispisuje koji igrac
 * je izvukao koju kartu. Pobednik je onaj igrac (ili igraci) cija je karta
 * najjaca, pri cemu se ne gleda boja karte a dzokeri su jaci od svih ostalih
 * karata. Ako je bilo vise pobednika igra se ponavlja samo sa pobednicima dok
 * ne ostane samo jedan. Program ispisuje ime konacnog pobednika.
 * 
 * Unapred smisliti imena za igrace, kreirati jedan spil i promesati ga pre
 * igre. Pretpostaviti da u toku igre nece nestati karata u spilu.
 */
public class Main {

	public static void main(String[] args) {
		Spil s = new Spil();
		s.promesaj();
		s.promesaj();

		List<Igrac> igraci = new ArrayList<Igrac>();

		for (int i = 0; i < 12; i++) {
			igraci.add(new Igrac("Igrac" + i));
		}

		System.out.println("Igra pocinje");
		while (igraci.size() > 1) {
			// svi igraci vuku kartu
			for (Igrac i : igraci) {
				i.setK(s.uzmiOdGore());
				System.out.print(i + " " + i.getK() + " | ");
			}
			System.out.println();

			// ostaju igraci samo sa najjacom kartom
			int max = 0;
			for (Igrac i : igraci) {
				if (i.getK().jacinaKarte() > max)
					max = i.getK().jacinaKarte();
			}
			System.out.println("najaca karta je: " + max);

			// vrati karte u spil
			for (Igrac i : igraci) {
				s.staviDole(i.getK());
			}

//			for (Igrac i : igraci) {
//				if (i.getK().jacinaKarte() < max) {
//					igraci.remove(i);
//				}
//			}

			for (Iterator<Igrac> it = igraci.iterator(); it.hasNext();) {
				if (it.next().getK().jacinaKarte() < max) {
					it.remove();
				}
			}

//			final int maxim = max;
//			igraci = igraci.stream().filter(i -> i.getK().jacinaKarte() >= maxim).collect(Collectors.toList());

			// promesaj spil
			s.promesaj();

		}

		System.out.println("Pobedio je igrac " + igraci.get(0) + " sa kartom " + igraci.get(0).getK());

	}

}

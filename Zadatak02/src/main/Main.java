package main;

import java.util.ArrayList;
import java.util.List;

/*
 * Data je implementacija beskonacnog spila karata (klase 'Spil' i 'Karta').
 * 
 * Napraviti nit koja uzima jednu po jednu kartu iz spila i deli ih drugim
 * nitima.
 * 
 * Napraviti 12 niti koje predstavljaju igrace. One cekaju da dobiju kartu od
 * dilera, koju potom ispisuju na ekranu i zavrsavaju svoj rad.
 * 
 * Glavna nit kreira i pokrece sve ostale niti posle cega zavrsava svoj rad.
 */

import java.util.Random;

class Karta {

	private String vrednost;

	public Karta(String vrednost) {
		this.vrednost = vrednost;
	}

	@Override
	public String toString() {
		return vrednost;
	}
}

class Spil {

	private static final String[] BOJE = "\u2660,\u2665,\u2666,\u2663".split(",");
	private static final String[] RANGOVI = "2,3,4,5,6,7,8,9,10,J,Q,K,A".split(",");
	private static final String[] DZOKERI = "\u2605,\u2606".split(",");
	private static final Random random = new Random();

	public Karta uzmi() {
		int id = random.nextInt(54);
		if (id == 53) {
			return new Karta(DZOKERI[0]);
		}
		if (id == 52) {
			return new Karta(DZOKERI[1]);
		}
		String boja = BOJE[id / 13];
		String rang = RANGOVI[id % 13];
		return new Karta(rang + boja);
	}
}

class Diler extends Thread {
	List<Igrac> igraci;
	Spil s;

	public Diler(List<Igrac> igraci) {
		super();
		this.igraci = igraci;
	}

	@Override
	public void run() {
		System.out.println("Diler pokrenut");
		s = new Spil();
		for (Igrac i : igraci) {
//			System.out.println("Proveravam igraca " + i.getName());
			if (i.getKarta() == null) {

				i.setKarta(s.uzmi());
				i.setFlag(true);
//				System.out.println("Dajem kartu igracu " + i.getName());
			}
		}
		System.out.println("Diler gotov");
	}
}

class Igrac extends Thread {
	String ime;
	Karta karta = null;
	volatile boolean flag = false;

	public Igrac(String ime) {
		super();
		this.ime = ime;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public void setFlag(boolean f) {
		this.flag = f;
	}

	@Override
	public void run() {
		System.out.println("Igrac " + ime + " ceka kartu...");
		while (flag == false) {
			// Igrac ceka kartu...
		}
		System.out.println("Igrac " + ime + " je dobio " + karta);
	}
}

public class Main {

	public static void main(String[] args) {
		List<Igrac> igraci = new ArrayList<Igrac>();

		for (int i = 0; i < 12; i++) {
			Igrac p = new Igrac(Integer.toString(i));
			igraci.add(p);
		}

		for (Igrac p : igraci) {
//			p.setDaemon(true);
			p.start();
		}

		Diler d = new Diler(igraci);
		d.start();
	}

}

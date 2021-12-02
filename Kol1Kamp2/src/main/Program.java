package main;

/*
 * Napisati program koji kreira jedan izvidjacki kamp i 24 izvidjaca. 12 izvi-
 * djaca je definisano pomocu klase Thread a 12 pomocu interfejsa Runnable i svi
 * su korisnicki procesi. Kamp nije zaseban proces. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu klase Thread po pokretanju idu u sumu da traze
 * pecurke, tj. pozivaju metod Suma::traziPecurke 20 puta, posle cega donose sve
 * pecurke koje su pronasli nazad u kamp (pomocu metoda Kamp::donesiPecurke).
 * Ako ih neko prekine u potrazi za pecurkama, hitno se vracaju u kamp bez
 * pecuraka i regularno zavrsavaju svoj rad. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu interfejsa Runnable po pokretanju idu u sumu
 * da traze drva za potpalu, tj. pozivaju metod Suma::traziDrva 25 puta, posle
 * cega donose sva sakupljena drva nazad u kamp (Kamp::donesiDrva). Ako ih neko
 * prekine u potrazi, izvidjaci se ne obaziru na to i regularno zavrsavaju svoj
 * rad kada obave svih 25 potraga. (5 poena)
 * 
 * Sinhronizovati klasu Kamp tako da se ni u kom slucaju ne izgube pecurke ili
 * drva. Takodje, dodati da izvidjaci po povratku u kamp, pre nego sto zavrse
 * rad, sacekaju da se vrate u kamp i svi ostali izvidjaci. (10 poena)
 * 
 * Obratiti paznju na elegantnost i objektnu orijentisanost realizacije i stil
 * resenja. Za program koji se ne kompajlira, automatski se dobija 0 poena bez
 * daljeg pregledanja.
 */
public class Program {

	public static void main(String[] args) {
		Kamp k = new Kamp();
		Suma s = new Suma();

		for (int i = 1; i <= 12; i++) {
			IzvidjacPecurka ip = new IzvidjacPecurka(k, s);
			ip.setName("IzvidjacPecurka " + i);
			ip.start();
		}

		for (int i = 1; i <= 12; i++) {
			Thread id = new Thread(new IzvidjacDrvo(k, s));
			id.setName("IzvidjacDrvo " + i);
			id.start();
		}

	}
}

class Kamp {

	private int pecurke = 0;
	private int drva = 0;
	private int vratiloSePatuljaka = 0;

	public synchronized void donesiPecurke(int koliko) {
		pecurke += koliko;
	}

	public synchronized void donesiDrva(int koliko) {
		drva += koliko;
	}

	public synchronized void sacekajOstale() throws InterruptedException {
		vratiloSePatuljaka++;
		if (vratiloSePatuljaka == 24) {
			notifyAll();
		}
		while (vratiloSePatuljaka < 24) {
			wait();

		}
	}
}

class Suma {

	public int traziPecurke() {
		System.out.println(Thread.currentThread().getName() + " trazi pecurke.");
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}

	public int traziDrva() {
		System.out.println(Thread.currentThread().getName() + " trazi drva.");
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}
}
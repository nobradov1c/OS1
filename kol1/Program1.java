/*
 * Napisati program koji kreira jedan izvidjacki kamp i 24 izvidjaca. 12 izvi-
 * djaca je definisano pomocu klase Thread kao pozadinski procesi a 12 pomocu
 * interfejsa Runnable kao korisnicki procesi. Kamp nije zaseban proces. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu klase Thread po pokretanju idu u sumu da traze
 * pecurke, tj. pozivaju metod Suma::traziPecurke 20 puta, i posle svakog poziva
 * donose pecurke koje su pronasli nazad u kamp (Kamp::donesiPecurke). Ako ih
 * neko prekine u potrazi za pecurkama, hitno se vracaju u kamp bez pecuraka i
 * regularno zavrsavaju svoj rad. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu interfejsa Runnable po pokretanju idu u sumu
 * da traze drva za korpe, tj. pozivaju metod Suma::traziDrva 25 puta, i posle
 * svakog poziva donose sakupljena drva nazad u kamp (Kamp::donesiDrva). Ako ih
 * neko prekine u potrazi, izvidjaci se ne obaziru na to i regularno zavrsavaju
 * svoj rad kada obave svih 25 potraga. (5 poena)
 * 
 * Sinhronizovati klasu Kamp tako da se ni u kom slucaju ne izgube pecurke ili
 * drva. Takodje, ne dozvoliti da u bilo kom trenutku u kampu bude vise saka
 * pecuraka nego sto je doneto drva za pravljenje korpi. (10 poena)
 * 
 * Obratiti paznju na elegantnost i objektnu orijentisanost realizacije i stil
 * resenja. Za program koji se ne kompajlira, automatski se dobija 0 poena bez
 * daljeg pregledanja.
 */
public class Program {

	public static void main(String[] args) {
		
	}
}

class Kamp {

	private int pecurke = 0;
	private int drva = 0;

	public void donesiPecurke(int koliko) {
		pecurke += koliko;
	}

	public void donesiDrva(int koliko) {
		drva += koliko;
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

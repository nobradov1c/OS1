<pre><div class="text_to_html">/*
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

public class CekajuOstale {

	public static void main(String[] args) throws InterruptedException {
		Kamp kamp = new Kamp();
		Suma suma = new Suma();

		for(int i = 0 ; i &lt; 12 ; i++) {

			IzvidjacThread izvT = new IzvidjacThread(suma, kamp);

			izvT.start();


			Runnable runnable  = new IzvidjacRunnable(suma, kamp);
			Thread t = new Thread(runnable);			
			t.setDaemon(false); 
			t.start();
		}

	}
}

class IzvidjacThread extends Thread { 

	private final Suma suma ;
	private final Kamp kamp ;


	public IzvidjacThread(Suma suma,Kamp kamp) {
		this.suma = suma ;
		this.kamp = kamp ;
		this.setDaemon(false); // korisnicki
	}
	@Override
	public void run() {

		int brojPecuraka = 0;
		for(int i = 0 ; i &lt; 20 &amp;&amp; !interrupted() ; i++) {

			brojPecuraka +=	suma.traziPecurke();
		}			
		kamp.donesiPecurke(brojPecuraka);

		try {					
			kamp.sacekajOstale();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(&quot;Zavrsio posao s pecurkama&quot;);
	}
}

class IzvidjacRunnable implements Runnable {

	private final Suma suma ;
	private final Kamp kamp ;

	public IzvidjacRunnable(Suma suma,Kamp kamp) {
		this.suma = suma ;
		this.kamp = kamp ;

	}
	@Override
	public void run() {
		int brojDrva = 0;
		for(int i = 0 ; i &lt; 25 ; i++) {			
			brojDrva += suma.traziDrva();	
		}
		kamp.donesiDrva(brojDrva);	
		try {
			kamp.sacekajOstale();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(&quot;Zavrsio posao s drvima&quot;);	
	}

}



class Kamp {

	private int pecurke = 0;
	private int drva = 0;
	private volatile int brojac = 0;

	public synchronized void donesiPecurke(int koliko) {

		pecurke += koliko;

	}

	public synchronized void donesiDrva(int koliko) {
		drva += koliko;
	}
	public synchronized void sacekajOstale() throws InterruptedException {
		brojac++;
		while(brojac &lt; 24) {
			wait();
		}
		notifyAll();
	}
}

class Suma {

	public int traziPecurke() {
		System.out.println(Thread.currentThread().getName() + &quot; trazi pecurke.&quot;);
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}

	public int traziDrva() {
		System.out.println(Thread.currentThread().getName() + &quot; trazi drva.&quot;);
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}
}

</div></pre>
<pre><div class="text_to_html">/*
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
public class ViseDrvaNegoPecuraka {

	public static void main(String[] args) {
		Suma mirkwood = new Suma();
		Kamp rivendell = new Kamp();
		
		String[] elves = {&quot;Aulendil&quot;, &quot;Felador&quot;, &quot;Gwelunil&quot;, &quot;Pant&quot;, &quot;Rusmben&quot;, &quot;Gobeldor&quot;, &quot;Harvaon&quot;, &quot;Aeragar&quot;, &quot;Crann&quot;, &quot;Liphen&quot;, &quot;Cambamir&quot;, &quot;Goenir&quot;,
				&quot;Norgalaben&quot;, &quot;&Iacute;dthor&quot;, &quot;Corna&quot;, &quot;S&iacute;rdhemdir&quot;, &quot;Saeldor&quot;, &quot;Amlugron&quot;, &quot;Amathris&quot;, &quot;Cabor&quot;, &quot;Limlugnir&quot;, &quot;Ninniacthor&quot;, &quot;Goeolben&quot;, &quot;Torogarch&quot;
		}; 
		int j = 0 ;
		for(int i = 0 ; i &lt; 12 ; i++) {
			ElfScout scout = new ElfScout(rivendell, mirkwood);
			scout.start();
			scout.setName(elves[j]);	
			
			Thread bigElf = new Thread(new BigElf(rivendell, mirkwood));
			bigElf.setDaemon(false);
			bigElf.start();
			j++;
			bigElf.setName(elves[j]);	
			j++;
		}
	}
}

class ElfScout extends Thread {
	private final Suma suma ;
	private final Kamp kamp ;
	
	public ElfScout(Kamp kamp, Suma suma) {
		this.suma = suma;
		this.kamp = kamp;
		this.setDaemon(true);
	}
	
	@Override
	public void run() {

		
		try {
			for(int i = 0 ; i &lt; 20 &amp;&amp; !interrupted() ; i ++) {
				int curke  =suma.traziPecurke();
				kamp.donesiPecurke(curke);
			}
		} catch (InterruptedException e) {
			System.out.println(&quot;Mladi elf izvidjac je osetio opasnost i vratio se u kamp&quot;);
			e.printStackTrace();
		}
		
	}
}

class BigElf implements Runnable {
	private final Suma suma ;
	private final Kamp kamp ;
	
	public BigElf(Kamp kamp, Suma suma) {
		this.suma = suma;
		this.kamp = kamp;
	}
	
	@Override
	public void run() {
		int brojDrva = 0 ;
		
			for(int i = 0 ; i &lt; 25  ; i ++) {
				try {
					brojDrva = suma.traziDrva();
					kamp.donesiDrva(brojDrva);
				} catch (Exception e) {}
				
			}
		
	}
	
}


class Kamp {

	private int pecurke = 0;
	private int drva = 0;

	public synchronized void donesiPecurke(int koliko) throws InterruptedException {
		while(pecurke + koliko &gt; drva) {
			System.out.println(&quot;&lt;----Young elf scout must wait a bit before depositing more shrooms.----&gt;&quot;);
			wait();
		}
		pecurke += koliko;
		System.out.println(&quot;&lt;----Young elf can now deposit more shrooms!----&gt;&quot;);
		
	}

	public synchronized void donesiDrva(int koliko) {
		drva += koliko;
		notifyAll();
	}
}

class Suma {

	public int traziPecurke() {
		System.out.println(&quot;Young elf scout &quot;+Thread.currentThread().getName() + &quot; started searching for wild shrooms.&quot;);
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}

	public int traziDrva() {
		System.out.println(&quot;Big elf \t&quot; +Thread.currentThread().getName() + &quot; ventured out into the woods in search of firewood.&quot;);
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return (int) (Math.random() * 3);
	}
}</div></pre>
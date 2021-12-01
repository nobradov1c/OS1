package main;

//Napisati program u Javi koji simulira rad vrlo popularnog
//vegetarijanskog restorana "Vege Laza".
//
//U danasnjoj ponudi restorana su tri obroka: vegetarijanski
//sendvic (230 din), potaz od bundeve (340 din) i grilovani
//tofu sa povrcem (520 din).
//
//U restoranu u prodaji i pripremi porcija rade dve konoba-
//rice: Rada i Dara. One svakih 10 sekundi obrade po jednu
//porudzbinu.
//
//Za vegetarijanski sendvic je od sastojaka potrebno: dva
//parceta domaceg hleba, jedan komad grilovanog tofu sira i
//100 grama povrca.
//
//Za porciju potaza od bundeve je potrebno pola litre potaza
//i jedno parce domaceg hleba.
//
//Za grilovani tofu sa povrcem je potreban jedan komad grilo-
//vanog tofu sira i 300g povrca.
//
//U kuhinji, na pripremi povrca radi Miki koji za 90 sekundi
//isecka kilu povrca. Takodje, Mica svaka 4 minuta skuva 10
//litara potaza, a Joki isece 6 parcadi hleba svaki minut.
//Na rostilju rade Vule i Gule koji svaki minut ispeku po ko-
//mad tofu sira.
//
//Napisati program koji kreira svakog od zaposlenih kao pose-
//ban proces i pokrece ih. 5 minuta kasnije, prekida procese
//koji uredno zavrsavaju svoj rad, posle cega program ispisuje
//ukupnu sumu novca koju je restoran zaradio.

class BrojacZarade {
	int zarada;

	BrojacZarade() {
		zarada = 0;
	}

	public synchronized void prodaj(int x) {
		zarada += x;
	}

	public synchronized int getZarada() {
		return zarada;
	}
}

public class Program {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Helou");
		BrojacZarade br = new BrojacZarade();
		Namirnice namirnice = new Namirnice();
		PomocniKuvarZaPovrce miki = new PomocniKuvarZaPovrce(namirnice);
		GlavniKuvarBundeva mica = new GlavniKuvarBundeva(namirnice);
		HlebMajstor joki = new HlebMajstor(namirnice);
		RostiljMajstor vuleGule = new RostiljMajstor(namirnice);
		// Rada i dara
		Konobar rada = new Konobar(namirnice, br);
		Konobar dara = new Konobar(namirnice, br);

		miki.setDaemon(true);
		mica.setDaemon(true);
		joki.setDaemon(true);
		vuleGule.setDaemon(true);
		rada.setDaemon(true);
		dara.setDaemon(true);

		miki.start();
		mica.start();
		joki.start();
		vuleGule.start();
		rada.start();
		dara.start();

		Thread.sleep(300_000); // radi 5 min
//		Thread.sleep(820);
//		rada.interrupt();

		System.out.println(namirnice);
		System.out.println(br.getZarada());
	}

}

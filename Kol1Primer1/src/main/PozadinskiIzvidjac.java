package main;

public class PozadinskiIzvidjac extends Thread {
	private final int brojTrazenja = 5;

	private String ime;
	private int pecurke;
	private Kamp kamp;
	private Suma suma;

	public PozadinskiIzvidjac(String ime, Kamp k, Suma s) {
		super();
		this.ime = ime;
		this.kamp = k;
		this.suma = s;
	}

	@Override
	public void run() {
		System.out.println(ime + " Pokrenut pozadinski izvidjac");
		for (int i = 0; i < brojTrazenja && !interrupted(); i++) {
			pecurke = suma.traziPecurke();

			kamp.donesiPecurke(pecurke);
		}
	}
}

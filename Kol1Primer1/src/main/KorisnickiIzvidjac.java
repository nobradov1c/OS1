package main;

public class KorisnickiIzvidjac implements Runnable {
	private final int brojTrazenja = 15;
	
	private String ime;
	private Kamp kamp;
	private Suma suma;
	private int drva;

	public KorisnickiIzvidjac(String ime, Kamp k, Suma s) {
		super();
		this.ime = ime;
		this.kamp = k;
		this.suma = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(ime + " pokrenut korisnicki izvidjac");
		for (int i = 0; i < brojTrazenja; i++) {
			drva = suma.traziDrva();

			kamp.donesiDrva(drva);

		}
	}

}

package main;

//* Implementirati klasu 'Karta' sa osobinama 'boja' i 'rang' koje predstavljaju

//* standardne osobine karata klasicnog spila od 52+2 karte.

public class Karta {

	Boja boja;
	Rang rang;

	public Karta() {
		super();
	}

	public Karta(Boja boja, Rang rang) {
		super();
		this.boja = boja;
		this.rang = rang;
	}

	public int jacinaKarte() {
		return this.rang.strength;
	}

	@Override
	public String toString() {
		return "Karta [" + rang + " " + boja + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

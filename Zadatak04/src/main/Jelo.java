package main;

//U danasnjoj ponudi restorana su tri obroka: vegetarijanski
//sendvic (230 din), potaz od bundeve (340 din) i grilovani
//tofu sa povrcem (520 din).
public enum Jelo {
	Sendvic(230), PotazOdBundeve(340), GrilovaniTofuSaPovrcem(520);

	public final int cena;

	Jelo(int i) {
		this.cena = i;
	}
}

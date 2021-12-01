package main;

public enum Rang {
	DVA(2), 
	TRI(3), 
	CETIRI(4), 
	PET(5), 
	SEST(6), 
	SEDAM(7), 
	OSAM(8), 
	DEVET(9), 
	DESET(10), 
	ZANDAR(12), 
	KRALJICA(13), 
	KRALJ(14), 
	KEC(11), 
	DZOKER(99);

	public final int strength;

	Rang(int i) {
		this.strength = i;
	}
}

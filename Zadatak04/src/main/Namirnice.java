package main;

class Namirnice {
	int kolicinaParcadiHleba;
	int kolicinaKomadaTofuSira;
	int stanjePovrca; // u gramima
	double stanjePotaza;

	Namirnice() {
		kolicinaParcadiHleba = 0;
		kolicinaKomadaTofuSira = 0;
		stanjePovrca = 0;
		stanjePotaza = 0;
	}

	public synchronized void dodajHleb(int x) {
		this.kolicinaParcadiHleba += x;
		notifyAll();
	}

	public synchronized void dodajSir(int x) {
		this.kolicinaKomadaTofuSira += x;
		notifyAll();
	}

	public synchronized void dodajPovrce(int x) {
		this.stanjePovrca += x;
		notifyAll();
	}

	public synchronized void dodajPotaz(double x) {
		this.stanjePotaza += x;
		notifyAll();
	}

	public synchronized void uzmiHleb(int x) throws InterruptedException {
		while (this.kolicinaParcadiHleba < x) {
			wait();
		}

		this.kolicinaParcadiHleba -= x;
	}

	public synchronized void uzmiSir(int x) throws InterruptedException {
		while (this.kolicinaKomadaTofuSira < x) {
			wait();
		}

		this.kolicinaKomadaTofuSira -= x;
	}

	public synchronized void uzmiPovrce(int x) throws InterruptedException {
		while (this.stanjePovrca < x) {
			wait();
		}

		this.stanjePovrca -= x;
	}

	public synchronized void uzmiPotaz(double x) throws InterruptedException {
		while (this.stanjePotaza < x) {
			wait();
		}

		this.stanjePotaza -= x;
	}

	public synchronized void proizvediSendvic() throws InterruptedException {
		while (!(this.kolicinaParcadiHleba >= 2 && this.kolicinaKomadaTofuSira >= 1 && this.stanjePovrca >= 100)) {
			wait();
		}
		this.uzmiHleb(2);
		this.uzmiSir(1);
		this.uzmiPovrce(100);
	}

	public synchronized void proizvediPotaz() throws InterruptedException {
		while (!(this.kolicinaParcadiHleba >= 1 && this.stanjePotaza >= 0.5)) {
			wait();
		}
		this.uzmiHleb(1);
		this.uzmiPotaz(0.5);
	}

	public synchronized void proizvediTofu() throws InterruptedException {
		while (!(this.kolicinaKomadaTofuSira >= 1 && this.stanjePovrca >= 300)) {
			wait();
		}
		this.uzmiSir(1);
		this.uzmiPovrce(300);
	}

	@Override
	public String toString() {
		return "Namirnice [kolicinaParcadiHleba=" + kolicinaParcadiHleba + ", kolicinaKomadaTofuSira="
				+ kolicinaKomadaTofuSira + ", stanjePovrca=" + stanjePovrca + ", stanjePotaza=" + stanjePotaza + "]";
	}

}
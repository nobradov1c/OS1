package main;

class Nit extends Thread {
	@Override
	public void run() {
		for (int i = 1; i <= 10000; i++) {
			System.out.println(i);
		}
	}
}

class Negativni implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = -1; i >= -10000; i--) {
			System.out.println(i);
		}
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start...");

		Nit n = new Nit();
		n.setName("Pozitivni");
		System.out.println(n.getName());

		Thread neg = new Thread(new Negativni());
		neg.setName("Negativni");

		n.start();
		neg.start();
	}

}

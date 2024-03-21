package view;

import java.util.concurrent.Semaphore;

import controller.Aeroporto;

public class Main {

	public static void main(String[] args) {
		Semaphore pista1 = new Semaphore(1);
		Semaphore pista2 = new Semaphore(1);
		Semaphore area1 = new Semaphore(2);
		Semaphore area2 = new Semaphore(2);
		
		for(int x = 0; x<21; x++) {
			Aeroporto ap = new Aeroporto(pista1, pista2, area1, area2);
			ap.start();
		}

	}

}

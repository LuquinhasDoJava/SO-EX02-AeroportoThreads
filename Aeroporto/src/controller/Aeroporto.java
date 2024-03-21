package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread {
	Semaphore pista01;
	Semaphore pista02;
	Semaphore area1;
	Semaphore area2;
	Random random;
	
	public Aeroporto(Semaphore pista1, Semaphore pista2, Semaphore area1, Semaphore area2) {
		this.pista01 = pista1;
		this.pista02 = pista2;
		this.area1 = area1;
		this.area2 = area2;
		random = new Random();
	}
	public void run() {
		faseManobrar();
	}
	private void faseManobrar() {
		System.out.println("Avião: #"+getId()+" está manobrando");
		int temp = random.nextInt(401)+300;
		try {
			sleep((long)temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Avião: #"+getId()+" terminou de manobrar");
		faseTaxiar();
	}
	private void faseTaxiar() {
		System.out.println("Avião: #"+getId()+" está taxiando");
		int temp = random.nextInt(501)+500;
		try {
			sleep((long)temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Avião: #"+getId()+" terminou de taxiar");
		faseDecolagem();
	}
	private void faseDecolagem() {
		System.out.println("Avião: #"+getId()+" está se preparando para a decolagem");
		if(getId() %2 == 0) {
			pistaSul();
		}else {
			pistaNorte();
		}
		faseAfastanento();
	}
	private void pistaSul() {
		int temp = random.nextInt(201)+600;
		try {
			area1.acquire();
			try {
				pista01.acquire();
				System.out.println("Avião: #"+getId()+" esta decolando");
				sleep((long)temp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				pista01.release();
			}
		} catch (InterruptedException e1) { 
			e1.printStackTrace();
		} finally {
			area1.release();
		}
	 
		System.err.println("Avião: #"+getId()+" terminou de decolar na pista Sul");
	}
	private void pistaNorte() {
		int temp = random.nextInt(201)+600;
		try {
			area2.acquire();
			try {
				pista02.acquire();
				System.out.println("Avião: #"+getId()+" esta decolando");
				sleep((long)temp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				pista02.release();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			area2.release();
		}
		System.err.println("Avião: #"+getId()+" terminou de decolar na pista Norte");
	}
	private void faseAfastanento() {
		System.out.println("Avião: #"+getId()+" está se afastando");
		int temp = random.nextInt(501)+300;
		try {
			sleep((long)temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Avião: #"+getId()+" terminou de se afastar");
		
	}
}

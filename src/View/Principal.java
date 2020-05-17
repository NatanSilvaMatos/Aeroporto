package View;

import java.util.concurrent.Semaphore;

import Controller.AeroportoController;

public class Principal {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(2);
		
		for(int idThread = 1;idThread <= 12;idThread++) {
			AeroportoController aviao = new AeroportoController(idThread, semaforo);
		}
		
		
	}
}

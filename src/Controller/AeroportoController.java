package Controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class AeroportoController extends Thread {
	private int idThread;
	private Random aleatorio = new Random();
	private static int decidePista;
	private Semaphore semaforo;

	public AeroportoController(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
		start();
	}

	public void run(){
		manobrar();
		try {
			semaforo.acquire();
			taxiar();
			decolar();
			afastar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			semaforo.release();
			liberarPista();
		}
	}
	
	public void manobrar() { //pode durar de 3 a 7 segundos
		try {
			sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tempoManobra = aleatorio.nextInt(8); //para ir até 7
		while(tempoManobra < 3) { 
			tempoManobra = aleatorio.nextInt(8);
		}
	}

	public void taxiar() { //pode durar de 5 a 10 segundos
		int contador = 0;
		try {
			sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tempoTaxi = 0;
		while(contador < 5) {
			tempoTaxi = aleatorio.nextInt(2);
			contador = contador + tempoTaxi;
		}
		decidePista++;
		switch(decidePista) {
		case 1: System.out.println("\nThread #" + idThread + " irá decolar na Pista Norte!");
		break;
		case 2: System.out.println("\nThread #" + idThread + " irá decolar na Pista Sul!");
		break;
		}
	}

	public void decolar() { //pode durar de 1 a 4 segundos
		decidePista = 0;
		try {
			sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tempoDecolagem = aleatorio.nextInt(5);
		while(tempoDecolagem < 1) {
			tempoDecolagem = aleatorio.nextInt(5);
		}
	}

	public void afastar() { //dura de 3 a 8 segundos
		try {
			sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tempoAfastamento = aleatorio.nextInt(9);
		while(tempoAfastamento < 3) {
			tempoAfastamento = aleatorio.nextInt(5);
		}
	}

	public void liberarPista() {
		System.out.println("\nPistas liberadas para decolagem!");
	}
}



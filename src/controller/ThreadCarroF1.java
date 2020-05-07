package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarroF1 extends Thread {
	// Informações do carro
	Carro carro;

	// Um semaforo para cada escuderia
	static Semaphore[] semaforoEscuderia = new Semaphore[] { new Semaphore(1), new Semaphore(1), new Semaphore(1),
			new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), };

	// Nome das escuderias
	static final String[] nomeEscuderia = new String[] { "Monza", "Kadett", "Marea", "Fiat Uno", "Kombi", "Corsa",
			"Golf" };

	// Informações da pista
	final int nVoltas = 3;
	Semaphore semaforoPista = new Semaphore(5);
	static Carro[] classificacao = new Carro[14];
	static int corridasConcluidas;
	Semaphore cSemaforo = new Semaphore(1);

	public ThreadCarroF1(Carro carro) {
		this.carro = carro;
		corridasConcluidas = 0;
	}

	@Override
	public void run() {
		try {
			semaforoEscuderia[carro.getnEscuderia()].acquire();
			semaforoPista.acquire();
			iniciarCorrida();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoEscuderia[carro.getnEscuderia()].release();
			finalizarCorrida();
			semaforoPista.release();
		}
	}

	private void iniciarCorrida() {
		System.out.println("#" + carro.getnCarro() + " " + nomeEscuderia[carro.getnEscuderia()] + " iniciou a corrida");
		for (int volta = 1; volta <= nVoltas; volta++) {
			int tempoVolta = (int) (Math.random() * 2001) + 1000;
			try {
				sleep(tempoVolta / 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#" + carro.getnCarro() + " " + nomeEscuderia[carro.getnEscuderia()] + " fez a volta "
					+ volta + "/" + nVoltas + " em " + (double) tempoVolta / 100 + "s.");

			registrarTempo(tempoVolta);
		}
	}

	public void registrarTempo(int tempoVolta) {
		carro.adicionarTempoTotal(tempoVolta);
		if (tempoVolta < carro.getMenorTempo()) {
			carro.setMenorTempo(tempoVolta);
		}
	}

	private void finalizarCorrida() {
		System.out
				.println("#" + carro.getnCarro() + " " + nomeEscuderia[carro.getnEscuderia()] + " terminou a corrida");
		try {
			cSemaforo.acquire();
			classificacao[corridasConcluidas] = carro;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			corridasConcluidas++;
			cSemaforo.release();
		}
		if (corridasConcluidas == 14) {
			mostrarClassificacao();
		}
	}

	public void mostrarClassificacao() {
		int i, j;
		Carro aux = null;
		System.out.println("-----------------\n");
		// BubbleSort
		for (i = 0; i < 13; i++) {
			for (j = i + 1; j < 14; j++) {
				if (classificacao[i].getTempoTotal() > classificacao[j].getTempoTotal()) {
					aux = classificacao[i];
					classificacao[i] = classificacao[j];
					classificacao[j] = aux;
				}
			}
			System.out.println((i + 1) + "º " + nomeEscuderia[classificacao[i].getnEscuderia()] + " #"
					+ classificacao[i].getnCarro() + "\t Menor Tempo: " + classificacao[i].getMenorTempo()
					+ "s Tempo Total: " + classificacao[i].getTempoTotal() + "s");
		}
		System.out.println((corridasConcluidas) + "º " + nomeEscuderia[classificacao[corridasConcluidas-1].getnEscuderia()] + " #"
				+ classificacao[corridasConcluidas-1].getnCarro() + "\t Menor Tempo: " + classificacao[corridasConcluidas-1].getMenorTempo()
				+ "s Tempo Total: " + classificacao[corridasConcluidas-1].getTempoTotal() + "s");
	}
}

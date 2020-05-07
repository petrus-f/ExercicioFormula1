package controller;

public class Carro {
	private int nCarro, nEscuderia, voltaMaisRapida, tempoTotal, menorTempo;
	
	public int getnCarro() {
		return nCarro;
	}

	public void setnCarro(int nCarro) {
		this.nCarro = nCarro;
	}

	public int getnEscuderia() {
		return nEscuderia;
	}

	public void setnEscuderia(int nEscuderia) {
		this.nEscuderia = nEscuderia;
	}

	public int getVoltaMaisRapida() {
		return voltaMaisRapida;
	}

	public void setVoltaMaisRapida(int voltaMaisRapida) {
		this.voltaMaisRapida = voltaMaisRapida;
	}

	public int getTempoTotal() {
		return tempoTotal;
	}

	public void adicionarTempoTotal(int tempoVolta) {
		this.tempoTotal += tempoVolta;
	}

	public int getMenorTempo() {
		return menorTempo;
	}

	public void setMenorTempo(int menorTempo) {
		this.menorTempo = menorTempo;
	}

	public Carro(int nCarro, int nEscuderia) {
		this.nCarro = nCarro;
		this.nEscuderia = nEscuderia;
		menorTempo= 5000;
	}
}

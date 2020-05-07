package view;
import controller.ThreadCarroF1;
import controller.Carro;

public class Formula1 {

	public static void main(String[] args) {
		ThreadCarroF1 t = null;
		int nEscuderia = 0;
		for(int nCarro = 1; nCarro <= 14; nCarro++) {
			Carro carro = new Carro(nCarro, nEscuderia);
			t = new ThreadCarroF1(carro);
			if(nCarro%2 == 0) {
				nEscuderia++;
			}
			t.start();				
		}
	}
	
}

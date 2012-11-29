package core;

import java.io.IOException;

public class Principal {

	public static void main(final String[] args) throws NumberFormatException, IOException {
		LeituraDeArquivo pr = new LeituraDeArquivo("/Users/rlmr/Documents/workspace/epIA/data/playtennis.data",
				"/Users/rlmr/Documents/workspace/epIA/data/playtennis.names");
		Dados dados = new Dados(pr);
		dados.inicializa();
		NaiveBayes1 NB = new NaiveBayes1(dados);
		double[] d = { 1, 2, 1, 1, 1, 1 };
	}
}
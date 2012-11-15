package core;

import java.io.IOException;

public class Principal {

	public static void main(final String[] args) throws NumberFormatException, IOException {
		ColecaoDeGrupos g = new ColecaoDeGrupos(0.1, new LeituraDeArquivo(
				"/Users/rlmr/Documents/workspace/epIA/data/spambase.data",
				"/Users/rlmr/Documents/workspace/epIA/data/spambase.names"), 3);
		g.testeGrupos();
	}
}

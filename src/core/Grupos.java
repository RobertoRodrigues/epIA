package core;

import java.util.List;

public class Grupos {
	List<Exemplo> treinamento;
	List<Exemplo> teste;

	public Grupos(final List<Exemplo> treinamento, final List<Exemplo> teste) {
		this.treinamento = treinamento;
		this.teste = teste;
	}

}

package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dados {
	List<Exemplo> treinamento;
	List<Exemplo> treinamentoClasse0;
	List<Exemplo> treinamentoClasse1;
	List<Exemplo> testeClasse0;
	List<Exemplo> testeClasse1;
	int pontoDeTrocaDeClasseTreinamento;
	int pontoDeTrocaDeClasseTeste;
	String[] classe;
	ArrayList<String> atributos;

	Dados(final List<Exemplo> treinamento, final List<Exemplo> teste, final String[] classe,
			final ArrayList<String> atributos) {
		this.treinamento = treinamento;
		this.classe = classe;
		this.atributos = atributos;
		Collections.sort(treinamento);
		for (int i = 0; i < treinamento.size(); i++) {
			Exemplo e = treinamento.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseTreinamento = i;
				break;
			}
		}
		Collections.sort(teste);
		for (int i = 0; i < teste.size(); i++) {
			Exemplo e = teste.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseTeste = i;
				break;
			}
		}

		treinamentoClasse0 = treinamento.subList(0, pontoDeTrocaDeClasseTreinamento);
		treinamentoClasse1 = treinamento.subList(pontoDeTrocaDeClasseTreinamento, treinamento.size());
		testeClasse0 = teste.subList(0, pontoDeTrocaDeClasseTeste);
		testeClasse1 = teste.subList(pontoDeTrocaDeClasseTeste, teste.size());
	}
}

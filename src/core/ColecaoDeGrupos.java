package core;

import java.util.ArrayList;
import java.util.Collections;

class ColecaoDeGrupos {
	ArrayList<String> atributos;
	ArrayList<Exemplo> baseRandomizada;
	ArrayList<Grupos> grupos = new ArrayList<Grupos>();
	int pontoDeDivisao = 0;
	Exemplo[] teste = null;
	Exemplo[] treinamento = null;

	ColecaoDeGrupos(final double porcentagemTreinamento, final LeituraDeArquivo entrada, final int folds) {
		baseRandomizada = entrada.exemplos;
		atributos = entrada.atributos;
		pontoDeDivisao = (int) (entrada.qtExemplo * porcentagemTreinamento);
		for (int i = 0; i < folds; i++) {
			Collections.shuffle(baseRandomizada);
			treinamento = (Exemplo[]) baseRandomizada.subList(0, pontoDeDivisao).toArray();
			teste = (Exemplo[]) baseRandomizada.subList(pontoDeDivisao, 0).toArray();
			grupos.add(new Grupos(treinamento, teste));
		}

	}

	void testeGrupos() {
		System.out.println("oi " + teste.length);
		for (int i = 0; i < teste.length; i++) {
			System.out.print(teste[i] + ",");
		}
	}
}
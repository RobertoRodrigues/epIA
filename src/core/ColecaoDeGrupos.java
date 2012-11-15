package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ColecaoDeGrupos {
	ArrayList<String> atributos;
	ArrayList<Exemplo> baseRandomizada;
	ArrayList<Grupos> grupos = new ArrayList<Grupos>();
	int pontoDeDivisao = 0;
	List<Exemplo> teste = null;
	List<Exemplo> treinamento = null;

	ColecaoDeGrupos(final double porcentagemTreinamento, final LeituraDeArquivo entrada, final int folds) {
		baseRandomizada = entrada.exemplos;
		atributos = entrada.atributos;
		pontoDeDivisao = (int) (entrada.qtExemplo * porcentagemTreinamento);
		for (int i = 0; i < folds; i++) {
			Collections.shuffle(baseRandomizada);
			treinamento = baseRandomizada.subList(0, pontoDeDivisao);
			teste = baseRandomizada.subList(pontoDeDivisao, entrada.qtExemplo);
			grupos.add(new Grupos(treinamento, teste));
		}
	}
}
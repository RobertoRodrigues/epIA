package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ColecaoDeGrupos {
	double[][] valoresDeAtributo;// na última linha temos a classe
	ArrayList<String> atributos;
	ArrayList<Exemplo1> baseRandomizada;
	ArrayList<Dados> grupos = new ArrayList<Dados>();
	int pontoDeDivisao = 0;
	List<Exemplo1> teste = null;
	List<Exemplo1> treinamento = null;
	String[] classe;

	ColecaoDeGrupos(final double porcentagemTreinamento, final LeituraDeArquivo entrada, final int folds) {
		classe = entrada.classe;
		valoresDeAtributo = entrada.valoresDeAtributo;
		baseRandomizada = entrada.exemplos;
		atributos = entrada.atributos;
		pontoDeDivisao = (int) (entrada.qtExemplo * porcentagemTreinamento);
		for (int i = 0; i < folds; i++) {
			Collections.shuffle(baseRandomizada);
			treinamento = baseRandomizada.subList(0, pontoDeDivisao);
			teste = baseRandomizada.subList(pontoDeDivisao, entrada.qtExemplo);
			// descretizacao
			// grupos.add(new Dados(treinamento, teste, classe, atributos,
			// entrada.qtExemplo, pontoDeDivisao));
		}
	}
}
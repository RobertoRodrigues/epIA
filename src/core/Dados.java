package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dados {
	List<Exemplo1> treiClasse0;
	List<Exemplo1> treiClasse1;
	List<Exemplo1> valClasse0;
	List<Exemplo1> valClasse1;
	List<Exemplo1> testClasse0;
	List<Exemplo1> testClasse1;
	private final ArrayList<Exemplo1> baseRandomizada;
	private List<Exemplo1> trei;
	private List<Exemplo1> test;
	private List<Exemplo1> val;
	int qtExemplo;
	private int pontoDeDivisao1;
	private int pontoDeDivisao2;
	private int pontoDeTrocaDeClasseVal;
	double[][] valoresDeAtributo;// na última linha temos a classe
	String[] classe;
	ArrayList<String> atributos;
	boolean comVal = false;
	boolean comTest = false;

	Dados(final LeituraDeArquivo entrada) {
		qtExemplo = entrada.qtExemplo;
		classe = entrada.classe;
		valoresDeAtributo = entrada.valoresDeAtributo;
		baseRandomizada = entrada.exemplos;
		atributos = entrada.atributos;
		Collections.shuffle(baseRandomizada);
		// descretizacao
	}

	void inicializa() {
		trei = baseRandomizada;
		Collections.sort(trei);
		for (int i = 0; i < trei.size(); i++) {
			Exemplo1 e = trei.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		treiClasse0 = trei.subList(0, pontoDeTrocaDeClasseVal);
		treiClasse1 = trei.subList(pontoDeTrocaDeClasseVal, baseRandomizada.size());
	}

	void inicializa(final double porcentagemTrei) {
		comTest = true;
		pontoDeDivisao1 = (int) (qtExemplo * porcentagemTrei);
		trei = baseRandomizada.subList(0, pontoDeDivisao1);
		test = baseRandomizada.subList(pontoDeDivisao1, qtExemplo);
		Collections.sort(trei);
		for (int i = 0; i < trei.size(); i++) {
			Exemplo1 e = trei.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		treiClasse0 = trei.subList(0, pontoDeTrocaDeClasseVal);
		treiClasse1 = trei.subList(pontoDeTrocaDeClasseVal, trei.size());
		Collections.sort(test);
		for (int i = 0; i < test.size(); i++) {
			Exemplo1 e = test.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		testClasse0 = test.subList(0, pontoDeTrocaDeClasseVal);
		testClasse1 = test.subList(pontoDeTrocaDeClasseVal, test.size());
	}

	void inicializa(final double porcentagemTrei, final double porcentagemTest) {
		comVal = true;
		comTest = true;
		pontoDeDivisao1 = (int) (qtExemplo * porcentagemTrei);
		pontoDeDivisao2 = (int) (qtExemplo * porcentagemTest) + pontoDeDivisao1;
		trei = baseRandomizada.subList(0, pontoDeDivisao1);
		test = baseRandomizada.subList(pontoDeDivisao1, pontoDeDivisao2);
		val = baseRandomizada.subList(pontoDeDivisao2, qtExemplo);
		Collections.sort(trei);
		for (int i = 0; i < trei.size(); i++) {
			Exemplo1 e = trei.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		treiClasse0 = trei.subList(0, pontoDeTrocaDeClasseVal);
		treiClasse1 = trei.subList(pontoDeTrocaDeClasseVal, trei.size());
		Collections.sort(test);
		for (int i = 0; i < test.size(); i++) {
			Exemplo1 e = test.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		testClasse0 = test.subList(0, pontoDeTrocaDeClasseVal);
		testClasse1 = test.subList(pontoDeTrocaDeClasseVal, test.size());
		Collections.sort(val);
		for (int i = 0; i < val.size(); i++) {
			Exemplo1 e = val.get(i);
			if (e.classe == 1) {
				pontoDeTrocaDeClasseVal = i;
				break;
			}
		}
		valClasse0 = val.subList(0, pontoDeTrocaDeClasseVal);
		valClasse1 = val.subList(pontoDeTrocaDeClasseVal, val.size());
	}
}
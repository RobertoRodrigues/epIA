package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class NaiveBayes {
	Dados dado;
	int[][] matrizConfusao;
	int qtAtributo = 0;
	int qtExemploTreinamento = 0;
	int qtExemploTeste = 0;
	int qtClasse1Treinamento = 0;
	int qtClasse0Treinamento = 0;
	double frequenciaTreinamento0 = 0;
	double frequenciaTreinamento1 = 0;
	List<Exemplo> exemplosTreinamento;
	List<Exemplo> exemplosTesteClasse0;
	List<Exemplo> exemplosTesteClasse1;
	double[][] valsAtributo;// na última linha temos a classe
	int[] qtValoresPorAtributo;
	int[][] frequenciaDosValores0;
	int[][] frequenciaDosValores1;

	public NaiveBayes(final Dados dados) {
		this.dado = dados;
		exemplosTreinamento = dado.treinamento;
		exemplosTesteClasse0 = dado.testeClasse0;
		exemplosTesteClasse1 = dado.testeClasse1;
		qtClasse1Treinamento = dado.testeClasse0.size();
		qtClasse0Treinamento = dado.testeClasse0.size();
		qtAtributo = dado.atributos.size();
		qtExemploTeste = dado.testeClasse0.size() + dado.testeClasse1.size();
		qtExemploTreinamento = dado.treinamento.size();
		frequenciaTreinamento0 = qtClasse0Treinamento / qtExemploTreinamento;
		frequenciaTreinamento1 = qtClasse1Treinamento / qtExemploTreinamento;
		setValoresAtributoEfrequencia();
		matrizConfusao = calculaMatrizConfusao();
	}

	private void setValoresAtributoEfrequencia() {
		valsAtributo = new double[qtAtributo + 1][]; // +1 para classe
		frequenciaDosValores1 = new int[qtAtributo + 1][];
		frequenciaDosValores0 = new int[qtAtributo + 1][];
		for (int i = 0; i < qtAtributo + 1; i++) { // +1 para classe
			HashSet<Double> setAtributos = new HashSet<Double>();
			for (int j = 0; j < qtExemploTreinamento; j++) {
				if (i != qtAtributo) {
					setAtributos.add(exemplosTreinamento.get(j).atributos[i]);
				} else {
					setAtributos.add((double) exemplosTreinamento.get(j).classe);
				}
			}
			valsAtributo[i] = new double[setAtributos.size()];
			frequenciaDosValores1[i] = new int[setAtributos.size()];
			frequenciaDosValores0[i] = new int[setAtributos.size()];
			qtValoresPorAtributo[i] = setAtributos.size();
			int ii = 0;
			for (Iterator<Double> iterator = setAtributos.iterator(); iterator.hasNext();) {
				valsAtributo[i][ii] = iterator.next();
				for (int j = 0; j < qtExemploTreinamento; j++) {
					if (exemplosTreinamento.get(j).atributos[i] == valsAtributo[i][ii]) {
						if (exemplosTreinamento.get(j).classe == 0) {
							frequenciaDosValores0[i][ii]++;
						} else {
							frequenciaDosValores1[i][ii]++;
						}
					}
				}
				ii++;
			}
		}
	}

	double probabilidadeDeOcorrer(final Exemplo exemplo) {
		double p1 = frequenciaTreinamento1; // todas as probabilidades dos
											// valores dado que a classe é 1
		double p0 = frequenciaTreinamento0; // todas as probabilidades dos
											// valores dado que a classe é 0
		for (int i = 0; i < exemplo.atributos.length; i++) {
			for (int j = 0; j < frequenciaDosValores0[i].length; j++) {
				if (valsAtributo[i][j] == exemplo.atributos[i]) {
					if (frequenciaDosValores0[i][j] == 0) {
						p0 *= (frequenciaDosValores0[i][j] + 1) / (qtClasse0Treinamento + qtValoresPorAtributo[i]);
					} else {
						p0 *= frequenciaDosValores0[i][j] / qtClasse0Treinamento;
					}
					break;
				}
			}
			for (int j = 0; j < frequenciaDosValores1[i].length; j++) {
				if (valsAtributo[i][j] == exemplo.atributos[i]) {
					if (frequenciaDosValores1[i][j] == 0) {
						p0 *= (frequenciaDosValores1[i][j] + 1) / (qtClasse1Treinamento + qtValoresPorAtributo[i]);
					} else {
						p1 *= frequenciaDosValores1[i][j] / qtClasse1Treinamento;
					}
					break;
				}
			}
		}
		return p1 / (p1 + p0);
	}

	int[][] calculaMatrizConfusao() {
		int[][] matrizConfusao = new int[2][2];
		for (Exemplo exemplo : exemplosTesteClasse0) {
			if (probabilidadeDeOcorrer(exemplo) < 0.5) {
				matrizConfusao[1][1]++;
			} else {
				matrizConfusao[1][0]++;
			}
		}
		for (Exemplo exemplo : exemplosTesteClasse1) {
			if (probabilidadeDeOcorrer(exemplo) >= 0.5) {
				matrizConfusao[0][0]++;
			} else {
				matrizConfusao[0][1]++;
			}
		}
		return null;
	}

	double taxaDeFalsoPositivo() {
		return matrizConfusao[0][1] / (matrizConfusao[0][1] + matrizConfusao[1][1]);
	}

	double taxaDeFalsoNegativo() {
		return matrizConfusao[1][0] / (matrizConfusao[0][0] + matrizConfusao[1][0]);
	}

	double taxaDeErro() {
		return (taxaDeFalsoNegativo() + taxaDeFalsoPositivo()) / qtExemploTeste;
	}

	double acuracia() {
		return 1 - taxaDeErro();
	}
}
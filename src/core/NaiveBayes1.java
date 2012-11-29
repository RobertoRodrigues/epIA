package core;

import java.util.HashSet;
import java.util.Iterator;

class NaiveBayes1 {
	Dados dado;
	int[][] matrizConfusao = new int[2][2];
	int qtAtributo = 0;
	int qtExemploTrei = 0;
	int qtExemploVal = 0;
	int qtExemploTest = 0;
	int qtClasse1Trei = 0;
	int qtClasse0Trei = 0;
	int qtClasse1Val = 0;
	int qtClasse0Val = 0;
	int qtClasse1Test = 0;
	int qtClasse0Test = 0;
	double frequenciaTrei0 = 0;
	double frequenciaTrei1 = 0;
	double frequenciaVal0 = 0;
	double frequenciaVal1 = 0;
	double frequenciaTest0 = 0;
	double frequenciaTest1 = 0;
	double[][] valsAtributo;// na última linha temos a classe
	int[] qtValoresPorAtributo;
	int[][] frequenciaDosValores0;
	int[][] frequenciaDosValores1;

	public NaiveBayes1(final Dados dados) {
		this.dado = dados;
		qtClasse1Trei = dado.treiClasse1.size();
		qtClasse0Trei = dado.treiClasse0.size();
		qtExemploTrei = qtClasse1Trei + qtClasse0Trei;
		qtAtributo = dado.atributos.size();
		qtExemploTrei = qtClasse0Trei + qtClasse1Trei;
		frequenciaTrei0 = qtClasse0Trei / qtExemploTrei;
		frequenciaTrei1 = qtClasse1Trei / qtExemploTrei;
		if (dado.comVal) {
			qtClasse1Val = dado.valClasse1.size();
			qtClasse0Val = dado.valClasse0.size();
			qtExemploVal = qtClasse0Val + qtClasse1Val;
			frequenciaVal0 = qtClasse0Val / qtExemploVal;
			frequenciaVal1 = qtClasse1Val / qtExemploVal;
		}
		if (dado.comTest) {
			qtClasse1Test = dado.testClasse1.size();
			qtClasse0Test = dado.testClasse0.size();
			qtExemploTest = qtClasse0Test + qtClasse1Test;
			frequenciaTest0 = qtClasse0Test / qtExemploTest;
			frequenciaTest1 = qtClasse1Test / qtExemploTest;
		}
		setValoresAtributoEfrequencia();

		// testes
		System.out.print("quantidade de Valores de atributo ");
		for (int i = 0; i < qtValoresPorAtributo.length; i++) {
			System.out.print(qtValoresPorAtributo[i] + " ");
		}
		System.out.println();
		System.out.println("valsAtributo");
		for (int i = 0; i < valsAtributo.length; i++) {
			for (int j = 0; j < valsAtributo[i].length; j++) {
				System.out.print(valsAtributo[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void setValoresAtributoEfrequencia() {
		valsAtributo = new double[qtAtributo][]; // +1 para classe
		frequenciaDosValores1 = new int[qtAtributo][];
		frequenciaDosValores0 = new int[qtAtributo][];
		qtValoresPorAtributo = new int[qtAtributo];
		for (int i = 0; i < qtAtributo; i++) { // +1 para classe
			HashSet<Double> setAtributos = new HashSet<Double>();
			for (int j = 0; j < qtClasse0Trei; j++) {
				setAtributos.add(dado.treiClasse0.get(j).atributos[i]);
			}
			for (int j = 0; j < qtClasse1Trei; j++) {
				setAtributos.add(dado.treiClasse1.get(j).atributos[i]);
			}
			valsAtributo[i] = new double[setAtributos.size()];
			frequenciaDosValores1[i] = new int[setAtributos.size()];
			frequenciaDosValores0[i] = new int[setAtributos.size()];
			qtValoresPorAtributo[i] = setAtributos.size();
			int ii = 0;
			for (Iterator<Double> iterator = setAtributos.iterator(); iterator.hasNext();) {
				valsAtributo[i][ii] = iterator.next();
				for (int j = 0; j < qtClasse0Trei; j++) {
					if (dado.treiClasse0.get(j).atributos[i] == valsAtributo[i][ii]) {
						frequenciaDosValores0[i][ii]++;
					}
				}
				for (int j = 0; j < qtClasse1Trei; j++) {
					if (dado.treiClasse1.get(j).atributos[i] == valsAtributo[i][ii]) {
						frequenciaDosValores1[i][ii]++;
					}
				}
				ii++;
			}
		}
	}

	double probabilidadeDeOcorrer(final Exemplo1 exemplo) {
		double p1 = frequenciaTrei1; // todas as probabilidades dos
										// valores dado que a classe é 1
		double p0 = frequenciaTrei0; // todas as probabilidades dos
										// valores dado que a classe é 0
		for (int i = 0; i < exemplo.atributos.length; i++) {
			for (int j = 0; j < frequenciaDosValores0[i].length; j++) {
				if (valsAtributo[i][j] == exemplo.atributos[i]) {
					// acho que pode dar problema pois pode ter um valor que não
					// tem na matriz e se for menor deveria ser a possicão zero,
					// se for maior a última possicão
					if (frequenciaDosValores0[i][j] == 0) {
						p0 *= (frequenciaDosValores0[i][j] + 1) / (qtClasse0Trei + qtValoresPorAtributo[i]);
					} else {
						p0 *= frequenciaDosValores0[i][j] / qtClasse0Trei;
					}
					break;
				}
			}
			for (int j = 0; j < frequenciaDosValores1[i].length; j++) {
				if (valsAtributo[i][j] == exemplo.atributos[i]) {// idem
					if (frequenciaDosValores1[i][j] == 0) {
						p0 *= 1 / (qtClasse1Trei + qtValoresPorAtributo[i]);
					} else {
						p1 *= frequenciaDosValores1[i][j] / qtClasse1Trei;
					}
					break;
				}
			}
		}
		return p1 / (p1 + p0);
	}

	void calculaMatrizConfusao() {
		for (Exemplo1 exemplo : dado.testClasse0) {
			if (probabilidadeDeOcorrer(exemplo) < 0.5) {
				matrizConfusao[1][1]++;
			} else {
				matrizConfusao[1][0]++;
			}
		}
		for (Exemplo1 exemplo : dado.testClasse1) {
			if (probabilidadeDeOcorrer(exemplo) >= 0.5) {
				matrizConfusao[0][0]++;
			} else {
				matrizConfusao[0][1]++;
			}
		}
	}

	double taxaDeFalsoPositivo() {
		return matrizConfusao[0][1] / (matrizConfusao[0][1] + matrizConfusao[1][1]);
	}

	double taxaDeFalsoNegativo() {
		return matrizConfusao[1][0] / (matrizConfusao[0][0] + matrizConfusao[1][0]);
	}

	double taxaDeErro() {
		return (taxaDeFalsoNegativo() + taxaDeFalsoPositivo()) / qtExemploTest;
	}

	double acuracia() {
		return 1 - taxaDeErro();
	}
}
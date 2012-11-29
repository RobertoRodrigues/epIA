package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

class LeituraDeArquivo {

	int qtAtributo = 0;
	int qtExemplo = 0;
	ArrayList<Exemplo1> exemplos;
	double[][] valoresDeAtributo;// na última linha temos a classe
	String[] classe = new String[2];
	ArrayList<String> atributos;

	LeituraDeArquivo(final String pathData, final String pathNames) throws NumberFormatException, IOException {
		setAtributos(new BufferedReader(new FileReader(pathNames)));
		setDados(new BufferedReader(new FileReader(pathData)));
		setValoresAtributo();
	}

	private void setAtributos(final BufferedReader names) throws IOException {
		atributos = new ArrayList<String>();
		String line;
		while ((line = names.readLine()) != null) {
			if (line.trim().length() != 0 && line.charAt(0) != '|' && line.charAt(0) != '0' && line.charAt(0) != '1') {
				atributos.add(line.substring(0, line.indexOf(":")));
			}
			if (line.trim().length() != 0 && line.charAt(0) == '0') {
				classe = line.substring(line.indexOf("| ") + 2, line.indexOf(" classes")).split(", ");
			}
			if (line.trim().length() != 0 && line.charAt(0) == '1') {
				String[] temp = line.substring(line.indexOf("| ") + 2, line.indexOf(" classes")).split(", ");
				classe[0] = temp[1];
				classe[1] = temp[0];
			}
		}
		qtAtributo = atributos.size();
	}

	private void setDados(final BufferedReader data) throws IOException {
		String line;
		ArrayList<String> lista = new ArrayList<String>();
		while ((line = data.readLine()) != null) {
			lista.add(line);
		}
		qtExemplo = lista.size();
		exemplos = new ArrayList<Exemplo1>();
		for (int i = 0; i < qtExemplo; i++) {
			String[] exemplo = lista.get(i).split(",");
			qtAtributo = exemplo.length - 1;
			double[] exemploD = new double[qtAtributo];
			for (int j = 0; j < exemplo.length - 1; j++) {
				exemploD[j] = new Double(exemplo[j]);
			}
			exemplos.add(new Exemplo1(exemploD, new Integer(exemplo[qtAtributo])));
		}
	}

	private void setValoresAtributo() {
		valoresDeAtributo = new double[qtAtributo + 1][]; // +1 para classe
		for (int i = 0; i < qtAtributo + 1; i++) { // +1 para classe
			HashSet<Double> setAtributos = new HashSet<Double>();
			for (int j = 0; j < qtExemplo; j++) {

				if (i != qtAtributo) {
					setAtributos.add(exemplos.get(j).atributos[i]);
				} else {
					setAtributos.add((double) exemplos.get(j).classe);
				}
			}
			valoresDeAtributo[i] = new double[setAtributos.size()];
			int ii = 0;
			for (Iterator<Double> iterator = setAtributos.iterator(); iterator.hasNext();) {
				valoresDeAtributo[i][ii] = iterator.next();
				ii++;
			}
		}
	}
}
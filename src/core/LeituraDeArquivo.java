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
	double[][] valoresDeAtributo;// na última linha temos a classe
	Double[][] matrizDeExemplos;
	String[] classe = new String[2];
	ArrayList<String> atributos;

	LeituraDeArquivo(final String pathData, final String pathNames) throws NumberFormatException, IOException {
		BufferedReader names = new BufferedReader(new FileReader(pathNames));
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
		BufferedReader data = new BufferedReader(new FileReader(pathData));
		ArrayList<String> lista = new ArrayList<String>();
		while ((line = data.readLine()) != null) {
			lista.add(line);
		}
		qtExemplo = lista.size();
		matrizDeExemplos = new Double[qtExemplo][];
		for (int i = 0; i < qtExemplo; i++) {
			String[] exemplo = lista.get(i).split(",");
			qtAtributo = exemplo.length - 1;
			matrizDeExemplos[i] = new Double[exemplo.length];
			for (int j = 0; j < exemplo.length; j++) {
				matrizDeExemplos[i][j] = new Double(exemplo[j]);
			}
		}
		valoresDeAtributo = new double[qtAtributo + 1][]; // +1 para classe
		for (int i = 0; i < qtAtributo + 1; i++) { // +1 para classe
			HashSet<Double> setAtributos = new HashSet<Double>();
			for (int j = 0; j < qtExemplo; j++) {
				setAtributos.add(matrizDeExemplos[j][i]);
			}
			valoresDeAtributo[i] = new double[setAtributos.size()];
			int ii = 0;
			System.out.print(i + " | " + setAtributos.size() + " | ");
			for (Iterator iterator = setAtributos.iterator(); iterator.hasNext();) {
				valoresDeAtributo[i][ii] = (Double) iterator.next();
				System.out.print(valoresDeAtributo[i][ii] + ", ");
				ii++;
			}
			System.out.println();
		}
	}
}

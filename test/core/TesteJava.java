package core;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TesteJava {
	LeituraDeArquivo pr;

	@Before
	public void before() throws NumberFormatException, IOException {
		pr = new LeituraDeArquivo("/Users/rlmr/Documents/workspace/epIA/data/spambase.data",
				"/Users/rlmr/Documents/workspace/epIA/data/spambase.names");
	}

	@Test
	public void primeiroTest() {
		assertEquals(0.64, pr.matrizDeExemplos[0][1], 0.00000000001);
		assertEquals(1, pr.matrizDeExemplos[0][57], 0.00000000001);
		assertEquals(0.65, pr.matrizDeExemplos[4600][2], 0.00000000001);
	}

	@Test
	public void TestaClasse() {
		assertEquals("spam", pr.classe[1]);
		assertEquals("non-spam", pr.classe[0]);
	}

	@Test
	public void TestaAtributos() {
		assertEquals("word_freq_make", pr.atributos.get(0));
		assertEquals("capital_run_length_total", pr.atributos.get(56));
	}

	@Test
	public void TestaHashAtributos() {
		assertEquals(273, pr.valoresDeAtributo[56][0], 0.0000001);
		assertEquals(143, pr.valoresDeAtributo[56][918], 0.0000001);
		assertEquals(919, pr.valoresDeAtributo[56].length);
	}
}

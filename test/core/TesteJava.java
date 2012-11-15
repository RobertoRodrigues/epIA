package core;

import java.io.IOException;

public class TesteJava {
	LeituraDeArquivo pr;

	@Before
	public void before() throws NumberFormatException, IOException {
		pr = new LeituraDeArquivo("/Users/rlmr/Documents/workspace/epIA/data/spambase.data",
				"/Users/rlmr/Documents/workspace/epIA/data/spambase.names");
	}

	@Test
	public void primeiroTest() {
		assertEquals(0.64, pr.exemplos.get(0).valores[1], 0.00000000001);
		assertEquals(1, pr.exemplos.get(0).classe, 0.00000000001);
		assertEquals(0.65, pr.exemplos.get(4600).valores[2], 0.00000000001);
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
		assertEquals(1, pr.valoresDeAtributo[57][1], 0.0000001);// classe
		assertEquals(0, pr.valoresDeAtributo[57][0], 0.0000001);// classe
	}
}

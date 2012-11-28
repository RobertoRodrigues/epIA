package core;

class Exemplo implements Comparable<Exemplo> {
	final double[] atributos;
	final int classe;

	Exemplo(final double[] atributos, final int classe) {
		this.atributos = atributos;
		this.classe = classe;
	}

	@Override
	public int compareTo(final Exemplo e) {
		return classe - e.classe;
	}
}
package core;

class Exemplo1 implements Comparable<Exemplo1> {
	final double[] atributos;
	final int classe;

	Exemplo1(final double[] atributos, final int classe) {
		this.atributos = atributos;
		this.classe = classe;
	}

	@Override
	public int compareTo(final Exemplo1 e) {
		return classe - e.classe;
	}
}
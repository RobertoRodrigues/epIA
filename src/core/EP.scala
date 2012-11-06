package core

class EP {

}
class NaiveBayes(dados: Dados) {

}
class Dados(pathNames: String, pathData: String) {

  var frequenciaClasse = new Array[Double](qtClasses)
  var frequenciaAtributo = new Array[Double](qtAtributos)
  var probabilidadeDeUmaClasseOcorra = new Array[Double](qtClasses)
  var probabilidadeDeUmAtributoOcorra = new Array[Double](qtAtributos)
  var probabilidadeDeUmAtributoDadoUmaClasse = new Array[Array[Double]](qtAtributos).map(a => new Array[Double](qtClasses))
  var probabilidadeDeUmClasseDadoUmAtributo = new Array[Array[Double]](qtClasses).map(a => new Array[Double](qtAtributos))
  var qtClasses: Int = setQtCalsses
  var qtAtributos: Int = setQtAtributos
  setFrequenciaClasse
  setFrequenciaAtributo
  setProbabilidadeDeUmaClasseOcorra
  setProbabilidadeDeUmAtributoOcorra
  setProbabilidadeDeUmAtributoDadoUmaClasse
  setProbabilidadeDeUmClasseDadoUmAtributo

  def setQtCalsses() = {
    0 // le do arquivo
  }
  def setQtAtributos() = {
    0 // le do arquivo
  }
  def setFrequenciaClasse() = {

  }
  def setFrequenciaAtributo() = {

  }
  def setProbabilidadeDeUmaClasseOcorra() = {

  }
  def setProbabilidadeDeUmAtributoOcorra() = {

  }
  def setProbabilidadeDeUmAtributoDadoUmaClasse() = {

  }
  def setProbabilidadeDeUmClasseDadoUmAtributo() = {

  }
}
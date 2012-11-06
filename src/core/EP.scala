package core

import java.util.Scanner
import java.io.BufferedReader
import java.io.FileReader
import scala.collection.mutable.ArrayBuffer

class EP {

}
class NaiveBayes(dados: Dados) {

}
class Dados(pathNames: String, pathData: String) {

  var str = ""
  val fileNames: BufferedReader = new BufferedReader(new FileReader(pathNames))
  var strNames = new ArrayBuffer
  while (fileNames.ready()) {
    str = fileNames.readLine()
    if (str.head != '|' && str.head != ' ') {
      strNames + str
    }
  }
  fileNames.close();

  val fileData: BufferedReader = new BufferedReader(new FileReader(pathData))
  var strData = new ArrayBuffer
  while (fileNames.ready()) {
    strData + fileNames.readLine()
  }
  fileData.close();

  var tamanhoDaBase = strData.size
  var frequenciaClasse = new Array[Double](qtClasses)
  var frequenciaAtributo = new Array[Double](qtAtributos)
  var probabilidadeDeUmaClasseOcorra = new Array[Double](qtClasses)
  var probabilidadeDeUmAtributoOcorra = new Array[Double](qtAtributos)
  var probabilidadeDeUmAtributoDadoUmaClasse = new Array[Array[Double]](qtAtributos).map(a => new Array[Double](qtClasses))
  var probabilidadeDeUmClasseDadoUmAtributo = new Array[Array[Double]](qtClasses).map(a => new Array[Double](qtAtributos))
  var qtClasses: Int = 2
  var qtAtributos: Int = strNames.size - 1
  setFrequenciaClasse
  setFrequenciaAtributo
  setProbabilidadeDeUmaClasseOcorra
  setProbabilidadeDeUmAtributoOcorra
  setProbabilidadeDeUmAtributoDadoUmaClasse
  setProbabilidadeDeUmClasseDadoUmAtributo

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
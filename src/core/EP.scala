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

  val tamanhoDaBase = strData.size
  val frequenciaClasse = new Array[Int](qtClasses)
  val frequenciaAtributo = new Array[Array[Int]](qtAtributos).map(a => new Array[Int](qtMaxVal)) //gostaria de colocar os valores dos atributos e suas frequencias
  val probabilidadeDeUmaClasseOcorra = new Array[Double](qtClasses)
  val probabilidadeDeUmAtributoDadoUmaClasse = new Array[Array[Double]](qtAtributos).map(a => new Array[Double](qtClasses))
  val qtClasses: Int = 2
  val qtAtributos: Int = strNames.size - 1
  val qtMaxVal = setQtMaxVal // preferia ter um por valor
  setFrequenciaClasse
  setFrequenciaAtributo
  setProbabilidadeDeUmaClasseOcorra
  setProbabilidadeDeUmAtributoDadoUmaClasse

  def setQtMaxVal() = {
    0
  }

  def setFrequenciaClasse() = {

  }
  def setFrequenciaAtributo() = {

  }
  def setProbabilidadeDeUmaClasseOcorra() = {

  }
  def setProbabilidadeDeUmAtributoDadoUmaClasse() = {

  }
}
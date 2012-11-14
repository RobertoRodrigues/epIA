package core

import java.util.Scanner
import java.io.BufferedReader
import java.io.FileReader
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.io.File

class EP {

}
class CrossValidation(pathNames: String, pathData: String) {
  val dados = new Dados(pathNames, pathData, 0.1)

  val r = new NaiveBayes(new Dados(pathNames, pathData, 0.1)).taxaDeErro //10 interações guardando em um array
  val errosMedios = new Array[Double](10)
  val erroMedio = 10 // soma das 10 interacoes dos errosMedios dividido por 10 que é a media dos erros

  val SE = math.sqrt((erroMedio * (1 - erroMedio)) / (dados.tamanhoDaBaseDeTeste + dados.tamanhoDaBase))
  val intervaloDeConfiancaInferior = erroMedio - 1.96 * SE
  val intervaloDeConfiancaSuperior = erroMedio + 1.96 * SE

}

class NaiveBayes(dados: Dados) {

  val mC = calculaMatrizConfusao

  def probabilidadeDeOcorrer(valores: Array[Valores]) = {
    var p1 = dados.probabilidadeDeUmaClasseOcorra(1) // * todas as probabilidades dos valores dado que a classe é 1
    var p0 = dados.probabilidadeDeUmaClasseOcorra(0) // * todas as probabilidades dos valores dado que a classe é 0
    p1 = p1 / (p1 + p0)
  }
  def calculaMatrizConfusao() = {
    //fazer um for percorrendo o conjunto de teste e comparando a saida com a esperada devolvendo a matriz confusão 
    //a matriz confusão é 2x2 registrando a quantidade de: saida 1 esperado 1, saida 1 esperado 0, saida 0 esperado 0
    //e saida 0 esperado 1
    //
    //VP  FP
    //FN VN
    0
  }
  def taxaDeFalsoPositivo() = {
    //mC(0)(1) / (mC(0)(1) + mC(1)(1))
  }
  def taxaDeFalsoNegativo() = {
    //mC(1)(0) / (mC(1)(0) + mC(0)(0))
  }
  def taxaDeErro() = {
    //(taxaDeFalsoNegativo + taxaDeFalsoPositivo) / (mC(0)(0) + mC(0)(1) + mC(1)(0) + mC(1)(1))
  }
  def acuracia() = {
    //1-taxaDeErroTotal
  }

}
class Valores {

}

class Dados(pathNames: String, pathData: String, pTreinamento: Double) {
  //separar os dados em dois grupos de treinamento e teste sendo a divisão aleatória

  val names = Source.fromFile(new File(pathNames)).getLines.filter(str => str.head != '|' && str.head != ' ')

  val data = Source.fromFile(new File(pathData)).getLines.map(_.split(",").map(_.toDouble))

  //val dadosDeTeste = new Array[Array[Valores]](qtAtributos + 1).map(a => new Array[Valores](tamanhoDaBaseDeTeste))
  val tamanhoDaBaseDeTeste = data.size - tamanhoDaBase
  val tamanhoDaBase = data.size * pTreinamento
  val frequenciaClasse = new Array[Int](qtClasses)
  //val frequenciaAtributo = new Array[Array[Valores]](qtAtributos).map(a => new Array[Int](qtMaxVal)) //gostaria de colocar os valores dos atributos e suas frequencias
  val probabilidadeDeUmaClasseOcorra = new Array[Double](qtClasses)
  //val probabilidadeDeUmAtributoDadoUmaClasse = new Array[Array[Valores]](qtAtributos).map(a => new Array[Double](qtClasses))
  val qtClasses: Int = 2
  //val qtAtributos: Int = names.size - 1
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
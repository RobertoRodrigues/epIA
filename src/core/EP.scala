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
  // val dados = new Dados(pathNames, pathData, 0.1)

  // val r = new NaiveBayes(new Dados(pathNames, pathData, 0.1)).taxaDeErro //10 interações guardando em um array
  val errosMedios = new Array[Double](10)
  val erroMedio = 10 // soma das 10 interacoes dos errosMedios dividido por 10 que é a media dos erros

  // val SE = math.sqrt((erroMedio * (1 - erroMedio)) / (dados.tamanhoDaBaseDeTeste + dados.tamanhoDaBase))
  // val intervaloDeConfiancaInferior = erroMedio - 1.96 * SE
  // val intervaloDeConfiancaSuperior = erroMedio + 1.96 * SE

}

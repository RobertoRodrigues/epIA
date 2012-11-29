package core

import scala.io.Source
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{ Map => MMap }
import scala.util.Random

class Exemplo(val atributos: List[String], val classe: Int) {
  override def toString = "Exemplo(%s, %s)".format(atributos.toString, classe.toString)
}

object Exemplo {
  def apply(string: String): Exemplo = {
    val linha = string.split(",")
    val exemplo = linha.splitAt(linha.length - 1)
    new Exemplo(exemplo._1.toList, exemplo._2(0).toInt)
  }
}

case class NaiveBayes(c0: List[MMap[String, Double]], c1: List[MMap[String, Double]], fClasse0: Double, fClasse1: Double) {
  def probabilidadeDeOcorrer(exemplo: Exemplo) = {
    val pre1 = pre(c1, fClasse1, exemplo)
    pre1 / (pre1 + pre(c0, fClasse0, exemplo))
  }
  def pre(lista: List[MMap[String, Double]], f: Double, exemplo: Exemplo) = {
    exemplo.atributos.foldLeft(1.0, 0) { (t, atributo) =>
      val (mult, i) = t
      val p = lista(i).get(atributo).getOrElse(lista(i).get("default").get)
      (p * mult, i + 1)
    }._1 * f
  }
}

case class Data(exemplos: List[Exemplo], classes: List[String], nomesAtributos: List[String]) {
  import scala.util.Random._
  lazy val frequencias = {
    val c0 = exemplos.filter(_.classe == 0)
    val c1 = exemplos.filter(_.classe == 1)
    NaiveBayes(calculaFrequencia(c0), calculaFrequencia(c1), c0.size.toDouble / exemplos.size, c1.size.toDouble / exemplos.size)
  }
  def particiona(pDiv: Double): (Data, List[Exemplo]) = {
    val e = shuffle(exemplos)
    val t = e.splitAt((e.size * pDiv).toInt)
    (Data(t._1, classes, nomesAtributos), t._2)
  }
  def seleciona(c: Cromossomo): Data = {
    if (!c.genes.foldLeft(false)(_ || _)) seleciona(c.mutacao._1)
    else
      Data(exemplos.zip(c.genes).filter(_._2).unzip._1, classes, nomesAtributos.zip(c.genes).filter(_._2).unzip._1)
  }
  def calculaFrequencia(c0: List[Exemplo]) = {
    val l = ListBuffer[MMap[String, Int]]()
    (0 to c0(0).atributos.size - 1).foreach(i => l += MMap())
    c0.foldLeft(l) { (list, exemplo) =>
      exemplo.atributos.foldLeft(0) { (i, valor) =>
        val map = list(i)
        map(valor) = map.get(valor).map(_ + 1).getOrElse(1)
        i + 1
      }
      list
    }
    calculaProbabilidade(l, c0)
  }
  def calculaProbabilidade(l: ListBuffer[MMap[String, Int]], c0: List[Exemplo]) = {
    l.map { m =>
      val x = m.map { dado =>
        (dado._1, dado._2.toDouble / c0.size)
      }
      x("default") = 1.0 / (1 + m.size + c0.size)
      x
    }.toList
  }
}
case class MatConf(vp: Int, fp: Int, vn: Int, fn: Int) {
  def plusvp = MatConf(vp + 1, fp, vn, fn)
  def plusfp = MatConf(vp, fp + 1, vn, fn)
  def plusvn = MatConf(vp, fp, vn + 1, fn)
  def plusfn = MatConf(vp, fp, vn, fn + 1)
  def acuracia = (vp.toDouble + vn) / (vp + fp + vn + fn)
}
case class Cromossomo(genes: List[Boolean]) {
  def cruza(c: Cromossomo) = {
    val a = genes.splitAt(genes.size / 2)
    val b = c.genes.splitAt(genes.size / 2)
    List((Cromossomo(a._1 ++ b._2), None), (Cromossomo(b._1 ++ a._2), None))
  }
  def mutacao = {
    (Cromossomo(genes.map(b => if (Random.nextDouble < 0.33) !b else b)), None)
  }
}
object CromossomoAleatorio {
  def apply(size: Int) = {
    Cromossomo((1 to size).map(w => Random.nextBoolean).toList)
  }
}
object Genetico {
  def apply(datae: Data) = {
    val (data, test) = datae.particiona(2.0 / 3)
    val cromossomos: Seq[(Cromossomo, Option[Data])] = (1 to 15).map(w => (CromossomoAleatorio(data.nomesAtributos.size), None))
    (1 to 1).map { w =>
      (1 to 40).foldLeft(cromossomos) { (cromossomos, i) =>
        val datas = cromossomos.map(c => data.seleciona(c._1))
        val mA = datas.zip(cromossomos).map { w =>
          val (cData, c) = w
          val d = cData.particiona(0.5)
          (Fold(10, d), c._1, Option(d._1))
        }.sortWith(_._1 > _._1).take(6).map(t => (t._2, t._3))
        mA ++ mA(0)._1.cruza(mA(1)._1) ++ mA(2)._1.cruza(mA(3)._1) ++ mA(4)._1.cruza(mA(5)._1) ++ mA.take(3).map(_._1.mutacao)
      }.head
    }.map { t =>
      val (cromossomo, trei) = t
      (Fold(1, (trei.get, test)), cromossomo)
    }.foreach { t =>
      println((t._1, data.seleciona(t._2).nomesAtributos))
    }
  }
}
object Fold {
  def apply(fold: Int, dataE: Data, pDiv: Double): Double = {
    apply(fold, dataE.particiona(pDiv))
  }

  def apply(fold: Int, aux: => (Data, List[Exemplo])): Double = {
    (1 to fold).foldLeft(0.0) { (soma, w) =>
      val (data, exemplos) = aux
      val mc = exemplos.foldLeft(MatConf(0, 0, 0, 0)) { (mc, exemplo) =>
        val p = data.frequencias.probabilidadeDeOcorrer(exemplo)
        (p, exemplo.classe) match {
          case (p, 0) if (p < 0.5) => mc.plusvn
          case (p, 0) if (p >= 0.5) => mc.plusfp
          case (p, 1) if (p < 0.5) => mc.plusfn
          case (p, 1) if (p >= 0.5) => mc.plusvp
        }
      }
      soma + mc.acuracia
    } / fold
  }
}
object Ep extends App {
  /*
  val dataTennis = read("/Users/rlmr/Documents/workspace/epIA/data/playtennis.data",
    "/Users/rlmr/Documents/workspace/epIA/data/playtennis.names")

  println(dataTennis.frequencias.probabilidadeDeOcorrer(Exemplo("1,2,1,1,0")))
  println(dataTennis.frequencias.probabilidadeDeOcorrer(Exemplo("2,2,2,2,1")))
  println(dataTennis.frequencias.probabilidadeDeOcorrer(Exemplo("1,1,1,1,0")))
*/
  val dataSpam = read("/Users/rlmr/Documents/workspace/epIA/data/discretespambase.data",
    "/Users/rlmr/Documents/workspace/epIA/data/spambase.names")

  //println(Fold(1, dataSpam, 2.0 / 3))
  // println(Fold(10, dataSpam, 0.9))

  Genetico(dataSpam)

  def read(pathData: String, pathNames: String) = {

    val exemplos = Source.fromFile(new File(pathData)).getLines.map(Exemplo(_)).toList
    val list = Source.fromFile(new File(pathNames)).getLines.filterNot(l => l.startsWith("|") || l.isEmpty).toList
    val classes = list.head.split(",").toList
    val nomesAtributos = list.tail.map(_.split(":")(0))
    Data(exemplos, classes, nomesAtributos)
  }
}
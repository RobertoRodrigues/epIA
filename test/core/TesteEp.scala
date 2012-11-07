package core;

import org.junit.Assert.assertEquals
import org.junit.Test

class testeEP {

  @Test
  def TamanhoDaBasePlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(14, ep.tamanhoDaBase)
  }
  @Test
  def QuantidadeDeAtributosPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(5, ep.qtAtributos)
  }
  @Test
  def frequenciaClasse0Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(4, ep.frequenciaClasse(0))
  }
  @Test
  def frequenciaClasse1Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(10, ep.frequenciaClasse(1))
  }
  @Test
  def frequenciaAtributoTempoSolPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(5, ep.frequenciaAtributo(tempo)(sol))
  }
  @Test
  def frequenciaAtributoTempoNubladoPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(4, ep.frequenciaAtributo(tempo)(nublado))
  }
  @Test
  def frequenciaAtributoTempoChuvaPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(5, ep.frequenciaAtributo(tempo)(chuva))
  }
  @Test
  def frequenciaAtributoTemperaturaQuentePlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(4, ep.frequenciaAtributo(temperatura)(quente))
  }
  @Test
  def frequenciaAtributoTemperaturaMedianaPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(6, ep.frequenciaAtributo(temperatura)(mediana))
  }
  @Test
  def frequenciaAtributoTemperaturaFriaPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(4, ep.frequenciaAtributo(temperatura)(frio))
  }
  @Test
  def frequenciaAtributoUmidadeAltaPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(7, ep.frequenciaAtributo(umidade)(alta))
  }
  @Test
  def frequenciaAtributoUmidadeNormalPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(7, ep.frequenciaAtributo(umidade)(normal))
  }
  @Test
  def frequenciaAtributoVentoFortePlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(6, ep.frequenciaAtributo(vento)(forte))
  }
  @Test
  def frequenciaAtributoVentoFracoPlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(8, ep.frequenciaAtributo(vento)(fraco))
  }
  @Test
  def probabilidadeClasse0Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(5 / 14, ep.probabilidadeDeUmaClasseOcorra(0), math.E)
  }
  @Test
  def probabilidadeClasse1Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    assertEquals(9 / 14, ep.probabilidadeDeUmaClasseOcorra(1), math.E)
  }
  @Test
  def probabilidadeDeVentoForteDadoClasse1Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(3 / 9, ep.probabilidadeDeUmAtributoDadoUmaClasse(vento)(forte)(1), math.E)
  }
  @Test
  def probabilidadeDeVentoForteDadoClasse0Playtennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    //assertEquals(3 / 5, ep.probabilidadeDeUmAtributoDadoUmaClasse(vento)(forte)(0), math.E)
  }
  @Test
  def probabilidadeDeOcorrerDadoSolMedianaAltaFortePlaytennis() {
    var ep = new Dados("data/playTennis.names", "data/playTennis.data", 0);
    var naiveBayes = new NaiveBayes(ep)
    //assertEquals(0.205, naiveBayes.probabilidadeDeOcorrer((sol, mediana, alta, forte)), math.E)
  }

}

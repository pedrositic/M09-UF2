import java.util.Random;

/**
 * Volem simular una població que durant la seva edat treballadora cobra el sou
 * i paga impostos i mostrar la quantitat final de diners que ha guanyat.
 */

public class Treballador extends Thread {

  private int sou_anual_brut;
  private int edat_inici_treball;
  private int edat_fi_treball;
  private int edat_actual;
  private double cobrat;
  private Random rnd;

  public int getEdat() {
    return edat_actual;
  }

  public double getCobrat() {
    return cobrat;
  }


  public Treballador(String nom, int sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
    super(nom);
    this.sou_anual_brut = sou_anual_brut;
    this.edat_inici_treball = edat_inici_treball;
    this.edat_fi_treball = edat_fi_treball;
    this.edat_actual = 0;
    this.cobrat = 0.0;
  }

  private void cobra() {
    cobrat = cobrat + (sou_anual_brut/12);
  }

  private void pagaImpostos() {
    double paga = sou_anual_brut/12;
    cobrat = cobrat - (paga*0.24);
  }

  @Override
  public void run() {

    for (edat_actual = edat_inici_treball; edat_actual < edat_fi_treball; edat_actual++) { // ANYS
      for (int j = 0; j < 12; j++) { // MESOS
        try {
          cobra();
          sleep(10);
          pagaImpostos();
          sleep(10);
          //System.out.printf("%d - %.2f\n", edat_actual, cobrat);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

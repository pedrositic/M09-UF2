
public class Associacio {
  private int numSocis;
  private Soci[] socis;

  public Associacio() {
    numSocis = 1000;
    socis = new Soci[numSocis];

    for (int i = 0; i < socis.length; i++) {
      socis[i] = new Soci();
    }
  }

  public void iniciaCompteTempsSocis() {
    // starts
    for (Soci soci : socis) {
      soci.start();
    }
  }

  public void esperaPeriodeSocis() {
    // join
    for (Soci soci : socis) {
      try {
        soci.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void mostraBalancComptes() {
    Soci soci = socis[183];
    System.out.println("Saldo: " + soci.getCompte().getSaldo());
  }

  public static void main(String[] args) {
    Associacio asso = new Associacio();
    asso.iniciaCompteTempsSocis();
    asso.esperaPeriodeSocis();
    asso.mostraBalancComptes();
  }
}

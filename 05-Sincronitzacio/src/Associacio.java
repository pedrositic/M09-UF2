import java.util.Random;

public class Associacio {
  private int numSocis;
  private Soci[] socis;
  private Random random = new Random();

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
    int indexAleatori = random.nextInt(numSocis);
    Soci soci = socis[indexAleatori];
    System.out.println("Soci número: " + indexAleatori);
    System.out.printf("Saldo: %.2f\n", soci.getCompte().getSaldo());
}

  public static void main(String[] args) {
    Associacio asso = new Associacio();
    asso.iniciaCompteTempsSocis();
    asso.esperaPeriodeSocis();
    asso.mostraBalancComptes();
  }
}

public class Barri {
  public Estanc estanc;
  public Fumador[] fumadors = new Fumador[3];

  public Barri() {
    estanc = new Estanc();
    fumadors[0] = new Fumador(estanc, 0);
    fumadors[1] = new Fumador(estanc, 1);
    fumadors[2] = new Fumador(estanc, 2);
  }

  public static void main(String[] args) {
    Barri barri = new Barri();
    for (Fumador fumador : barri.fumadors) {
      fumador.start();
    }
    barri.estanc.start();

    try {
      for (Fumador fumador : barri.fumadors) {
        fumador.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      barri.estanc.tancarEstanc();
    } catch (Exception e) {
      System.out.println("Estanc tancat");
    }

  }

}

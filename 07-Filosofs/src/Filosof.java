import java.util.Random;

public class Filosof extends Thread {
  public Forquilla forquillaDreta;
  public Forquilla forquillaEsquerra;
  public int gana;

  public Filosof(String nom) {
    super(nom);
    gana = 0;
  }

  public void menjar() {
    while (true) {
      // Agafem la forquilla esquerra
      if (!forquillaEsquerra.isEnUs()) {
        forquillaEsquerra.setEnUs(true);
        System.out.printf("%s agafa la forquilla esquerra(%d)%n", getName(), forquillaEsquerra.getId());

        // Agafem la forquilla dreta
        if (!forquillaDreta.isEnUs()) {
          System.out.printf("%s agafa la forquilla dreta(%d)%n", getName(), forquillaDreta.getId());
          forquillaDreta.setEnUs(true);

          // Menjem
          System.out.println(getName() + " menja");
          gana = 0;

          Random rnd = new Random();
          int temps = 1000 + rnd.nextInt(1000);
          try {
            sleep(temps);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          forquillaDreta.setEnUs(false);
          forquillaEsquerra.setEnUs(false);

          // Hem menjat
          System.out.println(getName() + " ha acabat de menjar");
          return;
        } else { // Esta ocupada
          System.out.printf("%s deixa l'esquerra(%d) i espera (dreta ocupada)%n", getName(), forquillaEsquerra.getId());
          forquillaEsquerra.setEnUs(false);
        }
      }

      // No hem menjat
      gana++;
      System.out.printf("%s gana=%d%n", getName(), gana);

      // Espera entre 0,5s i 1s
      Random rnd = new Random();
      int espera = 500 + rnd.nextInt(500);
      try {
        sleep(espera);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void pensar() {
    Random rnd = new Random();
    int temps = rnd.nextInt(1000) + 1000;
    try {
      sleep(temps);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(getName() + " pensa");
  }

  @Override
  public void run() {
    while (true) {
      menjar();
      pensar();
    }
  }
}

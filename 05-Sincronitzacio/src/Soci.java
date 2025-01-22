import java.util.Random;

public class Soci extends Thread {
  private static Compte compte = Compte.getInstance();
  private float aportacio;
  private int esperaMax;
  private Random rd;
  private int maxAnys;

  public Soci() {
    this.aportacio = 10f;
    this.esperaMax = 100;
    this.rd = new Random();
    this.maxAnys = 10;
  }

  public Compte getCompte() {
    return compte;
  }

  @Override
  public void run() {
    for (int i = 0; i < maxAnys; i++) {
      for (int j = 0; j < 12; j++) {
        if (j % 2 == 0) {
          // ingres
          // Es sincronitza l'objecte compte, només una instancia de compte podra entrar
          synchronized(compte) {
            compte.setSaldo(compte.getSaldo() + aportacio);
          }

        } else {
          // retirada
          synchronized(compte) {
            compte.setSaldo(compte.getSaldo() - aportacio);
          }
        }
        int interval = rd.nextInt(esperaMax);
        try {
          sleep(interval);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

import java.util.Random;

public class Fumador extends Thread {
  private final Estanc estanc;
  private final int id;
  private Tabac tabac = null;
  private Llumi llumi = null;
  private Paper paper = null;
  private int fumades = 0;

  public Fumador(Estanc estanc, int id) {
    this.estanc = estanc;
    this.id = id;
  }

  public void compraTabac() {
    try {
      tabac = estanc.venTabac();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void compraLlumi() {
    try {
      llumi = estanc.venLlumi();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void compraPaper() {
    try {
      paper = estanc.venPaper();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void fuma() {
    if (tabac != null && llumi != null && paper != null) {
      tabac = null;
      llumi = null;
      paper = null;
      fumades++;
      System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
      try {
        Thread.sleep(500 + new Random().nextInt(501)); // Temps de fumar
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void run() {
    try {
      while (fumades < 3) {
        // Compra els materials que li falten
        if (tabac == null)
          compraTabac();
        if (llumi == null)
          compraLlumi();
        if (paper == null)
          compraPaper();

        // Intenta fumar si té tots els materials
        fuma();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
import java.util.Random;

public class Fumador extends Thread {
  private Estanc estanc;
  private int id;
  private Tabac tabac = null;
  private Llumi llumi = null;
  private Paper paper = null;
  private int fumades = 0;

  public Fumador(Estanc estanc, int id) {
    this.estanc = estanc;
    this.id = id;
  }

  public void fuma() {
    if (tabac != null && llumi != null && paper != null) {
      tabac = null;
      llumi = null;
      paper = null;
      fumades++;
      System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
      try {
        Thread.sleep(500 + new Random().nextInt(501));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void compraTabac() {
    tabac = estanc.venTabac();
  }

  public void compraLlumi() {
    llumi = estanc.venLlumi();
  }

  public void compraPaper() {
    paper = estanc.venPaper();
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      compraTabac();
      compraLlumi();
      compraPaper();
      fuma();      
    }
  }
}

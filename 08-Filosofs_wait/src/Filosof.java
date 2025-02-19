import java.util.Random;

public class Filosof extends Thread {
  private final int id;
  public Forquilla forquillaEsquerra, forquillaDreta;
  private int gana;
  private final Random random = new Random();

  public Filosof(int id) {
    this.id = id;
    this.gana = 0;
  }

  public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
    this.forquillaEsquerra = forquillaEsquerra;
  }

  public void setForquillaDreta(Forquilla forquillaDreta) {
    this.forquillaDreta = forquillaDreta;
  }

  @Override
  public void run() {
    try {
      while (true) {
        if (agafarForquilles()) {
          menjar();
          deixarForquilles();
        }
        pensar();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private synchronized boolean agafarForquilles() throws InterruptedException {
    while (true) {
      // Esta lliure
      if (forquillaEsquerra.getOwner() == -1) {
        // L'agafem
        agafaForquillaEsquerra();
        // Esta lliure
        if (forquillaDreta.getOwner() == -1) {
          // L'agafem
          agafaForquillaDreta();
          return true;
        } else {
          forquillaEsquerra.setOwner(-1);
          notifyAll();
          System.out.println("Filòsof " + id + " ha deixat la forquilla esquerra.");
        }
      }
      // No hem pogut agafar les forquilles
      gana++;
      System.out.println("Gana del filòsof " + id + ": " + gana);
      wait();
    }
  }

  private synchronized void agafaForquillaEsquerra() {
    forquillaEsquerra.setOwner(id);
    notifyAll();
    System.out.println("Filòsof " + id + " ha agafat la forquilla esquerra.");
  }

  private synchronized void agafaForquillaDreta() {
    forquillaDreta.setOwner(id);
    notifyAll();
    System.out.println("Filòsof " + id + " ha agafat la forquilla dreta.");
  }

  private void deixarForquilles() {
    synchronized (forquillaEsquerra) {
      forquillaEsquerra.setOwner(-1);
      forquillaEsquerra.notifyAll();
    }
    synchronized (forquillaDreta) {
      forquillaDreta.setOwner(-1);
      forquillaDreta.notifyAll();
    }
    System.out.println("Filòsof " + id + " ha deixat les forquilles.");
  }

  private void menjar() throws InterruptedException {
    System.out.println("Filòsof " + id + " està menjant.");
    Thread.sleep(1000 + random.nextInt(2000));
    gana = 0;
  }

  private void pensar() throws InterruptedException {
    System.out.println("Filòsof " + id + " està pensant.");
    Thread.sleep(1000 + random.nextInt(2000));
  }
}

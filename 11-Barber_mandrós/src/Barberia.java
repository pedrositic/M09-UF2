import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
  private Queue<Client> salaEspera;
  private int maxCadires;
  public final Object condBarber = new Object();
  private static Barberia barberia;

  public Barberia(int maxCadires) {
    this.maxCadires = maxCadires;
    this.salaEspera = new LinkedList<>();
  }

  public Client seguentClient() {
    return salaEspera.poll();
  }

  public void entrarClient(Client client) {
    if (salaEspera.size() < maxCadires) { // Si hi ha lloc
      salaEspera.add(client);
      System.out.println("Client " + client.getNom() + " en espera");
      synchronized (condBarber) {
        condBarber.notify(); // Despertem barber conforme hi ha gent
      }
    } else {
      System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      try {
        entrarClient(new Client(i));
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < 10; i++) {
      try {
        entrarClient(new Client(i));
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args) {
    barberia = new Barberia(3);
    Barber barber = new Barber("Pepe", barberia);
    barber.start();
    barberia.start();
  }

}
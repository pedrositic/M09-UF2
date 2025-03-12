import java.util.Random;

public class Barber extends Thread {
  private String name;
  private Barberia barberia;

  public Barber(String name, Barberia barberia) {
    this.name = name;
    this.barberia = barberia;
  }

  @Override
  public void run() {
    while (true) {
      Client client = barberia.seguentClient();
      if (client != null) {
        try {
          System.out.println("Tallant cabell a " + client.getNom());
          Thread.sleep(900 + new Random().nextInt(100));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        synchronized (barberia.condBarber) {
          try {
            System.out.println("Barber " + name + " esta dormint");
            barberia.condBarber.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
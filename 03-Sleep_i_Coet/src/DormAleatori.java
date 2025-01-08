import java.util.Random;

public class DormAleatori extends Thread {

  private long birth = System.currentTimeMillis();

  public DormAleatori(String nom) {
    super(nom);
  }

  private long getAge() {
    long now = System.currentTimeMillis();
    return now - birth;
  }

  @Override
  public void run() {
    Random rnd = new Random();

    // Executi 10 vegades
    for (int i = 0; i < 10; i++) {
      int interval = rnd.nextInt(1000);
      // Mostrar nom, num_iteració, interval_aleatori, total de temps des de la
      // construcció
      System.out.printf("%-4s(%d) a dormir %5dms total %4d\n",
          getName(),
          i,
          interval,
          getAge());
      // Dormim el fil d'acord el interval
      try {
        // Posem el fil en sleep
        Thread.sleep(interval);
      } catch (InterruptedException e) {
        System.out.println("El fil ha estat interromput.");
      }
    }
  }

  public static void main(String[] args) {
    DormAleatori joan = new DormAleatori("Joan");
    DormAleatori pep = new DormAleatori("Pep");

    joan.start();
    pep.start();

    System.out.println("------ Fi de main ------");

  }

}

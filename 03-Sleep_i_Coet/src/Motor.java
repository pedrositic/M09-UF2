import java.util.Random;

public class Motor extends Thread {
  private int powerObj;
  private int powerCurr;

  public Motor() {
    super();
    powerCurr = 0;
    powerObj = 0;
  }

  public int getPowerObj() {
    return powerObj;
  }

  public int getPowerCurr() {
    return powerCurr;
  }

  public void setPotencia(int powerObj) {
    this.powerObj = powerObj;
  }

  @Override
  public void run() {
    while (true) {
      while (powerCurr != powerObj) {
        int interval = new Random().nextInt(2000);

        try {
          // Posem el fil en sleep
          Thread.sleep(interval);
          // Sumar o restar depenent els valors
          powerCurr = powerObj > powerCurr ? powerCurr + 1 : powerCurr - 1;
        } catch (InterruptedException e) {
          System.out.println("El fil ha estat interromput.");
        }
      }

      try {
        sleep(100);
      } catch (InterruptedException e) {
        System.out.println("El fil ha estat interromput.");
      }
      if (powerCurr == 0)
        break;
    }

  }

}

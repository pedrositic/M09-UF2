import java.util.Random;

public class Assistent extends Thread {
  private Esdeveniment esde;
  private Random rd;

  public Assistent(){}

  public Assistent(String nom, Esdeveniment esde) {
    super(nom);
    this.esde = esde;
    rd = new Random();
  }

  @Override
  public void run() {
    while (true) {
      try {
        int prob = rd.nextInt(2);
        if(prob == 0) esde.ferReserva(this);
        else esde.cancelaReserva(this);
  
        int temps = rd.nextInt(1000);
        sleep(temps);
        
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }
}

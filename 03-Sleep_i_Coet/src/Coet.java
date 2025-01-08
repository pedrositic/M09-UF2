import java.util.Scanner;

public class Coet {
  public static Motor[] motors = new Motor[4];
  public static void main(String[] args) {

    // Crear motors
    for (int i = 0; i < 4; i++) {
      motors[i] = new Motor(i);
    }

    Scanner sc = new Scanner(System.in);
    while (true) {
      int p = Integer.parseInt(sc.nextLine())
    }
    
  }

  public void passaAPotencia(int p) {
    for (Motor motor : motors) {
      motor.setPotencia(p);
    }
  }

  public void arranca() {
    for (Motor motor : motors) {
      motor.start();
    }
  }
}

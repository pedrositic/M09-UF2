import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Coet {
  public static Motor[] motors = new Motor[4];
  public static void main(String[] args) {

    // Crear motors
    for (int i = 0; i < 4; i++) {
      motors[i] = new Motor(i);
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      try {
        int p = Integer.parseInt(reader.readLine());
        passaAPotencia(p);
        System.out.printf("Passant a potencia: %d\n", p);
        arranca();
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
  }

  public static void passaAPotencia(int p) {
    for (Motor motor : motors) {
      motor.setPotencia(p);
    }
  }

  public static void arranca() {
    for (Motor motor : motors) {
      try {
        motor.start();
      } catch (Exception e) {
      }
    }
  }
}

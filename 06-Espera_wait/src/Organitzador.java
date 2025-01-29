public class Organitzador {
  public static void main(String[] args) {
    Esdeveniment esde = new Esdeveniment(5);

    for (int i = 0; i < 10; i++) {
      String nom = "Assistent-"+String.valueOf(i);
      Assistent asis = new Assistent(nom, esde);
      asis.start();
    }
  }
}

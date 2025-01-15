public class Administracio {
  private static int num_poblacio_activa = 50;
  public static Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

  public Administracio() {
    for (int i = 0; i < poblacio_activa.length; i++) {
      poblacio_activa[i] = new Treballador("Ciutadà-" + i, 25000, 20, 65);
    }
  }

  public static void main(String[] args) {

    Administracio adm = new Administracio();

    // Posem en marxa
    for (Treballador treb : poblacio_activa) {
      try {
        treb.start();
      } catch (Exception e) {
      }
    }

    for (Treballador treb : poblacio_activa) {
      try {
        treb.join();
      } catch (Exception e) {
      }
    }

    for (Treballador treb : poblacio_activa) {
      System.out.printf("%-12s -> edat: %d / total: %.2f\n", treb.getName(), treb.getEdat(), treb.getCobrat());
    }

  }
}

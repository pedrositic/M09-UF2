public class Compte {
  private float saldo;
  private static Compte instance = new Compte();

  public float getSaldo() {
    return saldo;
  }

  public void setSaldo(float saldo) {
    this.saldo = saldo;
  }

  public static Compte getInstance() {
    return instance;
  }

  private Compte() {
    if (instance != null) {
      throw new IllegalStateException("Ja està instanciada");
    }
  }

}

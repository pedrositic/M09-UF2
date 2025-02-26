import java.util.Random;

public class Filosof extends Thread {
  private Long iniciGana;
  private Long fiGana;
  private Long Gana = 0L;
  public Forquilla forquillaEsquerra, forquillaDreta;
  private final Random random = new Random();

  public Filosof(String nom) {
    super(nom);
    setIniciGana(System.currentTimeMillis());
  }

  public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
    this.forquillaEsquerra = forquillaEsquerra;
  }

  public void setForquillaDreta(Forquilla forquillaDreta) {
    this.forquillaDreta = forquillaDreta;
  }

  public Long getIniciGana() {
    return iniciGana;
  }

  public void setIniciGana(Long iniciGana) {
    this.iniciGana = iniciGana;
  }

  public Long getFiGana() {
    return fiGana;
  }

  public void setFiGana(Long fiGana) {
    this.fiGana = fiGana;
  }


  @Override
  public void run() {
    try {
      while (true) {
        menjar();
        pensar();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void calcularGana() {
    Gana = (fiGana - iniciGana) / 1000;
    System.out.println("Filòsof " + getName() + " menja amb gana " + Gana);
  }

  public void reiniciaGana() {
    iniciGana = System.currentTimeMillis();
    Gana = 0L;
  }

  private boolean agafarForquilles() {
    // Si puc agafar la forquilla
    if (!forquillaEsquerra.getBloqueig().isLocked()) {
      agafaForquillaEsquerra();
      if (!forquillaDreta.getBloqueig().isLocked()) {
        agafaForquillaDreta();
        return true;
      } else { // No puc agafar la forquilla dreta
        forquillaEsquerra.deixa();
        return false;
      }
    } else {
      return false;
    }
  }

  private void agafaForquillaEsquerra() {
    forquillaEsquerra.agafa();
  }

  private void agafaForquillaDreta() {
    forquillaDreta.agafa();
  }

  private void deixarForquilles() {
    forquillaDreta.deixa();
    forquillaEsquerra.deixa();
    System.out.println("Filòsof " + getName() + " deixa les forquilles.");
  }

  private void menjar() throws InterruptedException {
    while(true) {
      // Intentem agafar les forquilles
      if (agafarForquilles()) {
        System.out.printf("Filòsof %s té forquilles esq(%s) dreta(%s).%n", getName(), forquillaEsquerra.getId(), forquillaDreta.getId());
        setFiGana(System.currentTimeMillis());
        calcularGana();
        Thread.sleep(1000 + random.nextInt(1000));
        reiniciaGana();
        deixarForquilles();
        System.out.println("Filòsof " + getName() + " ha acabat de menjar.");
        return;
      } else { // No hem pogut agafar les forquilles - no hem pogut menjar
        Thread.sleep(500 + random.nextInt(500));
      }
    }
  }

  private void pensar() throws InterruptedException {
    System.out.println("Filòsof " + getName() + " està pensant.");
    setIniciGana(System.currentTimeMillis());
    Thread.sleep(1000 + random.nextInt(1000));
  }
}

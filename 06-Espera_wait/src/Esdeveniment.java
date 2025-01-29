import java.util.List;
import java.util.ArrayList;

public class Esdeveniment {
  private int placesMaximes;
  private List<Assistent> assistents;
  private int placesDisponibles;

  public Esdeveniment(int placesMaximes) {
    this.placesMaximes = placesMaximes;
    placesDisponibles = placesMaximes;
    this.assistents = new ArrayList<>();
  }

  public int getPlacesMaximes() {
    return placesMaximes;
  }

  public void setPlacesMaximes(int placesMaximes) {
    this.placesMaximes = placesMaximes;
  }

  public List<Assistent> getAssistents() {
    return assistents;
  }

  public void setAssistents(List<Assistent> assistents) {
    this.assistents = assistents;
  }

  public int getPlacesDisponibles() {
    return placesDisponibles;
  }

  public void setPlacesDisponibles(int placesDisponibles) {
    this.placesDisponibles = placesDisponibles;
  }

  // Els metodes son synchronized per garantir que nomes un thread pot modificar
  // l'estat

  public synchronized void ferReserva(Assistent assistent) {
    // Mentre que no ho hagin places disponibles
    while (placesDisponibles == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // Si encara no estan totes les places ocupades l'afegim
    if (assistents.size() < placesMaximes) {
      assistents.add(assistent);
      placesDisponibles--;
      System.out.printf("%s ha fet una reserva. Places disponibles: %d\n", assistent.getName(), placesDisponibles);
      // Notifiquem de canvis
      notifyAll();
    }
  }

  public synchronized void cancelaReserva(Assistent assistent) {
    if (assistents.contains(assistent)) {
      assistents.remove(assistent);
      placesDisponibles++;
      System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d\n", assistent.getName(), placesDisponibles);
      // Es notifica conforme s'ha alliberat una plaça
      notifyAll();
    } else {
      System.out.printf("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d\n", assistent.getName(),
          placesDisponibles);
    }
  }
}
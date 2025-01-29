import java.util.List;
import java.util.ArrayList;

public class Esdeveniment {
  private int placesMaximes;
  private List<Assistent> assistents;
  private int placesDisponibles;

  public Esdeveniment(int placesMaximes) {
    this.placesMaximes = placesMaximes;
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

  public void ferReserva(Assistent assistent) {
    // Si encara no estan totes les places ocupades l'afegim
    if (assistents.size() < placesMaximes) {
      assistents.add(assistent);
      placesDisponibles--;
      System.out.printf("%s ha fet una reserva. Places disponibles: %d", assistent.getName(), placesDisponibles);
    }
  }

  public void cancelaReserva(Assistent assistent) {
    if (assistents.contains(assistent)) {
      assistents.remove(assistent);
      placesDisponibles++;
      System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d", assistent.getName(), placesDisponibles);
    }
    else {
      System.out.printf("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d", assistent.getName(), placesDisponibles);
    }
  }
}
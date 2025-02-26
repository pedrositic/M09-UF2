import java.util.ArrayList;
import java.util.List;

public class Taula {
    private List<Filosof> comensals;
    private List<Forquilla> forquilles;

    public Taula(int comensals) {
        this.comensals = new ArrayList<>();
        this.forquilles = new ArrayList<>();
        for (int i = 0; i < comensals; i++) {
            this.comensals.add(new Filosof("Fil" + String.valueOf(i)));
            this.forquilles.add(new Forquilla(i));
        }
        for (int i = 0; i < comensals; i++) {
            this.comensals.get(i).setForquillaEsquerra(this.forquilles.get(i));
            this.comensals.get(i).setForquillaDreta(this.forquilles.get((i + 1) % comensals));
        }
    }

    public void showTaula() {
        for (int i = 0; i < comensals.size(); i++) {
            System.out.println(comensals.get(i).getName() + " - esq: " + comensals.get(i).forquillaEsquerra.getId()
                    + " dret: " + comensals.get(i).forquillaDreta.getId());
        }
    }

    public void cridarATaula() {
        for (int i = 0; i < comensals.size(); i++) {
            comensals.get(i).start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}

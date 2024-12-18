package src;

import java.util.Random;

public class Futbolista extends Thread {
    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols;
    private int ntirades;

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public Futbolista(String nom) {
        super(nom); // Assigna el nom al furbolista
        ngols = 0;
        ntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            // Calculem la probabilitat
            // Ens donarà un float del 0.0 al 1.0
            float prob = new Random().nextFloat();
            // Si el random es mes gran que la probabilitat, llavors ha marcat gol
            if (prob > PROBABILITAT) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        String[] list = {
                "Lionel Messi",
                "Cristiano Ronaldo",
                "Neymar Jr.",
                "Kylian Mbappé",
                "Mohamed Salah",
                "Kevin De Bruyne",
                "Robert Lewandowski",
                "Virgil van Dijk",
                "Sergio Ramos",
                "Gareth Bale",
                "Gerard Piqué"
        };

        Futbolista[] players = new Futbolista[NUM_JUGADORS];

        // Creem els futbolistes
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Futbolista player = new Futbolista(list[i]);
            players[i] = player;
        }

        System.out.println("Inici dels xuts -----------------------");
        // Inicialitzem els fils
        for (Futbolista futbolista : players) {
            futbolista.start();
        }

        // Esperem a que acabin els fils per mostrar les dades
        for (Futbolista futbolista : players) {
            futbolista.join();
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("------------------- Estadistiques -----------------------");

        for (Futbolista futbolista : players) {
            System.out.printf("%-20s -> %d gols%n", futbolista.getName(), futbolista.getNgols());
        }

        System.out.println("Termina thread main");
    }

}

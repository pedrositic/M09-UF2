public class Fil extends Thread {
    public Fil(String nom) {
        super(nom); // Assigna el nom al fil
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);
            try {
                // Posem el fil en sleep per a que es vagin intercal·lant
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("El fil ha estat interromput.");
            }
        }
        System.out.println("Termina el fil " + getName());
    }
}

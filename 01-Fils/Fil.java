public class Fil extends Thread {
    public Fil(String nom) {
        super(nom); // Assigna el nom al fil
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);
        }
        System.out.println("Termina el fil " + getName());
    }
}

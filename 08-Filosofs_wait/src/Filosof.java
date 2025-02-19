import java.util.Random;

public class Filosof implements Runnable {
    private final int id;
    private final Forquilla forquillaEsquerra, forquillaDreta;
    private int gana;
    private final Random random = new Random();

    public Filosof(int id, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.id = id;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                if (agafarForquilles()) {
                    menjar();
                    deixarForquilles();
                } else {
                    espera();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + id + " està pensant.");
        Thread.sleep(1000 + random.nextInt(1000));
    }

    private boolean agafarForquilles() throws InterruptedException {
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                return true;
            } else {
                forquillaEsquerra.deixar();
            }
        }
        return false;
    }

    private boolean agafaForquillaEsquerra() throws InterruptedException {
        synchronized (forquillaEsquerra) {
            return forquillaEsquerra.agafar();
        }
    }

    private boolean agafaForquillaDreta() throws InterruptedException {
        synchronized (forquillaDreta) {
            return forquillaDreta.agafar();
        }
    }

    private void menjar() throws InterruptedException {
        System.out.println("Filòsof " + id + " està menjant.");
        Thread.sleep(1000 + random.nextInt(1000));
        gana++;
    }

    private void deixarForquilles() {
        synchronized (forquillaEsquerra) {
            forquillaEsquerra.deixar();
        }
        synchronized (forquillaDreta) {
            forquillaDreta.deixar();
        }
    }

    private void espera() throws InterruptedException {
        System.out.println("Filòsof " + id + " està esperant.");
        Thread.sleep(500 + random.nextInt(500));
    }
}

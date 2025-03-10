import java.util.Random;

public class Estanc {
    private boolean hasTobacco = false;
    private boolean hasPaper = false;
    private boolean hasLighter = false;

    public synchronized void supply(String item) {
        switch (item) {
            case "Tobacco":
                hasTobacco = true;
                break;
            case "Paper":
                hasPaper = true;
                break;
            case "Lighter":
                hasLighter = true;
                break;
        }
        System.out.println("Supplied: " + item);
        notifyAll(); // Notify all smokers that supplies have been added
    }

    public synchronized void acquireSupplies(Fumador fumador) throws InterruptedException {
        while (!(hasTobacco && hasPaper && hasLighter)) {
            wait(); // Wait until all supplies are available
        }
        // Once all supplies are available, consume them
        hasTobacco = false;
        hasPaper = false;
        hasLighter = false;
        System.out.println(fumador.getName() + " is smoking!");
    }
}
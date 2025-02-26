import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int id;
    private final ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void agafa() {
        bloqueig.lock();
    }

    public void deixa() {
        if (bloqueig.isHeldByCurrentThread()) bloqueig.unlock();
    }

    public ReentrantLock getBloqueig() {
        return bloqueig;
    }
}

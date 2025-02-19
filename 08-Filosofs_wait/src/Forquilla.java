public class Forquilla {
    private int id;
    private int owner;
    public static final int LLIURE = -1;

    public Forquilla(int id) {
        this.id = id;
        this.owner = LLIURE;
    }

    public synchronized int getOwner() {
        return owner;
    }

    public synchronized void setOwner(int owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }
}

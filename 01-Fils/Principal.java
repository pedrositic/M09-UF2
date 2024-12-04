public class Principal {
    public static void main(String[] args) {
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        pepe.setPriority(5);
        juan.setPriority(2);

        juan.start();
        pepe.start();

        System.out.println("Termina thread main");
    }
}

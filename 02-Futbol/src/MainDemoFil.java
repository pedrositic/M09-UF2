package src;

public class MainDemoFil {
    public static void main(String[] args) {
        Thread filActual = Thread.currentThread();

        System.out.println("FIL ACTUAL --------");
        System.out.println("Nom: " + filActual.getName());
        System.out.println("Prioritat: " + filActual.getPriority());
        System.out.println("toString(): " + filActual.toString());
    }
    
}
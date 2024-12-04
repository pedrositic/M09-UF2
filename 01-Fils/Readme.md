
## **Comportaments implementats**

### **Comportament 1: Execució alterna**
Els fils s'executen alternant-se de manera aparentment equitativa.

- **Comportament**:  
  ```  
  Termina thread main  
  Juan 1  
  Pepe 1  
  Juan 2  
  Pepe 2  
  Juan 3  
  Pepe 3  
  ...  
  Termina el fil Juan  
  Termina el fil Pepe  
  ```  


- **Descripció tècnica**:  
Aquest comportament s'obté deixant que la **MVJ (Màquina Virtual de Java)** gestioni l'ordre d'execució dels fils de manera automàtica. 

- **Codi:**

    ***Principal.java***
    ``` java
    public class Principal {
        public static void main(String[] args) {
            Fil juan = new Fil("Juan");
            Fil pepe = new Fil("Pepe");
            
            juan.start();
            pepe.start();

            System.out.println("Termina thread main");
        }
    }
    ```

    ***Fil.java***
    ``` java
    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);
        }
        System.out.println("Termina el fil " + getName());
    }
    ```
---

### **Comportament 2: Priorització de fils**
El fil **Pepe** s'executa majoritàriament abans que el fil **Juan**.

- **Comportament**:  
  ```  
  Termina thread main  
  Pepe 1  
  Pepe 2  
  Pepe 3  
  ...  
  Juan 1  
  Juan 2  
  Juan 3  
  ...  
  Termina el fil Pepe  
  Termina el fil Juan  
  ```  

- **Descripció tècnica**:  
  Per aconseguir aquest comportament, s'ha utilitzat el mètode `setPriority()` assignant una prioritat alta a **Pepe** i baixa a **Juan**.

- **Codi:**

    ***Principal.java***
    ``` java
    public class Principal {
        public static void main(String[] args) {
            Fil juan = new Fil("Juan");
            Fil pepe = new Fil("Pepe");

            pepe.setPriority(6);
            juan.setPriority(5);
            
            juan.start();
            pepe.start();

            System.out.println("Termina thread main");
        }
    }
    ```

    ***Fil.java***
    ``` java
    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);
        }
        System.out.println("Termina el fil " + getName());
    }
    ```
---

### **Comportament 3: Alternança estricta**
Els fils s'executen en ordre altern estricte.

- **Comportament**:  
  ```  
  Termina thread main  
  Juan 1  
  Pepe 1  
  Juan 2  
  Pepe 2  
  Juan 3  
  Pepe 3  
  ...  
  Termina el fil Juan  
  Termina el fil Pepe  
  ```  

- **Descripció tècnica**:  
  Per aconseguir aquest comportament, s’ha utilitzat el mètode `Thread.sleep()` per forçar pauses curtes entre les iteracions de cada fil, garantint que l’ordre d'execució sigui estrictament alternat.

- **Codi:**

    ***Principal.java***
    ``` java
    public class Principal {
        public static void main(String[] args) {
            Fil juan = new Fil("Juan");
            Fil pepe = new Fil("Pepe");
            
            juan.start();
            pepe.start();

            System.out.println("Termina thread main");
        }
    }
    ```

    ***Fil.java***
    ``` java
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
    ```
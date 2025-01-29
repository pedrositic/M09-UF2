# Preguntes teoriques

1. Per què s'atura l'execució al cap d'un temps?

    Doncs perquè arriba un moment que tots volen fer la reserva i ningú cancel·la, llavors estan tots esperant a que algún cancel·li.

2. Que passaria si en lloc de probabilitat de 50-50 fora de 70 (Reserva) - 30 (Cancel·la)? I si foren al revés les probabilitats? -> Mostra la porció del codi modificada i la sortida resultant en cada un dels 2 casos

    **Cas 1:** 70-30
    - Hi hauran més reserves que cancel·lacions.
    - Molts assistents poden estar esperant per fer reserves quan no hi hagi places disponibles.
    ```java
    @Override
    public void run() {
      try {
        int prob = rd.nextInt(10); // Genera un número entre 0 i 9
        if (prob < 7) { // Probabilitat de reserva del 70%
          esde.ferReserva(this);
        } else { // Probabilitat de cancel·lació del 30%
          esde.cancelaReserva(this);
        }
        int temps = rd.nextInt(1000);
        sleep(temps);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    ---------------------------------------------------------
    Output:
    Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
    Assistent-9 ha fet una reserva. Places disponibles: 4
    Assistent-8 ha fet una reserva. Places disponibles: 3
    Assistent-7 ha fet una reserva. Places disponibles: 2
    Assistent-6 ha fet una reserva. Places disponibles: 1
    Assistent-5 ha fet una reserva. Places disponibles: 0
    Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
    ```
    ---

    **Cas 2:** 30-70
    - Hi hauran més cancel·lacions que reserves.
    - És possible que molts assistents intentin cancel·lar reserves que encara no han estat fetes, resultant en missatges d'errors.

    ```java
    @Override
    public void run() {
      try {
        int prob = rd.nextInt(10); // Genera un número entre 0 i 9
        if (prob < 3) { // Probabilitat de reserva del 30%
          esde.ferReserva(this);
        } else { // Probabilitat de cancel·lació del 70%
          esde.cancelaReserva(this);
        }
        int temps = rd.nextInt(1000);
        sleep(temps);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    ---------------------------------------------------------
    Output:
    Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
    Assistent-9 ha fet una reserva. Places disponibles: 4
    Assistent-8 ha fet una reserva. Places disponibles: 3
    Assistent-7 ha fet una reserva. Places disponibles: 2
    Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
    ```

3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?
   Doncs perque una llista permet gestionar directament qui ha fet la reserva. 
   Es pot saber si un assistent esta a la llista.
   És molt més funcional que només un enter, que només indica el nombre total de places reservades sense informació detallada.
   
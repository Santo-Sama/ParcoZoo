import java.util.Scanner;

public class Utility {

    static Scanner in = new Scanner(System.in).useDelimiter("\n");


    public static int sceltaInt() {

        int scelta = 0;
        boolean sceltaKO = true;

        while (sceltaKO) {
            try {
                scelta = in.nextInt();
                sceltaKO = false;
            } catch (Exception e) {
                System.out.println("Hai inserito un valore non valido riprova");
                in.next();
            }
        }
        return scelta;
    }

    public static String sceltaString() {

        String scelta = null;
        boolean sceltaKO = true;

        while (sceltaKO) {
            try {
                scelta = in.next();
                scelta.trim().toLowerCase();
                sceltaKO = false;
            } catch (Exception e) {
                System.out.println("Hai inserito un valore non valido riprova");
                scelta = in.next();
            }
        }
        return scelta;
    }

    public static void stampaAzioni() {

        System.out.println("1. Aggiungi un nuovo animale");
        System.out.println("2. Rimuovi un anime tramite ID");
        System.out.println("3. Visualizza gli animali presenti");
        System.out.println("4. Cerca e stampa animale per ID");
        System.out.println("5. Aggiungi una nuova specie");
        System.out.println("6. Rimuovi una specie");
        System.out.println("7. Visualizza lista specie");
        System.out.println("8. Esci");
    }


    public static String sceltaID() {

        String id = "";
        do {
            try {
                System.out.println("Inserisci un Id");
                id = in.next();
                if (id.length() != 3) {
                    System.out.println("DEVI INSERIRE 3 CARATTERI");
                }
            } catch (Exception e) {
                System.out.println("DEVI INSERIRE 3 CARATTERI");
                in.next();
            }
        } while (id.length() != 3);

        return id;
    }

    //dopo ogni azione compiuta
    public static int esciContinua(){
        System.out.println("\n1. Torna al menu");
        System.out.println("2. Esci ");
        int scelta = sceltaInt();
        while (scelta<0 || scelta>3){
            System.out.println("Hai inserito un valore non valido riprova");
            scelta = sceltaInt();
        }
        if (scelta == 2){
            return 8;
        }else {
            return 0;
        }

    }

    //non funziona
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


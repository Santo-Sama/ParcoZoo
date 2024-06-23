import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");

        ParcoZoo zoo = new ParcoZoo();

        int scelta = 0;

        while (scelta != 8) {

            Utility.stampaAzioni();
            scelta = Utility.sceltaInt();
            Utility.clearScreen();

            switch (scelta){

                case 1:
                    zoo.addAnimal();
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();
                    break;

                case 2:
                    zoo.removeAnimalFromId(Utility.sceltaID());
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                case 3:
                    zoo.stampaListaAnimal();
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                case 4:
                    zoo.stampaForId(Utility.sceltaID());
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                case 5:
                    zoo.addSpecie();
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                case 6:
                    zoo.rimuoviUnaSpecie();
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                case 7:
                    zoo.stampaSpecie();
                    scelta = Utility.esciContinua();
                    Utility.clearScreen();

                    break;

                default:
                    System.out.println("Hai inserito un valore non valido riprova");
                    break;
            }
        }


    }
}
import java.util.*;

public class ParcoZoo {
    private final Scanner in = new Scanner(System.in).useDelimiter("\n");


    private ArrayList<Animal> listaAnimal;
    private HashMap<String, Animal> idMap;
    private HashSet<String> listaSpecie;

    public ParcoZoo() {
        listaAnimal = new ArrayList<>();
        idMap = new HashMap<>();
        listaSpecie = new HashSet<>();
    }

    //l'oggetto animal dev'essere creato tramite questa funzione e non in altri modi
    public void addAnimal() {
        String tempID;
        String tempSpecie = null;

        //verifichiamo se Id inserito è gia presente
        System.out.println("Inserisci ID univoco 3 caratteri");
        tempID = in.next();
        while (controlloIdPresente(tempID) || tempID.length() != 3) {
            if (controlloIdPresente(tempID)) {
                System.out.println("ID gia presente");
            }
            System.out.println("Imettere un nuovo ID di 3 caratteri");
            tempID = in.next();
        }

        System.out.println("Vuoi utilizzare una specie gia presente o crearne una nuova?");

        int scelta = 0;

        //le specie possono essere aggiunte in fase di addanimal
        while (scelta != 1 && scelta != 2) {
            System.out.println("1. Crea una nuova specie");
            System.out.println("2. Seleziona specie");
            scelta = in.nextInt();

            switch (scelta) {

                case 1:
                    addSpecie();
                    scelta = 0;
                    break;

                case 2:
                    if (listaSpecie.isEmpty()) {
                        System.out.println("Non è presente nessuna specie nel registro");
                        break;
                    } else {
                        tempSpecie = selezionaSpecie();
                    }
                    break;

                default:
                    System.out.println("Valore non valido");
                    System.out.println("Inserisci un altro valore");
                    System.out.println("1. Crea una nuova specie");
                    System.out.println("2. Seleziona specie presente");
                    scelta =in.nextInt();

            }
        }

        //una volta ottenuti i dati necessari creiamo l'oggetto animal
        Animal animal = new Animal(tempID,tempSpecie);
        //lo aggiungiamo alla lista e alla mappa per tenere id univoci
        listaAnimal.add(animal);
        idMap.put(tempID,animal);
        System.out.println(animal.getName() + " è stato aggiunto allo zoo");

    }

    public void removeAnimalFromId(String tempId) {
        //verifichiamo se ID inserito è presente tramite la mappa
        if (controlloIdPresente(tempId)) {
            Set<String> keys = idMap.keySet();
            for (String key : keys) {
                if (key.equals(tempId)) {
                    //se è presente rimuovere prima dalla lista e successivamente dalla mappa
                    listaAnimal.remove(idMap.get(key));
                    idMap.remove(key);
                    System.out.println("Animale rimosso");
                    break;
                }
            }
        } else {
            System.out.println("ID non rovato");
        }
    }


    public void stampaListaAnimal() {
        if (listaAnimal.isEmpty()){
            System.out.println("Non è presente nessun animale");
        }else {
            for (Animal animale : listaAnimal) {
                animale.stampaDettagli();
            }
        }
    }

    //se ID è presente torna true
    public boolean controlloIdPresente(String id) {
        for (Animal animal : listaAnimal) {
            if (animal.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //ricerca per id e stampa di un animale
    public void stampaForId(String tempId) {
        if (controlloIdPresente(tempId)) {
            Set<String> verifica = idMap.keySet();
            for (String key : verifica) {
                if (key.equals(tempId)) {
                    idMap.get(tempId).stampaDettagli();
                }
            }
        } else {
            System.out.println("ID non rovato");
        }
    }

    public void addSpecie(){

        boolean confermaAdd = true;
        System.out.println("Inserisci il nome della specie: ");
        String tempSpecie = in.next();
        if (listaSpecie.isEmpty()){
            listaSpecie.add(tempSpecie);
            System.out.println("La specie: " + tempSpecie + " è ora disponibile nel registro");
        }else {

            if (listaSpecie.contains(tempSpecie)){
                System.out.println("Specie gia esiste nel regisro");
                confermaAdd = false;
            }
            if (confermaAdd) {
                listaSpecie.add(tempSpecie);
                System.out.println("La specie: " + tempSpecie + " è ora disponibile nel registro");
            }
        }

    }

    //oltre la stampa comunica quanti animali sono presenti in ogni specie
    public void stampaSpecie(){
        if (listaSpecie.isEmpty()){
            System.out.println("Non è presente nessuna specie nel registro");
        }else {
            int numListaSpecie = 1;
            int numAnimSpecie;
            HashMap<String, Integer> listaAnimSpecie = new HashMap<>();

            System.out.println("Le specie disponibili sono: ");
            for (String specie : listaSpecie){
                numAnimSpecie = 0;
                for (Animal animal : listaAnimal){
                    if (animal.getSpecie().equals(specie)){
                        numAnimSpecie++;
                    }
                }
                //inseriamo nella mappa specie e contatore
                listaAnimSpecie.put(specie,numAnimSpecie);

                System.out.print(numListaSpecie +". "+  specie);

                //lasciamo uno spazio default per avere il num di animali nella stessa colonna
                for (int i = 0; i< (15 - specie.length()); i++){
                    System.out.print(" ");
                }

                //se non è presente nessuna animale per specie stampa in rosso
                if (listaAnimSpecie.get(specie).equals(0)){
                    System.out.print("\033[0;31m");
                    System.out.print("Animali: " + listaAnimSpecie.get(specie));
                    System.out.print("\033[0m");
                }
                //se sono presenti animali per specie stampa in verde
                else if (listaAnimSpecie.get(specie) >0) {
                    System.out.print("\033[0;32m");
                    System.out.print("Animali: " + listaAnimSpecie.get(specie));
                    System.out.print("\033[0m");
                }
                System.out.println();
                numListaSpecie ++;
            }
        }
    }

    public String selezionaSpecie(){

        //la mappa serve ad associare un input int con valore della specie
        HashMap<Integer,String> tempListaSpecie = new HashMap<>();
        String specieSelezionata;

        System.out.println("Seleziona una specie inserendo il numero corrispondente");
        System.out.println("Le specie disponibili sono: ");

        int numSpecie = 1;
        for (String specie: listaSpecie){
            System.out.println(numSpecie +" "+  specie);
            //aggiungiamo alla mappa gli accopiamenti
            tempListaSpecie.put(numSpecie,specie);
            numSpecie ++;
        }
        //verifichiamo input
        int sceltaSpecie = Utility.sceltaInt();
        while (sceltaSpecie < 0 && sceltaSpecie > tempListaSpecie.size() + 1) {
            System.out.println("Scelta non valida riprova");
            sceltaSpecie = in.nextInt();
        }
        //torniamo la stringa contenente la specie prendendo il valore dalla mappa
        specieSelezionata = tempListaSpecie.get(sceltaSpecie);
        return specieSelezionata;
    }

    public void rimuoviUnaSpecie(){
        String specieToLeave = selezionaSpecie();
        boolean okRemove = true;

        //se ci sono animali presenti nella specie non è possibile rimuoverla
        for (Animal animal : listaAnimal){
            if (animal.getSpecie().equals(specieToLeave)){
                System.out.println("Non è possibile rimuovere questa specie perchè contiene degli animali");
                okRemove = false;
                break;
            }
        }
        if (okRemove){
            listaSpecie.remove(specieToLeave);
        }
    }

}

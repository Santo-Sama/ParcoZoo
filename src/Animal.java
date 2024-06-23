import java.util.Objects;
import java.util.Scanner;

public class Animal {
    private final Scanner in = new Scanner(System.in).useDelimiter("\n");

    private String id;
    private String specie;
    private String name;

    //accedere al costruttore solo tramite richiamo funzione addAnimal su ParcoZoo
    public Animal(String id, String specie){
        this.id = id;
        this.specie = specie;
        System.out.println("Inserisci il nome");
        name = in.next();

    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }


    public void stampaDettagli(){
        System.out.println("ID: "+id+ "  name: " + name + "  specie: " + specie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

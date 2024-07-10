package net.dazay.control;

import javafx.util.Pair;
import net.dazay.db.ZooDatabase;
import net.dazay.model.Animal;
import net.dazay.model.Visit;

import java.util.List;

public class ZooService
{
    private final ZooDatabase zooDatabase;

    public ZooService()
    {
        zooDatabase = ZooDatabase.getInstance();
    }

    public void addAnimal(String species, String name, int age, String habitat)
    {
        Animal animal = new Animal(-1, species, name, age, habitat);
        zooDatabase.addAnimal(animal);
    }

    public void addVisit(int animalId, String date)
    {
        Visit visit = new Visit(-1, animalId, date);
        zooDatabase.addVisit(visit);
    }

    public List<Animal> findAnimalsBySpec(String species)
    {
        return zooDatabase.findAnimalsBySpecies(species);
    }

    public List<Animal> findAnimalsByHabitat(String habitat)
    {
        return zooDatabase.findAnimalsByHabitat(habitat);
    }

    public List<Pair<String ,Integer>> findMostPopularAnimals()
    {
        return zooDatabase.getMostPopularAnimals();
    }

    public boolean containsAnimal(int id)
    {
        List<Animal> animals = zooDatabase.getAllAnimals();
        for(Animal animal : animals)
            if(animal.getId() == id)
                return true;
        return false;
    }
}

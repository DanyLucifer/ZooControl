package net.dazay.model;

public class Animal
{
    private final int id;
    private final String species;
    private final String name;
    private final int age;
    private final String habitat;

    public Animal(int id, String species, String name, int age, String habitat)
    {
        this.id = id;
        this.species = species;
        this.name = name;
        this.age = age;
        this.habitat = habitat;
    }

    public int getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHabitat() {
        return habitat;
    }

    public String toString()
    {
        return "Id: " + id + "\nSpecies: " + species + "\nName: " + name + "\nAge: " + age + "\nHabitat: " + habitat;
    }
}

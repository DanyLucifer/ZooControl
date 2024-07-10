package net.dazay;

import net.dazay.control.ZooService;
import net.dazay.model.Animal;

import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;
import net.dazay.util.Util;

public class Main {

    private static final ZooService service = new ZooService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add animal");
            System.out.println("2. Add visit");
            System.out.println("3. Search animals by species");
            System.out.println("4. Search animals by habitat");
            System.out.println("5. Get most popular animals");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    addVisit();
                    break;
                case 3:
                    searchAnimalsBySpecies();
                    break;
                case 4:
                    searchAnimalsByHabitat();
                    break;
                case 5:
                    getPopularAnimals();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }

    //Обработка действий команд -- начало

    private static void getPopularAnimals() {
        List<Pair<String, Integer>> result = service.findMostPopularAnimals();
        System.out.println(Util.pairToString(result));
    }

    private static void searchAnimalsBySpecies() {
        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        List<Animal> animals = service.findAnimalsBySpec(species);
        System.out.println("Animals with species: " + species);
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static void searchAnimalsByHabitat() {
        System.out.print("Enter habitat: ");
        String habitat = scanner.nextLine();

        System.out.println("Animals with habitat: " + habitat);
        List<Animal> animals = service.findAnimalsByHabitat(habitat);
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static void addVisit() {
        System.out.print("Enter animal ID: ");
        int animalId = scanner.nextInt();
        if(!service.containsAnimal(animalId))
        {
            String errorString = String.format("Error! Animal with id %s not found!", animalId);
            System.out.println(errorString);
            return;
        }
        scanner.nextLine();
        System.out.print("Enter visit date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        service.addVisit(animalId, date);
        System.out.println("Visit added.");
    }

    private static void addAnimal() {
        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        System.out.print("Enter name (optional): ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter habitat: ");
        String habitat = scanner.nextLine();

        service.addAnimal(species, name, age, habitat);
        System.out.println("Animal added.");
    }

    //Обработка действий команд -- конец
}
package net.dazay;

public class Storage
{
    public static final String createTableAnimals = "CREATE TABLE IF NOT EXISTS animals ("
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " species TEXT NOT NULL,"
            + " name TEXT,"
            + " age INTEGER,"
            + " habitat TEXT NOT NULL"
            + ");";
    public static final String createTableVisits = "CREATE TABLE IF NOT EXISTS visits ("
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " animal_id INTEGER NOT NULL,"
            + " date TEXT NOT NULL,"
            + " FOREIGN KEY (animal_id) REFERENCES animals(id)"
            + ");";
    public static final String insertAnimal = "INSERT INTO animals(species, name, age, habitat) VALUES(?, ?, ?, ?)";
    public static final String insertVisit = "INSERT INTO visits(animal_id, date) VALUES(?, ?)";
    public static final String selectAnimalsSpecies = "SELECT * FROM animals WHERE species = ?";
    public static final String selectAnimalsHabitat = "SELECT * FROM animals WHERE habitat = ?";
    public static final String selectPopularAnimals = "SELECT species, COUNT(*) as count FROM visits " +
            "JOIN animals ON visits.animal_id = animals.id " +
            "GROUP BY species ORDER BY count DESC LIMIT 3";
    public static final String selectAllAnimals = "SELECT * FROM animals";
}

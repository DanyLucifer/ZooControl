package net.dazay.db;

import javafx.util.Pair;
import net.dazay.Storage;
import net.dazay.model.Animal;
import net.dazay.model.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZooDatabase
{
    private final String URL = "jdbc:sqlite:zoo.db";

    private static ZooDatabase dbObject = null;

    private ZooDatabase()
    {
        initTables();
    }

    public void addAnimal(Animal animal)
    {
        try(Connection c = connect(); PreparedStatement ps = c.prepareStatement(Storage.insertAnimal))
        {
            ps.setString(1, animal.getSpecies());
            ps.setString(2, animal.getName());
            ps.setInt(3, animal.getAge());
            ps.setString(4, animal.getHabitat());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }


    public void addVisit(Visit visit)
    {
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(Storage.insertVisit))
        {
            ps.setInt(1, visit.getAnimalId());
            ps.setString(2, visit.getDate());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param spec - вид животного
     * @return список животных, вида spec
     */
    public List<Animal> findAnimalsBySpecies(String spec)
    {
        List<Animal> animals = new ArrayList<>();

        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(Storage.selectAnimalsSpecies))
        {
            ps.setString(1, spec);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                animals.add(new Animal(rs.getInt("id"),
                        rs.getString("species"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("habitat")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return animals;
    }

    /**
     *
     * 0@return список всех животных зоопарка
     */
    public List<Animal> getAllAnimals()
    {
        List<Animal> animals = new ArrayList<>();

        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(Storage.selectAllAnimals))
        {
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                animals.add(new Animal(rs.getInt("id"),
                        rs.getString("species"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("habitat")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return animals;
    }

    /**
     *
     * @param habitat - среда обитания
     * @return список животных, обитаемых в среде habitat
     */
    public List<Animal> findAnimalsByHabitat(String habitat)
    {
        List<Animal> animals = new ArrayList<>();

        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(Storage.selectAnimalsHabitat))
        {
            ps.setString(1, habitat);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                animals.add(new Animal(rs.getInt("id"),
                        rs.getString("species"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("habitat")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return animals;
    }

    /**
     * Метод для получения самых посещаемых животных зоопар4ка
     * @return список из трех самых популярных животных, с количеством посещений
     */
    public List<Pair<String, Integer>> getMostPopularAnimals()
    {
        List<Pair<String, Integer>> resultPair = new ArrayList<>();
        try(Connection c = connect(); Statement s = c.createStatement(); ResultSet rs = s.executeQuery(Storage.selectPopularAnimals))
        {
            if (rs.next()) {
                resultPair.add(new Pair<>(rs.getString("species"), rs.getInt("count")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return resultPair;
    }

    /**
     * Метод для инициализаии таблиц в случае их отсутствия
     */
    private void initTables()
    {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(Storage.createTableAnimals);
            stmt.execute(Storage.createTableVisits);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Единый метод для получения
     * подключения к базе данных
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**Метод для получения объекты базы данных,
     * без возможности повторной инициализации
     */
    public static ZooDatabase getInstance()
    {
        return dbObject == null ? (dbObject =  new ZooDatabase()) : dbObject;
    }
}

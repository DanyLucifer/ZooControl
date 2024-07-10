package net.dazay.model;

public class Visit
{
    private final int id;
    private final int animalId;
    private final String date;

    public Visit(int id, int animalId, String date)
    {
        this.id = id;
        this.animalId = animalId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getDate() {
        return date;
    }
}

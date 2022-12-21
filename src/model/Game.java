package model;

import java.util.ArrayList;

public class Game {

    private Environment environment;
    private int dayNumber;

    public Game() {
        this.environment = new Environment(new Weather(), initHouse());
        this.dayNumber = 0;
    }




    private House initHouse() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room("Cuisine"));
        rooms.add(new Room("Chambre"));
        rooms.add(new Room("Salon"));
        rooms.add(new Room("Salle de bain"));
        rooms.add(new Room("Bureau"));

        ArrayList<PowerGenerator> powerGenerators = new ArrayList<PowerGenerator>();
        powerGenerators.add(new PowerGenerator("Linky", 0, 2, 100));

        ArrayList<Perk> perks = new ArrayList<Perk>();
        perks.add(new Perk(0, "Automatic windows", 1000, 30, 20, false));
        perks.add(new Perk(0, "Automatic heaters/AC", 1500, 50, 30, false));

        Couple couple = new Couple();
        couple.addPerson(new Person("Jean", 0, 10, new ArrayList<Task>()));
        couple.addPerson(new Person("Marie", 0, 10, new ArrayList<Task>()));

        return new House(18f, .5f, 0, false, rooms, powerGenerators, perks, couple, 21f, .5f);
    }

}

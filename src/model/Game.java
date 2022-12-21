package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Environment environment;
    private int dayNumber;
    private ArrayList<Task> availableTasks;

    // à remplacer par les GUI
    private char newDayGUI;
    private char duringDayGUI;
    private char endOfDayGUI;

    public Game() {
        this.environment = new Environment(new Weather(), initHouse());
        this.dayNumber = 0;
        this.availableTasks = this.initTasks();
    }

    public void run() {

        while (true) {

            this.dayNumber++;
            environment.onNewDay(this.dayNumber);
            // à remplacer avec interfaces graphiques
            System.out.println(this.environment.getWeather());
            System.out.println(this.environment.getHouse());

            for (int i = 0; i < 2; i++) { // choix des actions

                Person person = this.environment.getHouse().getCouple().getPersons().get(i);
                System.out.println("Choisir actions de " + person.getName() +": (entrez 99 quand c'est terminé)");

                Scanner scanner = new Scanner(System.in);

                int taskNumber = 0;

                while (taskNumber != 99) {
                    taskNumber = scanner.nextInt();
                    person.addTask(this.availableTasks.get(taskNumber));
                }

            }

            // déroulement des actions
            // for task in Tasks:
            // faire tache
            // this.environment.getHouse().update()

            System.out.println(this.environment.getHouse());
            if (this.environment.getHouse().isViable()) {
                System.out.println("La maison est toujours viable");
            } else {
                System.out.println("La maison n'est plus viable, vous avez perdu");
                break;
            }

        }

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
        perks.add(new Perk(0, "Fenetres automatiques", 1000, 100, 30, false));
        perks.add(new Perk(1, "Chauffage et climatisation automatique", 1500, 150, 50, false));
        perks.add(new Perk(2, "Ameliorer le matelas", 1000, 0, 0, false));
        perks.add(new Perk(3, "Robot de cuisine", 1000, 0, 0, false));

        Couple couple = new Couple();
        couple.addPerson(new Person("Jean", 0, 10, new ArrayList<Task>()));
        couple.addPerson(new Person("Marie", 0, 10, new ArrayList<Task>()));

        return new House(18f, .5f, 0, false, rooms, powerGenerators, perks, couple, 21f, .45f);
    }

    private ArrayList<Task> initTasks() {
        availableTasks = new ArrayList<Task>();

        availableTasks.add(new Task(0, "Allumer le chauffage", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(1, "Eteindre le chauffage", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(2, "Allumer la climatisation", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(3, "Eteindre la climatisation", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(4, "Ouvrir les fenêtres", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(5, "Fermer les fenêtres", 1, 0, 0, 0, 0, 0));
        availableTasks.add(new Task(6, "Travailler", 5, 0, 0, 200, 0, 0));
        availableTasks.add(new Task(7, "Dormir", 0, 2, 0, 0, 0, 0));
        availableTasks.add(new Task(8, "Faire du vélo", 4, 0, 0, 0, 0, 50));
        availableTasks.add(new Task(9, "Cuisiner", 2, 0, 0, 0, 30, 0));
        availableTasks.add(new Task(10, "Réparer l'éléctricité", 2, 0, 200, 0, 0, 0));

        return availableTasks;
    }

}

package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private House house;
    private Weather weather;
    private int dayNumber;
    private ArrayList<Task> availableTasks;

    // à remplacer par les GUI
    private char newDayGUI;
    private char duringDayGUI;
    private char endOfDayGUI;

    public Game() {
        this.house = initHouse();
        this.weather = new Weather();
        this.dayNumber = 0;
        this.availableTasks = this.initTasks();
    }

    public void onNewDay(int dayNumber) {
        this.house.onNewDay();
        this.weather.update(dayNumber);
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            this.onNewDay(this.dayNumber);

            System.out.println("----- JOUR " + this.dayNumber + " -----");

            // interface de début de journée

            System.out.println(this.weather.toString());
            System.out.println(this.house.toString());

            for (Person person: this.house.getCouple().getPersons()) {

                System.out.println("Choisir actions de " + person.getName() + ": (entrez 99 quand c'est terminé)");

                int taskNumber;

                do {
                    taskNumber = scanner.nextInt();
                    person.addTask(this.availableTasks.get(taskNumber));
                } while (taskNumber != 99);

            }

            // interface durant la journée

            boolean taskRemaining = true;

            do {

                System.out.println(this.house.toString());
                System.out.println(this.house.getCouple().toString());

                for (Person person: this.house.getCouple().getPersons()) {
                    if (person.getTasks().size() == 0) {
                        taskRemaining = false;
                    } else {
                        Task currentTask = person.getTasks().remove(0);
                        taskRemaining = true;
                        float temperature;
                        switch (currentTask.getId()) {
                            case 0 -> { // Allumer le chauffage
                                System.out.println("À quelle température allumer le chauffage ?");
                                temperature = scanner.nextFloat();
                                this.house.setAllHeatersTemperature(temperature);
                                System.out.println("Chauffages allumés à " + temperature + "°C.");
                            }
                            case 1 -> { // Eteindre le chauffage
                                this.house.turnOffAllHeaters();
                                System.out.println("Chauffages éteins.");
                            }
                            case 2 -> { // Allumer clim
                                System.out.println("À quelle température allumer la climatisation ?");
                                temperature = scanner.nextFloat();
                                this.house.setAllACTemperature(temperature);
                                System.out.println("Climatiseurs allumés à " + temperature + "°C.");
                            }
                            case 3 -> { // Eteindre clim
                                this.house.turnOffAllAC();
                                System.out.println("Climatiseurs éteins.");
                            }
                            case 4 -> {
                                this.house.setAllWindowsOpen(true);
                                System.out.println("Fenêtres ouvertes.");
                            }
                            case 5 -> {
                                this.house.setAllWindowsOpen(false);
                                System.out.println("Fenêtres fermées.");
                            }
                            case 6 -> System.out.println("Travail productif pour " + person.getName() + ".");
                            case 7 -> System.out.println("Ptite sieste pour " + person.getName() + ".");
                            case 8 -> System.out.println("Séance de vélo riche en production d'énérgie.");
                            case 9 -> System.out.println("Un bon repas pour requinquer l'habitant");
                            case 10 -> this.house.setOnPowerOutage(false);
                        }
                        person.setStamina(person.getStamina() + currentTask.getStamina());
                        this.house.getCouple().setMoney(this.house.getCouple().getMoney() + currentTask.getMoney());
                        this.house.setEnergy(this.house.getEnergy() + currentTask.getEnergy());
                    }
                }

                this.house.update(this.weather);

            } while (taskRemaining);

            System.out.println(this.house.toString());

            if (this.house.isViable()) {
                System.out.println("La maison est toujours viable");
            } else {
                System.out.println("La maison n'est plus viable, vous avez perdu");
                break;
            }

            this.dayNumber++;

            this.weather.update(this.dayNumber);

            ArrayList<Perk> perks = this.house.getPerks();

            System.out.println("Améliorations disponibles:");
            for (Perk perk: perks) {
                System.out.println(perk);
            }
            System.out.println("Entrez le numéro d'amélioration souhaité (99 pour finir):");

            int perkId;
            do {
                perkId = scanner.nextInt();
                if (perks.get(perkId).isUpgraded()) {
                    System.out.println("Amélioration déjà effectuée");
                } else if (perks.get(perkId).getInstallationCost() <= this.house.getCouple().getMoney()) {
                    this.house.getCouple().setMoney(
                            this.house.getCouple().getMoney() - perks.get(perkId).getInstallationCost()
                    );
                    perks.get(perkId).setUpgraded(true);
                    System.out.println("Amélioration effectuée");
                } else {
                    System.out.println("Vous n'avez pas assez d'argent");
                }
            } while (perkId != 99);

            System.out.println(this.house.toString());

            if (this.house.getCouple().getMoney() <= 0) {
                System.out.println("Vous n'avez plus d'argent, vous avez perdu.");
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

        availableTasks.add(new Task(0, "Allumer le chauffage", -1, 0, 0));
        availableTasks.add(new Task(1, "Eteindre le chauffage", -1, 0, 0));
        availableTasks.add(new Task(2, "Allumer la climatisation", -1, 0, 0));
        availableTasks.add(new Task(3, "Eteindre la climatisation", -1, 0, 0));
        availableTasks.add(new Task(4, "Ouvrir les fenêtres", -1, 0, 0));
        availableTasks.add(new Task(5, "Fermer les fenêtres", -1, 0, 0));
        availableTasks.add(new Task(6, "Travailler", -5, 0, 0));
        availableTasks.add(new Task(7, "Dormir", 2, 0, 0));
        availableTasks.add(new Task(8, "Faire du vélo", -4, 0, 50));
        availableTasks.add(new Task(9, "Cuisiner", 2, 0, -30));
        availableTasks.add(new Task(10, "Réparer l'éléctricité", -2, -200, 0));

        return availableTasks;
    }

}

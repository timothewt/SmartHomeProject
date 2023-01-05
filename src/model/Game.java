/**
 * @file Menu.java
 * @date 18/12/2022
 * @brief Manage all the Play Scene component
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private House house;
	private Weather weather;
	private int dayNumber;
	private ArrayList<Task> availableTasks;
	private ArrayList<Perk> availablePerks;
	private ArrayList<Perk> currentPerks;

	/**
	 * @brief Constructor
	 */
	public Game() {

		this.house = initHouse();
		this.weather = new Weather();
		this.dayNumber = 0;
		this.availableTasks = this.initTasks();
		this.availablePerks = this.house.getPerks();
		this.currentPerks = new ArrayList<>();

	}

	/**
	 * @brief update the house and the weather on a new day
	 * @param dayNumber
	 */
	private void onNewDay(int dayNumber) {
		this.house.onNewDay();
		this.weather.update(dayNumber);
	}

	public void run() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {

			this.onNewDay(this.dayNumber);

			System.out.println("----- JOUR " + this.dayNumber + " -----");

			// interface de début de journée

			System.out.println(this.weather);
			System.out.println(this.house);

			for (Person person : this.house.getCouple().getPersons()) {

				System.out.println("Choisir actions de " + person.getName() + ": (entrez 99 quand c'est terminé)");

				int taskNumber;

				while (true) {
					taskNumber = scanner.nextInt();
					if (taskNumber == 99) {
						break;
					}
					person.addTask(this.availableTasks.get(taskNumber));
				}

			}

			// interface durant la journée

			boolean taskRemaining = true;

			do {
				System.out.println(this.house);
				for (Person person : this.house.getCouple().getPersons()) {
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
						case 4 -> { // Ouvrir les fenêtres
							this.house.setAllWindowsOpen(true);
							System.out.println("Fenêtres ouvertes.");
						}
						case 5 -> {
							this.house.setAllWindowsOpen(false);
							System.out.println("Fenêtres fermées.");
						}
						case 6 -> System.out.println("Travail effectué.");
						case 7 -> System.out.println("Ptite sieste faite.");
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

			System.out.println(this.house);

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
			for (Perk perk : perks) {
				System.out.println(perk);
			}
			System.out.println("Entrez le numéro d'amélioration souhaité (99 pour finir):");

			int perkId;
			do {
				perkId = scanner.nextInt();
				if (perks.get(perkId).isUpgraded()) {
					System.out.println("Amélioration déjà effectuée");
				} else if (perks.get(perkId).getInstallationCost() <= this.house.getCouple().getMoney()) {
					this.house.getCouple()
							.setMoney(this.house.getCouple().getMoney() - perks.get(perkId).getInstallationCost());
					perks.get(perkId).setUpgraded(true);
					System.out.println("Amélioration effectuée");
				} else {
					System.out.println("Vous n'avez pas assez d'argent");
				}
			} while (perkId != 99);

			System.out.println(this.house);

			if (this.house.getCouple().getMoney() <= 0) {
				System.out.println("Vous n'avez plus d'argent, vous avez perdu.");
				break;
			}

		}

	}

	/**
	 * @brief Instantiating rooms and perks
	 * @return
	 */
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
		couple.addPerson(new Person("Marie", 1, 10, new ArrayList<Task>()));

		return new House(18f, .5f, 0, false, rooms, powerGenerators, perks, couple, 21f, .45f);
	}
	
	/**
	 * @brief instantiating tasks
	 * @return
	 */
	private ArrayList<Task> initTasks() {
		availableTasks = new ArrayList<>();

		availableTasks.add(new Task(0, "Heater On", "Turning heater On.",-1, 0, 0));
		availableTasks.add(new Task(1, "Heater Off", "Turning heater Off.",-1, 0, 0));
		availableTasks.add(new Task(2, "AC On", "Turning AC On.",-1, 0, 0));
		availableTasks.add(new Task(3, "AC Off", "Turning AC Off.",-1, 0, 0));
		availableTasks.add(new Task(4, "Open windows", "Opening windows.",-1, 0, 0));
		availableTasks.add(new Task(5, "Closing windows.", "Closing closing windows.",-1, 0, 0));
		availableTasks.add(new Task(6, "Work", "Currently working.",-5, 0, 0));
		availableTasks.add(new Task(7, "Sleep", "Sleeping.",2, 0, 0));
		availableTasks.add(new Task(8, "Biking", "Biking.",-4, 0, 50));
		availableTasks.add(new Task(9, "Cook", "Cooking and eating.",2, 0, -30));
		availableTasks.add(new Task(10, "Repair Outage", "Repairing power Outage.",-2, -200, 0));

		return availableTasks;
	}

	/**
	 * @brief find a Task in all tasks using the task's id
	 * @param id
	 * @return the Task corresponding to the id
	 */
	public Task findTaskFromId(int id) {
		if (id >= 0 && id < availableTasks.size()) {
			for (Task task : availableTasks) {
				if (task.getId() == id) {
					return task;
				}
			}
		} else {
			System.out.println("Error system in Playing : id not recognized");
		}
		return null;
	}

	public void doNthTaskOfAllPersons(int n) {
		this.house.getCouple().getPersons().forEach(person -> {
			if (n < person.getTasks().size()) {
				Task currentTask = person.getTasks().get(n);
				float heaterTemp = 23;
				float ACTemperature = 18; // Hard coded for the moment, has to depend on the UI entry
				switch (currentTask.getId()) {
					case 0 -> this.house.setAllHeatersTemperature(heaterTemp); // turn on all heaters
					case 1 -> this.house.turnOffAllHeaters();					// turn off all heaters
					case 2 -> this.house.setAllACTemperature(ACTemperature);	// turn on all AC
					case 3 -> this.house.turnOffAllAC();						// turn off all AC
					case 4 -> this.house.setAllWindowsOpen(true);				// open all windows
					case 5 -> this.house.setAllWindowsOpen(false);				// close all windows
					case 6 -> {}												// work
					case 7 -> {}												// sleep
					case 8 -> {}												// bike
					case 9 -> {}												// cook
					case 10 -> this.house.setOnPowerOutage(false);				// resolve power outage
				}
				person.setStamina(person.getStamina() + currentTask.getStamina());
				this.house.getCouple().setMoney(this.house.getCouple().getMoney() + currentTask.getMoney());
				this.house.setEnergy(this.house.getEnergy() + currentTask.getEnergy());
			}
		});
		this.house.update(this.weather);
	}

	// Getters and Setters
	public House getHouse() {
		return this.house;
	}

	public Weather getWeather() {
		return this.weather;
	}

	public ArrayList<Task> getAvailableTasks() {
		return this.availableTasks;
	}
	
	public ArrayList<Perk> getAvailablePerks() {
		return this.availablePerks;
	}
	
	public ArrayList<Perk> getCurrentPerks() {
		return this.currentPerks;
	}
	
	public int getDayNumber() {
		return this.dayNumber;
	}
}
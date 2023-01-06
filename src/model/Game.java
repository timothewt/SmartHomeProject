/**
 * @file Menu.java
 * @date 18/12/2022
 * Core of the game model
 */
package model;

import java.util.ArrayList;

public class Game {

	private final House house;
	private final Weather weather;
	private int dayNumber;
	private final ArrayList<Task> availableTasks;
	private final ArrayList<Perk> availablePerks;
	private final ArrayList<Perk> currentPerks;

	/**
	 * Class constructor
	 */
	public Game() {
		this.house = initHouse();
		this.weather = new Weather();
		this.dayNumber = 0;
		this.availableTasks = new ArrayList<>();
		initTasks();
		this.availablePerks = this.house.getPerks();
		this.currentPerks = new ArrayList<>();

	}

	/**
	 * Updates the house and the weather on a new day
	 * @return True if the house is still viable, false otherwise
	 */
	public boolean onNewDay() {
		if (!this.house.isViable()) {
			return false;
		}
		this.dayNumber++;
		this.house.onNewDay();
		this.weather.update(this.dayNumber);
		return true;
	}

	/**
	 * Instantiates the rooms, power generators, available perks and family
	 */
	private House initHouse() {
		ArrayList<Room> rooms = new ArrayList<>();
		rooms.add(new Room("Cuisine"));
		rooms.add(new Room("Chambre"));
		rooms.add(new Room("Salon"));
		rooms.add(new Room("Salle de bain"));
		rooms.add(new Room("Bureau"));

		ArrayList<PowerGenerator> powerGenerators = new ArrayList<PowerGenerator>();
		powerGenerators.add(new PowerGenerator("Linky", 0, 100, 100));

		ArrayList<Perk> perks = new ArrayList<>();
		perks.add(new Perk(0, "Automatic windows", 1000, 100, 30, false));
		perks.add(new Perk(1, "Automatic AC and heaters", 1500, 150, 50, false));
		perks.add(new Perk(2, "Better mattress", 1000, 0, 0, false));
		perks.add(new Perk(3, "Cooking robot", 1000, 0, 0, false));

		Family family = new Family();
		family.addPerson(new Person("Jean", 0, 10));
		family.addPerson(new Person("Marie", 1, 10));

		return new House(18f, .5f, 0, false, rooms, powerGenerators, perks, family, 21f, .45f);
	}

	/**
	 * Instantiates all the available tasks
	 */
	private void initTasks() {
		availableTasks.add(new Task(0, "Heater On", "Turning heater On.", -1, 0, 0));
		availableTasks.add(new Task(1, "Heater Off", "Turning heater Off.", -1, 0, 0));
		availableTasks.add(new Task(2, "AC On", "Turning AC On.", -1, 0, 0));
		availableTasks.add(new Task(3, "AC Off", "Turning AC Off.", -1, 0, 0));
		availableTasks.add(new Task(4, "Open windows", "Opening windows.", -1, 0, 0));
		availableTasks.add(new Task(5, "Close windows", "Closing closing windows.", -1, 0, 0));
		availableTasks.add(new Task(6, "Work", "Currently working.", -5, 200, 0));
		availableTasks.add(new Task(7, "Sleep", "Sleeping.", 2, 0, 0));
		availableTasks.add(new Task(8, "Biking", "Biking.", -4, 0, 50));
		availableTasks.add(new Task(9, "Cook", "Cooking and eating.", 2, 0, -30));
		// availableTasks.add(new Task(10, "Repair Outage", "Repairing power Outage.", -2, -200, 0)); // power outage not yet implemented
	}

	/**
	 * Finds a Task in all tasks using its ID
	 * @param id: ID of the task
	 * @return the Task corresponding to the ID
	 */
	public Task findTaskFromId(int id) {
		if (id >= 0 && id < availableTasks.size()) {
			for (Task task : availableTasks) {
				if (task.ID() == id) {
					return task;
				}
			}
		} else {
			System.out.println("Error system in Playing : ID not recognized");
		}
		return null;
	}

	/**
	 * Executes the task of every member of the house at the index n of their tasks list
	 * @param n: index of the tasks to execute
	 */
	public void doNthTaskOfAllPersons(int n) {
		this.house.getCouple().getPersons().forEach(person -> {
			if (n < person.getTasks().size()) {
				Task currentTask = person.getTasks().get(n);
				float heaterTemp = 23;
				float ACTemperature = 18; // Hard coded for the moment, has to depend on the UI entry later
				switch (currentTask.ID()) {
					case 0 -> this.house.setAllHeatersTemperature(heaterTemp); // turn on all heaters
					case 1 -> this.house.turnOffAllHeaters(); // turn off all heaters
					case 2 -> this.house.setAllACTemperature(ACTemperature); // turn on all AC
					case 3 -> this.house.turnOffAllAC(); // turn off all AC
					case 4 -> this.house.setAllWindowsOpen(true); // open all windows
					case 5 -> this.house.setAllWindowsOpen(false); // close all windows
					case 6 -> {} // work
					case 7 -> {} // sleep
					case 8 -> {} // bike
					case 9 -> {} // cook
				}
				person.setStamina(person.getStamina() + currentTask.stamina());
				this.house.getCouple().setMoney(this.house.getCouple().getMoney() + currentTask.money());
				this.house.setEnergy(this.house.getEnergy() + currentTask.energy());
			}
		});
		this.house.update(this.weather);
	}

	/**
	 * Getters and setters
	 */
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
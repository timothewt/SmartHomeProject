/**
 * @file Menu.java
 * @date 18/12/2022
 * @brief Manage all the Play Scene component
 */
package model;

import java.util.ArrayList;

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
	 * Update the house and the weather on a new day
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
		powerGenerators.add(new PowerGenerator("Linky", 0, 100, 100));

		ArrayList<Perk> perks = new ArrayList<Perk>();
		perks.add(new Perk(0, "Automatic windows", 1000, 100, 30, false));
		perks.add(new Perk(1, "Automatic AC and heaters", 1500, 150, 50, false));
		perks.add(new Perk(2, "Better mattress", 1000, 0, 0, false));
		perks.add(new Perk(3, "Cooking robot", 1000, 0, 0, false));

		Couple couple = new Couple();
		couple.addPerson(new Person("Jean", 0, 10));
		couple.addPerson(new Person("Marie", 1, 10));

		return new House(18f, .5f, 0, false, rooms, powerGenerators, perks, couple, 21f, .45f);
	}

	/**
	 * @brief instantiating tasks
	 * @return
	 */
	private ArrayList<Task> initTasks() {
		availableTasks = new ArrayList<>();

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
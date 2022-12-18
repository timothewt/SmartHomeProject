/**
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package model;

import java.util.ArrayList;
import java.lang.Math;

public class House {

	private int temperature;
	private int humidityRate;
	private int energy;
	private boolean isOnPowerOutage;
	private ArrayList<Room> rooms;
	private ArrayList<PowerGenerator> powerSupply;
	private ArrayList<Perk> perks;
	private Couple couple;
	
	/**
	 * Constructors
	 */
	public House() {
		this.temperature = 0;
		this.humidityRate = 0;
		this.energy = 0;
		this.isOnPowerOutage = false;
		this.perks = new ArrayList<Perk>();

		initRooms();
		initPowerSupply();
		initCouple();
	}

	public House(int temperature, int humidityRate, int energy, boolean isOnPowerOutage, ArrayList<Room> rooms, ArrayList<PowerGenerator> powerSupply, ArrayList<Perk> perks, Couple couple) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.energy = energy;
		this.isOnPowerOutage = isOnPowerOutage;
		this.rooms = rooms;
		this.powerSupply = powerSupply;
		this.perks = perks;
		this.couple = couple;
	}

	/**
	 * Used to initialize the power supplies of the house
	 */
	private void initPowerSupply() {
		this.powerSupply = new ArrayList<PowerGenerator>();
		PowerGenerator linky = new PowerGenerator("Linky", 0, 2, 1);
		this.powerSupply.add(linky);
	}
	private void initCouple() {
		Person jean = new Person("Jean", 0, 10, new ArrayList<Task>());
		Person marie = new Person("Marie", 1, 10, new ArrayList<Task>());
		
		this.couple.addPerson(jean);
		this.couple.addPerson(marie);
	}
	private void initRooms() {
		this.rooms = new ArrayList<Room>();
		Room kitchen = new Room();

		this.rooms.add(kitchen);
	}
	
	/**
	 * Tells if the home is still viable or not, used to determine if the player can keep playing
	 * @return true if the house is still viable, false otherwise.
	 */
	public boolean isViable() {
		return temperature < 40 && temperature > 10 && humidityRate < .5;
	}

	/**
	 * Updates all the house elements according to the current weather
	 * @param weather: weather of the environment
	 */
	public void update(Weather weather) {

		this.temperature = 0;
		this.humidityRate = 0;

		for (Room room: rooms) {
			room.updateStats(weather.getHumidityRate(), weather.getTemperature());
			this.temperature += room.getTemperature() / this.rooms.size();
			this.humidityRate += room.getHumidityRate() / this.rooms.size();
		}

		if (weather.isLightning() && Math.random() < .2) {
			this.isOnPowerOutage = true;
		}

	}


	/**
	 * Getters and setters
	 */
	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidityRate() {
		return humidityRate;
	}

	public void setHumidityRate(int humidityRate) {
		this.humidityRate = humidityRate;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public boolean isOnPowerOutage() {
		return isOnPowerOutage;
	}

	public void setOnPowerOutage(boolean onPowerOutage) {
		isOnPowerOutage = onPowerOutage;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<PowerGenerator> getPowerSupply() {
		return powerSupply;
	}

	public void setPowerSupply(ArrayList<PowerGenerator> powerSupply) {
		this.powerSupply = powerSupply;
	}

	public Couple getCouple() {
		return couple;
	}

	public void setCouple(Couple couple) {
		this.couple = couple;
	}
}

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
	private float optimalTemperature;
	private float optimalHumidityRate;
	
	/**
	 * Constructors
	 */
	public House() {
		this.temperature = 0;
		this.humidityRate = 0;
		this.energy = 0;
		this.isOnPowerOutage = false;
		this.perks = new ArrayList<Perk>();
		this.optimalTemperature = 21f;
		this.optimalHumidityRate = .45f;

		initRooms();
		initPowerSupply();
		initCouple();
	}

	public House(int temperature, int humidityRate, int energy, boolean isOnPowerOutage, ArrayList<Room> rooms, ArrayList<PowerGenerator> powerSupply, ArrayList<Perk> perks, Couple couple, float optimalTemperature, float optimalHumidityRate) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.energy = energy;
		this.isOnPowerOutage = isOnPowerOutage;
		this.rooms = rooms;
		this.powerSupply = powerSupply;
		this.perks = perks;
		this.couple = couple;
		this.optimalTemperature = optimalTemperature;
		this.optimalHumidityRate = optimalHumidityRate;
	}
	
	/**
	 * Tells if the home is still viable or not, used to determine if the player can keep playing
	 * @return true if the house is still viable, false otherwise.
	 */
	public boolean isViable() {
		return temperature < optimalTemperature + 10 &&
				temperature > optimalTemperature - 10 &&
				humidityRate < optimalHumidityRate + .15 &&
				humidityRate < optimalHumidityRate - .15;
	}

	/**
	 * Updates all the house elements according to the current weather and perks
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

		if (weather.isLightning() && Math.random() < .1) {
			this.isOnPowerOutage = true;
		}

		for (Perk perk: perks) {
			switch (perk.getId()) {
				case 0: // automatic windows
					this.energy -= 10;
					this.setAllWindowsOpen(temperature < this.optimalTemperature && temperature < weather.getTemperature()); // if the temperature is better outside
				case 1: // automatic heaters
					this.energy -= 10;
					if (temperature < this.optimalTemperature && !this.rooms.get(0).isWindowOpen()) {
						this.setAllHeatersTemperature(this.optimalTemperature);
					} else {
						this.turnOffAllHeaters();
					}
					break;
			}
		}

		for (Room room: rooms) {
			if (room.isHeaterTurnedOn()) {
				this.energy -= 1;
			}
		}

	}

	private void setAllWindowsOpen(boolean status) {
		for (Room room: rooms) {
			room.setWindowOpen(status);
		}
	}

	private void setAllHeatersTemperature(float temperature) {
		for (Room room: rooms) {
			room.setHeaterTemperature(temperature);
			room.setHeaterTurnedOn(true);
		}
	}

	private void turnOffAllHeaters() {
		for (Room room: rooms) {
			room.setHeaterTurnedOn(false);
		}
	}

	public void onNewDay() {
		for (PowerGenerator powerGenerator: this.powerSupply) {
			this.energy += powerGenerator.getDailyProduction();
			this.couple.setMoney(this.couple.getMoney() - powerGenerator.getDailyCost());
		}
	}

	/**
	 * Used to initialize the power supplies of the house
	 */
	private void initPowerSupply() {
		this.powerSupply = new ArrayList<PowerGenerator>();
		PowerGenerator linky = new PowerGenerator("Linky", 0, 2, 100);
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

	public String toString() {
		return "temperature=" + temperature +
				", humidityRate=" + humidityRate +
				", energy=" + energy +
				", isOnPowerOutage=" + isOnPowerOutage +
				", rooms=" + rooms +
				", powerSupply=" + powerSupply +
				", perks=" + perks +
				", couple=" + couple;
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

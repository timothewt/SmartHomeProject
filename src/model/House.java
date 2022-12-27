/**
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package model;

import java.util.ArrayList;
import java.lang.Math;

public class House {

	private float temperature;
	private float humidityRate;
	private int energy;
	private boolean isOnPowerOutage;
	private ArrayList<Room> rooms;
	private ArrayList<PowerGenerator> powerSupply;
	private ArrayList<Perk> perks;
	private Couple couple;
	private final float optimalTemperature;
	private final float optimalHumidityRate;
	
	/**
	 * Constructors
	 */

	public House(float temperature, float humidityRate, int energy, boolean isOnPowerOutage, ArrayList<Room> rooms, ArrayList<PowerGenerator> powerSupply, ArrayList<Perk> perks, Couple couple, float optimalTemperature, float optimalHumidityRate) {
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
			if (!perk.isUpgraded()) {
				break;
			}
			switch (perk.getId()) {
				case 0: // automatic windows
					this.setAllWindowsOpen(temperature < this.optimalTemperature && temperature < weather.getTemperature() ||
											temperature > this.optimalTemperature && temperature > weather.getTemperature()); // if the temperature is better outside
				case 1: // automatic heaters and AC
					if (this.rooms.get(0).isWindowOpen()) {
						break;
					}
					if (temperature < this.optimalTemperature) {
						this.setAllHeatersTemperature(this.optimalTemperature);
						this.turnOffAllAC();
					} else {
						this.setAllACTemperature(this.optimalTemperature);
						this.turnOffAllHeaters();
					}
					break;
			}
			this.energy -= perk.getDailyEnergyCost();
			this.couple.setMoney(this.couple.getMoney() - perk.getDailyMoneyCost());
		}

		for (Room room: rooms) {
			if (room.isHeaterTurnedOn() || room.isACTurnedOn()) {
				this.energy -= 1;
			}
		}

	}

	public void setAllWindowsOpen(boolean status) {
		for (Room room: rooms) {
			room.setWindowOpen(status);
		}
	}

	public void setAllHeatersTemperature(float temperature) {
		for (Room room: rooms) {
			room.setHeaterTemperature(temperature);
			room.setHeaterTurnedOn(true);
		}
	}

	public void turnOffAllHeaters() {
		for (Room room: rooms) {
			room.setHeaterTurnedOn(false);
		}
	}

	public void setAllACTemperature(float temperature) {
		for (Room room: rooms) {
			room.setACTemperature(temperature);
			room.setACTurnedOn(true);
		}
	}

	public void turnOffAllAC() {
		for (Room room: rooms) {
			room.setACTurnedOn(false);
		}
	}

	public void onNewDay() {
		for (PowerGenerator powerGenerator: this.powerSupply) {
			this.energy += powerGenerator.getDailyProduction();
			this.couple.setMoney(this.couple.getMoney() - powerGenerator.getDailyCost());
		}
	}

	public String toString() {
		return "House:\n" +
				"temperature=" + temperature +
				", humidityRate=" + humidityRate +
				", energy=" + energy +
				", money=" + couple.getMoney() +
				", isOnPowerOutage=" + isOnPowerOutage +
				", optimalTemperature=" + optimalTemperature +
				", optimalHumidityRate=" + optimalHumidityRate;
	}

	/**
	 * Getters and setters
	 */
	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidityRate() {
		return humidityRate;
	}

	public void setHumidityRate(float humidityRate) {
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

	public ArrayList<Perk> getPerks() {
		return perks;
	}

	public void setPerks(ArrayList<Perk> perks) {
		this.perks = perks;
	}

	public float getOptimalTemperature() {
		return optimalTemperature;
	}

	public float getOptimalHumidityRate() {
		return optimalHumidityRate;
	}
}

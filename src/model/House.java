/**
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package model;

import java.util.ArrayList;
import java.lang.Math;

import static java.lang.Math.round;

public class House {

	private float temperature;
	private float humidityRate;
	private int energy;
	private boolean isOnPowerOutage;
	private final ArrayList<Room> rooms;
	private ArrayList<PowerGenerator> powerSupply;
	private ArrayList<Perk> perks;
	private final Couple couple;
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
				humidityRate < optimalHumidityRate + .2 &&
				humidityRate < optimalHumidityRate - .2;
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
		this.rooms.forEach(room -> room.setWindowOpen(status));
	}

	public void setAllHeatersTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setHeaterTemperature(temperature);
			room.setHeaterTurnedOn(true);
		});
	}

	public void turnOffAllHeaters() {
		this.rooms.forEach(room -> room.setHeaterTurnedOn(false));
	}

	public void setAllACTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setACTemperature(temperature);
			room.setACTurnedOn(true);
		});
	}

	public void turnOffAllAC() {
		this.rooms.forEach(room -> room.setACTurnedOn(false));
	}

	public void onNewDay() {
		this.powerSupply.forEach(powerGenerator -> {
			this.energy += powerGenerator.getDailyProduction();
			this.couple.setMoney(this.couple.getMoney() - powerGenerator.getDailyCost());
		});
		this.getCouple().getPersons().forEach(Person::onNewDay);
	}

	public String toString() {
		String windowsStatus = this.rooms.get(0).isWindowOpen() ? "Open" : "Closed";
		String heatersStatus = this.rooms.get(0).isHeaterTurnedOn() ? this.rooms.get(0).getHeaterTemperature() + "°C" : "Off";
		String ACStatus = this.rooms.get(0).isACTurnedOn() ? this.rooms.get(0).getACTemperature() + "°C" : "Off";
		return "House - T: " + (float)round(temperature * 10) / 10 + "°C, humidity: " + round(humidityRate * 100) + "%, windows: " + windowsStatus + ", heaters: " + heatersStatus + ", AC: " + ACStatus;
	}

	/**
	 * Getters and setters
	 */
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public Couple getCouple() {
		return couple;
	}

	public ArrayList<Perk> getPerks() {
		return perks;
	}
}

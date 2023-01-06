/**
 * @file Home.java
 * @date 18/12/2022
 * Used to represent the house, with the rooms and persons
 */
package model;

import java.util.ArrayList;
import java.lang.Math;

import static java.lang.Math.round;

public class House {

	private float temperature; // temperature inside the house
	private float humidityRate; // humidity rate inside the house
	private int energy; // energy available in the house
	// private boolean isOnPowerOutage; // power outage, not yet implemented
	private final ArrayList<Room> rooms; // rooms of the house
	private final ArrayList<PowerGenerator> powerSupply; // daily energy supplies of the house
	private final ArrayList<Perk> availablePerks; // available perks
	private final ArrayList<Perk> boughtPerks; // perks bought
	private final Family family; // members of the house
	private final float optimalTemperature; // optimal viable temperature inside
	private final float optimalHumidityRate; // optimal viable humidity rate inside
	
	/**
	 * Class constructor
	 */
	public House(float temperature, float humidityRate, int energy, ArrayList<Room> rooms, ArrayList<PowerGenerator> powerSupply, ArrayList<Perk> perks, Family family, float optimalTemperature, float optimalHumidityRate) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.energy = energy;
		// this.isOnPowerOutage = isOnPowerOutage;
		this.rooms = rooms;
		this.powerSupply = powerSupply;
		this.availablePerks = perks;
		this.boughtPerks = new ArrayList<>();
		this.family = family;
		this.optimalTemperature = optimalTemperature;
		this.optimalHumidityRate = optimalHumidityRate;
	}

	private boolean canAffordPerk(Perk perk) {
		return perk.installationCost() <= this.family.getMoney();
	}

	public void buyPerkFromId(int id) {
		for (Perk perk: this.availablePerks) {
			if (perk.ID() == id) {
				if (this.canAffordPerk(perk)) {
					this.availablePerks.remove(perk);
					this.boughtPerks.add(perk);
					this.family.setMoney(this.family.getMoney() - perk.installationCost());
				}
				break;
			}
		}
	}
	
	/**
	 * Tells if the home is still viable or not, used to determine if the player can keep playing
	 * @return "Viable" if the house is still viable, the reason of the non-viability as a String otherwise.
	 */
	public String isViable() {
		if (temperature > optimalTemperature + 10) {
			return "Temperature too high: " + (float)round(temperature * 10) / 10 + "°C";
		}
		if (temperature < optimalTemperature - 10) {
			return "Temperature too low: " + (float)round(temperature * 10) / 10 + "°C";
		}
		if (humidityRate > optimalHumidityRate + .2) {
			return "Humidity too high: " + (float)round(humidityRate * 100) / 100 + "%";
		}
		if (humidityRate < optimalHumidityRate - .2) {
			return "Humidity too low: " + (float)round(humidityRate * 100) / 100 + "%";
		}
		if (this.family.getMoney() <= 0) {
			return "Not enough money to continue";
		}
		return "Viable";
	}

	/**
	 * Updates all the house elements according to the current weather and perks
	 * @param weather: weather of the environment
	 */
	public void update(Weather weather) {
		this.temperature = 0;
		this.humidityRate = 0;

		for (Room room: rooms) {
			room.update(weather.getHumidityRate(), weather.getTemperature());
			this.temperature += room.getTemperature() / this.rooms.size();
			this.humidityRate += room.getHumidityRate() / this.rooms.size();
		}
		/*
		if (weather.isLightning() && Math.random() < .1) {
			this.isOnPowerOutage = true;
		}
		*/
		for (Perk perk: this.boughtPerks) {
			switch (perk.ID()) {
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
			this.energy -= perk.dailyEnergyCost();
			this.family.setMoney(this.family.getMoney() - perk.dailyMoneyCost());
		}

		for (Room room: rooms) {
			if (room.isHeaterTurnedOn() || room.isACTurnedOn()) {
				this.energy -= 1;
			}
		}

	}

	/**
	 * Opens all the windows of each rooms
	 * @param status: true if the windows will be open, false otherwise
	 */
	public void setAllWindowsOpen(boolean status) {
		this.rooms.forEach(room -> room.setWindowOpen(status));
	}

	/**
	 * Turns on all the heaters of the house at a certain temperature
	 * @param temperature: temperature of the heaters
	 */
	public void setAllHeatersTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setHeaterTemperature(temperature);
			room.setHeaterTurnedOn(true);
		});
	}

	/**
	 * Turns off all the heaters of the house
	 */
	public void turnOffAllHeaters() {
		this.rooms.forEach(room -> room.setHeaterTurnedOn(false));
	}

	/**
	 * Turns on all the ACs of the house at a certain temperature
	 * @param temperature: temperature of the ACs
	 */
	public void setAllACTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setACTemperature(temperature);
			room.setACTurnedOn(true);
		});
	}

	/**
	 * Turns off all the ACs of the house
	 */
	public void turnOffAllAC() {
		this.rooms.forEach(room -> room.setACTurnedOn(false));
	}

	/**
	 * Called at each new day
	 */
	public void onNewDay() {
		this.powerSupply.forEach(powerGenerator -> {
			this.energy += powerGenerator.getDailyProduction();
			this.family.setMoney(this.family.getMoney() - powerGenerator.getDailyCost());
		});
		this.getFamily().getPersons().forEach(Person::onNewDay);
	}

	/**
	 * Stringifies the house to display
	 * @return the main infos of the house as a String
	 */
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

	public Family getFamily() {
		return family;
	}

	public Perk getPerkById(int id) {
		for (Perk perk: this.availablePerks) {
			if (perk.ID() == id) {
				return perk;
			}
		}
		return null;
	}

	public ArrayList<Perk> getAvailablePerks() {
		return this.availablePerks;
	}

	public ArrayList<Perk> getBoughtPerks() {
		return this.boughtPerks;
	}
}

/**
 * @author Thibault Mayer
 * @file Room.java
 * @date 18/12/2022
 * @brief Class that create rooms
 */
package model;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Room {

	/*
	 * Attributes
	 */
	private String name;
	private float heaterTemperature;
	private boolean isHeaterTurnedOn;
	private float temperature;
	private float humidityRate; // Rates are set to stay between 0 and 1
	private float isolationRate;
	private boolean isWindowOpen;

	/*
	 * @brief Constructors
	 */
	public Room() {
		this.heaterTemperature = 0f;
		this.isHeaterTurnedOn = false;
		this.temperature = 18f;
		this.humidityRate = .1f;
		this.isolationRate = .5f;
		this.isWindowOpen = false;
	}

	public Room(String name, float heaterTemperature, boolean isHeaterTurnedOn, float temperature, float humidityRate, float isolationRate, boolean isWindowOpen) {
		this.name = name;
		this.heaterTemperature = heaterTemperature;
		this.isHeaterTurnedOn = isHeaterTurnedOn;
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.isolationRate = isolationRate;
		this.isWindowOpen = isWindowOpen;
	}

	/*
	 * @brief Updates the room stats according to outsideHumidityRate and the temperature
	 * @param float outsideHumidityRate : Weather's outsideHumidityRate
	 * @return void
	 */
	public void updateStats(float outsideHumidityRate, float outsideTemperature) {

		if (this.isWindowOpen) {

			this.humidityRate = (this.humidityRate + outsideHumidityRate) / 2;
			this.temperature = (this.temperature + outsideTemperature) / 2;

		} else {

			this.temperature = isHeaterTurnedOn ? (this.temperature + this.heaterTemperature) / 2 : this.temperature;
			// Loss of humidity and temperature due to non-optimal isolation
			int humidityChangeSign = this.humidityRate > outsideHumidityRate ? -1 : 1;
			int temperatureChangeSign = this.temperature > outsideTemperature ? -1 : 1;
			this.humidityRate = max(min(this.humidityRate + humidityChangeSign * (1 - this.isolationRate) * outsideHumidityRate, 1), 0);
			this.temperature = this.temperature + temperatureChangeSign * (1 - this.isolationRate) * outsideTemperature;

		}

	}

	public float getHeaterTemperature() {
		return heaterTemperature;
	}

	public void setHeaterTemperature(float heaterTemperature) {
		this.heaterTemperature = heaterTemperature;
	}

	public boolean isHeaterTurnedOn() {
		return isHeaterTurnedOn;
	}

	public void setHeaterTurnedOn(boolean heaterTurnedOn) {
		isHeaterTurnedOn = heaterTurnedOn;
	}

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
		this.humidityRate = max(min(humidityRate, 1), 0);;
	}

	public float getIsolationRate() {
		return isolationRate;
	}

	public void setIsolationRate(float isolationRate) {
		this.isolationRate = max(min(isolationRate, 1), 0);
	}

	public boolean isWindowOpen() {
		return isWindowOpen;
	}

	public void setWindowOpen(boolean windowOpen) {
		isWindowOpen = windowOpen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

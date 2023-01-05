/**
 * @file Weather.java
 * @date 18/12/2022
 * @brief Use to represent the weather of the environment
 */
package model;

import java.lang.Math;

import static java.lang.Math.round;

public class Weather {

	private float temperature;
	private float humidityRate;
	private boolean isRainy;
	private boolean isSnowy;
	private boolean isSunny;
	private boolean isLightning;
	private float difficultyRate;

	/**
	 * @brief Constructors
	 */
	public Weather() {
		this.temperature = 20f;
		this.humidityRate = 0.5f;
		this.isRainy = false;
		this.isSnowy = false;
		this.isSunny = false;
		this.isLightning = false;
	}

	/**
	 * @brief Constructors
	 */
	public Weather(float temperature, float humidityRate, boolean isRainy, boolean isSnowy, boolean isSunny,
			boolean isLightning) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.isRainy = isRainy;
		this.isSnowy = isSnowy;
		this.isSunny = isSunny;
		this.isLightning = isLightning;
	}

	/**
	 * @brief update the weather
	 * @param dayNumber
	 */
	public void update(int dayNumber) {
		this.updateDifficultyRate(dayNumber);

		boolean wasRainy = this.isRainy;
		this.setRainy(Math.random() < this.difficultyRate / 2);
		this.setSnowy(this.isRainy && this.temperature <= 0);
		this.setSunny(!this.isRainy);
		this.setLightning(false);

		int temperatureChangeSign = Math.random() < .5 ? -1 : 1;

		this.temperature += temperatureChangeSign * Math.random() * this.difficultyRate * 20;
		this.temperature += this.isSunny && wasRainy ? 5 : this.isRainy && !wasRainy ? -5 : 0;

		if (this.isRainy) {
			this.humidityRate = 1f;
			this.setLightning(Math.random() < this.difficultyRate / 4);
		} else if (wasRainy) {
			this.humidityRate = .5f;
		} else {
			this.humidityRate += temperatureChangeSign * Math.random() * this.difficultyRate * .1;
		}
	}

	/**
	 * @brief update The difficulty of the game according to the day. The difficulty
	 *        increases over days
	 * @param dayNumber
	 */
	private void updateDifficultyRate(int dayNumber) {
		this.difficultyRate = (float) (1 / (1 + Math.exp(-.5 * (dayNumber - 5))));
	}

	/**
	 * @brief toString method
	 */
	public String toString() {
		String sky = isSnowy ? "Snowy" : isLightning ? "Lightnings" : isRainy ? "Rainy" : "Sunny";
		return "Weather - T : " + (float)round(temperature * 10) / 10 + "°C, humidity : " + round(humidityRate * 100) + "%, Sky: " + sky;
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

	public boolean isRainy() {
		return isRainy;
	}

	public void setRainy(boolean rainy) {
		isRainy = rainy;
	}

	public boolean isSnowy() {
		return isSnowy;
	}

	public void setSnowy(boolean snowy) {
		isSnowy = snowy;
	}

	public boolean isSunny() {
		return isSunny;
	}

	public void setSunny(boolean sunny) {
		isSunny = sunny;
	}

	public boolean isLightning() {
		return isLightning;
	}

	public void setLightning(boolean lightning) {
		isLightning = lightning;
	}

	public float getDifficultyRate() {
		return difficultyRate;
	}

	public void setDifficultyRate(float difficultyRate) {
		this.difficultyRate = difficultyRate;
	}
}

/**
 * @file Weather.java
 * @date 18/12/2022
 * @brief Use to represent the weather of the environment
 */
package model;

import java.lang.Math;

public class Weather {

    private float temperature;
    private float humidityRate;
    private boolean isRainy;
    private boolean isSnowy;
    private boolean isSunny;
    private boolean isLightning;
    private float difficultyRate;

    /**
     * Constructors
     */
    public Weather() {
        this.temperature = 20f;
        this.humidityRate = 0.5f;
        this.isRainy = false;
        this.isSnowy = false;
        this.isSunny = false;
        this.isLightning = false;
    }
    public Weather(float temperature, float humidityRate, boolean isRainy, boolean isSnowy, boolean isSunny, boolean isLightning) {
        this.temperature = temperature;
        this.humidityRate = humidityRate;
        this.isRainy = isRainy;
        this.isSnowy = isSnowy;
        this.isSunny = isSunny;
        this.isLightning = isLightning;
    }

    public void update(int dayNumber) {
        this.updateDifficultyRate(dayNumber);

        int temperatureChangeSign = Math.random() < .5 ? -1 : 1;

        this.temperature += temperatureChangeSign * Math.random() * this.difficultyRate * 15;

        boolean wasRainy = this.isRainy;
        this.setRainy(Math.random() < this.difficultyRate / 2);
        this.setSnowy(this.isRainy && this.temperature <= 0);
        this.setSunny(!this.isRainy);
        this.setLightning(false);

        if (this.isRainy) {
            this.humidityRate = 1f;
            this.setLightning(Math.random() < this.difficultyRate / 4);
        } else if (wasRainy) {
            this.humidityRate = .5f;
        } else {
            this.humidityRate += temperatureChangeSign * Math.random() * this.difficultyRate * .05;
        }
    }

    private void updateDifficultyRate(int dayNumber) {
        this.difficultyRate = (float) (1 / (1 + Math.exp(-.5 * (dayNumber - 5))));
    }

    public String toString() {
        return "temperature=" + temperature +
                ", humidityRate=" + humidityRate +
                ", isRainy=" + isRainy +
                ", isSnowy=" + isSnowy +
                ", isSunny=" + isSunny +
                ", isLightning=" + isLightning +
                ", difficultyRate=" + difficultyRate;
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

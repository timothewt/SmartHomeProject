/**
 * @author Thibault Mayer
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package model;

import java.util.ArrayList;

public class Home {

	private int temperature;
	private int humidity;
	private int energy;
	private int energyUsed;
	
	private float isolationRate;
	private boolean isPowered;
	
	private ArrayList<Room> rooms;
	private ArrayList<PowerGenerator> powerSupply;
	private Couple couple;
	
	/**
	 * Constructors
	 */
	public Home() {
		this.rooms = new ArrayList<Room>();
		this.temperature = 18;
		this.humidity = 0;
		this.energy = 1;
		this.energyUsed = 0;
		this.isolationRate = 0.5f;
		this.isPowered = false;
		this.powerSupply = new ArrayList<PowerGenerator>();
		
		this.couple  = new Couple();
	}

	public Home(int temperature, int humidity, int energy, int energyUsed, float isolationRate, boolean isPowered) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.energy = energy;
		this.energyUsed = energyUsed;
		this.isolationRate = isolationRate;
		this.isPowered = isPowered;
		
		initPowerSupply();
		initCouple();
		initRooms();
	}

	/**
	 * Used to initialize the power supplies of the house
	 */
	private void initPowerSupply() {
		PowerGenerator linky = new PowerGenerator("Linky", 0, 2, 1);
		
		this.powerSupply.add(linky);
	}

	/**
	 * Used to initialize the inhabitants of the house
	 */
	private void initCouple() {
		Person jean = new Person(1, 10, new ArrayList<Task>());
		Person marie = new Person(2, 10, new ArrayList<Task>());
		
		this.couple.addPerson(jean);
		this.couple.addPerson(marie);
	}

	/**
	 * Used to initialize the rooms of the house
	 */
	private void initRooms() {
		Room kitchen = new Room();
		
		this.rooms.add(kitchen);
	}
	
	/**
	 * Tells if the home is still viable or not, used to determine if the player can keep playing
	 * @return true if the house is still viable, false otherwise.
	 */
	public boolean isViable() {
		return temperature < 40 && temperature > 10 && humidity < 40;
	}

	/**
	 * Updates all the house elements according to the current weather
	 * @param weather: weather of the environment
	 */
	public void updateFromWeather(Weather weather) {
		
	}
	
}

/**
 * @author Thibault Mayer
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package main;

import java.util.ArrayList;

public class Home 
{

	/*
	 * Attributs
	 */
	private int temperature;
	private int humidity;
	private int energy;
	private int energyUsed;
	
	private float isolationRate;
	private boolean isPowered;
	
	private ArrayList<Room> rooms;
	private ArrayList<PowerGenerator> powerSupply;
	private Couple couple;
	
	/*
	 * @brief Default constructor
	 */
	public Home()
	{
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
	
	/*
	 * 
	 */
	public Home(int temperature, int humidity, int energy, int energyUsed, float isolationRate, boolean isPowered) 
	{
		this.temperature = temperature;
		this.humidity = humidity;
		this.energy = energy;
		this.energyUsed = energyUsed;
		this.isolationRate = isolationRate;
		this.isPowered = isPowered;
		
		initPowerSupply();
		initCouple();
		initRoom();
	}
	
	/*
	 * @brief initialize PowerGenerator
	 */
	private void initPowerSupply()
	{
		PowerGenerator linky = new PowerGenerator("Linky", 0, 2, 1);
		
		this.powerSupply.add(linky);
	}
	/*
	 * @brief initialize Person in couple
	 */
	private void initCouple()
	{
		Person jean = new Person(1, 10, 10, false, new ArrayList<Task>());
		Person marie = new Person(2, 10, 10, false, new ArrayList<Task>());
		
		this.couple.addPerson(jean);
		this.couple.addPerson(marie);
	}
	
	/*
	 * initialize Room is rooms
	 */
	private void initRoom()
	{
		Room kitchen = new Room(0, 18, 0.2f, 0.5f);
		
		this.rooms.add(kitchen);
	}
	
	/*
	 * @brief Calculate if the home is still viable or not
	 * @return boolean 
	 */
	public boolean isViable()
	{
		if (temperature < 40 && temperature > 10 && humidity < 40)
			return true;
		else	
			return false;
	}
	
	

	public void updateFromWeather()
	{
		
	}
	
}

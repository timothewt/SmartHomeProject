/**
 * @author Thibault Mayer
 * @file Home.java
 * @date 18/12/2022
 * @brief Class that create a Home
 */
package model;

import java.util.ArrayList;

public class Home 
{

	/*
	 * Attributs
	 */
	private ArrayList<Room> rooms;
	
	private int temperature;
	private int humidity;
	private int energy;
	private int energyUsed;
	
	private float isolationRate;
	
	private boolean isPowered;
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
}

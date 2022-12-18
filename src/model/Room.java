/**
 * @author Thibault Mayer
 * @file Room.java
 * @date 18/12/2022
 * @brief Class that create rooms
 */
package model;

public class Room 
{

	/*
	 * Attributs
	 */
	private int heaterTemperature;
	private int temperature;
	
	/*
	 * Rate are set to stay between 0 and 1
	 */
	private float humidityRate;
	private float isolationRate;

	/*
	 * @brief Default constructor
	 */
	public Room()
	{
		this.heaterTemperature = 0;
		this.temperature = 18;
		this.humidityRate = 0.1f;
		this.isolationRate = 0.5f;
	}
	
	/*
	 * @brief Constructor
	 */
	public Room(int heaterTemperature, int temperature, float humidityRate, float isolationRate) 
	{
		this.heaterTemperature = heaterTemperature;
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.isolationRate = isolationRate;
	}


	/*
	 * 
	 * @brief Update the humidityRate according to the outside humidityRate
	 * @param float humidityRate : Weather's humidityRate
	 * @return void
	 */
	public void updateStats(float humidityRate)
	{
		if (this.humidityRate > humidityRate)
		{
			this.humidityRate = this.humidityRate + (1 - this.isolationRate) * humidityRate;
		}
		else if (this.humidityRate < humidityRate)
		{
			this.humidityRate = this.humidityRate - (1 - this.isolationRate) * humidityRate;
		}
		
		/*
		 * Fix the value in the definition range
		 */
		if (this.humidityRate <= 0)
		{
			this.humidityRate = 0;
		}
		else if (this.humidityRate >0)
		{
			this.humidityRate = 1;
		}
	}
	
	/*
	 * @brief Modify the value of heaterTemperature
	 */
	public void setHeaterTemperature(int heaterTemperature) 
	{
		this.heaterTemperature = heaterTemperature;
	}
}

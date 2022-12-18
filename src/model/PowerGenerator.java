/**
 * @author Thibault Mayer
 * @file PowerGenerator.java
 * @date 14/12/2022
 * @brief 
 */
package main;

public class PowerGenerator 
{
	/*
	 * @Attributs
	 */
	private String name;
	private int installationCost;
	private int dailyCost;
	private int dailyProduction;
	
	/*
	 * @brief Default constructor
	 */
	public PowerGenerator() 
	{
		this.name = "Default Gen";
		this.installationCost = 0;
		this.dailyCost = 0;
		this.dailyProduction = 0;
	}
	
	/*
	 * 
	 */
	public PowerGenerator(String name, int installationCost, int dailyCost, int dailyProduction) 
	{
		this.name = name;
		this.installationCost = installationCost;
		this.dailyCost = dailyCost;
		this.dailyProduction = dailyProduction;
	}

	
	
}

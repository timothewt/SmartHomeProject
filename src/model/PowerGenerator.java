/**
 * @author Thibault Mayer
 * @file PowerGenerator.java
 * @date 14/12/2022
 * @brief 
 */
package model;

public class PowerGenerator {

	private final String name;
	private final int installationCost;
	private final int dailyCost;
	private final int dailyProduction;

	/**
	 * Constructors
	 */
	public PowerGenerator()
	{
		this.name = "Default Gen";
		this.installationCost = 0;
		this.dailyCost = 0;
		this.dailyProduction = 0;
	}

	public PowerGenerator(String name, int installationCost, int dailyCost, int dailyProduction) 
	{
		this.name = name;
		this.installationCost = installationCost;
		this.dailyCost = dailyCost;
		this.dailyProduction = dailyProduction;
	}

	public String getName() {
		return name;
	}

	public int getInstallationCost() {
		return installationCost;
	}

	public int getDailyCost() {
		return dailyCost;
	}

	public int getDailyProduction() {
		return dailyProduction;
	}
}

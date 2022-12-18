/**
 * @author Thibault Mayer
 * @file Perk.java
 * @date 14/12/2022
 * @brief Use to describe a Perk
 */
package main;

public class Perk 
{
	/*
	 * @Attributs
	 */
	private int id;
	private String name;
	
	private int installationCost;
	private int dailyMoneyCost;
	private int dailyEnergyCost;
	
	private boolean isUpgraded;
	
	/*
	 * @brief Default constructor
	 */
	public Perk()
	{
		this.id = 0;
		this.name = "Default Perk";
		this.installationCost = 0;
		this.dailyMoneyCost = 0;
		this.dailyEnergyCost = 0;
		this.isUpgraded = false;
	}
	
	/*
	 * 
	 */
	public Perk(int id, String name, int installationCost, int dailyMoneyCost, int dailyEnergyCost, boolean isUpgraded)
	{
		this.id = id;
		this.name = name;
		this.installationCost = installationCost;
		this.dailyMoneyCost = dailyMoneyCost;
		this.dailyEnergyCost = dailyEnergyCost;
		this.isUpgraded = isUpgraded;
	}

	/*
	 * toString method to describe a perk
	 */
	public String toString()
    {
        return "Perk: " + this.name + " ID: " + this.id + " Installation Cost: " + this.installationCost + " Daily Money Cost: " + this.dailyMoneyCost + " Daily Energy Cost " + this.dailyEnergyCost + " isUpgraded " + this.isUpgraded;
    }
}

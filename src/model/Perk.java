/**
 * @file Perk.java
 * @date 14/12/2022
 * Describes a perk used to upgrade the house
 */
package model;

public class Perk {

	private final int id; // ID of the perk
	private final String name; // name of the perk
	private final int installationCost; // cost when buying the perk
	private final int dailyMoneyCost; // daily money cost of the perk
	private final int dailyEnergyCost; // daily energy cost of the perk
	private boolean isUpgraded; // true if the user bought it, false otherwise
	
	/**
	 * Class constructor
	 */
	public Perk(int id, String name, int installationCost, int dailyMoneyCost, int dailyEnergyCost) {
		this.id = id;
		this.name = name;
		this.installationCost = installationCost;
		this.dailyMoneyCost = dailyMoneyCost;
		this.dailyEnergyCost = dailyEnergyCost;
		this.isUpgraded = false;
	}

	/**
	 * Stringifies the perk to display it
	 * @return the main infos of the perk as a String
	 */
	public String toString() {
		return name + " | " +" cost: " + installationCost + ", daily money cost: " + dailyMoneyCost + " daily energy cost: " + dailyEnergyCost ;
	}

	/**
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public int getInstallationCost() {
		return installationCost;
	}

	public int getDailyMoneyCost() {
		return dailyMoneyCost;
	}

	public int getDailyEnergyCost() {
		return dailyEnergyCost;
	}

	public boolean isUpgraded() {
		return isUpgraded;
	}
}

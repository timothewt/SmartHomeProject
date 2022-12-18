/**
 * @author Thibault Mayer
 * @file Perk.java
 * @date 14/12/2022
 * @brief Use to describe a Perk
 */
package model;

public class Perk {

	private int id;
	private String name;
	private int installationCost;
	private int dailyMoneyCost;
	private int dailyEnergyCost;
	private boolean isUpgraded;
	
	/**
	 * Constructors
	 */
	public Perk() {
		this.id = 0;
		this.name = "Default Perk";
		this.installationCost = 0;
		this.dailyMoneyCost = 0;
		this.dailyEnergyCost = 0;
		this.isUpgraded = false;
	}

	public Perk(int id, String name, int installationCost, int dailyMoneyCost, int dailyEnergyCost, boolean isUpgraded) {
		this.id = id;
		this.name = name;
		this.installationCost = installationCost;
		this.dailyMoneyCost = dailyMoneyCost;
		this.dailyEnergyCost = dailyEnergyCost;
		this.isUpgraded = isUpgraded;
	}

	/**
	 * Stringifies the perk in order to display it
	 * @return a string indicating all the infos of the perk
	 */
	public String toString()
    {
        return "Perk: " + this.name + "\nID: " + this.id + "\nInstallation Cost: " + this.installationCost + "\nDaily Money Cost: " + this.dailyMoneyCost + "\nDaily Energy Cost " + this.dailyEnergyCost + "\nisUpgraded " + this.isUpgraded;
    }

	/**
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInstallationCost() {
		return installationCost;
	}

	public void setInstallationCost(int installationCost) {
		this.installationCost = installationCost;
	}

	public int getDailyMoneyCost() {
		return dailyMoneyCost;
	}

	public void setDailyMoneyCost(int dailyMoneyCost) {
		this.dailyMoneyCost = dailyMoneyCost;
	}

	public int getDailyEnergyCost() {
		return dailyEnergyCost;
	}

	public void setDailyEnergyCost(int dailyEnergyCost) {
		this.dailyEnergyCost = dailyEnergyCost;
	}

	public boolean isUpgraded() {
		return isUpgraded;
	}

	public void setUpgraded(boolean upgraded) {
		isUpgraded = upgraded;
	}
}

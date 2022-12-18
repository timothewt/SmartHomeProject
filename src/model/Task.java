/**
 * @file Task.java
 * @date 14/12/2022
 * @brief Use to describe a task
 */
package model;

public class Task {

	private final int id;
	private final String name;
	private final int staminaCost;
	private final int staminaGain;
	private final int moneyCost;
	private final int moneyGain;
	private final int energyCost;
	
	
	/*
	 * Constructor
	 */
	public Task() {
		this.id = 0;
		this.name = "default";
		this.staminaCost = 0;
		this.staminaGain = 0;
		this.moneyCost = 0;
		this.moneyGain = 0;
		this.energyCost = 0;
	}

	public Task(int id, String name, int staminaCost, int staminaGain, int moneyCost, int moneyGain, int energyCost) {
		this.id = id;
		this.name = name;
		this.staminaCost = staminaCost;
		this.staminaGain = staminaGain;
		this.moneyCost = moneyCost;
		this.moneyGain = moneyGain;
		this.energyCost = energyCost;
	}

	/**
	 * Stringifies the task in order to display it.
	 * @return a string indicating all the information of the task
	 */
	public String toString() {
        return "Task: " + this.name + "\nID: " + this.id + "\nStamina Cost: " + this.staminaCost + "\nStamina Gain: " + this.staminaGain + "\nMoney Cost: " + this.moneyCost + "\nMoney Gain: " + this.moneyGain + "\nEnergy Cost: " + this.energyCost;
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStaminaCost() {
		return staminaCost;
	}

	public int getStaminaGain() {
		return staminaGain;
	}

	public int getMoneyCost() {
		return moneyCost;
	}

	public int getMoneyGain() {
		return moneyGain;
	}

	public int getEnergyCost() {
		return energyCost;
	}
}

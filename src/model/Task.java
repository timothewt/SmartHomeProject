/**
 * @author Thibault Mayer
 * @file Task.java
 * @date 14/12/2022
 * @brief Use to describe a task
 */
package main;

public class Task 
{

	/*
	 * @Attributs
	 */
	private int id;
	private String name;
	
	private int staminaCost;
	private int staminaGain;
	private int moneyCost;
	private int moneyGain;
	private int energyCost;
	
	
	/*
	 * Default constructor
	 */
	public Task()
	{
		this.id = 0;
		this.name = "default";
		this.staminaCost = 0;
		this.staminaGain = 0;
		this.moneyCost = 0;
		this.moneyGain = 0;
		this.energyCost = 0;
	}
	
	/*
	 * 
	 */
	public Task(int id, String name, int staC, int staG, int moneyC, int moneyG, int eC)
	{
		this.id = id;
		this.name = name;
		this.staminaCost = staC;
		this.staminaGain = staG;
		this.moneyCost = moneyC;
		this.moneyGain = moneyG;
		this.energyCost = eC;
	}
	
	/*
	 * toString method to describe a task
	 */
	public String toString()
    {
        return "Task: " + this.name + " ID: " + this.id + " Stamina Cost: " + this.staminaCost + " Stamina Gain: " + this.staminaGain + " Money Cost: " + this.moneyCost + " Money Gain: " + this.moneyGain + " Energy Cost: " + this.energyCost;
    }
	
}

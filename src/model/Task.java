/**
 * @file Task.java
 * @date 14/12/2022
 * @brief Use to describe a task
 */
package model;

public class Task {

	private final int id;
	private final String name;
	private final String message;
	private final int stamina;
	private final int money;
	private final int energy;

	/**
	 * Constructor
	 */
	public Task(int id, String name, String message, int stamina, int money, int energy) {
		this.id = id;
		this.name = name;
		this.message = message;
		this.stamina = stamina;
		this.money = money;
		this.energy = energy;
	}

	public String toString() {
		return "id=" + id + ", name='" + name + '\'' + ", stamina=" + stamina + ", money=" + money + ", energy="
				+ energy;
	}

	/**
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStamina() {
		return stamina;
	}

	public int getMoney() {
		return money;
	}

	public int getEnergy() {
		return energy;
	}
	
	public String getMessage() {
		return this.message;
	}
}
/**
 * @file Person.java
 * @date 14/12/2022
 * @brief Person class
 */
package model;

import java.util.ArrayList;


public class Person {

	private String name;
	private int id;
	private int maxStamina;
	private int stamina;
	private Task currentTask;
	private ArrayList<Task> tasks;
	
	/**
	 * Constructors
	 */
	public Person()	{
		this.name = "default";
		this.id = 0;
		this.maxStamina = 10;
		this.stamina = 0;
		this.currentTask = null;
		this.tasks = new ArrayList<Task>();
	}

	public Person(String name, int id, int maxStamina, ArrayList<Task> tasks) {
		this.name = name;
		this.id = id;
		this.maxStamina = maxStamina;
		this.stamina = maxStamina;
		this.currentTask = null;
		this.tasks = tasks;
	}

	/**
	 * Adds a task to the ArrayList link to the Person
	 * @param task : Task to add
	 */
	public void addTask(Task task)	{
		this.tasks.add(task);
	}

	/**
	 * Getters and setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
}

/**
 * @file Person.java
 * @date 14/12/2022
 * @brief Person class
 */
package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


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
		if (task.getStamina() >= 0) {
			AtomicBoolean taskAlreadyPicked = new AtomicBoolean(false);
			this.tasks.forEach(pickedTask -> {
				if(pickedTask.getId() == task.getId()) {
					taskAlreadyPicked.set(true);
				}
			});
			if (taskAlreadyPicked.get()) {
				return;
			}
		}
		this.tasks.add(task);
	}

	public String toString() {
		return "name='" + name + '\'' +
				", maxStamina=" + maxStamina +
				", stamina=" + stamina;
	}

	/**
	 * Getters and setters
	 */

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getResultingStaminaForCurrentTasks() {
		int result = 0;
		for (Task task: this.tasks) {
			result += task.getStamina(); // - : necessary stamina are negative values
		}
		return result;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
}

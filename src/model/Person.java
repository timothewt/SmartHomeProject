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
	private ArrayList<Task> tasks;
	
	/**
	 * Class constructor
	 * @param name: name of the person
	 * @param id: id of the person
	 * @param maxStamina: maximum stamina in a day
	 */
	public Person(String name, int id, int maxStamina) {
		this.name = name;
		this.id = id;
		this.maxStamina = maxStamina;
		this.stamina = maxStamina;
		this.tasks = new ArrayList<>();
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
		return name + " - Stamina: " + stamina + ", max stamina: " + maxStamina;
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

	public void onNewDay() {
		this.stamina = this.maxStamina;
		this.tasks.clear();
	}
}

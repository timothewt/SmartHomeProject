/**
 * @author Thibault Mayer
 * @file Person.java
 * @date 14/12/2022
 * @brief Person class
 */
package model;

import java.util.ArrayList;


public class Person 
{

	/*
	 * @Attributs
	 */
	private int id;
	private int maxStamina;
	private int stamina;
	private boolean isTasking;
	private ArrayList<Task> tasks;
	
	/*
	 * @brief Default constructor
	 */
	public Person()
	{
		this.id = 0;
		this.maxStamina = 10;
		this.stamina = 0;
		this.tasks = new ArrayList<Task>();
		this.isTasking = false;
	}
	
	/*
	 * 
	 */
	public Person(int id, int maxS, int sta, boolean isT, ArrayList<Task> t)
	{
		this.id = id;
		this.maxStamina = maxS;
		this.stamina = sta;
		this.tasks = t;
		this.isTasking = isT;
	}
	
	/*
	 * @brief add a task to the ArrayList link to the Person
	 * @param Task t : The task to add
	 * @return void
	 */
	public void addTask(Task t)
	{
		this.tasks.add(t);
	}
	
	/*
	 * @brief Use to know if the Person is doing a task
	 * @return the boolean isTasking knowing if the Person is tasking or not
	 */
	public boolean doTask()
	{
		return this.isTasking;
	}
}

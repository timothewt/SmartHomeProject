/**
 * @author Thibault Mayer
 * @file Couple.java
 * @date 14/12/2022
 * @brief Use to manipulate people in the House
 */

package model;

import java.util.ArrayList;

public class Couple 
{
	
	/*
	 * @Attributes
	 */
	private int money;
	private ArrayList<Person> persons;
	
	
	/*
	 * @brief Default constructor
	 */
	public Couple()
	{
		this.money = 500;
		this.persons = new ArrayList<Person>();
	}
	
	/*
	 * 
	 */
	public Couple(int money, ArrayList<Person> persons)
	{
		this.money = money;
		this.persons = persons;
	}
	
	/*
	 * @brief add a person to the couple
	 * @param Task p : The person to add
	 * @return void
	 */
	public void addPerson(Person p)
	{
		this.persons.add(p);
	}
	
	
	/*
	 * Getters and Setters
	 */
	public int getMoney() 
	{
		return money;
	}
	public void setMoney(int money) 
	{
		this.money = money;
	}
	
	
	
}

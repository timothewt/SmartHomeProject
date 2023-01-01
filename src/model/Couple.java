/**
 * @file Couple.java
 * @date 14/12/2022
 * @brief Use to manipulate people in the House
 */
package model;

import java.util.ArrayList;

public class Couple {

	private int money;
	private final ArrayList<Person> persons;

	/**
	 * Constructors
	 */
	public Couple() {
		this.money = 500;
		this.persons = new ArrayList<Person>();
	}

	public Couple(int money, ArrayList<Person> persons)	{
		this.money = money;
		this.persons = persons;
	}
	
	/**
	 * Adds a person to the couple
	 * @param person : The person to add
	 */
	public void addPerson(Person person) {
		this.persons.add(person);
	}

	/**
	 * Getter and setter
	 */
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}
	public Person getPersonsFromId(int id) {
		Person result = null;
		for (Person person : this.persons) {
			if (id == person.getId()) {
				result = person;
			}
		};
		return result;
	}
}

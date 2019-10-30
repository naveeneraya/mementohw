
package cecs277momentohw;

import java.io.Serializable;

/**
 * Class the keeps the memento of a Person object
 * @author Naveene Raya Carlos Alvarenga
 * @date 10/30/2019
 */
public class PersonMemento implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3994525105352400685L;
	
	Person savedP;

	/**
	 * default constructor for PersonMomento
	 */
	public PersonMemento() {
	}
	

	/**
	 * Constructor that takes in a Person
	 * @param P
	 */
	public PersonMemento(Person P) {

		savedP = P;
		
	}
	
	public Person getSavedPerson() {
		//TODO
		Person clone = savedP;
		return clone;
	}
	
	/**
	 * Return string version of the Person.
	 * @return	Display string of the person.
	 */
	@Override
	public String toString () {
		return savedP.toString();
	}

}

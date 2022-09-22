package linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T>{
	
	protected Comparator<T> comparator;
	
	protected List<LinkedEquivalenceClass<T>> rest;
	
	
	/**
	 * creates a EquivalenceClasses object and initializes the instance variables. 
	 * 
	 * @param a comparator that is used to initialize the instance variable comparator
	 */
	public EquivalenceClasses(Comparator<T> c) {
		comparator = c;
		rest = new ArrayList<LinkedEquivalenceClass<T>>();
	}
	
	
	/**
	 * Adds the passed element to the class it belongs to. Returns false if it does not 
	 * belong to any class. 
	 * 
	 * @param element -- the element added to the class which it belongs to
	 * @return whether the passed element was actually added
	 */
	public boolean add(T element) {
		for (int i = 0; i < rest.size(); i++) {
			if (rest.get(i).belongs(element)) {
				return rest.get(i).add(element);
			};
		}
		return false;
	}
	
	
	/**
	 * checks the classes if the passed element belongs to any of 
	 * the equivalence classes
	 * 
	 * @param target -- the element used to check if it belongs in any of the classes
	 * @return whether the passed element belongs to any class
	 */
	public boolean contains(T target) {
		for (int i = 0; i < rest.size(); i++) {
			if (rest.get(i).belongs(target)) return true;
		}
		return false;
	}
	
	
	/**
	 * @return the size of the list
	 */
	public int size() {
		// are we checking size 
		return rest.size();
	}
	
	
	/**
	 * @return the number of LinkedEquivalenceClasses that rest holds
	 */
	public int numClasses() {
		return rest.size();
	}
	
	
	/**
	 * finds the class that the element belongs to and returns the index of that class or -1 
	 * if no such class exists.
	 * 
	 * @param element -- the element we are looking for
	 * @return index of the class that element belongs to or -1 if no such class exists
	 */
	protected int indexOfClass(T element) {
		for (int i = 0; i < rest.size(); i++) {
			if (rest.get(i).belongs(element)) return i;
		}
		return -1;
	}

	
	/**
	 * @return a string containing all LinkedEquivalenceClasses
	 */
	@Override
	public String toString() {
		return rest.toString();
	}
}
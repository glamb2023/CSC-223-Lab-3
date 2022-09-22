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
	 * 
	 * @param element
	 * @return whether the passed element was actually added
	 */
	public boolean add(T element) {
		if (rest.contains(element)) return false;
		
		// which linked equivalence class are we adding to?
		return true;
	}
	
	
	/**
	 * 
	 * @param target
	 * @return whether the passed element is in the list
	 */
	public boolean contains(T target) {
		
		// are we checking the linked equivalence classes for the element??
		// can't check rest for it since rest holds LinkedEquivalenceClass objects, right?
		return true;
	}
	
	/**
	 * @return the size of the list
	 */
	public int size() {
		// are we checking size 
		return rest.size();
	}
	
	
	/**
	 * 
	 * @return the number of LinkedEquivalenceClasses that rest holds
	 */
	public int numClasses() {
		return rest.size();
	}
	
	
	/**
	 * @param element
	 * @return index of the class
	 */
	protected int indexOfClass(T element) {
		for (int i = 0; i < rest.size(); i++) {
			if (comparator.compare(element, rest.get(i))) return i;	
		}
		return -1;
	}
	
	
	
	/**
	 * @return sb - a string containing all LinkedEquivalenceClasses
	 */
	@Override
	public String toString() {
		return rest.toString();
	}
}
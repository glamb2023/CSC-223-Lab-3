package linkedlist;
/**
 * An equivalence class with a linked list underlying it.
 * Stores a canonical object - the object used to represent all "equivalent" objects
 * Uses a given comparator to be used to compare the given object type.
 * The rest of the equivalent objects are added to the linked list.
 * 
 */
import java.util.Comparator;

public class LinkedEquivalenceClass<T> {

	/**
	 * Used to represent all equivalent objects
	 */
	protected T _canonical;
	
	/**
	 * Used to compare objects for equivalence, depending on specified type
	 */
	protected Comparator<T> _comparator;
	
	/**
	 * Used to store the rest of the elements
	 */
	protected LinkedList<T> _rest;
	
	/**
	 * Creates a new LinkedEquivalenceClass, with no specified canonical object and an 
	 * empty linked list. The specified comparator dictates the type of object the equivalence
	 * class will store.
	 * @param comparator - the comparator to use to compare objects of specified type
	 */
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_canonical = null; 
		_comparator = comparator;
		_rest = new LinkedList<T>();
	}
	
	/**
	 * Returns the canonical object of this equivalence class.
	 * @return the canonical object
	 */
	public T canonical() {
		return _canonical;
	}
	
	/**
	 * Returns whether this equivalence class is empty.
	 * The canonical object counts, and will prevent the 
	 * class from being empty if it exists.
	 * @return true if it is empty, false if it isn't
	 */
	public boolean isEmpty() {
		return _rest.isEmpty() && _canonical == null;
	}
	
	/**
	 * Clears all objects of this equivalence class, including the 
	 * canonical object
	 */
	public void clear() {
		_canonical = null;
		_rest.clear();
	}
	
	/**
	 * Clears all objects of this equivalence class that are
	 * not the canonical object
	 */
	public void clearNonCanonical() {
		_rest.clear();
	}
	
	/**
	 * Returns the size of this equivalence class, including 
	 * the canonical object
	 * @return the size of the equivalence class, incl. the
	 * canonical object
	 */
	public int size() {
		int size = 0;
		if (_canonical != null) size++; 
		size = size + _rest.size();
		
		return size;
	}
	
	/**
	 * Adds an element to the end of the linked list, only if 
	 * it belongs in this equivalence class. /////does it allow duplicates?
	 * @param element - the element to add
	 * @return whether the add operation was successful
	 */
	public boolean add(T element) {
		if(this.belongs(element)) {
			_rest.addToBack(element);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether this equivalence class contains the specified
	 * target. 
	 * @param target - the element to find
	 * @return whether this element exists in this equivalence class
	 */
	public boolean contains(T target) {
		if (!(this.belongs(target))) return false;
		if (_canonical.equals(target)) return true;
		
		return _rest.contains(target);
	}
	
	/**
	 * Using the comparator, checks whether the specified element belongs in this 
	 * equivalence class.
	 * @param target - the element to check
	 * @return whether this element belongs in this equivalence class
	 */
	public boolean belongs(T target) {
		return _comparator.compare(_canonical, target) == 0;
	}
	
	/**
	 * Removes a specified element from the equivalence class //////should the canonical be updated to something in _rest?
	 * @param target - the element to remove
	 * @return whether the remove operation was successful
	 */
	public boolean remove(T target) {
		// add check for canonical maybe?
		return _rest.remove(target);
	}
	
	/**
	 * Removes the canonical object. //////should the canonical be updated to something in _rest?
	 * @return whether the remove operation was successful
	 */
//	public boolean removeCanonical() {
//		
//	}
	
	/**
	 * If the specified element belongs in this equivalence class, 
	 * demotes the current canonical object (moves it into the linked list), if it exists, then
	 * updates the canonical object to the specified element.
	 * @param element
	 * @return
	 */
	public boolean demoteAndSetCanonical(T element) 
	{
		if (!this.belongs(element)) return false;
		
		// only demote canonical if it exists
		if (_canonical != null)
		{
			_rest.addToBack(_canonical);
		}
		// update canonical 
		_canonical = element;
		return true;
	}
	
	/**
	 * Returns a String representation of this equivalence class.
	 * e.g. an equivalence class of even integers in which 2 
	 * is the canonical object: 
	 * {2|4,6,8,10}
	 */
	public String toString() {

		return "{" + _canonical + "|" + _rest.toString() + "}";
	}
	
}

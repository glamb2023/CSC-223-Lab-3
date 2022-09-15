package linkedlist;
/**
 * A generic singly linked list class.
 * Nodes with null items are not allowed, except for the sentinel head and tail nodes.
 * @param <T>
 */
public class LinkedList<T> {

	protected Node _head;
	protected Node _tail;
	protected int _size;

	private class Node {
		private T _item;
		private Node _next;

		public Node(T item, Node next) {
			_item = item;
			_next = next;
		}
	}

	public LinkedList() {
		_tail = new Node(null, null);
		_head = new Node(null, _tail);
		_size = 0;
	}

	public boolean isEmpty() {
		return _size == 0;
	}

	public void clear() {
		_head._next = _tail;
		_size = 0;
	}

	public int size() {
		return _size;
	}

	/**
	 * Adds a new element to the front of the list.
	 * Nodes with null items are not allowed.
	 * @param element
	 */
	public void addToFront(T element) {
		if (element == null) return;
		
		_head._next = new Node(element, _head._next);
		_size++;
	}

	public boolean contains(T target) {
		return this.contains(target, _head._next);
	}

	/**
	 * Recurvise helper for contains.
	 * @param target
	 * @param current
	 * @return
	 */
	private boolean contains(T target, Node current) {

		if (target == null || current._next == null)
			return false;
		if (current._item.equals(target))
			return true;
		return contains(target, current._next);

	}

	/**
	 * Returns the node that points to the node that has
	 * the specified target as its item
	 * @param target - node that is pointed to by the node we want
	 * @return the found node
	 */
	private Node previous(T target) {
		return this.previous(target, _head);
	}

	/**
	 * Recursive helper method for previous.
	 * @param target
	 * @param current
	 * @return
	 */
	private Node previous(T target, Node current) {
		
		if (target == null || current._next.equals(_tail))
			return null;
		if (current._next._item.equals(target))
			return current;
		return previous(target, current._next);

	}

	/**
	 * Removes the first node in the list which item is 
	 * equal to the specified target. 
	 * @param target - item to find and remove
	 * @return - whether the remove was successful
	 */
	
	public boolean remove(T target) {
		//finds node previous to target
		Node n = previous(target);
		if (n == null) return false;
		n._next=n._next._next;
		_size--;
		return true;

	}

	/**
	 * Returns the last element in the linked list.
	 * If the list is empty, returns null.
	 * Calls the recursive helper method last(Node current)
	 * 
	 * @return the last node of the list
	 */
	private Node last() {
		if (_head._next == _tail) return null;
		return last(_head._next);
	}
	
	/**
	 * Recursive helper method that calls itself until the last node is reached.
	 * 
	 * @param current - current node
	 * @return last node of the list
	 */
	private Node last(Node current) {
		if (current._next.equals(_tail)) return current; 
		return last(current._next);
	}
	
	/**
	 * Adds a new element to the back of the list.
	 * Nodes with null items are not allowed.
	 * @param element - element to add
	 */
	public void addToBack(T element) {
		// adding to back on an empty list is the same as adding to front,
		// so do that method instead
		if (_size == 0)
		{
			addToFront(element);
			return;
		}
		// if list is non empty
		if (element == null) return;
		
		 last()._next = new Node(element, _tail);
		 _size++;
	}
	
	/**
	 * Returns whether n is at the end of the list.
	 * @param n - node to check
	 * @return whether it is at the end
	 */

	private boolean atEnd(Node n) {

		return n.equals(_tail);
		
	}

	/**
	 * Returns a string representation of the linked list.
	 * Lists each node's item, separated by spaces.
	 * Example:
	 * 2 4 6 8
	 */
	public String toString() {

		return toStringHelper(new StringBuilder(), _head._next);
		
	}

	/**
	 * Helper method for toString. Recursively concatenates each node's item
	 * as a string, separated by spaces.
	 * @param sb - stringbuilder
	 * @param n - node to add
	 * @return string - partially or fully built
	 */
	private String toStringHelper(StringBuilder sb, Node n) {
		if (atEnd(n)) {
			sb.delete(sb.length(), sb.length());
			return sb.toString();
		}
		sb.append(n._item + " ");
		return toStringHelper(sb, n._next);

	}
	/**
	 * reverses the linked list
	 */
	public void reverse() {
		Node prevNode = null;
		Node nextNode = null;
		// loops through the list to reverse all the pointers with 
		// the help of two temporary nodes. Stops at the _tail node
		for (Node n = _head; !n.equals(_tail); n = n._next) {			
			nextNode = n._next;
			n._next = prevNode;
			prevNode = n;
			n = nextNode;
		}
		// fixes the pointers to and from the head and tail nodes
		_head._next = prevNode;
		last()._next = _tail;
	}
}


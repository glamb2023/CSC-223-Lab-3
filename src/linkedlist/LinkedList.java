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
		_head = new Node(null, _tail);
		_tail = new Node(null, null);
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

		if (target == null || current == null)
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
		if (target == null || current._next == null)
			return null;
		if (current._next._item.equals(current))
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

		Node n = previous(target);
		if (n==null) return false;
		n._next=n._next._next;
		return true;

	}

	/**
	 * Returns the last node in the linked list.
	 * @return
	 */
	private Node last() {
		Node node = _head._next;
		// go through the list until the last node is reached
		// (the last node is the one that points to null)
		while (node._next != _tail)
		{
			node = node._next;
		}
		return node;

	}
	
	/**
	 * Adds a new element to the back of the list.
	 * Nodes with null items are not allowed.
	 * @param element - element to add
	 */
	public void addToBack(T element) {
		if (element == null) return;
		
		 last()._next = new Node(element, null);
	}
	
	/**
	 * Returns whether n is at the end of the list.
	 * @param n - node to check
	 * @return whether it is at the end
	 */
	public boolean atEnd(Node n) {

		return n == null;

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

		if (atEnd(n)) return sb.toString();
		sb.append(n._item + " ");
		return toStringHelper(sb, n._next);

	}

	public void reverse() {

	}

}


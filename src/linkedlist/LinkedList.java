package linkedlist;
/**
 * A generic singly linked list class.
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
		_head = new Node(null, null);
		_tail = new Node(null, null);
		_head._next = _tail;
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

	public void addToFront(T element) {
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
		if (n==null) return false;
		n._next=n._next._next;
		_size--;
		return true;

	}

	/**
	 * Calls the recursive helper method last(Node current)
	 * 
	 * @return the last node of the list
	 */
	private Node last() {
		return last(_head._next);
	}
	
	/**
	 * recursive helper method that calls itself until the last node is reached.
	 * 
	 * @param current -- current node
	 * @return last node of the list
	 */
	private Node last(Node current) {
		if (current._next.equals(_tail)) { return current; }
		return last(current._next);
	}
	
	/**
	 * Adds a new node using the specified element 
	 * to the back of the linked list.
	 * @param element - element to add
	 */
	public void addToBack(T element) {
		 last()._next = new Node(element, null);
	}
	
	/**
	 * Returns whether n is at the end of the list.
	 * @param n - node to check
	 * @return whether it is at the end
	 */
	public boolean atEnd(Node n) {
		return n.equals(this.last());
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


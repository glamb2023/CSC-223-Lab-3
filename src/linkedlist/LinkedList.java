package linkedlist;
/**
 * A generic singly linked list class.
 * Nodes with null items are not allowed, except for the sentinel head and tail nodes.
 * @param <T>
 */
public class LinkedList<T> {

	/**
	 * Sentinel head node. Does not count towards list size.
	 */
	protected Node _head;
	
	/**
	 * Sentinel tail node. Does not count towards list size.
	 */
	protected Node _tail;
	
	/**
	 * Keeps track of the number of nodes, excl. sentinel head and tail.
	 */
	protected int _size;

	/**
	 * Private node class to represent a node in the linked list.
	 */
	private class Node {
		private T _item;
		private Node _next;

		public Node(T item, Node next) {
			_item = item;
			_next = next;
		}
	}

	/**
	 * Creates an empty linked list.
	 */
	public LinkedList() {
		_tail = new Node(null, null);
		_head = new Node(null, _tail);
		_size = 0;
	}

	/**
	 * Returns whether the linked list is empty.
	 * @return true if it's empty, false if it's not
	 */
	public boolean isEmpty() {
		return _size == 0;
	}

	/**
	 * Clears the list.
	 */
	public void clear() {
		_head._next = _tail;
		_size = 0;
	}

	/**
	 * Returns the number of nodes in the linked list.
	 * @return size - number of nodes in the list
	 */
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

	/**
	 * Returns whether the linked list contains a node with the 
	 * specified target as its data.
	 * @param target - the data used to find the node we want
	 * @return whether the node exists in the linked list
	 */
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
		
		if (target == null || current._next == _tail)
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
	 * loops through the list until the last element is reached and 
	 * returns the last element of the list.
	 * 
	 * @return n -- the last node of the list
	 */
	/*
	private Node last() {
	Node n = _head._next;
	while (!n._next.equals(_tail))
	{
		n = node._next;
	}
	return n;
	} */
	
	/**
	 * Returns the last element in the linked list.
	 * If the list is empty, returns null.
	 * Calls the recursive helper method last(Node current)
	 * @return the last node of the list
	 */
	private Node last() {
		if (this.isEmpty()) return null;
		return last(_head._next);
	}
	
	/**
	 * Recursive helper method that calls itself until the last node is reached.
	 * 
	 * @param current - current node
	 * @return last node of the list
	 */
	private Node last(Node current) {
		if (current._next ==_tail) return current; 
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

		return n ==_tail;
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
			if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
		sb.append(n._item + " ");
		return toStringHelper(sb, n._next);
	}
	
	/**
	 * Reverses the linked list.
	 */
	public void reverse() {
		if (_size <= 1) return; // no use reversing an empty list or containing only 1 element

		this.reverseHelper(_head);
	}
	
	/**
	 * Helper method for reverse().
	 * The process of recursively reversing is as follows:
	 * A checkpoint is used--the first being sentinel head node.
	 * The last node of the list is inserted AFTER the checkpoint.
	 * The just-inserted node is passed as the next checkpoint.
	 * 
	 * example below. say we want to reverse this list:
	 * head -> a -> b -> c -> tail
	 * 
	 * Then, using the algorithm (assume capitalized nodes are the checkpoint):
	 * 
	 * iteration 1:
	 * HEAD -> a -> b -> c -> tail     <- head starts out as the checkpoint
	 * HEAD -> c -> a -> b -> tail	   <- c has been inserted
	 * 
	 * iteration 2:
	 * head -> C -> a -> b -> tail	   <- c is now the checkpoint
	 * head -> C -> b -> a -> tail	   <- b has been inserted
	 * 
	 * iteration 3:
	 * head -> c -> B -> a -> tail     <- b is now the checkpoint
	 * the algorithm ends because the list is now reversed.
	 * (B.next.next is tail)
	 * 
	 * @param checkpoint - node to insert after
	 */
	private void reverseHelper(Node checkpoint)
	{
		if (checkpoint._next._next == _tail) return;
		
		Node secondToLast = this.secondToLast();
		Node last = secondToLast._next;
		
		last._next = checkpoint._next; // last element points to node AFTER checkpoint
		checkpoint._next = last;	   // checkpoint now points to last element
		secondToLast._next = _tail;    // second to last node points to tail (is now the new last node).
		
		reverseHelper(last);		   // pass in the FORMER last node (the one that was just moved) as the next checkpoint
		
	}
	
	/**
	 * Helper method for reverse. Gets the second to last node in the list
	 * @return node
	 */
	private Node secondToLast() {
		if (this.isEmpty()) return null;
		return secondToLast(_head._next);
	}
	
	/**
	 * Helper recursive method for secondToLast.
	 * @param current - current node
	 * @return node
	 */
	private Node secondToLast(Node current) {
		if (current._next._next == _tail) return current; 
		return secondToLast(current._next);
	}
}


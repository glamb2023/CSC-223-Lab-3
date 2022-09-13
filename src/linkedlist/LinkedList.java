package linkedlist;

public class LinkedList<T> {

	protected Node _head;
	public Node _tail;
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

	public void addToFront(T element) {
		_head._next = new Node(element, _head._next);
		_size++;
	}

	public boolean contains(T target) {
		return this.contains(target, _head._next);
	}

	private boolean contains(T target, Node current) {

		if (target == null || current == null)
			return false;
		if (current._item.equals(target))
			return true;
		return contains(target, current._next);

	}

	public Node previous(T target) {
		return this.previous(target, _head);
	}

	private Node previous(T target, Node current) {
		if (target == null || current._next == null)
			return null;
		if (current._next._item.equals(current))
			return current;
		return previous(target, current._next);

	}

	public boolean remove(T target) {
		
		_size--;
		return false;
	}

	private Node last() {
		

	}

	public void addToBack(T element) {
		 return addToBackHelper(element, _head);

	}

	private void addToBackHelper(T element, Node node) {
		if (node._next.equals(_tail)) {
			node._next = new Node(element, _tail);
		}
		 return addToBackHelper(element, node._next);
	}

	public boolean atEnd(Node n) {

		return n==null;

	}

	public String toString() {

		return toStringHelper(new StringBuilder(), _head._next);

	}

	private String toStringHelper(StringBuilder sb, Node n) {

		if (atEnd(n)) return sb.toString();
		sb.append(n._item + " ");
		return toStringHelper(sb, n._next);

	}

	public void reverse() {

	}

}

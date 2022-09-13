package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import linkedlist.LinkedList;

class LinkedListTest {

	@Test
	void testisEmpty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		assertFalse(list.isEmpty());
	}
	@Test
	void testaddToFront() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		assertEquals("2 ", list.toString());
	}
	@Test
	void testContains() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4);
		list.addToFront(6);
		assertTrue(list.contains(2));
		assertTrue(list.contains(6));
		assertFalse(list.contains(8));
	}
	@Test
	void testPrevious() {

		
	}

}

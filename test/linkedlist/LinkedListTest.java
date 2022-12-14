package linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import linkedlist.LinkedList;

class LinkedListTest {

	// isEmpty

	@Test
	void testisEmpty_empty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertTrue(list.isEmpty());
	}

	@Test
	void testisEmpty_notEmpty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		assertFalse(list.isEmpty());
	}

	// addToFront
	// - integer
	// - null
	// - multiple adds

	@Test
	void testaddToFront_integer() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		assertEquals("2", list.toString());
	}

	@Test
	void testaddToFront_null() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(null);
		assertEquals("", list.toString());
	}

	@Test
	void testaddToFront_multipleAdds() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		list.addToFront(4);
		assertEquals("4 2", list.toString());
	}

	// contains
	// - empty
	// - try to find null
	// - only element in list
	// - at front
	// - in middle
	// - at back

	@Test
	void testContains_long() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4);
		list.addToFront(6);
		// list: 6,4,3,2
		assertTrue(list.contains(2));
		assertTrue(list.contains(3));
		assertTrue(list.contains(6));
		assertFalse(list.contains(8));
		assertFalse(list.contains(null));
	}

	@Test
	void testContains_size1() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(2);
		assertTrue(list.contains(2));
		assertFalse(list.contains(8));
		assertFalse(list.contains(null));
	}

	void testContains_empty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertFalse(list.contains(2));
		assertFalse(list.contains(null));
	}
	@Test
	void testRemove() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(5);
		list.addToFront(4);
		list.addToFront(3);
		assertTrue(list.size()==3);

		list.remove(4);
		
		assertTrue(list.size()==2);
		assertFalse(list.contains(4));
		assertTrue(list.contains(3));
		assertTrue(list.contains(5));

	}

	@Test
	void testRemoveNonExistent() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToFront(5);
		list.addToFront(4);
		list.addToFront(3);
		//remove non-existent value
		list.remove(7);
		assertTrue(list.size()==3);
	}
	
	// addToBack
	// - add null
	// - add integer
	// - add multiple

	@Test
	void testaddToBack_integer() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToBack(2);
		assertEquals("2", list.toString());
	}
	
	@Test
	void testaddToBack_null() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToBack(null);
		assertEquals("", list.toString());
	} 
	
	@Test
	void testaddToBack_multipleAdds() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addToBack(2);
		list.addToBack(4);		
		assertEquals("2 4", list.toString());
	}
	
	@Test
	void testReverse() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		// testing reversing with one element
		l.addToFront(1);
		String preReverse = l.toString();
		l.reverse();
		String postReverse = l.toString();
		assertEquals(preReverse, postReverse);
		
		// testing reversing list with two elements
		l.addToBack(2);
		preReverse = l.toString();
		StringBuilder sb = new StringBuilder(preReverse);
		StringBuilder revSb = sb.reverse();
		l.reverse();
		postReverse = l.toString();
		assertEquals(revSb.toString(), postReverse);
		
		// testing reversing list with five elements
		l.addToFront(5);
		l.addToBack(8);
		l.addToBack(7);
		preReverse = l.toString();
		sb = new StringBuilder(preReverse);
		revSb = sb.reverse();
		l.reverse();
		postReverse = l.toString();
		assertEquals(revSb.toString(), postReverse);
		
		// testing reversing after removing two elements
		l.remove(5);
		l.remove(1);
		preReverse = l.toString();
		sb = new StringBuilder(preReverse);
		revSb = sb.reverse();
		l.reverse();
		postReverse = l.toString();
		assertEquals(revSb.toString(), postReverse);
		
		// reverse empty
		LinkedList<Integer> empty = new LinkedList<Integer>();
		empty.reverse();
		postReverse = empty.toString();
		assertEquals("", postReverse);
	}
	
}


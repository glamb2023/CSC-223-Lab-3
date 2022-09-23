package linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class LinkedEquivalenceClassTest {
	
    private static final double EPSILON = Math.pow(10, -6); // 0.000001
	
	// compare two doubles to see if both numbers are perfect squares
	Comparator<Double> c = new Comparator<Double>() {
		public int compare(Double x, Double y) 
		{
			// only numbers who are both perfect squares are equivalent
			return isPerfectSquare(x) && isPerfectSquare(y) ? 0 : 1;
		}
	};

	private boolean isPerfectSquare(double x)
	{
		double sqrt = Math.sqrt(x);
		return doubleEquals(sqrt - Math.floor(sqrt), 0.0);
	}

    private boolean doubleEquals(double a, double b)
    {
        return Math.abs(a - b) < EPSILON;
    }
	
    // test comparator
    // - neither are perfect squares
    // - one is perfect square
    // - both are perfect squares
    @Test
    void testcompare_neither()
    {
    	int expected = 1;
    	int actual = c.compare(5.0, 3.0);
    	assertEquals(expected, actual);
    }
    
    @Test
    void testcompare_onlyone()
    {
    	int expected = 1;
    	int actual = c.compare(5.0, 4.0);
    	assertEquals(expected, actual);
    }
    
    @Test
    void testcompare_both()
    {
    	int expected = 0;
    	int actual = c.compare(9.0, 4.0);
    	assertEquals(expected, actual);
    }
    
	// isEmpty
	// - canonical and rest is empty
	// - canonical is empty, but not rest
    // - canonical is not empty, but rest is
	
	@Test
	void testisEmpty_bothEmpty() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		boolean expected = true;
		boolean actual = eqc.isEmpty();
		assertEquals(expected, actual);
	}
	
	@Test
	void testisEmpty_restexists() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		eqc.removeCanonical();
		
		boolean expected = false;
		boolean actual = eqc.isEmpty();
		assertEquals(expected, actual);
	}
	
	@Test
	void testisEmpty_canonicalexists() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		
		boolean expected = false;
		boolean actual = eqc.isEmpty();
		assertEquals(expected, actual);
	}
	

	
	// size
	// - empty
	// - canonical exists, rest empty
	// - canonical exists, rest has stuff in it
	// - canonical does not exist, rest has stuff in it
	
	@Test
	void testsize_empty()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		int expected = 0;
		int actual = eqc.size();
		assertEquals(expected, actual);
	}
	
	@Test
	void testsize_canonicalexists_restempty()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		
		int expected = 1;
		int actual = eqc.size();
		assertEquals(expected, actual);
	}
	
	@Test
	void testsize_canonicalexists_restnonempty()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		
		int expected = 2;
		int actual = eqc.size();
		assertEquals(expected, actual);
	}
	
	@Test
	void testsize_nocanonical_restnonempty()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		eqc.removeCanonical();
		
		int expected = 1;
		int actual = eqc.size();
		assertEquals(expected, actual);
	}
	
	// add
	// - null
	// - to empty
	// - doesn't belong
	// - add one
	// - add many
	
	@Test
	void testadd_toempty() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		boolean actual = eqc.add(4.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
		assertEquals(eqc._canonical, 4.0);
	}
	
	@Test
	void testadd_null() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.add(null);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testadd_nobelong() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.add(5.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testadd_addone() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.add(9.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
		assertEquals(eqc._rest.size(), 1);
	}
	
	@Test
	void testadd_addmany() 
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		int numSuccesses = 0;
		if (eqc.add(9.0)) numSuccesses++;
		if (eqc.add(16.0)) numSuccesses++;
		if (eqc.add(25.0)) numSuccesses++;
		if (eqc.add(36.0)) numSuccesses++;
		if (eqc.add(10.0)) numSuccesses++; // does not belong
		
		int expected = 4;
		assertEquals(expected, numSuccesses);
		assertEquals(eqc._rest.toString(), "9.0 16.0 25.0 36.0");
	}

	// contains
	// - null
	// - no belong
	// - belongs, and is canonical value
	// - belongs, but does not contain
	// - belongs, but contains
	
	@Test
	void testcontains_null()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.contains(null);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testcontains_nobelong()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.contains(11.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testcontains_belongsbutcanonical()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.contains(4.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	void testcontains_belongsbutnotin()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(16.0);
		boolean actual = eqc.contains(9.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testcontains_belongsbutin()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		boolean actual = eqc.contains(9.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	// belongs
	// - null
	// - canonical is null
	// - is the same as canonical
	// - does not belong
	// - belongs
	
	@Test
	void testbelongs_null()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.belongs(null);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testbelongs_canonicalnull()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		// no canonical set, canonical == null
		boolean actual = eqc.belongs(4.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testbelongs_iscanonical()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.belongs(4.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	void testbelongs_nobelong()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.belongs(3.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testbelongs_belongs()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.belongs(9.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	// remove
	// - null
	// - from empty
	// - no belong
	// - target not found
	// - target is canonical
	// - target is in linked list
	
	@Test
	void testremove_null()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.remove(null);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testremove_fromempty()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		boolean actual = eqc.remove(4.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testremove_nobelong()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.remove(3.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testremove_notfound()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		eqc.add(16.0);
		eqc.add(36.0);
		boolean actual = eqc.remove(25.0);
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testremove_canonical()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.remove(4.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
		assertEquals(eqc.canonical(), null);
	}
	
	@Test
	void testremove_inlist()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.add(9.0);
		eqc.add(16.0);
		eqc.add(36.0);
		boolean actual = eqc.remove(16.0);
		
		boolean expected = true;
		assertEquals(expected, actual);
		assertEquals(eqc._rest.toString(), "9.0 36.0");
		assertEquals(eqc._rest.size(), 2);
	}
	
	// removeCanonical
	// - canonical is null
	// - canonical is not null
	
	@Test
	void testremoveCanonical_canonicalnull()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		// canonical is not set, canonical == null
		boolean actual = eqc.removeCanonical();
		
		boolean expected = false;
		assertEquals(expected, actual);			
	}
	
	@Test
	void testremoveCanonical_canonicalnotnull()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		boolean actual = eqc.removeCanonical();
		
		boolean expected = true;
		assertEquals(expected, actual);	
		assertEquals(eqc._canonical, null);
	}
	
	// demoteAndSetCanonical
	// - no canonical exists
	// - does not belong
	// - replace existing canonical
	
	@Test
	void testdemoteAndSetCanonical_nocanonical()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		
		double expected = 4.0;
		double actual = eqc.canonical();
		assertEquals(expected, actual);
	}
	
	@Test
	void testdemoteAndSetCanonical_nobelong()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.demoteAndSetCanonical(5.0);
		
		double expected = 4.0;
		double actual = eqc.canonical();
		assertEquals(expected, actual);
	}
	
	@Test
	void testdemoteAndSetCanonical_replaceexisting()
	{
		LinkedEquivalenceClass<Double> eqc = new LinkedEquivalenceClass<Double>(c);
		
		eqc.demoteAndSetCanonical(4.0);
		eqc.demoteAndSetCanonical(16.0);
		
		double expected = 16.0;
		double actual = eqc.canonical();
		assertEquals(expected, actual);
		assertEquals(eqc._rest.size(), 1);
	}

}

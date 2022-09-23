package linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.Test;

public class EquivalenceClassesTest {

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
    
    // add
    // - add null
    // - add to empty
    // - add, but doesn't belong to any
    // - add, but belongs
    @Test
    public void testadd_null()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass);
    	
    	boolean actual = ec.add(null);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testadd_toEmpty()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
    	
    	boolean actual = ec.add(2.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testadd_nobelong()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass);
    	
    	boolean actual = ec.add(3.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testadd_belongs()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass);
    	
    	boolean actual = ec.add(9.0);
    	boolean expected = true;
    	assertEquals(expected, actual);
    }
    
    // contains
    // - null
    // - empty
    // - does not contain, 1 class
    // - does not contain, 2 classes
    // - contains, 1 class
    // - contains, 2 classes
    
    @Test
    public void testcontains_null()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass);
    	
    	boolean actual = ec.contains(null);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testcontains_empty()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
    	
    	boolean actual = ec.contains(4.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testcontains_nocontain_size1()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass1);
    	
    	boolean actual = ec.contains(9.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testcontains_nocontain_size2()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		LinkedEquivalenceClass<Double> linkedclass2 = new LinkedEquivalenceClass<Double>(c);
		linkedclass2.demoteAndSetCanonical(9.0);
		ec._classes.add(linkedclass1);
		ec._classes.add(linkedclass2);
    	
    	boolean actual = ec.contains(16.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testcontains_contains_size1()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass1);
    	
    	boolean actual = ec.contains(4.0);
    	boolean expected = true;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testcontains_contains_size2()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		LinkedEquivalenceClass<Double> linkedclass2 = new LinkedEquivalenceClass<Double>(c);
		linkedclass2.demoteAndSetCanonical(9.0);
		ec._classes.add(linkedclass1);
		ec._classes.add(linkedclass2);
    	
    	boolean actual = ec.contains(9.0);
    	boolean expected = true;
    	assertEquals(expected, actual);
    }
    
    // indexOfClass
    // - null
    // - empty
    // - does not contain, 1 class
    // - does not contain, 2 classes
    // - contains, 1 class
    // - contains, 2 classes
    
    @Test
    public void testindexOfClass_null()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass);
    	
    	int actual = ec.indexOfClass(null);
    	int expected = -1;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testindexOfClass_empty()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);

    	int actual = ec.indexOfClass(4.0);
    	int expected = -1;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testindexOfClass_nocontain_size1()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass1);
    	
    	int actual = ec.indexOfClass(9.0);
    	int expected = -1;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testindexOfClass_nocontain_size2()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		LinkedEquivalenceClass<Double> linkedclass2 = new LinkedEquivalenceClass<Double>(c);
		linkedclass2.demoteAndSetCanonical(9.0);
		ec._classes.add(linkedclass1);
		ec._classes.add(linkedclass2);
    	
    	int actual = ec.indexOfClass(16.0);
    	int expected = -1;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testindexOfClass_contains_size1()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		ec._classes.add(linkedclass1);
    	
    	int actual = ec.indexOfClass(4.0);
    	int expected = 0;
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testindexOfClass_contains_size2()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>(c);
		LinkedEquivalenceClass<Double> linkedclass1 = new LinkedEquivalenceClass<Double>(c);
		linkedclass1.demoteAndSetCanonical(4.0);
		LinkedEquivalenceClass<Double> linkedclass2 = new LinkedEquivalenceClass<Double>(c);
		linkedclass2.demoteAndSetCanonical(9.0);
		ec._classes.add(linkedclass1);
		ec._classes.add(linkedclass2);
    	
    	int actual = ec.indexOfClass(9.0);
    	int expected = 1;
    	assertEquals(expected, actual);
    }
    
}

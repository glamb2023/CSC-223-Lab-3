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
    void testadd_null()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>();
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec.append(linkedclass);
    	
    	boolean actual = ec.add(null);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    void testadd_toEmpty()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>();
    	
    	boolean actual = ec.add(2.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    void testadd_nobelong()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>();
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec.append(linkedclass);
    	
    	boolean actual = ec.add(3.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    @Test
    void testadd_belongs()
    {
    	EquivalenceClasses<Double> ec = new EquivalenceClasses<Double>();
		LinkedEquivalenceClass<Double> linkedclass = new LinkedEquivalenceClass<Double>(c);
		linkedclass.demoteAndSetCanonical(4.0);
		ec.append(linkedclass);
    	
    	boolean actual = ec.add(9.0);
    	boolean expected = false;
    	assertEquals(expected, actual);
    }
    
    // contains
    // - null
    // - empty
    // - does not contain, 1 class
    // - does not contain, 2 classes
    // - contains, 1 class
    // - contains, 2 classes
    
    // indexOfClass
    // - null
    // - empty
    // - does not contain, 1 class
    // - does not contain, 2 classes
    // - contains, 1 class
    // - contains, 2 classes
    
}

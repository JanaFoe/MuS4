package study;

import simulation.lib.counter.DiscreteAutocorrelationCounter;

/*
 * TODO Problem 4.1.2 - Implement this class
 * You can call your test from the main-method in SimulationStudy
 */

public class AutocorrelationTest {

    public static void testAutocorrelation() {
    	
    	DiscreteAutocorrelationCounter test = new DiscreteAutocorrelationCounter("test", 5);
    	test.reset();
		
    	for (int i = 0; i< 10; i++) {
    		test.count(2);
			
		}
    	
    	System.out.println(test.report());
    }
}

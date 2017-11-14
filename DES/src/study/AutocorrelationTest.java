package study;

import simulation.lib.counter.DiscreteAutocorrelationCounter;

/*
 * TODO Problem 4.1.2 - Implement this class
 * You can call your test from the main-method in SimulationStudy
 */

public class AutocorrelationTest {

    public static void testAutocorrelation() {
    	
    	DiscreteAutocorrelationCounter test = new DiscreteAutocorrelationCounter("X = 0,1,....9", 9);
    	test.reset();
  	
    	for (double i = 0; i< 10; i++) {
    		test.count(i);
		}
    	
    	System.out.println(test.report());
    	
    	
    	DiscreteAutocorrelationCounter test1 = new DiscreteAutocorrelationCounter("X = 0,1,1,2", 3);
    	test1.reset();
  	
    	for (double i = 0; i < 4; i++) {
    		if(i == 0)	test1.count(0);
    		else if(i == 1 || i == 2) test1.count(1);
    		else if(i == 3)  test1.count(2);
 		}
    	
    	System.out.println(test1.report());
    }
}

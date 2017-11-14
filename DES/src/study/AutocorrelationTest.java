package study;

import simulation.lib.counter.DiscreteAutocorrelationCounter;

/*
 * TODO Problem 4.1.2 - Implement this class
 * You can call your test from the main-method in SimulationStudy
 */

public class AutocorrelationTest {

    public static void testAutocorrelation() {
    	
    	/**
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
    	
    	**/
    	
    	//========== Example 1: X = 2,2,2,2,2,2.... 
    	DiscreteAutocorrelationCounter example1 = new DiscreteAutocorrelationCounter("X = 2,2,2,2,2...", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		example1.count(2);
 		}
    	
    	System.out.println(example1.report());
    	
    	//========== Example 2: X = 2,2,-2,2,2,-2.... 
    	DiscreteAutocorrelationCounter example2 = new DiscreteAutocorrelationCounter("X = 2,2,-2,2,2,-2...", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		if(i%3 == 0) example2.count(-2);
    		else example2.count(2);
 		}
    	
    	System.out.println(example2.report());
    	
    	
    	//========== Example 3: X = 2,-2,2,-2,2,-2.... 
    	DiscreteAutocorrelationCounter example3 = new DiscreteAutocorrelationCounter("X = 2,-2,2,-2,2,-2...", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		if(i%2 == 0) example3.count(-2);
    		else example3.count(2);
 		}
    	
    	System.out.println(example3.report());
    	
    	//========== Example 4: X = 2,-2,2,2,2,2.... 
    	DiscreteAutocorrelationCounter example4 = new DiscreteAutocorrelationCounter("X = 2,-2,2,2,2,2...", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		if(i == 1) example4.count(-2);
    		else example4.count(2);
 		}
    	
    	System.out.println(example4.report());
    	
    	//========== Example 5: X = -2,-2,-2,-2,-2,-2.... 
    	DiscreteAutocorrelationCounter example5 = new DiscreteAutocorrelationCounter("X = -2,-2,-2,-2,-2,-2...", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		example5.count(-2);
 		}
    	
    	System.out.println(example5.report());
    	
    	//========== Example 6: X = -2,-2,-2,-2,-2,-2.... 
    	DiscreteAutocorrelationCounter example6 = new DiscreteAutocorrelationCounter("X = 0,1,2,...,9", 9);
    	example1.reset();
  	
    	for (double i = 0; i < 10; i++) {
    		example6.count(i);
 		}
    	
    	System.out.println(example6.report());
    	
    	
    	
    }
}

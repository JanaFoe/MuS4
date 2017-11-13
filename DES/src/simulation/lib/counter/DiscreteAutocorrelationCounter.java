package simulation.lib.counter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import simulation.lib.Simulator;

/**
 * This class implements a discrete time autocorrelation counter
 */
public class DiscreteAutocorrelationCounter extends DiscreteCounter {
	/*
	 * TODO Problem 4.1.1 - Implement this class according to the given class diagram!
	 * Hint: see section 4.4 in course syllabus
	 */
	private int maxLag;
	private double[] arrSamples = {0,1,1,2};
	private double sumPowerOne = 4;
	private double mean = 1; 
	private double variance = 0.67;
	//		(7/2)  4/(4-1)* ((6/4)-(1*1)) = 4/3 *(3/2 -1) = 4/3 * 1/2 = 4/6 = 

		
	private double[] arrFirstj = new double[(int)getNumSamples()];
	private double[] arrLastj  = new double[(int)getNumSamples()];

	private int arrayCounter = 0;

	/**
	 * Konstruktor
	 * @param variable
	 * @param maxLag
	 */
	public DiscreteAutocorrelationCounter(String variable, int maxLag) {
		super(variable,"counter type: discrete-time autocorrelation counter");
		this.maxLag = maxLag;
	}

	/**
	 * Konstruktor
	 * @param variable
	 * @param type
	 * @param maxLag
	 */
	public DiscreteAutocorrelationCounter(String variable, String type, int maxLag) {
		super(variable,type);
		this.maxLag = maxLag; 
	}


	public long getMaxLag() {
		return maxLag;
	}

	public void setMaxLag(long maxLag) {
		this.maxLag = (int) maxLag;
	}


	public void count(double x) {
		super.count(x);
		increaseSumPowerOne(x);   
		increaseSumPowerTwo(x * x); 

		arrFirstj[arrayCounter] = x;  
		arrayCounter++;
	}

	public double getAutoCovariance(int lag) {
		double autoCov 	 = 0.0;
		double sumFirstj = 0.0;
		double sumLastj  = 0.0;
		double temp 	 = 0.0;

		/**
		 * Summe 0 bis lag
		 */
		for(int i = 0; i < lag; i++) {
			sumFirstj += arrSamples[i];
			System.out.println(i+" lag: "+lag+"  arrSamples[i]"+arrSamples[i]);
		}
		/**
		 * Summe n-lag bis n
		 */
		for(int i = ((int)getNumSamples()-lag); i < getNumSamples(); i++) {
			sumLastj += arrSamples[i];
			System.out.println(i+" lag: "+lag+"  arrSamples[i]"+arrSamples[i]);
		}
		System.out.println("sumFirstj "+sumFirstj+"  sumLastj "+sumLastj);


		for(int j = lag; j < getNumSamples(); j++ ) {
			temp += arrSamples[j]*arrSamples[j-lag];  }
		

		autoCov = (1/(double)(getNumSamples()-lag)) * (double)( temp - (mean * ( (2 * sumPowerOne) - sumFirstj -sumLastj ))) + Math.pow(mean,2);
//		autoCov = (1/(getNumSamples()-lag)) * ( temp - getMean() * ( 2 * getSumPowerOne()- sumFirstj -sumLastj )) + Math.pow(getMean(),2);

		System.out.println("temp  "+temp+"  autocov "+autoCov);

		return autoCov;
		
	}


	public double getAutoCorrelation(int lag) {
		double autoCorr = (getAutoCovariance(lag))/(variance);

//		if(getVariance() == 0) {
//			autoCorr = 0.0;
//		}
//		else{
//			autoCorr = (getAutoCovariance(lag) / getVariance());
//		}


		return autoCorr;
	}


	/**
	 * @see Counter#report()
	 * TODO Uncomment this function if you have implemented the class!
	 */
	@Override
	public String report() {
		String out  = super.report();
		out += ("\n\tCorrelation/Covariance:\n");
		for(int i = 0; i <= (getNumSamples() < maxLag ? getNumSamples() : maxLag); i++){
			out += ("\t\tlag = " + i + "   " +
					"covariance = " + getAutoCovariance(i) + "   " +
					"correlation = " + getAutoCorrelation(i)+"\n");
		}
		return out;
	}
	/**
	 * @see Counter#csvReport(String)
	 * TODO Uncomment this function if you have implemented the class!
	 */
	@Override
	public void csvReport(String outputdir){
		String content = "";
		for(int i = 0; i <= (getNumSamples() < maxLag ? getNumSamples() : maxLag); i++) {
			content += observedVariable + " (lag=" + i + ")" + ";" + getNumSamples() + ";" + getMean() + ";" +
					getVariance() + ";" + getStdDeviation() + ";" + getCvar() + ";" + getMin() + ";" + getMax() + ";" +
					getAutoCovariance(i) + ";" + getAutoCorrelation(i) + "\n";
		}
		String labels = "#counter ; numSamples ; MEAN; VAR; STD; CVAR; MIN; MAX; COV; CORR\n";
		writeCsv(outputdir, content, labels);
	}


	// public void reset() {  COUNTER
	//  sumPowerOne = 0;
	//  sumPowerTwo = 0;
	//  min = Double.POSITIVE_INFINITY;
	//  max = Double.NEGATIVE_INFINITY;
	//  numSamples = 0;
	// }

	public void reset() {
		super.reset();
	}
}
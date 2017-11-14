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
	private double[] arrVars;
	private int arrayCounter;

	/**
	 * Konstruktor
	 * @param variable
	 * @param maxLag
	 */
	public DiscreteAutocorrelationCounter(String variable, int maxLag) {
		super(variable,"counter type: discrete-time autocorrelation counter");
		this.maxLag = maxLag;
		reset();
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
		reset();
	}


	public long getMaxLag() {
		return maxLag;
	}

	public void setMaxLag(long maxLag) {
		this.maxLag = (int)maxLag;
		reset();
	}


	@Override
	public void count(double x) {
		super.count(x);
		
		arrVars[arrayCounter] = x;  
		arrayCounter++;

		getSumPowerOne();
		getSumPowerTwo();
		}

	public double getAutoCovariance(int lag) {
		double autoCov 	 = 0.0;
		double sumFirstj = 0.0;
		double sumLastj  = 0.0;
		double temp 	 = 0.0;

		//teil 1: summe bis lag
		for(int i = 0; i < lag; i++) {
			sumFirstj += arrVars[i];
		}

		//teil 2: summe bis numSamples -lag
		for(int i = ((int)getNumSamples()-lag); i < getNumSamples(); i++) {
			sumLastj += arrVars[i];
		}
		
		for(int j = lag; j < getNumSamples(); j++ ) {
			temp += arrVars[j]*arrVars[j-lag];  }
		
		autoCov = (1/(double)(getNumSamples()-lag)) * (double)( temp - (getMean() * ( (2 * getSumPowerOne()) - sumFirstj -sumLastj ))) + Math.pow(getMean(),2);

		return autoCov;
		
	}


	public double getAutoCorrelation(int lag) {
		double autoCorr = 0.0;
		
		if(lag <= maxLag) {
			if(getVariance() == 0) {
			autoCorr = 1.0;
			}
			else {
			autoCorr = (getAutoCovariance(lag) / getVariance());
			}
		}
		else {
			throw new IllegalArgumentException("ein lag <= "+ maxLag + " ist noetig!!");
		}
		

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


	@Override
	public void reset() {
		super.reset();
		arrVars = new double[1000]; 
		arrayCounter = 0;

	}
}
package parallelRandomGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class ParallelRandDouble extends Random implements Runnable {


	private static final long serialVersionUID = 1L;
	
	private ArrayList<Double> randDoubleArrList; // ArrayList Containing The Random Numbers
		
	private int cores; // Number Of Core Available
	
	private int threadNum; // Number Of Threads To Use
	
	private int seqLength; // Random Number's Length
	
	private int seqPerThread; // Random Number's Length For Every Thread
		
	private double range; // Range For The Random Numbers
		
	private Thread[] t; // Threads' Array

	

	
	
	/** 
	 * Constructor
	 * @param seed Seed Used To Generate The Random Values
	 * @param seqLength Random Number's Length
	 * @param range Range For The Random Numbers
	 */
	public ParallelRandDouble(long seed, int seqLength, double range) {
			
		super(seed);
		
		this.randDoubleArrList = new ArrayList<Double>();
		
		this.seqLength = seqLength;
		
		this.range = range;
		
		this.cores = Runtime.getRuntime().availableProcessors();
		
		this.threadNum = this.cores;
		
		// If more core than required random number, decrease thread number
		if (this.threadNum > this.seqLength) { 
			
			this.threadNum = this.seqLength;
			
		}		
		
		// Random Number Per Core
		this.seqPerThread = (int)(this.seqLength / this.threadNum);
		
		
		// Initialize Threads Array
		this.t = new Thread[this.threadNum];
		
		// Create And Start The Threads
		for (int i = 0; i < this.threadNum; i++) {
		
			t[i] = new Thread (this, "Rand Double THread " + i);
						
			t[i].run();
		
		}
		
		// Number Of The Remaining Random Values To Calculate
		int remainingNumbers = this.seqLength - (this.seqPerThread*this.threadNum); 
		
		// If There Are Other Numbers To Calculate
		if (remainingNumbers > 0) {
			
			Double[] rem = new Double[remainingNumbers];
			
			rem = this.DoubleRandGen(remainingNumbers, this.range);
			
			// Add The Remaining Random Numbers To The ArrayList
			for (int i = 0; i < rem.length; i++) {
				
				this.randDoubleArrList.add(rem[i]);
				
			}
			
		}
		
		
			
	}
		
	
	
	/** 
	 * Runnable Method For Thread Execution
	 */
	@Override
	public void run() {
			
		Double[] doub = this.DoubleRandGen();
		
		for (int i=0; i<doub.length; i++) {
				
			this.randDoubleArrList.add(doub[i]);
				
		}
			
	}

	
		
	/** 
	 * Calculate Random Doubles With Private Arguments
	 * @return Double Array Of Random Numbers
	 */
	private Double[] DoubleRandGen () {
			
		return this.DoubleRandGen (this.seqPerThread, this.range);
			
	}
	
	
	
	/** 
	 * Calculate Random Doubles With Inputed Arguments
	 * @param seqLength Array's Length
	 * @param range 
	 * @return Doubles'Array Of Random Numbers
	 */
	public Double[] DoubleRandGen (int seqLength, double range) {
		
		Double[] ArrTmp = new Double[seqLength];
		
		for (int i = 0; i < seqLength; i++) {
			
			ArrTmp[i] = range*this.nextDouble();
			
		}
		
		return ArrTmp;
		
	}

	
	
	/** 
	 * Return The Doubles'ArrayList
	 * @return Doubles'ArrayList Containing The Random Numbers
	 */
	public ArrayList<Double> getRandDoubleArrList() {
		
		return this.randDoubleArrList;
	
	}
	
	
	
	/** 
	 * Return The Doubles'Array
	 * @return Doubles'Array Containing The Random Numbers
	 */
	public Double[] getRandDoubleArr() {
		
		Double[] randDoubleArr = new Double[this.randDoubleArrList.size()];
		
		randDoubleArr = this.randDoubleArrList.toArray(((Double[])Array.newInstance(this.randDoubleArrList.get(0).getClass(), this.randDoubleArrList.size())));
				
		return randDoubleArr;
	
	}


	
	
	/**
	 * Outputs A String Containing The Random Doubles
	 */
	@Override
	public String toString() {
		
		return "randDouble [randDoubleArrList=" + randDoubleArrList + "]";
		
	}
	
	

}


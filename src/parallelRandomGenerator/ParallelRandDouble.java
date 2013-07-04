package parallelRandomGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.security.SecureRandom;


public class ParallelRandDouble extends SecureRandom implements Runnable {


	private static final long serialVersionUID = 1L;
	
	private ArrayList<Double> randDoubleArrList; // ArrayList Containing The Random Numbers
		
	private int cores; // Number Of Core Available
	
	private int threadNum; // Number Of Threads To Use
	
	private int seqLength; // Random Number's Length
	
	private int seqPerThread; // Random Number's Length For Every Thread
		
	private double range; // Range For The Random Numbers
		
	private Thread[] t; // Threads' Array

	

	/** 
	 * Constructor Without Seed
	 * @param seqLength Random Number's Length
	 * @param range Range For The Random Numbers
	 */
	public ParallelRandDouble(int seqLength, double range) {
		
		byte[] seed = this.generateSeed(seqLength);
		
		this.setSeed(seed);
		
		this.ParallelRandDoubleInitialise (seqLength, range);
		
	}
	
	
	
	/** 
	 * Constructor With Seed
	 * @param seed Seed Used To Generate The Random Values
	 * @param seqLength Random Number's Length
	 * @param range Range For The Random Numbers
	 */
	public ParallelRandDouble(long seed, int seqLength, double range) {
			
		this.setSeed(seed);
	
		this.ParallelRandDoubleInitialise (seqLength, range);		
		
	}
	
	
	
	/** 
	 * Method that initialise the object
	 * @param seqLength Random Number's Length
	 * @param range Range For The Random Numbers
	 * @throws InterruptedException 
	 */
	private void ParallelRandDoubleInitialise (int seqLength, double range) {
		
		this.randDoubleArrList = new ArrayList<Double>();
		
		this.seqLength = seqLength;
		
		this.range = range;
		
		this.cores = Runtime.getRuntime().availableProcessors();
		
		this.threadNum = 2*this.cores;
		
		// If more core than required random number, decrease thread number
		while (this.threadNum > this.seqLength) { 
			
			this.threadNum--;
			
		}	
		
		// Random Number Per Core
		this.seqPerThread = (int)(this.seqLength / this.threadNum);
		
		
		// Initialize Threads Array
		this.t = new Thread[this.threadNum];
		
		// Create And Start The Threads
		for (int i = 0; i < this.threadNum; i++) {
		
			t[i] = new Thread (this, "Rand Double Thread " + i);
						
			t[i].start();
		
		}
		
		for (int i = 0; i < this.threadNum; i++) {
			
			while (t[i].isAlive()) {
				
				try {
					
					Thread.sleep(50);
				
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				
				}					
				
			}
		
		}	
		
		// Number Of The Remaining Random Values To Calculate
		int remainingNumbers = this.seqLength - (this.seqPerThread*this.threadNum); 
		
		// If There Are Other Numbers To Calculate
		if (remainingNumbers > 0) {
			
			this.DoubleRandGen(remainingNumbers, this.range);			
						
		}		
			
	}
		
	
	
	/** 
	 * Runnable Method For Thread Execution
	 */
	@Override
	public void run() {
			
		this.DoubleRandGen (this.seqPerThread, this.range);
			
	}

	
		
	/** 
	 * Calculate Random Doubles With Inputed Arguments
	 * @param seqLength Array's Length
	 * @param range 
	 * @return Doubles'Array Of Random Numbers
	 */
	public synchronized void DoubleRandGen (int seqLength, double range) {
		
		for (int i = 0; i < seqLength; i++) {
			
			this.randDoubleArrList.add(range*this.nextDouble());
			
		}		
				
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
	 * Return The ArrayList Size
	 * @return Int Contatining The ArrayList Size
	 */
	public int getRandDoubleArrSize() {
		
		return this.randDoubleArrList.size();
		
	}

	

	/**
	 * Outputs A String Containing Used Algorithm
     */
	public String getAlgo () {
		
		return this.getAlgorithm();
		
	}
	
	
	
	/**
	 * Outputs A String Containing The Random Doubles
	 */
	@Override
	public String toString() {
		
		return randDoubleArrList.toString();
		
	}
	
	

}


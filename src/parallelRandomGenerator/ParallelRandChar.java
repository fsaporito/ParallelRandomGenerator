package parallelRandomGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.security.SecureRandom;


public class ParallelRandChar extends SecureRandom implements Runnable {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Character> randCharArrList; // ArrayList Containing The Random Char
		
	private int cores; // Number Of Core Available
	
	private int threadNum; // Number Of Threads To Use
	
	private int seqLength; // Random Number's Length
	
	private int seqPerThread; // Random Number's Length For Every Thread
		
	private int range; // Range For The Random Char
		
	private Thread[] t; // Threads' Array

	
	
	/** 
	 * Constructor Without Seed
	 * @param seqLength Random Number's Length
	 */
	public ParallelRandChar(int seqLength) {
		
		byte[] seed = this.generateSeed(seqLength);
		
		this.setSeed(seed);
		
		this.ParallelRandCharInitialise (seqLength);
		
	}
	
	
	/** 
	 * Constructor With Seed
	 * @param seed Seed Used To Generate The Random Values
	 * @param seqLength Random Number's Length
	 */
	public ParallelRandChar(long seed, int seqLength) {
			
		this.setSeed(seed);
	
		this.ParallelRandCharInitialise (seqLength);		
		
	}
	
	
	
	/**
	 * Method that initialise the object
	 * @param seqLength seqLength Random Number's Length
	 */
	private void ParallelRandCharInitialise(int seqLength) {
		
		this.randCharArrList = new ArrayList<Character>();
		
		this.seqLength = seqLength;
		
		this.range = 127; // Unicode Charset Table
		
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
		
			t[i] = new Thread (this, "Rand Char THread " + i);
						
			t[i].run();
		
		}
		
		// Number Of The Remaining Random Values To Calculate
		int remainingNumbers = this.seqLength - (this.seqPerThread*this.threadNum); 
		
		// If There Are Other Numbers To Calculate
		if (remainingNumbers > 0) {
			
			Character[] rem = new Character[remainingNumbers];
			
			rem = this.CharacterRandGen(remainingNumbers, this.range);
			
			// Add The Remaining Random Numbers To The ArrayList
			for (int i = 0; i < rem.length; i++) {
				
				this.randCharArrList.add(rem[i]);
				
			}
			
		}
		
		
			
	}
		
	
	
	/** 
	 * Runnable Method For Thread Execution
	 */
	@Override
	public void run() {
			
		Character[] charat = this.CharacterRandGen();
		
		for (int i=0; i<charat.length; i++) {
				
			this.randCharArrList.add(charat[i]);
				
		}
			
	}

	
	
	/** 
	 * Calculate Random Chars With Private Arguments
	 * @return Char Array Of Random Numbers
	 */
	public Character[] CharacterRandGen () {
			
		return this.CharacterRandGen(this.seqPerThread, this.range);
			
	}
	
	
	
	/** 
	 * Calculate Random Chars With Inputed Arguments
	 * @param seqLength Array's Length
	 * @param range Range For The Random Numbers
	 * @return Character Array Of Random Numbers
	 */
	public Character[] CharacterRandGen (int seqLength, int range) {
		
		Character[] ArrTmp = new Character[seqPerThread];
		
		int tmpValue = 0;
		
		for (int i = 0; i < seqPerThread; i++) {
			
			do {
				
				tmpValue = this.nextInt(range);
			
			} while (tmpValue < 32);
			
			ArrTmp[i] =(char) tmpValue;
				
		}
		
		return ArrTmp;
			
	}
	
	
	
	
	/** 
	 * Return The Char ArrayList
	 * @return Char ArrayList Containing The Random Numbers
	 */
	public ArrayList<Character> getRandCharArrList() {
		
		return this.randCharArrList;
	
	}
	
	
	
	/** 
	 * Return The Char Array
	 * @return Char Array Containing The Random Numbers
	 */
	public Character[] getRandCharArr() {
		
		Character[] randCharArr = new Character[this.randCharArrList.size()];
		
		randCharArr = this.randCharArrList.toArray(((Character[])Array.newInstance(this.randCharArrList.get(0).getClass(), this.randCharArrList.size())));
				
		return randCharArr;
	
	}


	
	/** 
	 * Print The Requested Random Char As A Unique Word
	 */
	@Override
	public String toString() {
		
		String s = new String();
		
		for (Character el: this.getRandCharArrList()) {
			
			s += el;
		}
		return s;
		
	}
	
	
	/** 
	 * Print The Requested Random Chars One Per Line
	 */
	public String toStringNl() {
		
		String s = new String();
		
		for (Character el: this.getRandCharArrList()) {
			
			s += el;
			
		}
		
		return s;
		
	}

	

}


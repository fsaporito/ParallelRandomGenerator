package parallelRandomGenerator;



public class RandMain {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
	
		int num = 9;
		boolean INTEGER = true;
		boolean DOUBLE = true;
		boolean CHARACTER = true;
		
		/////////////
		/* INTEGER */
		/////////////
		
		if (INTEGER) {
		
			System.out.println ("Generating " + num + " Random Integers:");
		
			ParallelRandInt rand1 = new ParallelRandInt(788, num, 1020);
		
			System.out.println ("Algorithm: " + rand1.getAlgo());		
		
			System.out.println (rand1.toString());
		
			System.out.println ("Predicted Lenght = " + num);
			System.out.println ("Actual Lenght = " + rand1.getRandIntArrSize());
		
			if (num == rand1.getRandIntArrSize()) {
			
				System.out.println ("OK, " + num + " random int generated!\n");
			
			} else {
			
				System.out.println ("Lenghts Different!!!\n");			
			
			}
		
		}
		
		/////////////
		/* DOUBLE */
		/////////////
		
		if (DOUBLE) {
		
			System.out.println ("Generating " + num + " Random Double:");
		
			ParallelRandDouble rand2 = new ParallelRandDouble(1423, num, 10);
		
			System.out.println ("Algorithm: " + rand2.getAlgo());	
		
			System.out.println (rand2.toString());
		
			System.out.println ("Predicted Lenght = " + num);
			System.out.println ("Actual Lenght = " + rand2.getRandDoubleArrSize());
		
			if (num == rand2.getRandDoubleArrSize()) {
			
				System.out.println ("OK, " + num + " random double generated!\n");
			
			} else {
			
				System.out.println ("Lenghts Different!!!\n");			
			
			}
		
		}
		
		
		
		///////////////
		/* CHARACTER */
		///////////////
		
		if (CHARACTER) {
			
			System.out.println ("Generating " + num + " Random Char:");
				
			ParallelRandChar rand3 = new ParallelRandChar(788, num);
		
			System.out.println ("Algorithm: " + rand3.getAlgo());	
		
			System.out.println (rand3.toString());
		
			System.out.println ("Predicted Lenght = " + num);
			System.out.println ("Actual Lenght = " + rand3.getRandCharArrSize());
			
			if (num == rand3.getRandCharArrSize()) {
			
				System.out.println ("OK, " + num + " random double generated!\n");
			
			} else {
			
				System.out.println ("Lenghts Different!!!\n");			
			
			}
		
		}
	
	}

}

package parallelRandomGenerator;



public class RandMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int num = 5;
		
		System.out.println ("Generating " + num + " Random Integers:");
		ParallelRandInt rand1 = new ParallelRandInt(788, num, 1020);
		System.out.println (rand1.toString());
		System.out.println ("OK, " + num + " random int generated!\n");
		
		System.out.println ("Generating " + num + " Random Double:");
		ParallelRandDouble rand2 = new ParallelRandDouble(1423, num, 10);
		System.out.println (rand2.toString());
		System.out.println ("OK, " + num + " random double generated!\n");
		
		System.out.println ("Generating " + num + " Random Char:");
		ParallelRandChar rand3 = new ParallelRandChar(788, num);
		System.out.println (rand3.toString());
		System.out.println ("OK, " + num + " random char generated!\n");
		
	}

}

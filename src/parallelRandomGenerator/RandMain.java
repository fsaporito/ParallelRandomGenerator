package parallelRandomGenerator;



public class RandMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int num = 4;
		
		ParallelRandInt rand1 = new ParallelRandInt(788, num, 1020);
		System.out.println (rand1.toString());
		System.out.println ("OK, " + num + "   random int generated!\n");
		
		ParallelRandDouble rand2 = new ParallelRandDouble(1423, num, 10);
		System.out.println (rand2.toString());
		System.out.println ("OK, " + num + "   random double generated!\n");
		
		ParallelRandChar rand3 = new ParallelRandChar(788, num);
		System.out.println (rand3.toString());
		System.out.println ("\nOK, " + num + "   random char generated!\n");
		
	}

}

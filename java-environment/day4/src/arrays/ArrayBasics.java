package arrays;

public class ArrayBasics {
	public static void main(String[] args) {
		int[] numArray = new int[5];
		String[] strArray = new String[5];
		
		// Alternate array creation method
		// Note that the size of an array cannot be changed
		
		int[] numArrayTwo = new int[] {11,22,33,44,55};
		// Shorter version (However we do not explicitly declare
		// the data type).  This is known as an anonymous array
		int[]mnumArrayThree = {1,2,3,4,5};
		
		System.out.println(numArray[0]);
		System.out.println(numArrayTwo[3]);
	}
}

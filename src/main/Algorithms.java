package main;
public class Algorithms {

	public static long powRec4(long n) {
		if(n == 0) {
			return 1;
		}
		
		int resultMultiplier = 1;
		if(n%2 != 0) {
			resultMultiplier = 2;
		}
		doNothing(n);
		long aux = powRec4(n/2);
		return (long) (resultMultiplier * aux^2);
	}
	
	public static long powRec3(long n) {
		if(n == 0) {
			return 1;
		}
		
		int resultMultiplier = 1;
		if(n%2 != 0) {
			resultMultiplier = 2;
		}
		doNothing(n);
		return resultMultiplier * powRec3(n/2) * powRec3(n/2);
	}
	
	public static long powRec2(long n) {
		if(n == 0) {
			return 1;
		}
		doNothing(n);
		return 2 * powRec2(n-1);
	}
	
	public static long powRec1(long n) {
		if(n == 0) {
			return 1;
		}
		doNothing(n);
		return powRec1(n-1) + powRec1(n-1);
	}
	
	//documentation with conclusion, graph with time of execution, for recursive and linear!
	public static long pow(long n) {
		if(n == 0) {
			return 1;
		}
		long resultNumber = 1;
		for (long i =0; i<n; i++) {
			doNothing(n);
			resultNumber *= 2;
		}
		return resultNumber;
	}
	
	public static long factorialRecursive(long n) {
		if (n == 0) {
			return 1;
		}
		return n * factorialRecursive(n - 1);
	}

	public static long factorial(long n) {
		long factorial = 1;
		for (long i = n; i > 0; i--) {
			factorial *= i;
		}
		return factorial;
	}

	public static void linear(long n) {
		for (long i = 0; i < n; i++) {
			doNothing(i);
		}
	}

	public static void quadratic(long n) {
		for (long i = 0; i < n; i++) {
			for (long j = 0; j < n; j++) {
				doNothing(i);
			}
		}
	}

	public static void cubic(long n) {
		for (long i = 0; i < n; i++) {
			for (long j = 0; j < n; j++) {
				for (long k = 0; k < n; k++) {
					doNothing(k);
				}
			}
		}
	}

	public static void logarithmic(long n) {
		while (n > 0) {
			doNothing(n);
			n /= 2;
		}
	}

	public static void doNothing(long i) {
		System.out.println("Doing nothing at iteration...(" + i + ")");

		long endTime = System.currentTimeMillis() + TestBench.SLEEP_TIME;
		while (System.currentTimeMillis() < endTime) {
			// do nothing
		}
	}

}

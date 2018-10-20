package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

public class TestBench {

	public static final int SLEEP_TIME = 2;
	public static final String CLASS_NAME = "main.Algorithms";

	public static void main(String[] args) {		
/*		testAlgorithm(CLASS_NAME, "pow", 1, 50, 3, "TestPow.txt");
		testAlgorithm(CLASS_NAME, "powRec1", 1, 12, 3, "TestPowRec1.txt");
		testAlgorithm(CLASS_NAME, "powRec2", 1, 50, 3, "TestPowRec2.txt");
		testAlgorithm(CLASS_NAME, "powRec3", 1, 50, 3, "TestPowRec3.txt");
		testAlgorithm(CLASS_NAME, "powRec4", 1, 50, 3, "TestPowRec4.txt");*/
		Graph<String> newGraph = new Graph<String>(6);
		try {
			newGraph.addNode("V1");
			newGraph.addNode("V2");
			newGraph.addNode("V3");
			newGraph.addNode("V4");
			newGraph.addNode("V5");
			newGraph.addNode("V6");
			newGraph.addEdge("V1", "V2", 3);
			newGraph.addEdge("V1", "V3", 4);
			newGraph.addEdge("V1", "V5", 8);
			newGraph.addEdge("V2", "V5", 5);
			newGraph.addEdge("V3", "V5", 3);
			newGraph.addEdge("V5", "V4", 7);
			newGraph.addEdge("V5", "V6", 3);
			newGraph.addEdge("V6", "V4", 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newGraph.print();
		newGraph.initsFloyd();
		newGraph.floyd(newGraph.getSize());
		try {
			System.out.println("V1" + newGraph.printFloydPath("V1", "V6") + "V6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void testAlgorithm(String className, String methodName, int startN, int endN, int samples,
			String outputFileName) {
		List<Integer> averageTimes = new ArrayList<>(endN - startN);
		for (int i = startN; i <= endN; i++) {
			long totalTime = 0;
			for (long loop = 0; loop < samples; loop++) {
				long before = System.currentTimeMillis();
				invokeMethod(className, methodName, i);
				long after = System.currentTimeMillis();
				long timeElapsed = after - before;
				totalTime += timeElapsed;
			}
			long averageTime = totalTime / samples;
			averageTimes.add((int) averageTime);
		}
		saveFile(outputFileName, averageTimes);
	}

	public static void testAlgorithm(String className, String methodName, int startN, int endN, String outputFileName) {
		List<Integer> times = new ArrayList<>(endN - startN);
		for (int i = startN; i <= endN; i++) {
			long before = System.currentTimeMillis();
			invokeMethod(className, methodName, i);
			Algorithms.doNothing(i);
			long after = System.currentTimeMillis();
			long timeElapsed = after - before;
			times.add((int) timeElapsed);
			System.out.println(methodName + "| executed: " + i);
		}
		saveFile(outputFileName, times);
	}
	
	public static void invokeMethod(String className, String methodName, long n) {
		Class<?> theClass;
		Constructor<?> ctor;
		try {
			theClass = Class.forName(className);
			ctor = theClass.getConstructor();
			Object theObject = ctor.newInstance();
			Method theMethod = theObject.getClass().getMethod(methodName, long.class);
			theMethod.invoke(theObject, n);
			System.out.println("invoked " + theMethod.getName() +" ------ " + n + " time.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveFile(String fileName, List<Integer> timesElapsed) {
		File file = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			file = new File(fileName);
			fw = new FileWriter(file);
			pw = new PrintWriter(fw);

			for (int i = 0; i < timesElapsed.size(); i++) {
				pw.println(timesElapsed.get(i));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

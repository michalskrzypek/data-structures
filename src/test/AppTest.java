package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Algorithms;
import main.Graph;

public class AppTest {

	Graph<String> newGraph = null;
	
	private void init() {
		newGraph = new Graph<String>(4);
		try {
			newGraph.addNode("FirstNode");
			newGraph.addNode("SecondNode");
			newGraph.addNode("ThirdNode");
			newGraph.addNode("FourthNode");
			newGraph.addEdge("FirstNode", "FourthNode", 14);
			newGraph.addEdge("FirstNode", "ThirdNode", 13);
			newGraph.addEdge("ThirdNode", "SecondNode", 13);
			newGraph.addEdge("SecondNode", "ThirdNode", 23);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		init();
		/*assertEquals( 1099511627776L, Algorithms.pow(40));
		assertEquals( 4096, Algorithms.powRec1(12));
		assertEquals( 1099511627776L, Algorithms.powRec2(40));
		assertEquals( 1099511627776L, Algorithms.powRec3(40));
		assertEquals( 1099511627776L, Algorithms.powRec4(40));*/
		
		assertEquals("FirstNode - ThirdNode - SecondNode - FourthNode - ", newGraph.traverseGraphDF("FirstNode"));
		
	}

}

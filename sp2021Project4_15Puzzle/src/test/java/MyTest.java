import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void NodeTest() {
		int[] p = new int[] {1, 2, 3, 4};
		Node n = new Node(p);
		assertArrayEquals(p, n.getKey());
		assertEquals(Arrays.toString(p), n.getKey2());
		assertEquals(null, n.getParent());
	}
	
	@Test
	void NodeTest2() {
		int[] p = new int[] {1, 2, 3, 4};
		Node n = new Node(p);
		
		int[] p2 = new int[] {5, 6, 7, 8};
		Node n2 = new Node(p2);
		
		n.setParent(n2);
		assertEquals(n2, n.getParent());
		
		n.setDepth(69);
		assertEquals(69, n.getDepth());
		
		n.set_hValue(420);
		assertEquals(420, n.get_hValue());
	}
	
	@Test
	void DB_Solver2Test() {
		int[] p = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		Node n = new Node(p);
		
		DB_Solver2 s = new DB_Solver2(n, "heuristicOne");
		assertEquals(0, s.findZero(p));
		
		p = new int[] {4, 1, 2, 3, 5, 9, 6, 7, 8, 0, 10, 11, 12, 13, 14, 15};
		assertEquals(9, s.findZero(p));
		
		p = new int[] {4, 1, 2, 3, 5, 9, 6, 7, 8, 10, 14, 11, 12, 13, 15, 0};
		assertEquals(15, s.findZero(p));
		
		p = new int[] {4, 1, 2, 3, 5, 9, 6, 7, 8, 10, 14, 11, 12, 13, 15, 16};
		assertEquals(-1, s.findZero(p));
	}
	
	
	@Test
	void DB_Solver2Test2() {
		int[] p = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		Node n = new Node(p);
		
		DB_Solver2 s = new DB_Solver2(n, "heuristicTwo");
		
		s.moveZero(p, s.findZero(p), 10);
		assertEquals(10, s.findZero(p));
		
		s.moveZero(p, s.findZero(p), 15);
		assertEquals(15, s.findZero(p));
		
		s.moveZero(p, s.findZero(p), 0);
		assertEquals(0, s.findZero(p));
		
		s.moveZero(p, s.findZero(p), 15);
		assertEquals(15, s.findZero(p));
	}
	
	@Test
	void DB_Solver2Test3() {
		int[] p = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		Node n = new Node(p);
		
		DB_Solver2 s = new DB_Solver2(n, "heuristicOne");
		assertTrue(s.goalTest(p));
		
		s.moveZero(p, s.findZero(p), 10);
		assertFalse(s.goalTest(p));
		
		s.moveZero(p, s.findZero(p), 15);
		assertFalse(s.goalTest(p));
		
		s.moveZero(p, s.findZero(p), 0);
		assertFalse(s.goalTest(p));
		
		s.moveZero(p, s.findZero(p), 15);
		assertFalse(s.goalTest(p));
	}
	
	@Test
	void DB_Solver2Test4() {
		int[] p  = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int[] p2 = new int[] {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		
		assertNotEquals(p, p2);
		
		Node n = new Node(p);
		
		DB_Solver2 s = new DB_Solver2(n, "heuristicTwo");
		assertArrayEquals(p, s.copyArray(p));
		
		assertArrayEquals(p2, s.copyArray(p2));
		
		assertNotEquals(p, s.copyArray(p2));
		
		int[] p3 = s.copyArray(p2);
		assertArrayEquals(p3, p2);
		
		p3 = s.copyArray(p);
		assertNotEquals(p2, p3);
	}
	
	@Test
	void A_IDS_A_15solverTest() {
		int[] p = new int[] {4, 1, 2, 3, 5, 9, 6, 7, 8, 0, 10, 11, 12, 13, 14, 15};
		int[] p2 = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		
		Node n = new Node(p);
		ArrayList<Node> solution = A_IDS_A_15solver.A_Star(n, "heuristicTwo");
		
		assertArrayEquals(p, solution.get(0).getKey());
		assertArrayEquals(p2, solution.get(solution.size()-1).getKey());
		
		p = new int[] {5, 14, 4, 1, 7, 15, 12, 2, 13, 0, 6, 10, 8, 9, 3, 11};
		
		n = new Node(p);
		solution = A_IDS_A_15solver.A_Star(n, "heuristicOne");
		
		assertArrayEquals(p, solution.get(0).getKey());
		assertArrayEquals(p2, solution.get(solution.size()-1).getKey());
	}
}

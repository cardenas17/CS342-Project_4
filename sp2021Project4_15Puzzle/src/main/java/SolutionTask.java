import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.concurrent.Task;

public class SolutionTask extends Task<Void> {
	Consumer<ArrayList<Node>> callback;
	String heuristic;
	int[] currentPuzzle;
	
	public SolutionTask(Consumer<ArrayList<Node>> c, String h, int[] p) {
		callback = c;
		heuristic = h;
		currentPuzzle = p;
	}

	@Override
	protected Void call() throws Exception {
		ArrayList<Node> solutionPath = new ArrayList<Node>();
		
		Node startState = new Node(currentPuzzle);
		startState.setDepth(0);
		
		solutionPath = A_IDS_A_15solver.A_Star(startState, heuristic);
		callback.accept(solutionPath);
		
		return null;
	}

}

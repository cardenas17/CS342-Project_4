import java.util.ArrayList;
import java.util.concurrent.*;

public class CallSolution implements Callable<ArrayList<ArrayList<Integer>>>{

	public CallSolution() {
		super();
		System.out.println("CallSolution created");
	}
	@Override
	public ArrayList<ArrayList<Integer>> call() throws Exception {
		while (true) {
			ExecutorService ex = Executors.newFixedThreadPool(5);
			Future<ArrayList<ArrayList<Integer>>> future = ex.submit(new CallSolution());
			try {
				ArrayList<ArrayList<Integer>> answer = future.get();
			} catch(Exception k) {System.out.println("Fail!! From H1");}
			ex.shutdown();
		}
	}

}

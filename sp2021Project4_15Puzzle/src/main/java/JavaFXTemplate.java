import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	Stage ourstage;
	PauseTransition pause;
	GridPane puzzleBoard;
	Button showSolutionB, solveH1B, solveH2B;
	int puzzleCounter = 1;
	ArrayList<GameButton> puzzleList;
	ArrayList<Integer> puzzle1 = new ArrayList<>(Arrays.asList(4, 1, 2, 3, 5, 9, 6, 7, 8, 0, 10, 11, 12, 13, 14, 15));
	ArrayList<Integer> puzzle2 = new ArrayList<>(Arrays.asList(14, 9, 1, 0, 3, 2, 13, 11, 6, 4, 10, 8, 5, 15, 7, 12));
	ArrayList<Integer> puzzle3 = new ArrayList<>(Arrays.asList(8, 5, 14, 13, 0, 15, 6, 9, 2, 1, 7, 12, 4, 11, 10, 3));
	ArrayList<Integer> puzzle4 = new ArrayList<>(Arrays.asList(11, 8, 15, 0, 13, 12, 7, 2, 3, 14, 10, 4, 1, 5, 9, 6));
	ArrayList<Integer> puzzle5 = new ArrayList<>(Arrays.asList(5, 8, 7, 14, 13, 3, 2, 10, 4, 15, 0, 9, 6, 1, 12, 11));
	ArrayList<Integer> puzzle6 = new ArrayList<>(Arrays.asList(2, 1, 4, 15, 8, 7, 0, 13, 12, 6, 14, 5, 10, 3, 11, 9));
	ArrayList<Integer> puzzle7 = new ArrayList<>(Arrays.asList(15, 2, 9, 7, 0, 6, 13, 14, 5, 3, 8, 11, 4, 1, 12, 10));
	ArrayList<Integer> puzzle8 = new ArrayList<>(Arrays.asList(1, 10, 4, 9, 6, 5, 13, 7, 2, 3, 15, 12, 0, 11, 8, 14));
	ArrayList<Integer> puzzle9 = new ArrayList<>(Arrays.asList(8, 15, 0, 12, 9, 14, 3, 13, 2, 5, 4, 6, 7, 1, 10, 11));
	ArrayList<Integer> puzzle10 = new ArrayList<>(Arrays.asList(14, 12, 11, 3, 8, 7, 1, 4, 9, 2, 5, 15, 6, 10, 13, 0));
	ArrayList<Node> solution;
	ExecutorService threads;
	
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		ourstage = primaryStage;
		// TODO Auto-generated method stub
		ourstage.setTitle("Welcome to 15 Puzzle!");
		
		threads = Executors.newFixedThreadPool(4);
		ourstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            	threads.shutdown();
                Platform.exit();
                System.exit(0);
            }
        });
		
		ourstage.setScene(welcomeScene());
		ourstage.show();
		
		pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(e-> {
			ourstage.setScene(gameScene());
			ourstage.show();
		});
		pause.play();
	}
	
	private Scene welcomeScene() {
		Text message = new Text("Welcome to 15-Puzzle!");
		message.setFont(Font.font(35));
		
		
		VBox align = new VBox(message);
		align.setAlignment(Pos.CENTER);
		HBox align2 = new HBox(align);
		align2.setAlignment(Pos.CENTER);
		
		
		return new Scene(new BorderPane(align2), 500, 400);
	}
	
	public ArrayList<Integer> pickPuzzle() {
		if(puzzleCounter >= 11) {
			puzzleCounter = 1;
		}
		if(puzzleCounter == 1) {
			puzzleCounter++;
			return puzzle1;
		} else if(puzzleCounter == 2) {
			puzzleCounter++;
			return puzzle2;
		} else if(puzzleCounter == 3) {
			puzzleCounter++;
			return puzzle3;
		} else if(puzzleCounter == 4) {
			puzzleCounter++;
			return puzzle4;
		} else if(puzzleCounter == 5) {
			puzzleCounter++;
			return puzzle5;
		} else if(puzzleCounter == 6) {
			puzzleCounter++;
			return puzzle6;
		} else if(puzzleCounter == 7) {
			puzzleCounter++;
			return puzzle7;
		} else if(puzzleCounter == 8) {
			puzzleCounter++;
			return puzzle8;
		} else if(puzzleCounter == 9) {
			puzzleCounter++;
			return puzzle9;
		} else if(puzzleCounter == 10) {
			puzzleCounter++;
			return puzzle10;
		}
		return null;
	}
	
	public int[] ArrayListToArray(ArrayList<GameButton> p) {
		int[] temp = new int[p.size()];
		
		for (int i = 0; i < p.size(); i++) {
			temp[i] = p.get(i).num;
		}
		
		return temp;
	}
	
	private void displayState(Node n) {
		int[] puzzleArray = n.getKey();
		
		for(int i =0; i< puzzleArray.length; i++){
			// TODO update arraylist, maybe update colors
//			puzzleList.get(i).setText();
		}
	}
	
	public void displaySolution() {
		for(int i=0; i<solution.size(); i++){
			
			displayState(solution.get(i));
			
		}
	}
	
	private Scene gameScene() {
		puzzleBoard = new GridPane();
		puzzleBoard.setAlignment(Pos.TOP_RIGHT);
		puzzleBoard.setPadding(new Insets(10.0));
		puzzleBoard.setVgap(10.0);
		puzzleBoard.setHgap(10.0);
		
		puzzleList = new ArrayList<GameButton>();
		ArrayList<Integer> currPuzzle = pickPuzzle();
		
		for (int i = 0; i < 16; i++) {
			puzzleList.add(new GameButton(currPuzzle.get(i)));
		}
		
		for (int i = 0; i < 16; i++) {
			if (i < 4) {
				puzzleBoard.add(puzzleList.get(i), i, 0);
			} else if (i < 8) {
				puzzleBoard.add(puzzleList.get(i), i-4, 1);
			} else if (i < 12) {
				puzzleBoard.add(puzzleList.get(i), i-8, 2);
			} else if (i < 16) {
				puzzleBoard.add(puzzleList.get(i), i-12, 3);
			}
		}
		
		BorderPane gameScreen = new BorderPane();
		HBox root = new HBox(puzzleBoard);
		root.setPadding(new Insets(15,15,15,15));
		gameScreen.setCenter(root);
		
		Button newPuzzleB = new Button("New Puzzle");
		newPuzzleB.setOnAction(newPuzzle);
		newPuzzleB.setPadding(new Insets(15,15,15,15));
		
		solveH1B = new Button("Solve with H1");
		solveH1B.setOnAction(H1);
		solveH1B.setPadding(new Insets(15,15,15,15));
		
		solveH2B = new Button("Solve with H2");
		solveH2B.setOnAction(H2);
		solveH2B.setPadding(new Insets(15,15,15,15));
		
		showSolutionB = new Button("Show Solution");
		showSolutionB.setPadding(new Insets(15,15,15,15));
		showSolutionB.setOnAction(solve);
		showSolutionB.setDisable(true);
		
		Button exit = new Button("Exit Game");
		exit.setOnAction(quit);
		exit.setPadding(new Insets(15,15,15,15));
		
		VBox node = new VBox(newPuzzleB, solveH1B, solveH2B, showSolutionB, exit);
		gameScreen.setLeft(node);
		
		return new Scene(gameScreen, 500, 400);
	}
	
	EventHandler<ActionEvent> newPuzzle = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			solution.clear();
			ourstage.setScene(gameScene());
		}
	};
	
	EventHandler<ActionEvent> H1 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			SolutionTask task = new SolutionTask(data->{
				Platform.runLater(()->{
				solution = data;
				showSolutionB.setDisable(false);
				});
			}, "heuristicOne", ArrayListToArray(puzzleList));
			threads.submit(task);
			
			solveH1B.setDisable(true);
			solveH2B.setDisable(true);
			
			solveH1B.setText("Solving with H1");
			solveH2B.setText("̵S̵o̵l̵v̵e̵ ̵w̵i̵t̵h̵ ̵H̵2");
		}
	};
	
	EventHandler<ActionEvent> H2 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			SolutionTask task = new SolutionTask(data->{
				Platform.runLater(()->{
				solution = data;
				showSolutionB.setDisable(false);
				});
			}, "heuristicTwo", ArrayListToArray(puzzleList));
			threads.submit(task);
			
			solveH1B.setDisable(true);
			solveH2B.setDisable(true);
			
			solveH1B.setText("̵S̵o̵l̵v̵e̵ ̵w̵i̵t̵h̵ ̵H̵1");
			solveH2B.setText("Solving with H2");
		}
	};
	
	EventHandler<ActionEvent> solve = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			solveH1B.setDisable(false);
			solveH2B.setDisable(false);
			
			showSolutionB.setDisable(true);
			
			solveH1B.setText("Solve with H1");
			solveH2B.setText("Solve with H2");
			
			solution.clear();
		}
	};
	
	EventHandler<ActionEvent> quit = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			ourstage.close();
		}
	};
	
	
	public boolean checkWin() {
		if (puzzleList.get(0).num != 0) {
			return false;
		}
		
		for (int i = 2; i<16; i++) {
			if (puzzleList.get(i-1).num > puzzleList.get(i).num) {
				return false;
			}
		}
		
		return true;
	}
	
//	public void printArray() {
//		for (int i = 0; i < puzzleList.size(); i++) {
//			System.out.print(puzzleList.get(i).num + " ");
//		}
//		System.out.print("\n");
//	}
	
	public Scene winScene() {
		return new Scene(new BorderPane(), 500, 500);
	}
	//--------------------------------------------------------------------------
	public class GameButton extends Button {
		public Integer num;
		
		public GameButton(int n) {
			super();
			this.num = n;
			
			if (num != 0) {
				this.setText(this.num.toString());
			}
			this.setPrefSize(75, 75);
			this.setOnAction(e-> makeMove());
		}
		
		private void makeMove() {
			int bPos = puzzleList.indexOf(this);
			
			if ((bPos)%4 != 0 && puzzleList.get(bPos - 1).num == 0) {
				swapButton(this, puzzleList.get(bPos - 1));
			} else if ((bPos+1)%4 != 0 && puzzleList.get(bPos + 1).num == 0) {
				swapButton(this, puzzleList.get(bPos + 1));
			} else if (bPos - 4 >= 0 && puzzleList.get(bPos - 4).num == 0) {
				swapButton(this, puzzleList.get(bPos - 4));
			} else if (bPos + 4 <= 15 && puzzleList.get(bPos + 4).num == 0) {
				swapButton(this, puzzleList.get(bPos + 4));
			}
			
		}
		
		private void swapButton(GameButton b1, GameButton b2) {
			if (b2 == null) {
				return;
			}
			
			int tempNum = b1.num;
		
			b1.num = b2.num;
			if (b1.num == 0) {
				b1.setText("");
			} else {
				b1.setText(b1.num.toString());
			}
			
			b2.num = tempNum;
			if (b2.num == 0) {
				b2.setText("");
			} else {
				b2.setText(b2.num.toString());
			}
			// printArray();
			if (checkWin()) {
				ourstage.setScene(winScene());
				ourstage.show();
			}
			
		}
	}  // end of button class
	//--------------------------------------------------------------------------
}

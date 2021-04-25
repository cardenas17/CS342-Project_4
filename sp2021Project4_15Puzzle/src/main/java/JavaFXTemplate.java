import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.application.Application;
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
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	Stage ourstage;
	PauseTransition pause;
	GridPane puzzleBoard;
	Button showSolutionB;
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
	
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		ourstage = primaryStage;
		// TODO Auto-generated method stub
		ourstage.setTitle("Welcome to JavaFX");

		ourstage.setScene(welcomeScene());
		ourstage.show();
		
		pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(e-> {
			ourstage.setScene(gameScene());
			ourstage.show();
		});
		pause.play();
		
//		Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
//		t.start();

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
		
		Button solveH1B = new Button("Solve with H1");
		solveH1B.setOnAction(H1);
		solveH1B.setPadding(new Insets(15,15,15,15));
		
		Button solveH2B = new Button("Solve with H2");
		solveH2B.setOnAction(H2);
		solveH2B.setPadding(new Insets(15,15,15,15));
		
		showSolutionB = new Button("Show Solution");
		showSolutionB.setPadding(new Insets(15,15,15,15));
		
		Button exit = new Button("Exit Game");
		exit.setOnAction(quit);
		exit.setPadding(new Insets(15,15,15,15));
		
		VBox node = new VBox(newPuzzleB, solveH1B, solveH2B, showSolutionB, exit);
		gameScreen.setLeft(node);
		
		return new Scene(gameScreen, 500, 400);
	}
	
	EventHandler<ActionEvent> newPuzzle = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			ourstage.setScene(gameScene());
		}
	};
	
	EventHandler<ActionEvent> quit = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			ourstage.close();
		}
	};
	
	EventHandler<ActionEvent> H1 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			System.out.println("FROM H1");
		}
	};
	
	EventHandler<ActionEvent> H2 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			// add code
			System.out.println("FROM H2");
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

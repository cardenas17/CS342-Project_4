import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.prism.paint.Color;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	Stage ourstage;
	PauseTransition pause;
	GridPane puzzleBoard;
	Button newPuzzleB, showSolutionB, solveH1B, solveH2B;
	int puzzleCounter = 1;
	ArrayList<GameButton> puzzleList;
	ArrayList<Integer> puzzle1 = new ArrayList<>(Arrays.asList(4, 1, 2, 3, 5, 9, 6, 7, 8, 0, 10, 11, 12, 13, 14, 15));
	ArrayList<Integer> puzzle2 = new ArrayList<>(Arrays.asList(4, 0, 1, 2, 5, 9, 7, 3, 12, 8, 6, 10, 13, 14, 15, 11));
	ArrayList<Integer> puzzle3 = new ArrayList<>(Arrays.asList(5, 14, 4, 1, 7, 15, 12, 2, 13, 0, 6, 10, 8, 9, 3, 11));
	ArrayList<Integer> puzzle4 = new ArrayList<>(Arrays.asList(13, 7, 12, 14, 5, 8, 9, 1, 4, 6, 3, 11, 0, 15, 2, 10));
	ArrayList<Integer> puzzle5 = new ArrayList<>(Arrays.asList(6, 0, 2, 9, 5, 7, 15, 1, 8, 13, 12, 3, 4, 11, 14, 10));
	ArrayList<Integer> puzzle6 = new ArrayList<>(Arrays.asList(0, 7, 13, 2, 4, 1, 15, 10, 6, 5, 3, 14, 8, 11, 9, 12));
	ArrayList<Integer> puzzle7 = new ArrayList<>(Arrays.asList(15, 1, 6, 0, 9, 7, 11, 13, 4, 14, 5, 3, 8, 2, 12, 10));
	ArrayList<Integer> puzzle8 = new ArrayList<>(Arrays.asList(15, 14, 8, 6, 11, 3, 5, 7, 9, 2, 13, 10, 0, 4, 12, 1));
	ArrayList<Integer> puzzle9 = new ArrayList<>(Arrays.asList(15, 8, 1, 6, 11, 9, 2, 5, 3, 12, 10, 7, 14, 4, 13, 0));
	ArrayList<Integer> puzzle10 = new ArrayList<>(Arrays.asList(9, 14, 12, 15, 11, 5, 6, 2, 8, 0, 10, 1, 4, 13, 3, 7));
	ArrayList<Integer> puzzle11 = new ArrayList<>(Arrays.asList(9, 2, 14, 15, 3, 12, 0, 8, 7, 13, 4, 5, 6, 10, 1, 11));
	ArrayList<Node> solution = new ArrayList<Node>();
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
		
		threads = Executors.newFixedThreadPool(11);
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
		
		pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(e-> {
			ourstage.setScene(gameScene());
			ourstage.show();
		});
		pause.play();
	}
	
	private Scene welcomeScene() {
		Text message = new Text("Welcome to 15-Puzzle!");
		message.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
	
		
		
		Text names = new Text("By: Angel Cardenas and Kartik Maheshwari");
		names.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		VBox align = new VBox(message, names);
		align.setAlignment(Pos.CENTER);
		HBox align2 = new HBox(align);
		align2.setAlignment(Pos.CENTER);
		
		BorderPane welcome = new BorderPane(align2);
		Image image1 = new Image("welcome2.gif", 550, 500, false, true);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		
		welcome.setBackground(new Background(new BackgroundImage(image1,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
		
		return new Scene(welcome, 550, 500);
	}
	
	public ArrayList<Integer> pickPuzzle() {
		if(puzzleCounter >= 12) {
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
		} else if(puzzleCounter == 11) {
			puzzleCounter++;
			return puzzle11;
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
		
		for(int i = 0; i< puzzleArray.length; i++){
			puzzleList.get(i).updateNum(puzzleArray[i]);
		}
	}
	
	public void displaySolution() {
		PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
		pause2.play();
		AtomicInteger count = new AtomicInteger(1);
		
		pause2.setOnFinished(e-> {
			if (checkWin()) {
				;
			} else if (count.get() <= 10) {
				displayState(solution.get(count.get()));
				count.set(count.get() + 1);;
				pause2.play();
			} else {
				solveH1B.setDisable(false);
				solveH2B.setDisable(false);
				
				solveH1B.setText("Solve with H1");
				solveH2B.setText("Solve with H2");
				
				newPuzzleB.setDisable(false);
			}
		});
		
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
			puzzleList.add(new GameButton(currPuzzle.get(i), i));
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
		HBox gameScreenH = new HBox(puzzleBoard);
		gameScreenH.setAlignment(Pos.CENTER);
		VBox gameScreenV = new VBox(gameScreenH);
		gameScreenV.setAlignment(Pos.CENTER);
		
		newPuzzleB = new Button("New Puzzle");
		newPuzzleB.setOnAction(newPuzzle);
		newPuzzleB.setPadding(new Insets(10,10,10,10));
		newPuzzleB.setMinWidth(110);
		HBox newPuzzleH = new HBox(newPuzzleB);
		newPuzzleH.setPadding(new Insets(15,15,15,15));
		newPuzzleH.setAlignment(Pos.CENTER);
		
		solveH1B = new Button("Solve with H1");
		solveH1B.setOnAction(H1);
		solveH1B.setPadding(new Insets(10,10,10,10));
		solveH1B.setMinWidth(110);
		HBox solveH1H = new HBox(solveH1B);
		solveH1H.setPadding(new Insets(15,15,15,15));
		solveH1H.setAlignment(Pos.CENTER);
		
		solveH2B = new Button("Solve with H2");
		solveH2B.setOnAction(H2);
		solveH2B.setPadding(new Insets(10,10,10,10));
		solveH2B.setMinWidth(110);
		HBox solveH2H = new HBox(solveH2B);
		solveH2H.setPadding(new Insets(15,15,15,15));
		solveH2H.setAlignment(Pos.CENTER);
		
		showSolutionB = new Button("Show Solution");
		showSolutionB.setOnAction(solve);
		showSolutionB.setDisable(true);
		showSolutionB.setPadding(new Insets(10,10,10,10));
		showSolutionB.setMinWidth(110);
		HBox showSolutionH = new HBox(showSolutionB);
		showSolutionH.setPadding(new Insets(15,15,15,15));
		showSolutionH.setAlignment(Pos.CENTER);
		
		Button exit = new Button("Exit Game");
		exit.setOnAction(quit);
		exit.setPadding(new Insets(10,10,10,10));
		exit.setMinWidth(110);
		HBox exitH = new HBox(exit);
		exitH.setPadding(new Insets(15,15,15,15));
		exitH.setAlignment(Pos.CENTER);
		
		Button HTPB = new Button("How to Play?");
		HTPB.setOnAction(displayInst);
		HTPB.setPadding(new Insets(10,10,10,10));
		HTPB.setMinWidth(110);
		HBox HTPH = new HBox(HTPB);
		HTPH.setPadding(new Insets(15,15,15,15));
		HTPH.setAlignment(Pos.CENTER);
		
		VBox node = new VBox(newPuzzleH, solveH1H, solveH2H, showSolutionH, exitH, HTPH);
		node.setAlignment(Pos.CENTER);
		
		HBox everything = new HBox(node, gameScreenV);
		everything.setAlignment(Pos.CENTER);
		gameScreen.setCenter(everything);
		
		Image image1 = new Image("main.gif", 550, 500, false, true);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		
		gameScreen.setBackground(new Background(new BackgroundImage(image1,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
		
		return new Scene(gameScreen, 550, 500);
	}
	
	EventHandler<ActionEvent> displayInst = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			// creates a new Dialog box for displaying instructions
			Dialog<String> directions = new Dialog<String>();
			directions.setTitle("How to play 15 Puzzle");
			directions.setResizable(true);
			directions.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			directions.getDialogPane().setMinWidth(500.0);
			
			// instructions for playing the game
			directions.setContentText("Numbered tiles can be swapped with the blank spot one at a time. \r\n"
					+ "A solved puzzle should have all of the tiles in order from lowest number to highest number with the blank spot in the upper left.\r\n\n"
					+ "You can click either of the Solve Buttons to solve 10 moves in the background.\r\n"
					+ "After it is solved, you can see those 10 moves by clicking the Show Solution Button.\r\n\n"
					+ "H1 uses the number of tiles that are out of place to solve the board.\r\n"
					+ "H2 uses the manhattan distance to solve the board.\r\n\n"
					+ "If you have trouble you can get a new puzzle by clicking New Puzzle or exit by clicking Exit.\r\n");
			
			// Button to close the dialog box
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			directions.getDialogPane().getButtonTypes().add(ok);
			
			// display dialog box
			directions.showAndWait();
		}
	};
	
	EventHandler<ActionEvent> newPuzzle = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			ourstage.setScene(gameScene());
		}
	};
	
	EventHandler<ActionEvent> H1 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			newPuzzleB.setDisable(true);
			
			SolutionTask task = new SolutionTask(data->{
				Platform.runLater(()->{
				solveH1B.setText("Solved with H1");
				solution = data;
				showSolutionB.setDisable(false);
				newPuzzleB.setDisable(false);
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
				solveH2B.setText("Solved with H2");
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
			newPuzzleB.setDisable(true);
			showSolutionB.setDisable(true);
			
			displaySolution();
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
		
		ourstage.setScene(winScene());
		ourstage.show();
		
		return true;
	}
	
//	public void printArray() {
//		for (int i = 0; i < puzzleList.size(); i++) {
//			System.out.print(puzzleList.get(i).num + " ");
//		}
//		System.out.print("\n");
//	}
	
	public Scene winScene() {
		Text message = new Text("Welcome to 15-Puzzle!");
		message.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
		return new Scene(new BorderPane(), 500, 500);
	}
	
	//--------------------------------------------------------------------------
	public class GameButton extends Button {
		public Integer num;
		
		public GameButton(Integer n, int pos) {
			super();

			this.num = n;
			if (n == 0) {
				this.setText("");
				this.setStyle("-fx-background-color: #fbfb6a;");
			} else {
				this.setText(n.toString());
				if (pos == n) {
					this.setStyle("-fx-background-color: #9cfc9c;");
				} else {
					this.setStyle("-fx-background-color: #9cccfc;");
				}
			}
			
			this.setPrefSize(95, 95);
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
			
			b1.updateNum(b2.num);
			b2.updateNum(tempNum);
			
			// printArray();
			checkWin();
		}
		
		public void updateNum(Integer n) {
			this.num = n;
			if (n == 0) {
				this.setText("");
				this.setStyle("-fx-background-color: #fbfb6a;");
			} else {
				this.setText(n.toString());
				if (puzzleList.indexOf(this) == n) {
					this.setStyle("-fx-background-color: #9cfc9c;");
				} else {
					this.setStyle("-fx-background-color: #9cccfc;");
				}
			}
		}
	}  // end of button class
	//--------------------------------------------------------------------------
}

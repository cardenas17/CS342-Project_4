import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.application.Application;
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
	ArrayList<GameButton> puzzleList;
	ArrayList<Integer> puzzle1 = new ArrayList<>(Arrays.asList( 4, 1, 2, 3,
			 													5, 0, 6, 7,
			 													8, 9, 10, 11,
			 													12, 13, 14, 15 ));
	ArrayList<Integer> puzzle2 = new ArrayList<>(Arrays.asList( 4, 1, 2, 3,
																5, 0, 6, 7,
																8, 9, 10, 11,
																12, 13, 14, 15 ));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		
		return new Scene(new BorderPane(align2), 500, 500);
	}
	
	private Scene gameScene() {
		puzzleBoard = new GridPane();
		puzzleBoard.setAlignment(Pos.TOP_LEFT);
		puzzleBoard.setPadding(new Insets(10.0));
		puzzleBoard.setVgap(10.0);
		puzzleBoard.setHgap(10.0);
		
		puzzleList = new ArrayList<GameButton>();
		
		for (int i = 0; i < 16; i++) {
			puzzleList.add(new GameButton(puzzle1.get(i)));
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
		gameScreen.setCenter(puzzleBoard);
		
		return new Scene(gameScreen, 500, 500);
	}
	
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
	
	public Scene winScene() {
		return new Scene(new BorderPane(), 500, 500);
	}
	
	public class GameButton extends Button {
		public Integer num;
		
		public GameButton(int n) {
			super();
			this.num = n;
			
			if (num != 0) {
				this.setText(this.num.toString());
			}
			this.setPrefSize(50, 50);
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
			
			if (checkWin()) {
				ourstage.setScene(winScene());
				ourstage.show();
			}
			
		}
	}
	
}

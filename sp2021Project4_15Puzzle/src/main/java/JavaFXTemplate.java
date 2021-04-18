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
			puzzleList.add(new GameButton(i, puzzle1.get(i)));
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
	
	private class GameButton extends Button {
		public Integer pos, num;
		
		public GameButton(int p, int n) {
			super();
			this.pos = p;
			this.num = n;
			
			if (num != 0) {
				this.setText(this.num.toString());
			}
			this.setPrefSize(50, 50);
			this.setOnAction(e-> makeMove());
		}
		
		private void makeMove() {
			if ((this.pos)%4 != 0 && puzzleList.get(this.pos-1).num == 0) {
				swapButton(this.pos, this.pos-1);
			} else if ((this.pos+1)%4 != 0 && puzzleList.get(this.pos+1).num == 0 ) {
				swapButton(this.pos, this.pos+1);
			}
		}
		
		private void swapButton(Integer pos1, Integer pos2) {
			System.out.println("Before:");
			System.out.print("Num1 = " + puzzleList.get(pos1).num);
			System.out.println("  Pos1 = " + pos1);
			System.out.print("Num2 = " + puzzleList.get(pos2).num);
			System.out.println("  Pos2 = " + pos2);
			
			Integer num1 = puzzleList.get(pos1).num;
			Integer num2 = puzzleList.get(pos2).num;
			
			puzzleList.get(pos1).num = num2;
			System.out.println("Num: " + puzzleList.get(pos1).num);
			puzzleList.get(pos1).pos = pos2;
			puzzleList.get(pos1).setText(num2.toString());
			
			puzzleList.get(pos2).num = num1;
			puzzleList.get(pos2).pos = pos1;
			puzzleList.get(pos2).setText(num1.toString());
			
			System.out.println("After:");
			System.out.print("Num1 = " + puzzleList.get(pos1).num);
			System.out.println("  Pos1 = " + pos1);
			System.out.print("Num2 = " + puzzleList.get(pos2).num);
			System.out.println("  Pos2 = " + pos2 + "\n");
		}
	}
	
}

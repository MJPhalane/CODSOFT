/**
 * 
 */
package codsoft4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizapi.QuizPane;

/**
 * @author mjpha
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		QuizPane pane = new QuizPane();
		Scene scene = new Scene(pane, 550,200);
		primaryStage.setTitle("Quiz App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}

}

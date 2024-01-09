/**
 * 
 */
package codsoft5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author mjpha
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		StudentCourseReg pane = new StudentCourseReg();
		Scene scene = new Scene(pane, 550,200);
		primaryStage.setTitle("Student Grade calculator");
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

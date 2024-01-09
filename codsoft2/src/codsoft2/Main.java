/**
 * 
 */
package codsoft2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author MJPhalane
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GradeCalculator pane = new GradeCalculator();
		Scene scene = new Scene(pane, 550,250);
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









//--module-path "C:\Users\mjpha\OneDrive\Desktop\javafx-sdk-11.0.2\javafx-sdk-11.0.2\lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media

/**
 * 
 */
package codsoft3;

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
		UserAccount account = new UserAccount(1000.0);
		ATMInterface pane = new ATMInterface(primaryStage, account);
		Scene scene = new Scene(pane, 350,150);
		primaryStage.setTitle("ATM Interface");
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

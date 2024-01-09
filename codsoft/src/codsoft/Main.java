package codsoft;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author MjPhalane
 *
 */
public class Main extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RandomGen pane = new RandomGen();
		Scene scene = new Scene(pane, 550,200);
		primaryStage.setTitle("Random Number Generator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

package codsoft;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Random;

/**
 * @author MjPhalane
 *
 */
public class RandomGen extends GridPane {

    private TextField txtUserValue;
    private Label lblUserValue;
    private Button btnSubmit;
    private Button btnQuit;

    private int rand;
    private boolean blnContinue = true;
    private int count = 1;
    private int totalPoints = 0;
    private int roundsPlayed = 0;

    Random gen = new Random();

    public RandomGen() {
        setupGUI();
    }

    public void setupGUI() {
        setHgap(20);
        setVgap(15);
        setAlignment(Pos.CENTER);

        txtUserValue = new TextField();
        lblUserValue = new Label("Enter your guess:");

        btnSubmit = new Button("Submit");
        btnSubmit.setOnAction((e) -> {
            handleGuess();
        });

        btnQuit = new Button("Quit");
        btnQuit.setOnAction((e) -> {
            handleQuit();
        });

        add(lblUserValue, 0, 1);
        add(txtUserValue, 1, 1);
        add(btnSubmit, 0, 2);
        add(btnQuit, 1, 2);
    }

    private void handleGuess() {
        if (blnContinue) {
            if (count == 1) {
                rand = gen.nextInt(100);
            }

            int userGuess = Integer.parseInt(txtUserValue.getText());

            String message;

            if (userGuess == rand) {
                message = "Congratulations! You have guessed correctly!!!";
                blnContinue = false;
                totalPoints += 10; 
                showPopup("Correct Guess", message);
            } else if (userGuess < rand) {
                message = "You have guessed incorrectly, your guess was too low.";
                message += "\nYou have " + (5 - count) + " attempts remaining.";
                showPopup("Incorrect Guess", message);
            } else {
                message = "You have guessed incorrectly, your guess was too high.";
                message += "\nYou have " + (5 - count) + " attempts remaining.";
                showPopup("Incorrect Guess", message);
            }

            count++;

            if (count > 5) {
                endRound();
            }

            System.out.println(message);
        }
    }

    private void handleQuit() {
        boolean confirmQuit = showQuitConfirmation("Quit Confirmation", "Are you sure you want to quit?");
        if (confirmQuit) {
            endRound();
        }
    }

    
    private void endRound() {
        roundsPlayed++;

        if (roundsPlayed < 5) {
            showEndRoundPopup("Round Over", "Round " + roundsPlayed + " completed.\nTotal Points: " + totalPoints + "\n\nThe correct number was: " + rand);
        } else {
            blnContinue = false;
            showEndGamePopup("Game Over", "All rounds completed. Total Points: " + totalPoints);
            showThankYouPopup("Thank You", "Thank you for playing the game!");
        }
    }

    private void showThankYouPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void showEndRoundPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType btnContinue = new ButtonType("Continue");
        ButtonType btnQuit = new ButtonType("Quit");
        alert.getButtonTypes().setAll(btnContinue, btnQuit);

        alert.showAndWait().ifPresent(response -> {
            if (response == btnContinue) {
                count = 1;
                blnContinue = true;
                txtUserValue.clear();
                setupGUI();
            } else if (response == btnQuit) {
                endRound();
            }
        });
    }

    private void showPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showEndGamePopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showQuitConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(alert.getButtonTypes().get(0), alert.getButtonTypes().get(1));

        return alert.showAndWait().filter(response -> response == alert.getButtonTypes().get(0)).isPresent();
    }
}
























//
//private void showEndRoundPopup(String title, String message) {
//    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//    alert.setTitle(title);
//    alert.setHeaderText(null);
//    alert.setContentText(message + "\n\nThe correct number was: " + rand);
//
//    ButtonType btnContinue = new ButtonType("Continue");
//    ButtonType btnQuit = new ButtonType("Quit");
//    alert.getButtonTypes().setAll(btnContinue, btnQuit);
//
//    alert.showAndWait().ifPresent(response -> {
//        if (response == btnContinue) {
//            count = 1;
//            blnContinue = true;
//            txtUserValue.clear();
//            setupGUI(); // Reset the GUI for a new round
//        } else if (response == btnQuit) {
//            endRound();
//        }
//    });
//}





//package codsoft;
//
//import javafx.geometry.Pos;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//
//import java.util.Random;
///**
// * @author MJPhalane
// * A pane class to set up GUI and helper methods for randomly generating numbers.
// */
//public class RandomGen extends GridPane {
//
//	//variables
//    private TextField txtUserValue;
//    private Label lblUserValue;
//
//    private Button btnSubmit;
//    private Button btnQuit;
//
//    private int rand;
//    private boolean blnContinue = true;
//    private int count = 1;
//
//    Random gen = new Random();
//
//    // class constructor
//    public RandomGen() {
//        setupGUI();
//    }
//
//    //Setting up GUI
//    public void setupGUI() {
//        setHgap(20);
//        setVgap(15);
//        setAlignment(Pos.CENTER);
//
//        txtUserValue = new TextField();
//        lblUserValue = new Label("Enter your guess:");
//
//        btnSubmit = new Button("Submit");
//        btnSubmit.setOnAction((e) -> {
//            handleGuess();
//        });
//
//        btnQuit = new Button("Quit");
//        btnQuit.setOnAction((e) -> {
//            handleQuit();
//        });
//
//        add(lblUserValue, 0, 1);
//        add(txtUserValue, 1, 1);
//        add(btnSubmit, 0, 2);
//        add(btnQuit, 1, 2);
//    }
//
//    // Helper method to handle the user's guess
//    private void handleGuess() {
//        if (blnContinue) {
//            if (count == 1) {
//                rand = gen.nextInt(100);
//            }
//
//            int userGuess = Integer.parseInt(txtUserValue.getText());
//
//            String message;
//
//            if (userGuess == rand) {
//                message = "Congratulations! You have guessed correctly!!!";
//                blnContinue = false;
//                showPopup("Correct Guess", message);
//            } else if (userGuess < rand) {
//                message = "You have guessed incorrectly, your guess was too low.";
//                message += "\nYou have " + (5 - count) + " attempts remaining.";
//                showPopup("Incorrect Guess", message);
//            } else {
//                message = "You have guessed incorrectly, your guess was too high.";
//                message += "\nYou have " + (5 - count) + " attempts remaining.";
//                showPopup("Incorrect Guess", message);
//            }
//
//            count++;
//
//            if (count > 5) {
//                message = "Sorry!!! You are out of attempts. The correct number is " + rand + ".";
//                blnContinue = false;
//                showEndGamePopup("Game Over", message);
//            }
//
//            System.out.println(message);
//        }
//    }
//
//    //A helper method to handle the user quitting the game
//    private void handleQuit() {
//        boolean confirmQuit = showQuitConfirmation("Quit Confirmation", "Are you sure you want to quit?");
//        if (confirmQuit) {
//            blnContinue = false;
//            showEndGamePopup("Thank You", "Thank you for playing the game!\n");
//        }
//    }
//    
//    // A helper method for pop ups used withing the class
//    private void showPopup(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    //A helper method for ending the game
//    private void showEndGamePopup(String title, String message) {
//    
//    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//    	    alert.setTitle(title);
//    	    alert.setHeaderText(null);
//    	    alert.setContentText(message);
//
//    	    ButtonType btnContinue = new ButtonType("Continue");
//    	    ButtonType btnQuit = new ButtonType("Quit");
//    	    alert.getButtonTypes().setAll(btnContinue, btnQuit);
//
//    	    alert.showAndWait().ifPresent(response -> {
//    	        if (response == btnContinue) {
//    	          count = 1;
//    	            blnContinue = true;
//    	            txtUserValue.clear(); 
//    	        } else if (response == btnQuit) {
//    	            blnContinue = false;
//    	            showPopup("Thank You", "Thank you for playing the game!\n");
//    	        }
//    	    });
//    }
//
//    private boolean showQuitConfirmation(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.getButtonTypes().setAll(alert.getButtonTypes().get(0), alert.getButtonTypes().get(1)); // OK and Cancel buttons
//
//        return alert.showAndWait().filter(response -> response == alert.getButtonTypes().get(0)).isPresent();
//    }
//}

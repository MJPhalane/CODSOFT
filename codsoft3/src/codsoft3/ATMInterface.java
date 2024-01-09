
/**
 * 
 */
package codsoft3;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author MJPhalane
 *
 */
public class ATMInterface extends GridPane {
	//GUI variables
		private Button btnWithdraw;
	    private Button btnBalance;
	    private Button btnDeposit;
	    private Button btnLogOut;
	    //class variables
	    private UserAccount account;

	    //class constructor
	    public ATMInterface(Stage primaryStage,UserAccount account) {
	        this.account = account;
	        setupWelcomePopup();
	        setupGUI(primaryStage);       
	    }
	    //GUI helper method
	    public void setupGUI(Stage primaryStage) {
	        setHgap(20);
	        setVgap(15);
	        setAlignment(Pos.CENTER);

	        btnWithdraw = new Button("Withdraw");
	        btnWithdraw.setOnAction((e) -> {
	        	showWithdrawalDialog();
	        	});

	        btnBalance = new Button("Balance");
	        btnBalance.setOnAction((e) -> {
	        	checkBalance();
	        	});

	        btnDeposit = new Button("Deposit");
	        btnDeposit.setOnAction((e) -> {
	        	showDepositDialog();
	        	});

	        btnLogOut = new Button("Logout");
	        btnLogOut.setOnAction((e) -> {
	        	showLogoutDialog(primaryStage);
	        });
	        
	        add(btnBalance, 0, 0);
	        add(btnWithdraw, 0, 1);
	        add(btnDeposit, 1, 0);
	        add(btnLogOut, 1, 1);
	    }
	    

	    public void setupWelcomePopup() {
	        Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
	        welcomeAlert.setTitle("Welcome!");
	        welcomeAlert.setHeaderText(null);
	        welcomeAlert.setContentText("Welcome. Enjoy your banking experience!");
	        welcomeAlert.showAndWait();
	    }
	    
	    private void checkBalance() {
	        showAlert("Current balance: R" + account.getBalance());
	    }

	    private void showDepositDialog() {
	        TextInputDialog dialog = new TextInputDialog();
	        dialog.setTitle("Deposit");
	        dialog.setHeaderText("Enter amount to deposit:");

	        Optional<String> result = dialog.showAndWait();
	        result.ifPresent(amount -> {
	            try {
	                double depositAmount = Double.parseDouble(amount);
	                account.deposit(depositAmount);
	            } catch (NumberFormatException ex) {
	                showAlert("Invalid input. Please enter a valid number!!!");
	            }
	        });
	    }
	    
	    private void showWithdrawalDialog() {
	        TextInputDialog dialog = new TextInputDialog();
	        dialog.setTitle("Withdrawal");
	        dialog.setHeaderText("Enter amount to withdraw:");

	        Optional<String> result = dialog.showAndWait();
	        result.ifPresent(amount -> {
	            try {
	                double withdrawalAmount = Double.parseDouble(amount);
	                account.withdraw(withdrawalAmount);
	            } catch (NumberFormatException ex) {
	                showAlert("Invalid input. Please enter a valid number!!!");
	            }
	        });
	    }

	    private void showAlert(String message) {
	        account.showAlert(message);
	    }
	    
	    private void showLogoutDialog(Stage primaryStage) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Logout");
	        alert.setHeaderText("Are you sure you want to logout?");

	        ButtonType confirmButton = new ButtonType("Confirm");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

	        alert.getButtonTypes().setAll(confirmButton, cancelButton);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent() && result.get() == confirmButton) {
	            account.logout();
	            primaryStage.close();
	        }
	    }

}


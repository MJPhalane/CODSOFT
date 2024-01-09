/**
 * 
 */
package codsoft3;
import javafx.scene.control.Alert;

/**
 * @author MJPhalane
 *
 */
public class UserAccount {
	//class variables
	private double balance;
	
	
	/**
	 * @param initalBal
	 * Class constructor
	 */
    public UserAccount(double initialBal) {
        this.balance = initialBal;
    }
//balancemethod
    public double getBalance() {
        return balance;
    }
//withdrawal method
    public void withdraw(double amount) {
        if (amount > balance) {
            showAlert("Insufficient funds!!! Withdrawal failed.");
        } else {
            balance -= amount;
            showAlert("Withdrawal was successful. Current balance: R" + balance);
        }
    }
//deposit method
    public void deposit(double amount) {
        balance += amount;
        showAlert("Deposit was successful. Current balance: R" + balance);
    }
//logout method
    public void logout() {
        showAlert("Thank you for using our ATM. Have a fantastic day!!!");
    }
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transaction");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

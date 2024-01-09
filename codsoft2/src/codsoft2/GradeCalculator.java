package codsoft2;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
/**
 * @author MJPhalane
 *
 */
public class GradeCalculator extends GridPane {

	//class variables
    private TextField txtmark;
    private Label lblmark;

    private int sum = 0;
    private double average;
    private int num;
    private int grade;
    private int remainingsubs;

    private TextArea txtResults; // total marks, average percentage, grade
    private Button btnResults;
    private Button btnAddMark;

    //class constructor
    public GradeCalculator() {
        setupGUI();
    }

    //setting up GUI 
    public void setupGUI() {
        setHgap(20);
        setVgap(15);
        setAlignment(Pos.CENTER);

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of Subjects");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the number of subjects:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            num = Integer.parseInt(value);
            remainingsubs = num;
        });

        txtmark = new TextField();
        lblmark = new Label("Subject Mark");

        txtResults = new TextArea("\r\n");
        btnResults = new Button("Results");
        btnResults.setOnAction((e) -> {
            calcgrade();
        });

        btnAddMark = new Button("Add Mark");

        
        btnAddMark.setOnAction((e) -> {
            sum += getmarks();					//adding the sum of all the marks
            
            remainingsubs--;
            if (remainingsubs ==0) {
                // Disable the button if there are no more subjects
                btnAddMark.setDisable(true);
            }
            
        });

        //adding the controls to the pane
        add(lblmark, 0, 1);
        add(txtmark, 1, 1);
        add(btnAddMark, 0, 2);
        add(btnResults, 1, 2);
        add(txtResults, 0, 3, 2, 1);
    }
    
  //helper method to calculate the grade
    public double calcAv() {
    	if (num > 0) {
            average = (double) sum / num;
            average = Math.round(average * 100.0) / 100.0;
    	}
    	return average;
    }

    //helper method to calculate the grade
    public void calcgrade() {
        
    	calcAv();
    	//calculating the grades based on the average
            if (average <= 20) {
                grade = 1;
            } else if (average > 20 && average <= 30) {
                grade = 2;
            } else if (average > 30 && average <= 40) {
                grade = 3;
            } else if (average > 40 && average <= 60) {
                grade = 4;
            } else if (average > 60 && average <= 70) {
                grade = 5;
            } else if (average > 70 && average <= 80) {
                grade = 6;
            } else if (average > 80 && average <= 100) {
                grade = 7;
            }
            //outputting the results in the text area
            txtResults.appendText("Total Marks: " + sum + "\n" + "Average: " + average + "\n" + "Grade: " + grade + "\n");
        
    }

    
  //a helper method to get the marks from the text box.
    public int getmarks() {
        int subjectMarks = 0;
        try {
            subjectMarks = Integer.parseInt(txtmark.getText());
            txtmark.clear();
            //validating the marks to be from 0 to 100
            if (subjectMarks < 0 || subjectMarks > 100) {
                txtResults.appendText("Marks should be between 0 and 100!!!.\n");
                txtmark.clear();
                subjectMarks = Integer.parseInt(txtmark.getText());
            }
        } catch (NumberFormatException e) {
            txtResults.appendText("Please enter a valid integer for marks.\n");
           txtmark.clear();
            subjectMarks = Integer.parseInt(txtmark.getText());
        }
        txtResults.clear();
        return subjectMarks;
    }

}

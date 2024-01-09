package quizapi;

import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuizPane extends GridPane {

    private int remainingTime = 60;
    private Timeline timer;
    private int userScore = 0;
    private int questionIndex = 0;
    private ComboBox<String> difficultyDropdown;
    private List<QuizQuestion> quizQuestions = new ArrayList<>();

    public QuizPane() {
        setUpGUI();
        setupTimer();
        showWelcomeMessage();
    }

    public void setUpGUI() {
        setHgap(20);
        setVgap(15);
        setAlignment(Pos.CENTER);

        difficultyDropdown = new ComboBox<>();
        difficultyDropdown.getItems().addAll("easy", "medium", "hard");
        difficultyDropdown.setValue("easy");
        add(difficultyDropdown, 0, 0);

        Label timerLabel = new Label("Time remaining: " + remainingTime + " seconds");
        add(timerLabel, 1, 0);

        Button startButton = new Button("Start Quiz");
        startButton.setOnAction(event -> startQuiz());
        add(startButton, 3, 0);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> {
            timer.stop();
            System.exit(0);
        });
        add(quitButton, 2, 0);
    }

    private void setupTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime--;
            updateTimerLabel();
            if (remainingTime <= 0) {
                showResultsAndReset("Time's up! Quiz completed.");
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateTimerLabel() {
        getChildren().forEach(node -> {
            if (node instanceof Label && ((Label) node).getText().startsWith("Time remaining:")) {
                ((Label) node).setText("Time remaining: " + remainingTime + " seconds");
            }
        });
    }

    private void showWelcomeMessage() {
        Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
        welcomeAlert.setTitle("Welcome!");
        welcomeAlert.setHeaderText(null);
        welcomeAlert.setContentText("Welcome to the Quiz App! Get ready for the quiz.");
        welcomeAlert.showAndWait();
    }

    private void startQuiz() {
        String selectedDifficulty = difficultyDropdown.getValue();
        try {
            String quizData = QuizAPI.getQuizData(selectedDifficulty);
              
            quizQuestions = parseQuizData(quizData);

            // Ask the user for the answer to the first question
            askUserForAnswer();

            // Start the timer after fetching the quiz data
            timer.play();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error
        }
    }

    private List<QuizQuestion> parseQuizData(String quizData) {
        Gson gson = new Gson();
        APIResponse apiResponse = gson.fromJson(quizData, APIResponse.class);

        if (apiResponse != null && apiResponse.getResults() != null) {
            return apiResponse.getResults();
        } else {
            // Handle the case where quizQuestions is null
            System.err.println("Error parsing quiz data.");
            return new ArrayList<>(); // Return an empty list to avoid NPE
        }
    }
    private void askUserForAnswer() {
        if (questionIndex < quizQuestions.size()) {
            QuizQuestion currentQuestion = quizQuestions.get(questionIndex);

            // Create a custom dialog with a ChoiceBox for answer choices
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Answer Quiz");
            dialog.setHeaderText("Question " + (questionIndex + 1) + " (" + currentQuestion.getCategory() + "):");
            dialog.setContentText(currentQuestion.getQuestion());

            // Create a ChoiceBox for answer choices
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll(currentQuestion.getAllAnswerChoices());

            // Set the default value to the first answer choice
            choiceBox.setValue(currentQuestion.getAllAnswerChoices().get(0));

            dialog.getDialogPane().setContent(choiceBox);

            // Add OK button to submit the selected answer
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getButtonTypes().setAll(okButton);

            Optional<ButtonType> result = dialog.showAndWait();

            result.ifPresent(buttonType -> {
                if (buttonType == okButton) {
                    String selectedAnswer = choiceBox.getValue();
                    // Check the answer
                    checkAnswer(selectedAnswer, currentQuestion);
                    // Move to the next question
                    questionIndex++;
                    // Ask the user for the next answer
                    askUserForAnswer();
                }
            });
        } else {
            showResultsAndReset("Quiz completed!");
        }
    }

    
    
//    private void askUserForAnswer() {
//        if (questionIndex < quizQuestions.size()) {
//            QuizQuestion currentQuestion = quizQuestions.get(questionIndex);
//
//            // Create a dialog with radio buttons for answer choices
//            Dialog<String> dialog = new Dialog<>();
//            dialog.setTitle("Answer Quiz");
//            dialog.setHeaderText("Question " + (questionIndex + 1) + " (" + currentQuestion.getCategory() + "):");
//            dialog.setContentText(currentQuestion.getQuestion());
//
//    VBox answerBox = new VBox(10);

//            // Create a ToggleGroup for radio buttons
//            ToggleGroup toggleGroup = new ToggleGroup();
//
//            // Add radio buttons for each answer choice
//            for (String answerChoice : currentQuestion.getAllAnswerChoices()) {
//                RadioButton radioButton = new RadioButton(answerChoice);
//                radioButton.setToggleGroup(toggleGroup);
//               // dialog.getDialogPane().getChildren().add(radioButton);
    //			answerBox.getChildren().add(radioButton);
//            }
//			dialog.getDialogPane().setContent(answerBox);

    //       
    //  // Add OK button to submit the selected answer
//            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//            dialog.getDialogPane().getButtonTypes().add(okButton);
//
//            // Handle the OK button action
//            dialog.setResultConverter(buttonType -> {
//                if (buttonType == okButton) {
//                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
//                    if (selectedRadioButton != null) {
//                        return selectedRadioButton.getText();
//                    }
//                }
//                return null; // Null if no answer selected
//            });
//
//            Optional<String> result = dialog.showAndWait();
//
//            result.ifPresent(answer -> {
//                // Check the answer
//                checkAnswer(answer, currentQuestion);
//                // Move to the next question
//                questionIndex++;
//                // Ask the user for the next answer
//                askUserForAnswer();
//            });
//        } else {
//            showResultsAndReset("Quiz completed!");
//        }
//    }

    private void checkAnswer(String userAnswer, QuizQuestion currentQuestion) {
        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            userScore++;
            Alert correctAlert = new Alert(Alert.AlertType.INFORMATION);
            correctAlert.setTitle("Correct!");
            correctAlert.setHeaderText(null);
            correctAlert.setContentText("Your answer is correct!");
            correctAlert.showAndWait();
        } else {
            Alert incorrectAlert = new Alert(Alert.AlertType.INFORMATION);
            incorrectAlert.setTitle("Incorrect!");
            incorrectAlert.setHeaderText(null);
            incorrectAlert.setContentText("Your answer is incorrect. The correct answer is: " + currentQuestion.getCorrectAnswer());
            incorrectAlert.showAndWait();
        }
    }


    private void showResultsAndReset(String message) {
        Alert resultsAlert = new Alert(Alert.AlertType.INFORMATION);
        resultsAlert.setTitle("Quiz Results");
        resultsAlert.setHeaderText(null);
        resultsAlert.setContentText(message + "\nYour score: " + userScore);
        resultsAlert.showAndWait();

        // Reset quiz variables
        remainingTime = 60;
        userScore = 0;
        questionIndex = 0;
        timer.stop();
    }
}
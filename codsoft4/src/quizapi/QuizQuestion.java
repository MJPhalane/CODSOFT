/**
 * 
 */
package quizapi;

import java.util.ArrayList;
import java.util.List;
/**
 * @author mjpha
 *
 */
public class QuizQuestion {
    private final String category;
    private final String question;
    private final String correctAnswer;
    private final List<String> incorrectAnswers;

    public QuizQuestion(String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;

        if (incorrectAnswers != null) {
            this.incorrectAnswers = incorrectAnswers;
        } else {
            this.incorrectAnswers = new ArrayList<>();
        }
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public List<String> getAllAnswerChoices() {
        List<String> allAnswers = new ArrayList<>();
        
        if (incorrectAnswers != null) {
            allAnswers.addAll(incorrectAnswers);
        }

        allAnswers.add(correctAnswer);

        return allAnswers;
    }

}



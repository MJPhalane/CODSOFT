package quizapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {

    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("results")
    private List<QuizQuestion> results;

    public int getResponseCode() {
        return responseCode;
    }

    public List<QuizQuestion> getResults() {
        return results;
    }
}
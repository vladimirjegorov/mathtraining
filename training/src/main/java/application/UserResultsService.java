package application;

import java.io.IOException;

public class UserResultsService {

  UserResultsRepository repository = new UserResultsRepository();

  private int correctCount = 0;
  private int wrongCount = 0;
  private String username = "";

  public void initMathTrainingSession(String username) {
    this.username = username;
    correctCount = 0;
    wrongCount = 0;
  }
  
  public void increaseCorrectCount() {
    correctCount++;
  }

  public void increaseWrongCount() {
    wrongCount++;
  }

  public void persistUserResultAndReset() throws IOException {
    repository.persistUserResults(username, correctCount, wrongCount);
    correctCount = 0;
    wrongCount = 0;
  }

  public int getCorrectCount() {
    return correctCount;
  }

  public int getWrongCount() {
    return wrongCount;
  }
}

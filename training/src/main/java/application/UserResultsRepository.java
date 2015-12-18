package application;

import static java.lang.String.format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class UserResultsRepository {

  public void persistUserResults(String username, int correctCount, int wrongCount) {
    try (PrintWriter out = getPrintWriter()) {
      out.println(format("%s %d %d", username, correctCount, wrongCount));
    } catch (Exception e) {
      System.out.println("Error persisting results");
    }
  }

  PrintWriter getPrintWriter() throws Exception {
    return new PrintWriter(new BufferedWriter(new FileWriter("userResults.txt", true)));
  }
}

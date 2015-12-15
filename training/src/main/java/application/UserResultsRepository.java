package application;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UserResultsRepository {
  
  public void persistUserResults(String username, int correctCount, int wrongCount) throws IOException {
    List<String> lines = asList(format("%s %d %d", username, correctCount, wrongCount));
    Path file = Paths.get("the-file-name.txt");
    Files.write(file, lines, Charset.forName("UTF-8"));
  }
}

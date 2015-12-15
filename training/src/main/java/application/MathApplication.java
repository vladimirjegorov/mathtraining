package application;

import static javafx.fxml.FXMLLoader.load;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MathApplication extends Application {

  static final String MATH_APPLICATION_ROOT_FXML = "/MathApplication.fxml";
  
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = load(getClass().getResource(MATH_APPLICATION_ROOT_FXML));

    Scene scene = new Scene(root);

    stage.setTitle("Math training");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}

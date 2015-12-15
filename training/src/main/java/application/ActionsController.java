package application;

import static application.CalculationService.add;
import static application.CalculationService.divide;
import static application.CalculationService.multuply;
import static application.CalculationService.subtract;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ActionsController {

  @FXML
  Button start;
  @FXML
  Button controlResult;
  @FXML
  Button stop;

  @FXML
  Label arg1;
  @FXML
  Label operationSign;
  @FXML
  Label arg2;
  @FXML
  TextField answerInput;

  @FXML
  CheckBox plusBtn;
  @FXML
  CheckBox minusBtn;
  @FXML
  CheckBox multiplicationBtn;
  @FXML
  CheckBox divisionBtn;

  @FXML
  Label errorLabel;
  
  @FXML
  Button loginBtn;
  @FXML
  TextField usernameInput;

  // needs some dependency injection, but no time to investigate what better
  // suites for JavaFX / JUnit
  private CalculationService calculationService = new CalculationService();
  private UserResultsService userResultsService = new UserResultsService();
  
  // called automatically by JavaFX
  public void initialize() {
    setAllAllOperationCheckboxesDisabled(true);
    stop.setDisable(true);
    controlResult.setDisable(true);
    start.setDisable(true);
    answerInput.setDisable(true);
  }
  
  @FXML
  void handleLoginButtonClick() {
    if ("".equals(usernameInput.getText().trim())) {
      errorLabel.setText("Please enter a username!");
      return;
    }
    
    errorLabel.setText("");
    setAllAllOperationCheckboxesDisabled(false);
    stop.setDisable(false);
    controlResult.setDisable(false);
    start.setDisable(false);
    answerInput.setDisable(false);
    
    usernameInput.setDisable(true);
    loginBtn.setDisable(true);
  }

  @FXML
  void handlePlusButtonClick() {
    operationSign.setText("+");
    setAllAllOperationCheckboxesDisabled(true);
  }

  @FXML
  void handleMinusButtonClick() {
    operationSign.setText("-");
    setAllAllOperationCheckboxesDisabled(true);
  }

  @FXML
  void handleMultiplicationButtonClick() {
    operationSign.setText("x");
    setAllAllOperationCheckboxesDisabled(true);
  }

  @FXML
  void handleDivisionButtonClick() {
    operationSign.setText(":");
    setAllAllOperationCheckboxesDisabled(true);
  }

  private void setAllAllOperationCheckboxesDisabled(boolean disable) {
    plusBtn.setDisable(disable);
    minusBtn.setDisable(disable);
    multiplicationBtn.setDisable(disable);
    divisionBtn.setDisable(disable);
  }

  @FXML
  void handleStartButtonClick() {
    if ("Start".equals(start.getText())) {
      userResultsService.initMathTrainingSession(usernameInput.getText());
    }
    start.setText("Next");
    answerInput.setText("");
//    answerInput.getStyleClass().add("correct-answer");
    answerInput.getStyleClass().removeAll("correct-answer", "wrong-answer");
    
    int valueForArg1 = 0;
    int valueForArg2 = 0;
    
    if (plusBtn.isSelected() || minusBtn.isSelected()) {
      valueForArg1 = calculationService.getnerateNumberForAddAndSubtract();
      valueForArg2 = calculationService.getnerateNumberForAddAndSubtract();
    } else if (multiplicationBtn.isSelected()) {
      valueForArg1 = calculationService.generateNumberForMultiply();
      valueForArg2 = calculationService.generateNumberForMultiply();
    } else if (divisionBtn.isSelected()) {
      valueForArg1 = calculationService.generateNumberForDivide();
      valueForArg2 = calculationService.generateNumberForDivide();
    }
    
    arg1.setText(Integer.toString(valueForArg1));
    arg2.setText(Integer.toString(valueForArg2));
  }

  @FXML
  void handleControlResultButtonClick() {   
    if (answerInput.getText().trim().length() == 0) {
      errorLabel.setText("Please give an answer!");
    } else {
      errorLabel.setText("");
    }
    
    int answer = 0;
    try {
      answer = parseInt(answerInput.getText());
    } catch (NumberFormatException e) {
      errorLabel.setText("Cannot understand answer input");
      return;
    }
    
    int result = 0;
    
    if ("+".equals(operationSign.getText())) {
      result = add(parseInt(arg1.getText()), parseInt(arg2.getText()));
    } else if ("-".equals(operationSign.getText())) {
      result = subtract(parseInt(arg1.getText()), parseInt(arg2.getText()));
    } else if ("x".equals(operationSign.getText())) {
      result = multuply(parseInt(arg1.getText()), parseInt(arg2.getText()));
    } else if (":".equals(operationSign.getText())) {
      result = divide(parseInt(arg1.getText()), parseInt(arg2.getText()));
    }
    
    if (result == answer) {
      answerInput.getStyleClass().add("correct-answer");
      userResultsService.increaseCorrectCount();
    } else {
      answerInput.getStyleClass().add("wrong-answer");
      userResultsService.increaseWrongCount();
    }
    
    errorLabel.setText(Integer.toString(result));
  }

  @FXML
  void handleStopButtonClick() {
    start.setText("Start");
    arg1.setText("");
    arg2.setText("");
    operationSign.setText("");
    
    plusBtn.setDisable(false);
    minusBtn.setDisable(false);
    multiplicationBtn.setDisable(false);
    divisionBtn.setDisable(false);
    
    plusBtn.setSelected(false);
    minusBtn.setSelected(false);
    multiplicationBtn.setSelected(false);
    divisionBtn.setSelected(false);
    
    answerInput.getStyleClass().removeAll("correct-answer", "wrong-answer");
    answerInput.setText("");
    
    try {
      userResultsService.persistUserResultAndReset();
    } catch (IOException e) {
      // in a good way this should be some logger or user error
      out.println("Error persiting scoring results");
    }
  }

}

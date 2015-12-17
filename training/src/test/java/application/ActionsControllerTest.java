package application;

import static application.CalculationService.add;
import static application.CalculationService.divide;
import static application.CalculationService.multuply;
import static application.CalculationService.subtract;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CheckBox.class, Label.class, TextField.class, Button.class, System.class, CalculationService.class })
public class ActionsControllerTest {

  private ActionsController actionsController;

  @Before
  public void setUp() {
    actionsController = new ActionsController();

    actionsController.arg1 = mock(Label.class);
    actionsController.operationSign = mock(Label.class);
    actionsController.arg2 = mock(Label.class);
    actionsController.plusBtn = mock(CheckBox.class);
    actionsController.minusBtn = mock(CheckBox.class);
    actionsController.multiplicationBtn = mock(CheckBox.class);
    actionsController.divisionBtn = mock(CheckBox.class);

    actionsController.errorLabel = mock(Label.class);

    actionsController.loginBtn = mock(Button.class);
    actionsController.usernameInput = mock(TextField.class);
    actionsController.stop = mock(Button.class);
    actionsController.controlResult = mock(Button.class);
    actionsController.start = mock(Button.class);
    actionsController.answerInput = mock(TextField.class);
    
    actionsController.calculationService = mock(CalculationService.class);
  }

  @Test
  public void onlyLoginOptionIsAvailableOnStartup() {
    actionsController.initialize();

    verify(actionsController.plusBtn).setDisable(true);
    verify(actionsController.minusBtn).setDisable(true);
    verify(actionsController.multiplicationBtn).setDisable(true);
    verify(actionsController.divisionBtn).setDisable(true);

    verify(actionsController.stop).setDisable(true);
    verify(actionsController.controlResult).setDisable(true);
    verify(actionsController.start).setDisable(true);
    verify(actionsController.answerInput).setDisable(true);

    verifyZeroInteractions(actionsController.loginBtn, actionsController.usernameInput);
  }

  @Test
  public void userHasNotProvidedUsernameAndUnableToLogIn() {
    when(actionsController.usernameInput.getText()).thenReturn("");

    actionsController.handleLoginButtonClick();

    verify(actionsController.errorLabel).setText("Please enter a username!");
    verifyZeroInteractions(actionsController.plusBtn, actionsController.minusBtn, actionsController.multiplicationBtn,
        actionsController.divisionBtn, actionsController.stop, actionsController.controlResult, actionsController.start,
        actionsController.answerInput, actionsController.loginBtn);

  }

  @Test
  public void userHasProvidedUsernameAndLoggedInSuccessfully() {
    when(actionsController.usernameInput.getText()).thenReturn("username");

    actionsController.handleLoginButtonClick();

    verify(actionsController.errorLabel).setText("");
    verify(actionsController.plusBtn).setDisable(false);
    verify(actionsController.minusBtn).setDisable(false);
    verify(actionsController.multiplicationBtn).setDisable(false);
    verify(actionsController.divisionBtn).setDisable(false);
    
    verify(actionsController.stop).setDisable(false);
    verify(actionsController.controlResult).setDisable(false);
    verify(actionsController.start).setDisable(false);
    verify(actionsController.answerInput).setDisable(false);
    
    verify(actionsController.usernameInput).setDisable(true);
    verify(actionsController.loginBtn).setDisable(true);
  }

  @Test
  public void clickingPlusCheckboxDisablesAllCheckboxAndSetsPlusOperation() {
    actionsController.handlePlusButtonClick();

    verify(actionsController.operationSign).setText("+");
    verify(actionsController.plusBtn).setDisable(true);
    verify(actionsController.minusBtn).setDisable(true);
    verify(actionsController.multiplicationBtn).setDisable(true);
    verify(actionsController.divisionBtn).setDisable(true);
  }

  @Test
  public void clickingMinusCheckboxDisablesAllCheckboxAndSetsMinusOperation() {
    actionsController.handleMinusButtonClick();

    verify(actionsController.operationSign).setText("-");
    verify(actionsController.plusBtn).setDisable(true);
    verify(actionsController.minusBtn).setDisable(true);
    verify(actionsController.multiplicationBtn).setDisable(true);
    verify(actionsController.divisionBtn).setDisable(true);
  }

  @Test
  public void clickingMultiplicationCheckboxDisablesAllCheckboxAndSetsMultiplicationOperation() {
    actionsController.handleMultiplicationButtonClick();

    verify(actionsController.operationSign).setText("x");
    verify(actionsController.plusBtn).setDisable(true);
    verify(actionsController.minusBtn).setDisable(true);
    verify(actionsController.multiplicationBtn).setDisable(true);
    verify(actionsController.divisionBtn).setDisable(true);
  }

  @Test
  public void clickingDivideCheckboxDisablesAllCheckboxAndSetsDivideOperation() {
    actionsController.handleDivisionButtonClick();

    verify(actionsController.operationSign).setText(":");
    verify(actionsController.plusBtn).setDisable(true);
    verify(actionsController.minusBtn).setDisable(true);
    verify(actionsController.multiplicationBtn).setDisable(true);
    verify(actionsController.divisionBtn).setDisable(true);
  }
  
  @Test
  public void clickingStartInitializesTheGameWhenPlusOperationSelected() {
    String username = "username";
    actionsController.userResultsService = mock(UserResultsService.class);
    when(actionsController.usernameInput.getText()).thenReturn(username);
    when(actionsController.start.getText()).thenReturn("Start");
    when(actionsController.plusBtn.isSelected()).thenReturn(true);
    when(actionsController.calculationService.getnerateNumberForAddAndSubtract()).thenReturn(10, 15);
    
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyleList = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyleList);
    
    actionsController.handleStartButtonClick();
    
    verify(actionsController.userResultsService).initMathTrainingSession(username);
    verify(actionsController.start).setText("Next");
    verify(actionsController.answerInput).setText("");
    verify(mockStyleList).removeAll("correct-answer", "wrong-answer");
    verify(actionsController.arg1).setText("10");
    verify(actionsController.arg2).setText("15");
  }
  
  @Test
  public void clickingNextContinuesTheGameWhenMultiplyOperationSelected() {
    String username = "username";
    actionsController.userResultsService = mock(UserResultsService.class);
    when(actionsController.usernameInput.getText()).thenReturn(username);
    when(actionsController.start.getText()).thenReturn("Next");
    when(actionsController.multiplicationBtn.isSelected()).thenReturn(true);
    when(actionsController.calculationService.generateNumberForMultiply()).thenReturn(20, 25);
    
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyleList = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyleList);
    
    actionsController.handleStartButtonClick();
    
    verifyZeroInteractions(actionsController.userResultsService);
    verify(actionsController.start).setText("Next");
    verify(actionsController.answerInput).setText("");
    verify(mockStyleList).removeAll("correct-answer", "wrong-answer");
    verify(actionsController.arg1).setText("20");
    verify(actionsController.arg2).setText("25");
  }
  
  @Test
  public void clickingNextContinuesTheGameWhenDivideOperationSelected() {
    String username = "username";
    actionsController.userResultsService = mock(UserResultsService.class);
    when(actionsController.usernameInput.getText()).thenReturn(username);
    when(actionsController.start.getText()).thenReturn("Next");
    when(actionsController.divisionBtn.isSelected()).thenReturn(true);
    when(actionsController.calculationService.generateNumberForDivide()).thenReturn(5, 10);
    
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyleList = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyleList);
    
    actionsController.handleStartButtonClick();
    
    verifyZeroInteractions(actionsController.userResultsService);
    verify(actionsController.start).setText("Next");
    verify(actionsController.answerInput).setText("");
    verify(mockStyleList).removeAll("correct-answer", "wrong-answer");
    verify(actionsController.arg1).setText("5");
    verify(actionsController.arg2).setText("10");
  }

  @Test
  public void clickingControlResultsShowsErrorWhenAnswerInputIsEmpty() {
    actionsController.userResultsService = mock(UserResultsService.class);
    when(actionsController.answerInput.getText()).thenReturn("");
    
    actionsController.handleControlResultButtonClick();
    
    verify(actionsController.errorLabel).setText("Please give an answer!");
    verifyZeroInteractions(actionsController.userResultsService);
  }
  
  @Test
  public void errorIsDisplayedIfUserInputsAnswerWichIsNotPossibleToUnderstand() {
    actionsController.userResultsService = mock(UserResultsService.class);
    when(actionsController.answerInput.getText()).thenReturn("answer");
    
    actionsController.handleControlResultButtonClick();
    
    verify(actionsController.errorLabel).setText("Cannot understand answer input");
    verifyZeroInteractions(actionsController.userResultsService);
  }
  
  @Test
  public void clickingControlResultsSaysCorrectAnswerOnAddition() throws Exception {
    mockStatic(CalculationService.class);
    
    when(actionsController.answerInput.getText()).thenReturn("35");
    when(actionsController.operationSign.getText()).thenReturn("+");
    when(actionsController.arg1.getText()).thenReturn("17");
    when(actionsController.arg2.getText()).thenReturn("18");
    when(add(17, 18)).thenReturn(35);
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyles = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyles);
    actionsController.userResultsService = mock(UserResultsService.class);

    
    actionsController.handleControlResultButtonClick();
    
    
    verify(mockStyles).add("correct-answer");
    verify(actionsController.userResultsService).increaseCorrectCount();
    verify(actionsController.errorLabel).setText("35");
  }
  
  @Test
  public void clickingControlResultsSaysCorrectAnswerOnSubtract() throws Exception {
    mockStatic(CalculationService.class);
    
    when(actionsController.answerInput.getText()).thenReturn("-1");
    when(actionsController.operationSign.getText()).thenReturn("-");
    when(actionsController.arg1.getText()).thenReturn("17");
    when(actionsController.arg2.getText()).thenReturn("18");
    when(subtract(17, 18)).thenReturn(-1);
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyles = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyles);
    actionsController.userResultsService = mock(UserResultsService.class);

    actionsController.handleControlResultButtonClick();
    
    verify(mockStyles).add("correct-answer");
    verify(actionsController.userResultsService).increaseCorrectCount();
    verify(actionsController.errorLabel).setText("-1");
  }
  
  @Test
  public void clickingControlResultsSaysCorrectAnswerOnMultiply() throws Exception {
    mockStatic(CalculationService.class);
    
    when(actionsController.answerInput.getText()).thenReturn("72");
    when(actionsController.operationSign.getText()).thenReturn("x");
    when(actionsController.arg1.getText()).thenReturn("9");
    when(actionsController.arg2.getText()).thenReturn("8");
    when(multuply(9, 8)).thenReturn(72);
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyles = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyles);
    actionsController.userResultsService = mock(UserResultsService.class);

    actionsController.handleControlResultButtonClick();
    
    verify(mockStyles).add("correct-answer");
    verify(actionsController.userResultsService).increaseCorrectCount();
    verify(actionsController.errorLabel).setText("72");
  }
  
  @Test
  public void clickingControlResultsSaysCorrectAnswerOnDivide() throws Exception {
    mockStatic(CalculationService.class);
    
    when(actionsController.answerInput.getText()).thenReturn("5");
    when(actionsController.operationSign.getText()).thenReturn(":");
    when(actionsController.arg1.getText()).thenReturn("20");
    when(actionsController.arg2.getText()).thenReturn("4");
    when(divide(20, 4)).thenReturn(5);
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyles = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyles);
    actionsController.userResultsService = mock(UserResultsService.class);

    actionsController.handleControlResultButtonClick();
    
    verify(mockStyles).add("correct-answer");
    verify(actionsController.userResultsService).increaseCorrectCount();
    verify(actionsController.errorLabel).setText("5");
  }
  
  @Test
  public void clickingControlResultsSaysWrongAnswerOnAddition() throws Exception {
    mockStatic(CalculationService.class);
    
    when(actionsController.answerInput.getText()).thenReturn("5");
    when(actionsController.operationSign.getText()).thenReturn("+");
    when(actionsController.arg1.getText()).thenReturn("2");
    when(actionsController.arg2.getText()).thenReturn("4");
    when(add(2, 4)).thenReturn(6);
    @SuppressWarnings("unchecked")
    ObservableList<String> mockStyles = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(mockStyles);
    actionsController.userResultsService = mock(UserResultsService.class);

    actionsController.handleControlResultButtonClick();
    
    verify(mockStyles).add("wrong-answer");
    verify(actionsController.userResultsService).increaseWrongCount();
    verify(actionsController.errorLabel).setText("6");
  }
  
  
  
  
  @Test
  public void clickingStopButtonStopsTheGameAndPersistsResult() throws Exception {
    actionsController.userResultsService = mock(UserResultsService.class);
    @SuppressWarnings("unchecked")
    ObservableList<String> listMock = mock(ObservableList.class);
    when(actionsController.answerInput.getStyleClass()).thenReturn(listMock);
    
    actionsController.handleStopButtonClick();
    
    verify(actionsController.start).setText("Start");
    verify(actionsController.arg1).setText("");
    verify(actionsController.arg2).setText("");
    verify(actionsController.operationSign).setText("");
    
    verify(actionsController.plusBtn).setDisable(false);
    verify(actionsController.minusBtn).setDisable(false);
    verify(actionsController.multiplicationBtn).setDisable(false);
    verify(actionsController.divisionBtn).setDisable(false);
    
    verify(listMock).removeAll("correct-answer", "wrong-answer");
    verify(actionsController.answerInput).setText("");
    
    verify(actionsController.userResultsService).persistUserResultAndReset();
  }
  
}

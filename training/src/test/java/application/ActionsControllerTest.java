package application;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
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
@PrepareForTest({ CheckBox.class, Label.class, TextField.class, Button.class })
public class ActionsControllerTest {

  private ActionsController actionsController;

  @Before
  public void setUp() {
    actionsController = new ActionsController();

    actionsController.operationSign = mock(Label.class);
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

}

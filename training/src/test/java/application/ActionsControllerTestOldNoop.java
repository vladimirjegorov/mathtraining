package application;

//import static java.lang.System.setProperty;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;

public class ActionsControllerTestOldNoop {

  // NOOP, all this trying for nothing... almost 2 days... poof... gone...
  
//  @BeforeClass
//  public static void beforeClass() {
//    // Based on
//    // https://github.com/TestFX/TestFX/issues/224#issuecomment-112231820
//    setProperty("java.awt.headless", "true");
//    setProperty("testfx.robot", "glass");
//    setProperty("testfx.headless", "true");
//    setProperty("prism.order", "sw");
//    setProperty("prism.text", "t2k");
//  }
//
//  @Test
//  public void clickingPlusCheckboxDisablesAllCheckboxAndSetsPlusOperation() {
//    clickOnCheckboxAndDoAsserts("#plusBtn", "+");
//  }
//
//  @Test
//  public void clickingMinusCheckboxDisablesAllCheckboxAndSetsMinusOperation2() {
//    clickOnCheckboxAndDoAsserts("#minusBtn", "-");
//  }
//
//  @Test
//  public void clickingMultiplicationCheckboxDisablesAllCheckboxAndSetsMultiplicationOperation() {
//    clickOnCheckboxAndDoAsserts("#multiplicationBtn", "x");
//  }
//
//  @Test
//  public void clickingDivideCheckboxDisablesAllCheckboxAndSetsDivideOperation() {
//    clickOnCheckboxAndDoAsserts("#divisionBtn", ":");
//  }
//
//  private void clickOnCheckboxAndDoAsserts(String btnSelector, String expectedOperationSign) {
//    clickOn(btnSelector);
//
//    Label operationSign = lookup("#operationSign").queryFirst();
//
//    assertNotNull(operationSign);
//    assertEquals(expectedOperationSign, operationSign.getText());
//
//    assertTrue(lookup("#plusBtn").queryFirst().isDisabled());
//    assertTrue(lookup("#minusBtn").queryFirst().isDisabled());
//    assertTrue(lookup("#multiplicationBtn").queryFirst().isDisabled());
//    assertTrue(lookup("#divisionBtn").queryFirst().isDisabled());
//  }
//
//  @Override
//  public void start(Stage stage) throws Exception {
//    new MathApplication().start(stage);    
//  }
}

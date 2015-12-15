package application;

import static application.CalculationService.add;
import static application.CalculationService.divide;
import static application.CalculationService.multuply;
import static application.CalculationService.subtract;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceTest {

  @Mock
  private Random random;

  @InjectMocks
  private CalculationService service;

  @Test
  public void addsTwoNumbers() {
    assertEquals(6, add(2, 4));
    assertEquals(10, add(4, 6));
    assertEquals(15, add(7, 8));
    assertEquals(66, add(6, 60));
  }

  @Test
  public void substractsTwoNumbers() {
    assertEquals(6, subtract(10, 4));
    assertEquals(0, subtract(6, 6));
  }

  @Test
  public void multipliesTwoNumbers() {
    assertEquals(32, multuply(8, 4));
    assertEquals(500, multuply(100, 5));
  }

  @Test
  public void dividesTwoNumbers() {
    assertEquals(2, divide(8, 4));
    assertEquals(20, divide(100, 5));
  }

  @Test
  public void generatesNumberForAddAndSubtract() {
    when(random.nextDouble()).thenReturn(0.0); // min
    assertEquals(10, service.getnerateNumberForAddAndSubtract());

    when(random.nextDouble()).thenReturn(1.0); // max
    assertEquals(51, service.getnerateNumberForAddAndSubtract());

    when(random.nextDouble()).thenReturn(0.5); // middle
    assertEquals(30, service.getnerateNumberForAddAndSubtract());
  }

  @Test
  public void generatesNumberForMultiplication() {
    when(random.nextDouble()).thenReturn(0.0); // min
    assertEquals(1, service.generateNumberForMultiply());

    when(random.nextDouble()).thenReturn(1.0); // max
    assertEquals(26, service.generateNumberForMultiply());

    when(random.nextDouble()).thenReturn(0.5); // middle
    assertEquals(13, service.generateNumberForMultiply());
  }

  @Test
  public void generatesNumberForDivision() {
    when(random.nextDouble()).thenReturn(0.0); // min
    assertEquals(1, service.generateNumberForDivide());

    when(random.nextDouble()).thenReturn(1.0); // max
    assertEquals(11, service.generateNumberForDivide());

    when(random.nextDouble()).thenReturn(0.5); // middle
    assertEquals(6, service.generateNumberForDivide());
  }

}

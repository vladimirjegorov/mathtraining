package application;

import java.util.Random;

public class CalculationService {
 
  Random random = new Random();

  public int getnerateNumberForAddAndSubtract() {
    return 10 + (int) (random.nextDouble() * ((50 - 10) + 1));
  }

  public int generateNumberForMultiply() {
    return 1 + (int) (random.nextDouble() * ((25 - 1) + 1));
  }

  public int generateNumberForDivide() {
    return 1 + (int) (random.nextDouble() * ((10 - 1) + 1));
  }

  public static int add(int a, int b) {
    return a + b;
  }

  public static int subtract(int a, int b) {
    return a - b;
  }

  public static int multuply(int a, int b) {
    return b * a;
  }

  public static int divide(int a, int b) {
    return a / b;
  }
}

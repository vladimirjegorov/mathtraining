package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserResultsServiceTest {
  
  private UserResultsService service;

  @Before
  public void setUp() {
    service = new UserResultsService();
  }
  
  @Test
  public void initialState() {
    assertEquals(0, service.getCorrectCount());
    assertEquals(0, service.getWrongCount());
  }
}

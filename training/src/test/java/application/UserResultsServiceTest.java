package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

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
    assertEquals("", service.getUsername());
  }
  
  @Test
  public void initializesServiceSessionForUsername() {
    String username = "username";
    service.initMathTrainingSession(username);
    
    assertEquals(username, service.getUsername());
    assertEquals(0, service.getCorrectCount());
    assertEquals(0, service.getWrongCount());
  }
  
  @Test
  public void increasesCorrectCount() {
    service.increaseCorrectCount();
    assertEquals(1, service.getCorrectCount());
    service.increaseCorrectCount();
    assertEquals(2, service.getCorrectCount());
  }
  
  @Test
  public void increasesWrongCount() {
    service.increaseWrongCount();
    assertEquals(1, service.getWrongCount());
    service.increaseWrongCount();
    assertEquals(2, service.getWrongCount());
  }
  
  @Test
  public void persistsAndResetsUserResult() throws Exception {
    service.repository = mock(UserResultsRepository.class);
    
    String username = "username";
    service.initMathTrainingSession(username);
    service.increaseCorrectCount();
    service.increaseCorrectCount();
    service.increaseWrongCount();
    
    service.persistUserResultAndReset();
    
    verify(service.repository).persistUserResults(username, 2, 1);
  }
}

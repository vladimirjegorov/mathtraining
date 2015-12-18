package application;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserRepositoryTest {

  private UserResultsRepository repository;

  @Before
  public void setUp() {
    repository = mock(UserResultsRepository.class);
    doCallRealMethod().when(repository).persistUserResults(anyString(), anyInt(), anyInt());
  }

  @Test
  public void storesResultsCorrectly() throws Exception {
    PrintWriter pw = mock(PrintWriter.class);
    when(repository.getPrintWriter()).thenReturn(pw);

    repository.persistUserResults("username", 5, 2);

    verify(pw).println("username 5 2");
  }

}
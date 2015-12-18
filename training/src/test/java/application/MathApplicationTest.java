package application;

import static application.MathApplication.main;
import static javafx.application.Application.launch;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import javafx.application.Application;
import javafx.stage.Stage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Application.class, Stage.class})
public class MathApplicationTest {
  
  @Test
  public void mainMethodLaunchesTheApplication() {
    mockStatic(Application.class);
    
    main(null);
    
    verifyStatic();
    launch();
  }
}

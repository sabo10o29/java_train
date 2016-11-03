package Hello;

import static org.junit.Assert.*;

import org.junit.Test;
import StdCap.StdoutCapture;

public class HelloWorldTest {

	@Test
    public void testMain() {
        String[] expected = new String[] {"HelloWorld"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        HelloWorld.main(new String[0]);
        
        sc.stop();
        sc.assertEquals(expected);
    }

}

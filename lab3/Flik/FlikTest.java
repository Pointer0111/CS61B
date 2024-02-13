import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void test(){
        int i = 128;
        int j = i;
        assertFalse(Flik.isSameNumber(i, j));
    }



}

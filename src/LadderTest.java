import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LadderTest {
Ladder l;
	
	@Before //sets up a ladder object
	public void setUp() {
		l = new Ladder(15,10);
	}

	@Test //tests our getter to make sure it gets the bottom of the ladder
	public final void testGetBottom() {
		assertEquals(l.getBottom(), 15);
	}

	@Test //tests our getter to make sure it gets the top of the ladder
	public final void testGetTop() {
		assertEquals(l.getTop(), 10);
	}

}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SnakeTest {
Snake s;
	@Before //creates a snake object
	public void setUp() throws Exception {
		s = new Snake(10, 8);
	}


	@Test //tests our getter to make sure it gets the head of the snake
	public final void testGetHead() {
		assertEquals(s.getHead(),10);
	}

	@Test //tests our getter to make sure it gets the tail of the snake
	public final void testGetTail() {
		assertEquals(s.getTail(), 8);
	}

}

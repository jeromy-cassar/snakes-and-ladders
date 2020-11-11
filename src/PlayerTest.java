import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player player1;
	
	@Before //sets up a player object to test with 
	public void setup(){
		 
		player1 = new Player( 1, "player1", 1, 0);
	}
	


	@Test //tests our getter to make sure it gets the players number 
	public final void testGetNumber() {
		assertEquals(player1.getNumber(), 1);
	}

	@Test //tests our getter to make sure it gets the players name 
	public final void testGetName() {
		assertEquals(player1.getName(), "player1");
	}

	@Test //tests our getter to make sure it gets the players location 
	public final void testGetLocation() {
		assertEquals(player1.getLocation(), 1);
	}

	@Test //tests our getter to make sure it gets the players number of turns 
	public final void testGetTurn() {
		assertEquals(player1.getTurn(), 0);
	}

	@Test //tests out setter by setting a new location of 10 and then makes sure we can retrieve this updated location with out getter
	public final void testSetLocation() {
		player1.setLocation(10);
		assertEquals(player1.getLocation(),10);
	}
	
	@Test //tests our setter by getting the current number of turns setting the turns by + 1 and then making sure we get the correct value
	public final void testSetTurn() {
		int turn = player1.getTurn();
		player1.setTurns(turn + 1);
		assertEquals(player1.getTurn(), 1);
	}

}

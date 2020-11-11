
public class Player {
//variables for a Player object
private int number;
private String name;
private int location;
private int turn;

//initialises the player constructor
public Player(int number, String name, int location, int turn) {
	this.number = number;
	this.name = name;
	this.location = location; 
	this.turn = turn;
			
}
//returns the players number value
public int getNumber() { return number; } 

//returns the players name
public String getName() { return name; } 

//returns the players location (piece position)
public int getLocation() { return location; } 

//returns the number of turns the player has
public int getTurn() { return turn; } 

//A setter so that the main game logic can update a players location (piece position) 
public void setLocation(int location) {
	this.location = location;
}

//A setter to update the amount of players turns
public void setTurns(int turn) {
	this.turn= turn;
}
}

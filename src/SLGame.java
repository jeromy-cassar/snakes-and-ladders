import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;




public class SLGame
{
	

   Snake snakes[] = new Snake[15];    // array Can store up to 15 Snake objects
   Ladder ladders[] = new Ladder[15]; // array Can store up to 15 Ladder objects
   Player players[] = new Player[4]; //array for storing player data
   int snakesCount = 0;				 
   int laddersCount = 0;	
   int trapsCount = 0;
   int playercount =0;
   int mode =0;
   
   // Creating a Board, dice and a Scanner objects
   Board bd = new Board(); //TODO try implementing user in put here for amount of players
   Dice dice = bd.getDice();
   Scanner scan = new Scanner(System.in);
      
   
  
	 public void setup(Board bd)
	 {
		 int choice = 0; 
	 }

   
	 public void customSetup(Board bd)
	 {
		  int choice = 0;
     
		//  Login.run();  
		  
		  //selecting the game mode
		   mode = getInt("Select Game Type \n1 - Classic\n2 - Collaborative",1,2);
		  
// Ladders
	//Prompts for user input for amount of ladders, and ensures theyre between 1 and 15 our max
	  System.out.print("Enter the number of ladders : "); 
	  laddersCount = getInt("Number of ladders ",1,15);  
	  
	  //loops through and asks for the posistion of ladders ensures that theres nothing else there and that the top is a larger number than the bottom
	  for (int i=0; i<laddersCount; i++)
	  {  boolean ok;
	      int bottom;
	      int top;
	      do { 
	    	  
	  	ok = true;
	  	    bottom = getInt("Enter ladder bottom position ", 2, 99);
		    top = getInt("Enter ladder top position ", bottom, 99);

	         // comparing with all previous ladders to ensure no violation
	         for (int j=0; j<i; j++) // j terminates at i-1 - comparing all the previous ladders
	         {
	             if (ladders[j].getBottom() == bottom || ladders[j].getBottom() == top || 
	  		            ladders[j].getTop() == bottom || ladders[j].getTop() == top)
	             {             ok = false;  // if validations fails user need to re-enter
	  		 plainMessage("Violation. Re-enter values for bottom and tail");
	  		 break;
	             }
	         }
	         // comparing with all snakes to ensure no violation
	         for (int j=0; j<snakesCount; j++)  
	            if (snakes[i].getTail() == bottom || snakes[i].getTail() == top || 
	  		       snakes[i].getHead() == bottom || snakes[i].getHead() == top)       
	           {
	  		    ok = false;   // if validations fails user need to re-enter
	  		    plainMessage("Violation. Re-enter values for bottom and top");
	  		    break;
	            }   
	      } while (ok == false);  // keep repeating until no conflicts!
	      ladders[i] = new Ladder(bottom,top);  // no violation therefore adding to ladders array
	      bd.add(ladders[i]);
	  }
	
// Snakes
	  
	//Prompts for user input for amount of snakes, and ensures they're between 1 and 15 our max
	  System.out.print("Enter the number of snakes : ");
	  snakesCount = getInt("Number of snakes ",1,15);  
	  
	//loops through and asks for the position of ladders ensures that there is nothing else there and that the top is a larger number than the bottom
	  for (int i=0; i<snakesCount; i++)
	  {  boolean ok;
	      int head;
	      int tail;
	      do { 
	  	ok = true;
	  	    head = getInt("Enter snake head position ", 1, 99);
		    tail = getInt("Enter snake tail position ", 1, head);

	         // comparing with all previous snakes to ensure no violation
	         for (int j=0; j<i; j++) // j terminates at i-1 - comparing all the previous snakes
	         {
	             if (snakes[j].getHead() == head || snakes[j].getHead() == tail || 
	  		            snakes[j].getTail() == head || snakes[j].getTail() == tail)
	             {             ok = false;  // if validations fails user need to re-enter
	  		 plainMessage("Violation. Re-enter values for head and tail");
	  		 break;
	             }
	         }
	         // comparing with all ladders to ensure no violation
	         for (int j=0; j<laddersCount; j++)  
	            if (ladders[j].getTop() == head || ladders[j].getTop() == tail || 
	  		       ladders[j].getBottom() == head || ladders[j].getBottom() == tail)       
	           {
	  		    ok = false;   // if validations fails user need to re-enter
	  		    plainMessage("Violation. Re-enter values for head and tail");
	  		    break;
	            }   
	      } while (ok == false);  // keep repeating until no conflicts!
	      snakes[i] = new Snake(head,tail);  // no violation therefore adding to snakes array
	      bd.add(snakes[i]);
	  }
	  
	  
	//Players
	  //prompting user for the amount of players between 1 and 4
	  playercount = getInt("Number of players", 1, 4);
	  int number = 1;
	  //loop through and set up each player with their name, the starting posistion (1) and setting their turns to 0
	  
	  //TODO user validation
	  for (int i =0; i<playercount; i++) {
		  
		  String name;
		  int location;
		  int turn = 0;
		  	do { 
		  		
		  		name = getString("Enter Player " + number + "'s Name: "); 
		  		location = 1;
		  		turn = 1;
		  		
		  		players[i] = new Player(number, name, location,turn);		  		
			  	
		  		
		  	} while (playercount < i);
		  	number = number+1;
	  }
	  
	}
	

  

     
   // A method to print a message and to read an int value in the range specified
   int getInt(String message, int from, int to)
   {
	   String s;
	   int n = 0;
	   boolean invalid;
	   do {
		 invalid = false;
	     s = (String)JOptionPane.showInputDialog(
	      bd,  message,  "Customized Dialog",
	          JOptionPane.PLAIN_MESSAGE);	
	      try {
	         n = Integer.parseInt(s);
	         if (n < from || n > to )
	    	     plainMessage("Re-enter: Input not in range " + from + " to " + to);
	      }
	      catch (NumberFormatException nfe)
	      {
	    	  plainMessage("Re-enter: Invalid number");
	    	  invalid = true;
	      }
	   } while ( invalid || n < from || n > to);
	   return n;
   }

   // A method to print a message and to read a String
   String getString(String message)
   {
	   String s = (String)JOptionPane.showInputDialog(
	      bd,  message,  "Customized Dialog",
	          JOptionPane.PLAIN_MESSAGE);	
	   return s;
   }   

   // A method to print a message
   void plainMessage(String message)
   {
        JOptionPane.showMessageDialog(bd,
		    message, "A prompt message",
		    JOptionPane.PLAIN_MESSAGE);
   }
   
   
   
//The main game logic
   public void control() {
	   //variables
	   boolean winner = false;
	   int i =0;
	   int playernumber = 1;
	   int curLocation = 1;
	   
	  
  
	   //Set up the Game Board with player names
	   bd.clearMessages(); //clears the game board
	   bd.addMessage("Players - Turns Left");

	    
	   //loops through all the players to add them to the board and sets a player to a piece at the same time
	   while (i < playercount)  {
		   bd.addMessage(playernumber + ":  " + players[i].getName() + " - " + (30 - (players[i].getTurn())));
		   
		   //sets their piece
		   bd.setPiece(playernumber, players[i].getLocation());
		   
		   i++;
		   playernumber = playernumber + 1;
		   
	   } ;
   
	   
	   //resetting the values
	   int playernum = 0;
	   i = 0;
   
	  
	 //runs the game while there is no winner
	   
	   
	   //Classic game mode
	   while (winner == false && mode ==1) {

		   //makes sure that we only run for players that actually exist
		   if (playernum > (playercount-1)) {
		   playernum = 0;
		   

		   
	   }
	   
		   
		   //gets the amount of turns the player has 
		   int turns = players[playernum].getTurn();
		   
		   //checks to make sure the player hasn't reached the maximum number of turns and if so results in a game over and asks if they players would like to play again
		   if (turns >30) {
			   plainMessage("Game Over maximum number of turns reached");
			   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
			   if (end ==1) { 
				   bd.clear();
				   customSetup(bd);
				   control();
		   }
			   else {
				   System.exit(0);
			   }
		   }
		   
		 //throws the dice for the player /for debug purposes you can enter a dice value
		   int val = getInt(players[playernum].getName() + ": Enter 0 to throw dice.", 0, 6);
	  
	   
	   
	   //if the dice is rolled we take the players current location and add the value on top to get their new location and move the piece there and update to their new location
	   if (val ==0) {
		   val = dice.roll();
		   curLocation = players[playernum].getLocation() + val;
		   players[playernum].setLocation(curLocation);
		   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   players[playernum].setTurns(turns+1);
	   }
	   //if a number for debug was selected we simply add that value onto their location, update it and move their piece there
	   else {
		   dice.set(val);
		   curLocation = players[playernum].getLocation() + val;
		   players[playernum].setLocation(curLocation);
		   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   players[playernum].setTurns(turns+1);
	   }
	   
	   
	   //if the players location lands on 100 they win the game and we prompt them if they would like to play a new game otherwise we close the program
	   if (curLocation ==100) {
		   curLocation = 100;
		   players[playernum].setLocation(curLocation);
		   plainMessage("Winner!");
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
		   if (end ==1) { 
			   bd.clear();
			   customSetup(bd);
			   control();
			   winner = true;
			   
		   }
		   else {
			   System.exit(0);
		   }
	   }
	   
	   //checking to see if the players location is on a ladder and if so we update their location to the top of the ladder
	   for ( i=0; i< laddersCount;i++ ) {
		   if (curLocation == ladders[i].getBottom()) {
			   curLocation = ladders[i].getTop();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move up the ladder to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
	   }
	   
	   //checking to see if the players location is on the head of a snake, if so we update their position to the bottom of the snake
	   for (i=0; i< snakesCount; i++) {
		   if ( curLocation == snakes[i].getHead()) {
			   curLocation =snakes[i].getTail();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move down the snake to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
	   }
	   
	   //if the player rolls a 6, they get another turn in which case we repeat
	   while (val ==6) {
		   plainMessage("You got a 6! Roll Again");
		   //throws the dice for the player /for debug purposes you can enter a dice value
		   val = getInt(players[playernum].getName() + ": ENter a 0 to throw dice. Enter 1 - 6 for Testing", 0,6);
		  
		   //if the dice is rolled we take the players current location and add the value on top to get their new location and move the piece there and update to their new location
		   if (val == 0) {
			   val = dice.roll();
			   curLocation = players[playernum].getLocation() + val;
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
		   //if a number for debug was selected we simply add that value onto their location, update it and move their piece there
		   else {
			   dice.set(val);
			   curLocation = players[playernum].getLocation() + val;
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": moving to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
		   //if the players location lands on 100 they win the game and we prompt them if they would like to play a new game otherwise we close the program
		   if (curLocation >100) {
			   curLocation -= val;
			   players[playernum].setLocation(curLocation);
			   plainMessage("Must land on 100 to win");
			   bd.setPiece(players[playernum].getNumber(), curLocation);
			   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
			   if (end == 1) {
				   int choice = getInt("Custom Game 1 \n Same Game 2",1,2);
				   if (choice == 1){
				   customSetup(bd);
				   winner = true;
			   }
				   else if(choice ==2) {
					   control();
				   }
		   }
			   else {
				   System.exit(0);;
			   }
	   }
		   //checking to see if the players location is on a ladder and if so we update their location to the top of the ladder 
		   for (i = 0; i < laddersCount; i++) {
			   if(curLocation == ladders[i].getBottom()) {
				   curLocation = ladders[i].getTop();
				   players[playernum].setLocation(curLocation);
				   plainMessage(players[playernum].getName() + ": is about to move up the ladder to " + curLocation);
				   bd.setPiece(players[playernum].getNumber(), curLocation);
			   }
		   }
		   //checking to see if the players location is on the head of a snake, if so we update their position to the bottom of the snake
		   for (i=0; i<snakesCount; i++) {
			   if (curLocation == snakes[i].getHead()) {
				   curLocation = snakes[i].getTail();
				   players[playernum].setLocation(curLocation);
				   plainMessage(players[playernum].getName() + ": is about to move down the snake to " + curLocation);
				   bd.setPiece(players[playernum].getNumber(), curLocation);
			   }
		   }
	   }
	   
	 //Updates the remain Turns a player has left
	   bd.clearMessages();
	   bd.addMessage("Players - Turns Left");
	   int ii = 0;
	   int playernumberr = 1;
	   while (ii < playercount)  {
		   bd.addMessage(playernumberr + ":  " + players[ii].getName() + " - " + (30 - (players[ii].getTurn())));
		   ii++;
		   playernumberr = playernumberr + 1; 
	   } ;
   
	   //increment the playernum so that the next players turn starts correctly
	   playernum = playernum + 1;
	   
   } 
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   //collaborative mode
	   while (winner == false && mode ==2) {

		   //makes sure that we only run for players that actually exist
		   if (playernum > (playercount-1)) {
		   playernum = 0;
		   

		   
	   }
	   
		   
		   //gets the amount of turns the player has 
		   int turns = players[playernum].getTurn();
		   
		   //checks to make sure the player hasn't reached the maximum number of turns and if so results in a game over and asks if they players would like to play again
		   if (turns >30) {
			   plainMessage("Game Over maximum number of turns reached");
			   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
			   if (end ==1) { 
				   bd.clear();
				   customSetup(bd);
				   control();
		   }
			   else {
				   System.exit(0);
			   }
		   }
		   
		 //throws the dice for the player /for debug purposes you can enter a dice value
		   int val = getInt(players[playernum].getName() + ": Enter 0 to throw dice.", 0, 6);
	  
	   
	   //players turn
	   //if the dice is rolled we take the players current location and add the value on top to get their new location and move the piece there and update to their new location
	   if (val ==0) {
		   val = dice.roll();
		   curLocation = players[playernum].getLocation() + val;
		   players[playernum].setLocation(curLocation);
		   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   players[playernum].setTurns(turns+1);
	   }
	   //if a number for debug was selected we simply add that value onto their location, update it and move their piece there
	   else {
		   dice.set(val);
		   curLocation = players[playernum].getLocation() + val;
		   players[playernum].setLocation(curLocation);
		   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   players[playernum].setTurns(turns+1);
	   }
	   
	   
	   //if the players location lands on 100 they win the game and we prompt them if they would like to play a new game otherwise we close the program
	   if (curLocation ==100) {
		   curLocation = 100;
		   players[playernum].setLocation(curLocation);
		   plainMessage("Winner!");
		   bd.setPiece(players[playernum].getNumber(), curLocation);
		   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
		   if (end ==1) { 
			   bd.clear();
			   customSetup(bd);
			   control();
			   winner = true;
			   
		   }
		   else {
			   System.exit(0);
		   }
	   }
	   
	   //checking to see if the players location is on a ladder and if so we update their location to the top of the ladder
	   for ( i=0; i< laddersCount;i++ ) {
		   if (curLocation == ladders[i].getBottom()) {
			   curLocation = ladders[i].getTop();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move up the ladder to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
	   }
	   
	   //checking to see if the players location is on the head of a snake, if so we update their position to the bottom of the snake
	   for (i=0; i< snakesCount; i++) {
		   if ( curLocation == snakes[i].getHead()) {
			   curLocation =snakes[i].getTail();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move down the snake to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
	   }
	   
	   //if the player rolls a 6, they get another turn in which case we repeat
	   while (val ==6) {
		   plainMessage("You got a 6! Roll Again");
		   //throws the dice for the player /for debug purposes you can enter a dice value
		   val = getInt(players[playernum].getName() + ": ENter a 0 to throw dice.", 0,6);
		  
		   //if the dice is rolled we take the players current location and add the value on top to get their new location and move the piece there and update to their new location
		   if (val == 0) {
			   val = dice.roll();
			   curLocation = players[playernum].getLocation() + val;
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": Moving to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
		   //if a number for debug was selected we simply add that value onto their location, update it and move their piece there
		   else {
			   dice.set(val);
			   curLocation = players[playernum].getLocation() + val;
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": moving to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
		   //if the players location lands on 100 they win the game and we prompt them if they would like to play a new game otherwise we close the program
		   if (curLocation >100) {
			   curLocation -= val;
			   players[playernum].setLocation(curLocation);
			   plainMessage("Must land on 100 to win");
			   bd.setPiece(players[playernum].getNumber(), curLocation);
			   int end = getInt("Would you like to play again? Enter 1 for Yes and 0 for No", 0, 1);
			   if (end == 1) {
				   int choice = getInt("Custom Game 1 \n Same Game 2",1,2);
				   if (choice == 1){
				   customSetup(bd);
				   winner = true;
			   }
				   else if(choice ==2) {
					   control();
				   }
		   }
			   else {
				   System.exit(0);;
			   }
	   }
		   //checking to see if the players location is on a ladder and if so we update their location to the top of the ladder 
		   for (i = 0; i < laddersCount; i++) {
			   if(curLocation == ladders[i].getBottom()) {
				   curLocation = ladders[i].getTop();
				   players[playernum].setLocation(curLocation);
				   plainMessage(players[playernum].getName() + ": is about to move up the ladder to " + curLocation);
				   bd.setPiece(players[playernum].getNumber(), curLocation);
			   }
		   }
		   //checking to see if the players location is on the head of a snake, if so we update their position to the bottom of the snake
		   for (i=0; i<snakesCount; i++) {
			   if (curLocation == snakes[i].getHead()) {
				   curLocation = snakes[i].getTail();
				   players[playernum].setLocation(curLocation);
				   plainMessage(players[playernum].getName() + ": is about to move down the snake to " + curLocation);
				   bd.setPiece(players[playernum].getNumber(), curLocation);
			   }
		   }
	   }
	   
	 //Updates the remain Turns a player has left
	   bd.clearMessages();
	   bd.addMessage("Players - Turns Left");
	   int boardnum = 0;
	   int boardPlayer = 1;
	   while (boardnum < playercount)  {
		   bd.addMessage(boardPlayer + ":  " + players[boardnum].getName() + " - " + (30 - (players[boardnum].getTurn())));
		   boardnum++;
		   boardPlayer = boardPlayer + 1; 
	   } ;
   
	   //increment the playernum so that the next players turn starts correctly
	   playernum = playernum + 1;
	   
	   //snakes turn
	   
	   if (playernum >= playercount) {
		   bd.addMessage("Snakes Turn");
		   //picks a random snake from our array
		   //possible due to one snake
		   int random = new Random().nextInt(snakesCount);
		   int newHead =0;
		   int newTail =0;
		   int oldHead =0;
		   int oldTail =0;
		   
		   //rolls the dice to pick the amount of spaces the snake will move
		   val = dice.roll();
		   
		   int entity= (int) Math.random();
		   if (entity%2 ==0) {
			   if (snakes[random].getHead() + val >= 100) {
				   newHead = snakes[random].getHead() - val;
				   oldTail = snakes[random].getTail();  
			   }
			   
			   else {
			  newHead = snakes[random].getHead() + val;
			   oldTail = snakes[random].getTail();  
		   }
		   }
		   
		  
		   
		   else {
			   if (snakes[random].getTail() + val <=0) {
			   newTail = snakes[random].getTail() - val;
				  oldHead = snakes[random].getHead();
			   } else {
				   newTail = snakes[random].getTail() + val;
					  oldHead = snakes[random].getHead();
			   }
		   }
		   

		   
		   bd.clearSnakes();
		   
		   
		   
		   //Redraws our snakes for an updated version
		   for (i=0;i<(snakes.length - (15 - snakesCount));i++) {
			  // System.out.println(i + " " + snake[i].getHead() + " "+ snake.length);
			   	oldHead = snakes[i].getHead();
				oldTail = snakes[i].getTail();
				
				//if the snake does not equal our random number we place it back exactly where it is
				if (i != random) {
					bd.add(snakes[i]);
					 //if our snake does equal the the random number we update its location
				}else if (i== random) {
					//if the target snake is remainder is equal to zero we move its head
					if (entity%2 ==0) {
						plainMessage("Snake is moving its head from " + snakes[i].getHead() + " to" + newHead);
						snakes[i].resetSnake(newHead, oldTail);
						bd.add(snakes[i]);
					}
					//if the target snake is remainder is not equal to zero we move its tail
					else {
						snakes[i].resetSnake(oldHead, newTail);
						bd.add(snakes[i]);
					}
				}
				
				
				
		   }
		   
 
	   }
	   
	   for (i=0; i< snakesCount; i++) {
		   if ( curLocation == snakes[i].getHead()) {
			   curLocation =snakes[i].getTail();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move down the snake to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
		   
	   }
	   
	 //Updates the remain Turns a player has left
	   bd.clearMessages();
	   bd.addMessage("Players - Turns Left");
	   int ii = 0;
	   int playernumberr = 1;
	   while (ii < playercount)  {
		   bd.addMessage("Player " + playernumberr + ":  " + players[ii].getName() + " - " + (30 - (players[ii].getTurn())));
		   ii++;
		   playernumberr = playernumberr + 1; 
	   } ;
	   
	   //Ladders turn
	   if (playernum >= playercount) {
		   bd.addMessage("Ladders Turn");
		   //picks a random snake from our array
		   //possible due to one snake
		   int random = new Random().nextInt(laddersCount);
		   int newTop =0;
		   int newBottom =0;
		   int oldTop =0;
		   int oldBottom =0;
		   
		   //rolls the dice to pick the amount of spaces the snake will move
		   val = dice.roll();
		   
		   int entity= (int) Math.random();
		   if (entity%2 ==0) {
			   if (ladders[random].getTop() + val >= 100) {
				   newTop = ladders[random].getTop() - val;
				   oldBottom = ladders[random].getBottom();  
			   }
			   else {
			  newTop = ladders[random].getTop() + val;
			   oldBottom = ladders[random].getBottom();  
			   }
		   }
		   
		  
		   
		   else {
			   newBottom = ladders[random].getTop() + val;
				  oldTop = ladders[random].getBottom();
		   }
		   

		   bd.clearLadders();
		   
		   
		   
		   //Redraws our snakes for an updated version
		   for (i=0;i<(ladders.length - (15 - laddersCount));i++) {
			   	oldTop = ladders[i].getTop();
				oldBottom = ladders[i].getBottom();
				
				//if the snake does not equal our random number we place it back exactly where it is
				if (i != random) {
					 //if our snake does equal the the random number we update its location
					bd.add(ladders[i]);
				}else if (i== random) {
					//if the target snake is remainder is equal to zero we move its head
					if (entity%2 ==0) {
						plainMessage("Ladders is moving its Top from " + ladders[i].getTop() + " to" + newTop);
						ladders[i].resetLadder(newTop, oldBottom);
						bd.add(ladders[i]);
						
					}
					//if the target snake is remainder is not equal to zero we move its tail
					else {
						ladders[i].resetLadder(oldTop, newBottom);
						bd.add(ladders[i]);
					}
				}
				
				
				
		   }
		   
 
		   
	   }
	   //checks the ladders new location and if a player is on there 
	   for (i = 0; i < laddersCount; i++) {
		   if(curLocation == ladders[i].getBottom()) {
			   curLocation = ladders[i].getTop();
			   players[playernum].setLocation(curLocation);
			   plainMessage(players[playernum].getName() + ": is about to move up the ladder to " + curLocation);
			   bd.setPiece(players[playernum].getNumber(), curLocation);
		   }
	   }
	   
	 //Updates the remain Turns a player has left
	   bd.clearMessages();
	   bd.addMessage("Players - Turns Left");
	   ii = 0;
	    playernumberr = 1;
	   while (ii < playercount)  {
		   bd.addMessage("Player " + playernumberr + ":  " + players[ii].getName() + " - " + (30 - (players[ii].getTurn())));
		   ii++;
		   playernumberr = playernumberr + 1; 
	   } ;
	   
	   
   }
   

      
     
   }
   
   private Object SLGame() {
	
	return null;
}
   
   
   public void menu()
   {
	  int option;
	  setup(bd);
	  do {
	  option = getInt("Play   1\nExit   2",1,2);
	  if (option == 1){
		  customSetup(bd);
		  control();}
	  else if (option == 2)
	      System.exit(0);
   }while (option != 3);
   }
   

// The very first method to be called
   // This method constructs a SLGame object and calls its control method 
   public static void main(String args[])
   {
       SLGame slg = new SLGame();
       slg.menu();
   }


}
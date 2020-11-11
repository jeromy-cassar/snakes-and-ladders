public class Snake
{
	//variables for a snake
   private int head;
   private int tail;
   
   
   //constructor for the snake object
   public Snake(int head, int tail)
   {
       this.head = head;
       this.tail = tail;
   }
   
   public void resetSnake(int head, int tail) {
		this.head = head;
		this.tail = tail;
	}

   
   
   //returns the value for the snakes head
   public int getHead() { return head; }
   
   //returns the value for the snakes tail
   public int getTail() { return tail; }


}

public class Ladder
{
	//variables for the ladder object
   private int bottom;
   private int top;
   
   //Constructor for the ladder
   public Ladder(int bottom, int top)
   {
       this.bottom = bottom;
       this.top = top;
   }
   //returns the value for the bottom of a ladder
   public int getBottom() { return bottom; }
   
   //returns the top value for a ladder
   public int getTop() { return top; } 


public void resetLadder(int top, int bottom) {
		this.top = top;
		this.bottom = bottom;
		}
}
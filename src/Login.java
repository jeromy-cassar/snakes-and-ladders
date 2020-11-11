import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner; // I use scanner because it's command line.

import javax.swing.JOptionPane;

public class Login {

	public static void run() {
    Scanner scan = null;
	try {
		scan = new Scanner (new File("users.txt"));
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    Scanner keyboard = new Scanner (System.in);
    String Users[] = new String[50];; 
    
    int i = 0;
    while (scan.hasNextLine() ) {
    	Users[i] = scan.nextLine();
    	i++;
    }

    
   String inpUser = JOptionPane.showInputDialog("Please enter your UserName");
   String inpPass = JOptionPane.showInputDialog("Please enter your Password");

   
  
    if (Arrays.asList(Users).indexOf(inpUser) == (Arrays.asList(Users).indexOf(inpPass)-1)) {
        	JOptionPane.showMessageDialog(null, "Successfull Login");
    } else {
    		JOptionPane.showMessageDialog(null, "Incorrect Login");
        System.exit(0);
    }
}
} 
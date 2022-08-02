import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;


public class Consumer
{
    public static void main(String args[])throws IOException, InterruptedException
    {
        Socket s=new Socket("localhost",6666);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
        //Streams
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.println("CONSUME");
        while(true){
        	String check_produce = JOptionPane.showInputDialog( "Do you Want to consume?" );
        	
        	//System.out.println("Want to consume?");
        	//String check_produce=sc.readLine();
        	if(check_produce==null) {
        	s.close();
        	}
        	
        	if(check_produce.equalsIgnoreCase("Yes")){
        		 out.println("CONSUME");
        		
        		String item=in.readLine();
            	
        		//System.out.println("Consumer consumed - "  + item);
        		JOptionPane.showMessageDialog( null, "Consumer consumed - " + item,
   			       "Consumer", JOptionPane.PLAIN_MESSAGE );
        	}
        	 
        	
        }
        
    }
}
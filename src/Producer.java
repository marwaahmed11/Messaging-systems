import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Producer
{
    public static void main(String args[])throws IOException, InterruptedException
    {
        Socket s=new Socket("localhost",6666);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
        //Input Output Streams
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.println("p");
        while(true){
        	
        	//System.out.println("Want to produce?");
        	String check_produce = JOptionPane.showInputDialog( "Do you Want to produce?" );
        	//String check_produce=sc.readLine();
        	if(check_produce==null) {
        	s.close();
        	}
        	
        	if(check_produce.equalsIgnoreCase("Yes")){
        		
        		//String item=sc.readLine();
        		String item = JOptionPane.showInputDialog( "Enter item to produce" );
        		
        		out.println(item);
        		
        		in.readLine();
        		System.out.println("Producer produced - "  + item);
        		JOptionPane.showMessageDialog( null, "Producer produced - " + item,
           				 "producer", JOptionPane.PLAIN_MESSAGE );
        		}
        	
        	
        }
        
    }
}
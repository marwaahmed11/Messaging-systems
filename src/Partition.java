import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Partition {
	
	static File filePath=null;
	
	public Partition()
	{
		try {
			filePath= File.createTempFile("out_", ".txt", new File("C:\\Users\\musta\\eclipse-workspace\\20185003_20186028_20186029_Ass1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void storeMsg(String item) 
	{
		try {
			 
			String str = item;
		    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));  
		    writer.append(str);
		    writer.close();
	    	} 
	     catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		            }
		
	}
	public static ArrayList<String> getmesg() throws FileNotFoundException//String p
	{
		ArrayList<String> msgs = new ArrayList<String>();	
		File myObj = new File("C:\\Users\\musta\\eclipse-workspace\\20185003_20186028_20186029_Ass1\\"+filePath.getName());
		Scanner myReader = new Scanner(myObj);
	
		while(myReader.hasNextLine()) {
			
			String data = myReader.nextLine();
			msgs.add(data);
			
		}
		return msgs;
		
			
	}
	
	
	  public static void main(String args[])throws IOException, InterruptedException
	    {
		  Partition p=new Partition();
		     Socket s=new Socket("localhost",6666);
             BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
             //Input Output Streams
             PrintStream out = new PrintStream(s.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
              
             out.println("Partition");
             String identity=null;
     		String item=null;
	            	while(true){
	       
						try {
							identity= in.readLine();
							if(identity.equals("store"))
							{
								System.out.println(identity);
								item= in.readLine();
								storeMsg(item+"\n");
							}							
							
							if(identity.equals("CONSUME")) {
								System.out.println(identity);
								ArrayList<String> msgs = new ArrayList<String>();
								msgs=getmesg();
								System.out.println(msgs.size());
							    out.println(msgs);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	  
	            		
	            		
	            }
	   

	  
	    }
}
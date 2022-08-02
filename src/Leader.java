import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leader
{
	
	public static Queue<String> item_q1 = new PriorityQueue<String>();////producer
	public static Queue<String> item_q2 = new PriorityQueue<String>();/////consumer
	static int capacity = 100;
	 
	public static void print_q2(){
		System.out.println("---Queue elements consumed----");
		
		for(String s : item_q2) { 
			  System.out.print(s.toString()+" | "); 
			}
		System.out.println("\n-------------");
	}
	public static void print_q1(){
		System.out.println("---Queue elements produced----");
		
		for(String s : item_q1) { 
			  System.out.print(s.toString()+" | "); 
			}
		System.out.println("\n-------------");
	}
	
	
    public static void produce(String value) throws InterruptedException
    {
        
                while (item_q1.size()==capacity)
                    Thread.sleep(1000);;

                System.out.println("Producer produced-" + value);

                synchronized(item_q1){
                	
                	 item_q1.add(value);
                	 print_q1();
                	 System.out.println("Lock with producer");
                	 Thread.sleep(1000);
               }     
                Thread.sleep(1000);
                
           
    }
    

    
    public static ArrayList <String> consume() throws InterruptedException
    {
           
                while (item_q2.size()==0)
                	Thread.sleep(1000);;

             
                ArrayList <String>val=new ArrayList();
               
                synchronized(item_q2){
                	for(String s : item_q2) { 
            			val.add(s.toString());
            			
            			}
                	System.out.println("Lock with consumer");
                	Thread.sleep(1000);
                }
                System.out.println("Consumer consumed-" + val);
                print_q2();
                Thread.sleep(1000);
                
                return val;
          
    }
    
    
    public static void main(String args[])throws IOException, InterruptedException
    {
    	ServerSocket s=new ServerSocket(6666);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
      while(true) { 
        
        Socket ss1=s.accept();
        PrintStream out = new PrintStream(ss1.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
        
        String identity = in.readLine();
          
         if(identity.equals("p"))
        {
        	
        Thread producer = new Thread(new Runnable()
        {
        	@Override
            public void run()
            {
            	while(true){
            		String item=null;
					try {
						item = in.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		try {
						produce(item);
			        	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            		out.println("PRODUCE");
            		
            	}
            }
        });
       	
        producer.start();
        }
        else if(identity.equals("CONSUME")) {
       
        Thread consumer = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
            	while(true){
            		try {
						in.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		ArrayList <String>arr=new ArrayList();
            		String item=null;
					//
					try {
						arr=consume();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
            		out.println(arr);
            	}
            }
        });
        
        consumer.start();
        }
        else if(identity.equals("Partition")) {
         	System.out.println(identity);
         	// Create consumer thread
         	Thread partition = new Thread(new Runnable()
            {
                @Override
                public void run() 
                {
                
                		String item=null;
                		System.out.println(item_q1.size());
                		while (item_q1.size()>0)
                		{
	                		out.println("store");
	    					item=item_q1.poll();
	    					out.println(item);
                		}
   
                		out.println("CONSUME");
                 		
                 		try {
                 			
							String item2=in.readLine();
							String[]  y=item2.split(",");
							for(int i=0;i<y.length;i++)
								{
								
									item_q2.add(y[i]);		
								}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		
                	}
             
            });
       	 partition.start();
         	 
         } 
      } 
    }
}
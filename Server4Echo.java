//*************************************************************	
//***************************************************************
//
// Developer: Rhoda Chalmers
//
// Java Version: JavaSE-17
//
// Name: Server4Echo.java
//
// Decription: Demonstrates simple TCP/IP socket server
// that echoes every message from the client.
// Server remains open and listening for 3 seconds
// and then closes with a message.
// Server is single threaded.	
//
//  Associated Class: Client4Echo
//
//*************************************************************
//*************************************************************

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Exception;

public class Server4Echo {
    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the project
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public static void main(String[] args) {
		// Create an object of the main class and use it to call
                // the non-static developerInfo method as well as
		// the non-static createServer method
		Server4Echo obj = new Server4Echo();
		obj.developerInfo();
		obj.createServer(4302);	//This is hardcoded for assignment
		
	}// end of main method
	
	//***************************************************************
	//
	//  Method:       createServer (Non Static)
	// 
	//  Description:  Creates an echo server that listens for
        //                client connection and echoes client input
        //                until command to end connection from client
        //
        //  Parameters:   Integer port (listening port)
	//                
        //
        //  Returns:      N/A 
        //
        //**************************************************************
	public void createServer(Integer port) {
		// open a server socket and listen for incoming connections
		// print helpful messages to the screen
		try {
			ServerSocket serverSock = new ServerSocket(port);
			System.out.println("[Server]: Listening on port " + port);
						
			// handling client connection
			// print helpful messages to screen as needed
			while(true) {
				Socket clientSock = serverSock.accept();
				System.out.println("[Server]: New client connected");
				
				// Get the input stream from the socket connection
				BufferedReader buffReader = new BufferedReader (new InputStreamReader (clientSock.getInputStream()));
				// Get the output stream from the socket connection
				PrintWriter printOutStream = new PrintWriter (clientSock.getOutputStream(),true);
				
				// Read the input stream from the socket connection
				// Test for exit string
				String line;
				while((line = buffReader.readLine()) != null) {
					System.out.println("[Client]: " + line);
					// write input stream to the socket
					printOutStream.println(line);
				}
				
				// close the client and listen for new connections
				clientSock.close();
				
				// The Server remains available for new connections
				System.out.println("[Server]: Client disconnected. Listening ...");
				Thread.sleep(3000); // wait 3 seconds and then close server socket
				System.out.println("[Server]: No more incoming connections. Shutting down ...");
				// Force exit
				System.exit(0);
			}		
			
		} catch(IOException ioe) {
			System.err.println("[Server]: There has been a communication error.");
			System.out.println("[Server]: Server is shutting down...");
			System.exit(1);
		} catch(Exception e) {
			System.err.println("[Server]: An unexpected error occurred.");
			System.out.println("[Server]: Server is shutting down...");
			System.exit(1);
		} 
		
	}// end of createServer method
	
    //***************************************************************
    //
    //  Method:       developerInfo (Non Static)
    // 
    //  Description:  The developer information method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void developerInfo(){
    	System.out.println("Name:    Rhoda Chalmers");
    	System.out.println("Program: Server4Echo\n");
    } // End of the developerInfo method
	
}// End of Server4Echo class

  //*************************************************************	
  //***************************************************************
  //
  // Developer: Rhoda Chalmers
  //
  // File Name: Client4Echo.java
  //
  // Java Version: JavaSE-17
  //
  // Decription: Demonstrates a simple TCP/IP socket client that
  // reads input from the user (keyboard) and prints an
  // echoed message from the server. Client is single threaded.
  //
  //  Associated Class: Server4Echo
  //
  //*************************************************************
  //*************************************************************

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.lang.Exception;

public class Client4Echo {
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
		// the non-static createClient method
		Client4Echo obj = new Client4Echo();
		obj.developerInfo();
		InetAddress address;
		try {
			address = InetAddress.getByName("localhost");
			obj.createClient(address, 4302);// port hard coded for assignment
		} catch (UnknownHostException uhe) {
			System.err.println("[Client]: Host Unknown");
			System.out.println("[Client]: Failed to connect. Shutting down...");
			System.exit(1);
		}				
	}// end of main method
	
  //***************************************************************
  //
  //  Method:       createClient (Non Static)
  // 
  //  Description:  Creates a client that reads user input
  //                from the keyboard and then
  //                prints the echoed message from the server
  //
  //  Parameters:   String address (IP address)
  //                Integer port (listening port)
  //
  //  Returns:      N/A 
  //
  //**************************************************************
	public void createClient(InetAddress address, Integer port) {
		// Make a connection to the server socket
		// Print helpful messages to screen as needed
		try{
			Socket clientSock = new Socket(address, port);
			
			// Get the input stream from the client socket connection
			PrintWriter printOutStream = new PrintWriter(clientSock.getOutputStream(), true);
			// Get the output stream from the client socket connection
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

			// Scan user input from keyboard
			Scanner scanUserIn = new Scanner(System.in);
				
			// Display instructions to the client
			System.out.println("[Server]: Enter text to be echoed");
			System.out.println("[Server]: Type \"BYE\" to disconnect");
				
			// Loop to get user input until the word BYE is received to exit
			while(true) {
				// Scan in user input
				System.out.print("[Client]: ");
				String input = scanUserIn.nextLine();
					
				// Check to see if the client wants to close the connection
				if("bye".equalsIgnoreCase(input)) {
					System.out.println("[Server]: Disconnecting client ....");
						
					// Close the open scanner & socket and then exit
					scanUserIn.close();
					clientSock.close();	
					System.exit(0);
				}// end if testing for exit condition
					
				printOutStream.println(input);
					
				//read the user input from the client socket
				String response = buffReader.readLine();
				System.out.println("[Server]: " + response);
			} // end of while loop
			
		} catch(IOException ioe) {
			System.err.println("[Client]: Error reading input");
			System.out.println("[Client]: Shutting down...");
			System.exit(1);
		} catch(Exception e) {
			System.err.println("[Client]: An unexpected error occurred.");
			System.out.println("[Client]: Shutting down...");
			System.exit(2);
		} 
	}// end createClient method
	
//***************************************************************
//
//  Method:       developerInfo (Non Static)
// 
//  Description:  The developer information method of the program
//                This method must be included in all projects.
//
//  Parameters:   None
//
//  Returns:      N/A
//
//**************************************************************
    public void developerInfo(){
    	System.out.println("Name:    Rhoda Chalmers");
    	System.out.println("Program: Client4Echo\n");
    } // End of the developerInfo method

}// End of Client4Echo class

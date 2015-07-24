package morracineseserver;

import java.io.IOException;

public class Computer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 		Server Computer	= new Server(1337);
 		try {
 			Computer.startServer();
 		}
 		catch (IOException e) {
 			System.err.println(e.getMessage());
 		}
 	}
	
}

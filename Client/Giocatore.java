package morracineseclient;
import java.io.IOException;


public class Giocatore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Client Giocatore = new Client("127.0.0.1", 1337);
	try {
		Giocatore.startClient();
	} catch (IOException e) {
		System.err.println(e.getMessage());
		}
	}

}

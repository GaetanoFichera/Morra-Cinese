package morracineseserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Server {

	private int	port;

	public Server(int port) {
		this.port =	port;
		}

	public void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port); //inizializzo un un serversocket per permettere al client di instaurare una connessione
		System.out.println("Server socket pronto sulla porta: " + port);
		Socket socket = serverSocket.accept(); //creo un socket aspettando la connessione di un client alla porta del server
		System.out.println("Connessione Client Ricevuta");
		Scanner	socketIn = new Scanner(socket.getInputStream()); //inizializzo uno scanner per acquisire i flussi di dati in entrata nel socket
		PrintWriter	socketOut	= new PrintWriter(socket.getOutputStream());
		int punteggioclient = 0;
		int punteggioserver = 0;
		while (true) {
			String line = socketIn.nextLine();
			if (line.equals("Esci") || line.equals("esci")) { //se ricevo una stringa "Esci" o "esci" esco dal ciclo 
				break;
			} else {
				Random r = new Random();
				int n = r.nextInt(3);
				String mossa = "";
				String risultato = "";
				switch (n){
				case (0): mossa = "Sasso";
						  if (line.equals("Sasso") || line.equals("sasso")){
							  risultato = "Pari";
						  } else if(line.equals("Carta") || line.equals("carta")){
							  risultato = "Hai Vinto!"; 
							  punteggioclient++;
						  } else //(line == "Forbici" || line == "forbici")
							  {
							  risultato = "Hai Perso!";
							  punteggioserver++;
						  }
						  break;
				case (1): mossa = "Carta";
				  		  if (line.equals("Sasso") || line.equals("sasso")){
				  			  risultato = "Hai Perso!";
				  			  punteggioserver++;
				  		  } else if(line.equals("Carta") || line.equals("carta")){
				  			  risultato = "Pari";
				  		  } else //(line == "Forbici" || line == "forbici")
				  			  {
				  			  risultato = "Hai Vinto!";
				  			  punteggioclient++;
				  		  }
				  		  break;
				case (2): mossa = "Forbici";
						  if (line.equals("Sasso") || line.equals("sasso")){
							  risultato = "Hai Vinto!";
							  punteggioclient++;
						  } else if(line.equals("Carta") || line.equals("carta")){
							  risultato = "Hai Perso!";
							  punteggioserver++;
						  } else //(line == "Forbici" || line == "forbici")
							  {
							  risultato = "Pari";
						  }
						  break;
				}
				socketOut.println("La tua mossa e': " + line + " - La mossa dell'avversario e': " + mossa + " .... " + risultato +
							" - Punteggio Utente: " + punteggioclient + " - Punteggio Server: " + punteggioserver);
				socketOut.flush();
			}
		}
		System.out.println("Chiusura socket");
		socketIn.close();
		socketOut.close();
		socket.close();
		serverSocket.close();
		}
}

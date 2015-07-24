package morracineseclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Client {
	
	private String ip;
	private int port;
	
	public Client(String ip, int port) {
		 this.ip = ip;
		 this.port = port;
		 }
	
	public void startClient() throws IOException { //eseguo il metodo attraverso IOException poiche necessito di un controllo dei dati in input ed output
		Socket socket = new Socket(ip, port); //inizializzo un socket con ip del destinatario e porta da utilizzare
		System.out.println("Connessione con il server stabilita" + '\n' + "Per terminare la partita scrivere: 'Esci'" + '\n');
		Scanner	socketIn = new	Scanner(socket.getInputStream()); //inizializzo uno scanner per poi stampare il flusso di dati proveniente dal server attarverso il socket
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream()); //inizializzzo un printwriter per inserire nel socket il flusso di dati da inviare al server
		Scanner	in = new Scanner(System.in); //inizializzo uno scanner per acquisire l'input da tastiera
		String inputLine; //stringa per l'input da tastiera
		int n = 1; //tiene il conto delle partite
		try {
			while (true) {
				boolean ripeti;
				do{ 
					ripeti = false;
					System.out.print(n + "^ Partita. Scegli tra Sasso, Carta, Forbici: ");
					inputLine = in.nextLine();
					switch (inputLine){
					case("Sasso"): break;
					case("sasso"): break;
					case("Carta"): break;
					case("carta"): break;
					case("Forbici"): break;
					case("forbici"): break;
					case("Esci"): break;
					case("esci"): break;
					default: 
						System.out.print("ERRORE: Scelta non chiara, si prega di scegliere tra Sasso, Carta, Forbici: " + '\n');
						ripeti = true;
					}
				}while(ripeti); //cinvio il contenuto del socket la servericla solo quando si esegue il codice di default e viene richiesto all'utente di reinserire la propria scelta
				socketOut.println(inputLine); //stampo nel socket la stringa da voler inviare
				socketOut.flush();	//svuota il flusso di dati prendi su socketOut
				String socketLine = socketIn.nextLine(); //acquisisco dal socket la stringa dal flusso in entrata
				System.out.println(socketLine);
				n++;
			}	
		}
		catch(NoSuchElementException e) { //si esegue come conseguenza alla risposta che riceveremo dopo aver inviato "Esci" o "esci" al server
			System.out.println("Connessione chiusa");
		}
		finally {
			in.close();
		}
		socketIn.close(); //chiusura del scanner per l'acquisizionde dei flussi in entrata
		socketOut.close(); //chiusura del printwriter per l'invio dei flussi in uscita
		socket.close();
	}
}
























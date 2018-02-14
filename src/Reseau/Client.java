package Reseau;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) {
		
		/*
		 * L'objet socket a besoin de l'IP et du Port du ServerSocket
		 */
		Socket client = null;
		try {
			client = new Socket("localhost", 11111);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		/*
		 * Création des objets permettant de communiquer avec le client
		 */
		
		
		PrintStream output = null;
		BufferedReader input = null;
		try {
			output = new PrintStream(client.getOutputStream());
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		/*
		 * Boucle principale
		 */
		while (true) {
			/*
			 * Boucle principale
			 */
			for (int i = 0; i < 10; i++) {
				output.println("Hello " + i);
				output.flush(); 
			}
			
			/*
			 * Fermeture
			 */
			try {
				input.close();
				output.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
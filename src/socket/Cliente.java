package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private final String HOST = "localhost"; //IP donde se ha ejecutado el Socket Servidor
	private final Integer PUERTO = 13; //Puerto donde se ha ejecutado el Socket Servidor
	
	public Cliente() {
		try {
			Socket cliente = new Socket(HOST, PUERTO);
			System.out.println(">>> Cliente >>> INICIO Peticiones al servidor");
			
			
		
			System.out.println(">>> Cliente >>> FIN Peticiones al servidor");
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Cliente();
	}
}

package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private final Integer PUERTO = 13;
	private Socket cliente = null;
	
	public Servidor() {
		try {
			ServerSocket serverSocket = new ServerSocket(PUERTO);
			while(true) {
				System.out.println(">>> Server >>> Esperando las peticiones");
				System.out.println("---------------------------");
				cliente = serverSocket.accept();
				System.out.println(">>> Server >>> INICIO Atendiendo las peticiones ");

				
				
				
				System.out.println(">>> Server >>> FIN Atendiendo las peticiones ");
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}

}

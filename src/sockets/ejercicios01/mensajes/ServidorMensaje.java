package sockets.ejercicios01.mensajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMensaje {

	
	private final Integer PUERTO = 13;

	private Socket socketCliente;
	
	@SuppressWarnings("unused")
	public ServidorMensaje(){
		try {
			ServerSocket  socketServidor = new ServerSocket(PUERTO);
			while(true){
				System.out.println("______ Servidor Esperando peticiones _____");
				System.out.println("__________________________________________");

				socketCliente = socketServidor.accept();
				System.out.println(">> Server >> INICIO atención");
				
				//--------------------------------------------
				//   Medios para la comunicacin para MENSAJE
				//--------------------------------------------
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(),true);
				
				//PASO 2: SERVIDOR recibe el área 
				String mensaje = entrada.readLine();
				System.out.println(">> mensaje >> " + mensaje);
				
				//PASO 3: SERVIDOR envía el mensaje
				if (mensaje.equals("Sistemas")) {
					salida.println("Juan,Manuel,María");
				}else if (mensaje.equals("Logistica")) {
					salida.println("Pedro,Sonia,Jhonny");
				}else if (mensaje.equals("Ventas")) {
					salida.println("Teresa,Alberto");
				}
				
				System.out.println(">> Server >> FIN atención ");
				socketCliente.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ServidorMensaje();
	}

}

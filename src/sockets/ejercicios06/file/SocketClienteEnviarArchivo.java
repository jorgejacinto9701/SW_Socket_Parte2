package sockets.ejercicios06.file;

import java.net.*;
import java.io.*;

public class SocketClienteEnviarArchivo {

	private final Integer PUERTO = 3456;
	private final String HOST = "localhost";
	private final String FILE_PATH = "D:/__RECURSOS/Duke.png";
	private final String FILE_NAME = "Duke.png";

	@SuppressWarnings("unused")
	public SocketClienteEnviarArchivo() {
		try {
			Socket socketCliente = new Socket(HOST, PUERTO);

			//1 ENVO DEL NOMBRE DEL ARCHIVO
			//====================================
			//Permite el envio de cualquier objeto
			ObjectOutputStream ous = new ObjectOutputStream(socketCliente.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
			
			ous.writeObject(FILE_NAME);
			
			
			//2 ENVO DE LOS PAQUETES DE BYTES DEL ARCHIVO
			//============================================
			File archivo = new File(FILE_PATH);
			FileInputStream fis = new FileInputStream(archivo);
			DataOutputStream salida = new DataOutputStream(socketCliente.getOutputStream());
			
			int leido;
			while( (leido = fis.read()) != -1) {
				salida.write(leido);
			}
			
			fis.close();
			salida.close();
			socketCliente.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new SocketClienteEnviarArchivo();
	}

}

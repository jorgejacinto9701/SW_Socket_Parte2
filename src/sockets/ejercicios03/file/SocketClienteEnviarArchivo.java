package sockets.ejercicios03.file;

import java.net.*;
import java.io.*;

public class SocketClienteEnviarArchivo {

	private final Integer PUERTO = 3456;
	private final String HOST = "localhost";
	private final String RUTA_ORIGEN = "D:/_RECURSOS/Duke.png";
	private final String FILE_ORIGEN = "Duke.png";

	@SuppressWarnings("unused")
	public SocketClienteEnviarArchivo() {
		try {
			Socket socketCliente = new Socket(HOST, PUERTO);

			//PASO 1 ENVIO DEL NOMBRE DEL ARCHIVO
			//====================================
			//Permite el envio de cualquier objeto
			ObjectOutputStream ous = new ObjectOutputStream(socketCliente.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
			
			ous.writeObject(FILE_ORIGEN);
			
			
			//PASO 3 ENVIO DEL ARCHIVO (PAQUETES DE BYTES)(
			//============================================
			File archivo = new File(RUTA_ORIGEN);
			FileInputStream fis = new FileInputStream(archivo);
			DataOutputStream salida = new DataOutputStream(socketCliente.getOutputStream());
			
			int leido;
			while(( leido = fis.read()) != -1) {
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

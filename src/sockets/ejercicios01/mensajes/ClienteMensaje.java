package sockets.ejercicios01.mensajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class ClienteMensaje {
	
	private final String HOST = "localhost";

	private final Integer PUERTO = 13;
	
	 @SuppressWarnings("unused") 
	public ClienteMensaje(JFrame form){
		
		Socket socketCliente = null;
		try {
			socketCliente = new Socket(HOST,PUERTO);
			//--------------------------------------------
			//   Medios para la comunicacin para MENSAJE
			//--------------------------------------------
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(),true);

			// salida.println() -> Permite enviar mensajes
 			// entrada.readLine() -> Permite recibir mensajes
			
			//--------------------------------------------
			//  Empieza la comunicacin!!!!
			//--------------------------------------------

			FormularioMensaje formulario = (FormularioMensaje)form;
			
			//PASO 1: CLIENTE envía el área
			String mensaje = formulario.jcbArea.getSelectedItem().toString();
			salida.println(mensaje);
			
			//PASO 4: CLIENTE recibir los empleados
			String integrantes = entrada.readLine();
			
			//PASO 5: CLIENTE Se muestra en el formulario
			String[] separados = integrantes.split(",");
			formulario.txtMensaje.setText("");
			
			for (String aux : separados) {
				formulario.txtMensaje.append(aux  + "\n");
			}
			
			socketCliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}

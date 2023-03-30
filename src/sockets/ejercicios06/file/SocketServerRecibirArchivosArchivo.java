package sockets.ejercicios06.file;

import java.net.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;

public class SocketServerRecibirArchivosArchivo {

	private final Integer PUERTO = 3456;

	private Socket socketCliente;
	private static final String RUTA_LLEGADA = "D:/__RECURSOS/servidor/";


	@SuppressWarnings("unused")
	public SocketServerRecibirArchivosArchivo() {
		System.out.println("FileServer: esperando peticiones TCP/IP");
		System.out.println("_______________________________________");

		try {
			ServerSocket servidor = new ServerSocket(PUERTO);
			while (true) {
				socketCliente = servidor.accept();// /esta escuchando hasta que
												// alguien se conecte
				System.out.println("------SE ATENDIO A UN CLIENTE--------------");
				
				//1 RECIBE DEL NOMBRE DEL ARCHIVO
				//====================================
				//Permite el envio de cualquier objeto
				ObjectOutputStream ous = new ObjectOutputStream(socketCliente.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
			
				String fileName = (String) ois.readObject();
				
				//2 RECIBE LOS PAQUETES DE BYTES DEL ARCHIVO
				//============================================
				// --- creando los flujos
				File archivoDestino = new File(RUTA_LLEGADA + fileName);
				FileOutputStream fos = new FileOutputStream(archivoDestino);
				DataInputStream entrada =new DataInputStream(socketCliente.getInputStream());

				int leido;
				while( (leido = entrada.read()) != -1) {
					fos.write(leido);
				}
				
				//Covertir el archivo en un zip
				FileInputStream fis = new FileInputStream(archivoDestino);
				File archivoZipDestino = new File(RUTA_LLEGADA + "duke.zip");
				ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(archivoZipDestino));
				ZipEntry fileZipDestino = new ZipEntry(fileName);
				outZip.putNextEntry(fileZipDestino);
				
				int leido2;
				while( (leido2 = fis.read()) != -1) {
					outZip.write(leido2);
				}
				fis.close();
				outZip.closeEntry();
				outZip.close();
				
				fos.close();
				entrada.close();
				socketCliente.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SocketServerRecibirArchivosArchivo();
	}

}

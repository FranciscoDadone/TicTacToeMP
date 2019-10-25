package game.window.multiplayer.serverConnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game.util.Utilities;

public class ServerConnection {
	
	public ServerConnection(Packet paquete) {
		try {
			s = new Socket("localhost", 9999);			
		}catch(Exception e) {
			Utilities.logs("Error al conectar con el servidor 500");
		}
		
		ObjectOutputStream paqueteDatos;
		try {
			paqueteDatos = new ObjectOutputStream(s.getOutputStream());
			paqueteDatos.writeObject(paquete);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static String getResponse() {
		
		try {
			DataInputStream fromServer = new DataInputStream(ServerConnection.getSocket().getInputStream());
			return fromServer.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error 500";
		
	}
	
	public static void close() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Socket getSocket() {
		return s;
	}


	private static Socket s;
}

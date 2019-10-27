package game.window.multiplayer.serverConnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import game.util.Utilities;
import game.window.multiplayer.Multiplayer;

public class ServerConnection {
	
	public ServerConnection(Packet paquete) {
		
		username = paquete.getUsername();
		password = paquete.getPassword();
		
		try {
			s = new Socket(serverIP, 9999);
		}catch(Exception e) {
			Utilities.logs("Error al conectar con el servidor 500");
			e.printStackTrace();
			error = true;
		}
		
		if(error == false) {
			
			ObjectOutputStream paqueteDatos;
			try {
				paqueteDatos = new ObjectOutputStream(s.getOutputStream());
				paqueteDatos.writeObject(paquete);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public static String getResponse() {
		if(error) {
			return "Error al conectar con el servidor 500";
		} else {
			try {
				DataInputStream fromServer = new DataInputStream(ServerConnection.getSocket().getInputStream());
				return fromServer.readUTF();
			} catch (IOException e) {
				ServerConnection.f = false;
			}
		}
		return "Error al conectar con el servidor 500";
	}
	
	public static void close() {
		if(error == false) {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Socket getSocket() {
		return s;
	}
	
	public static void deleteGame() {
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("GAME: delete");
		paquete.setGameID(gameID);
		new ServerConnection(paquete);
		ServerConnection.close();
	}
	
	public static void createNewGame(String gameID) {
		ServerConnection.gameID = gameID;
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("GAME: create");
		paquete.setGameID(gameID);
		new ServerConnection(paquete);
		ServerConnection.close();
	}
	
	public static void getWins() {
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("INFO: wins");
		paquete.setGameID(gameID);
		new ServerConnection(paquete);
	}
	
	public static void cargarUserAPartida(String gameID, String username, String simbolo) {
		ServerConnection.gameID = gameID;
		Multiplayer.simbolo = simbolo;
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("GAME: addUser");
		paquete.setGameID(gameID);
		paquete.setSimbolo(simbolo);
		paquete.setSimboloRival(Multiplayer.simboloRival);
		new ServerConnection(paquete);
		ServerConnection.close();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getOtherPlayerInfo(String gameID) {
		ServerConnection.gameID = gameID;
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("GAME: getOtherPlayerInfo");
		paquete.setGameID(gameID);
		new ServerConnection(paquete);
		
		try {
			ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());
			Object object = objectInput.readObject();
            info =  (ArrayList<String>) object;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return info;
	}
	
	public static void getReadyPlayers(String gameID) {
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setPassword(password);
		paquete.setDatabaseChange("GAME: readyPlayers");
		paquete.setGameID(gameID);
		new ServerConnection(paquete);
	}
	
	public static void pasarTurno(int x, int y) {
		Packet paquete = new Packet();
		paquete.setUsername(username);
		paquete.setDatabaseChange("GAME: pasarTurno");
		paquete.setGameID(gameID);
		paquete.setX(x);
		paquete.setY(y);
		new ServerConnection(paquete);
		ServerConnection.close();
	}


	private static Socket s;
	private static boolean error = false;
	public static String serverIP = "181.114.48.92", username, password, gameID, wins;
	public static ArrayList<String> info = new ArrayList<String>();
	@SuppressWarnings("unused") //  está siendo usada
	private static boolean f = true;

}

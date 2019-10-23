package game.main;

import game.util.Utilities;
import game.window.*;
import game.window.multiplayer.mysqlConnection.DBConnection;

public class Main {
	
	public static void main(String args[]) {
		
		Utilities.font("impact");
		Utilities.font("arlrdbd");
		new GameWindow().setVisible(true);;
		try {
			new DBConnection();
		} catch (Exception e) {
			Utilities.logs("Error al crear la base de datos.");
			e.printStackTrace();
		}
		
	}
	
}

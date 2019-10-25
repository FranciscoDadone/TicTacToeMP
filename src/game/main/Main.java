package game.main;

import game.util.Utilities;
import game.window.GameWindow;
import game.window.multiplayer.serverConnection.ServerConnection;

public class Main {
	
	public static void main(String args[]) {
		
		Utilities.font("impact");
		Utilities.font("arlrdbd");
		new GameWindow().setVisible(true);;
		
		
	}
	
}

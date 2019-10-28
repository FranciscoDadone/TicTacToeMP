package game.window;

import javax.swing.JFrame;

import game.util.Utilities;
import game.window.multiplayer.Multiplayer;
import game.window.multiplayer.MultiplayerGameSelector;
import game.window.multiplayer.MultiplayerLogin;
import game.window.multiplayer.serverConnection.ServerConnection;
import game.window.singleplayer.Singleplayer;

public class WindowManagement {
	
	public WindowManagement(JFrame jframe) {
		
		WindowManagement.jframe = jframe;
		
	}
	
	public static void render(String modo) {
		
		WindowManagement.modo = modo;
		System.gc();
		
		if(modo == "menu") {
			
			sp.setVisible(false);
			mp.setVisible(false);
			jframe.add(mainMenu);
			jframe.revalidate();
			mainMenu.setVisible(true);
			jframe.setTitle("TicTacToe - Main menu");
			Utilities.logs("Main menu");
			
		} else if(modo == "Singleplayer") {
			
			mainMenu.setVisible(false);
			jframe.add(sp);
			jframe.revalidate();
			sp.setVisible(true);
			jframe.setTitle("TicTacToe - Singleplayer");
			Utilities.logs("Singleplayer");
			
		} else if(modo == "Multiplayer") {
			
			mainMenu.setVisible(false);
			mpGS.setVisible(false);
			jframe.add(mp);
			jframe.revalidate();
			mp.setVisible(true);
			jframe.setTitle("TicTacToe - Jugando multiplayer");
			Utilities.logs("Jugando multiplayer");
			
			if(!ServerConnection.info.get(2).equals("JUEGA")) {
				Multiplayer.enableButtons(true);
			} else {
				Multiplayer.enableButtons(false);
			}
			
		} else if(modo == "MultiplayerLogin") {
			
			mainMenu.setVisible(false);
			jframe.add(mpL);
			jframe.revalidate();
			mpL.setVisible(true);
			jframe.setTitle("TicTacToe - Login");
			Utilities.logs("Multiplayer Login");
			
		} else if(modo == "MultiplayerGameSelector") {
			
			mp.setVisible(false);
			mainMenu.setVisible(false);
			mpL.setVisible(false);
			jframe.add(mpGS);
			jframe.revalidate();
			mpGS.setVisible(true);
			jframe.setTitle("TicTacToe - Multiplayer > Crear / Entrar");
			Utilities.logs("Multiplayer > Crear / Entrar");
			
		}
		
		
	}
	
	private static JFrame jframe;
	private static mainMenu mainMenu = new mainMenu();
	private static Singleplayer sp = new Singleplayer();
	private static Multiplayer mp = new Multiplayer();
	private static MultiplayerLogin mpL = new MultiplayerLogin();
	private static MultiplayerGameSelector mpGS = new MultiplayerGameSelector();
	public static String modo = "null";
	
	
}

package game.window;

import java.awt.Toolkit;
import javax.swing.JFrame;

import game.util.Utilities;
import game.window.multiplayer.serverConnection.ServerConnection;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -210909840497170549L;


	public GameWindow() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	try {
		    		ServerConnection.deleteGame();
		    	} catch(Exception e1) {}
		    	Utilities.logs("Cerrando...");
		        e.getWindow().dispose();		        
		    }
		});
		
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		Utilities.logs("Screen: " + screenWidth + " " + screenHeight);
		
		this.setBounds(((screenWidth/2)-400), ((screenHeight/2)-250), 800, 500);
		this.setTitle("TicTacToe - Main menu");
		
		new WindowManagement(this);
		WindowManagement.render("menu");
		
		
		
	}
	
	
	public static int screenWidth, screenHeight;
	
}


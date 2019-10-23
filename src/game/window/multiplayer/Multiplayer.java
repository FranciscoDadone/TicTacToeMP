package game.window.multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.util.Utilities;
import game.window.Botones;
import game.window.WindowManagement;
import game.window.multiplayer.mysqlConnection.DBConnection;

public class Multiplayer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -530541721635197577L;
	public Multiplayer() {
		
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		
		//	JPanel: Game
		
		game = new JPanel();
		game.setBackground(new Color(47, 47, 47));
		game.setLayout(new GridLayout(3, 3, 10, 10));
		this.add(game, BorderLayout.CENTER);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(47, 47, 47));
		JLabel l = new JLabel();
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Impact", Font.ITALIC, 20));
		panelSuperior.add(l);
		
		
		this.add(panelSuperior, BorderLayout.NORTH);
		
		//	--	/END/ Game	--
		
		
		
		//	SIDE MENU
		sideMenu = new JPanel();
		sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));

		turnoXO = new JLabel();
		
		turno.setForeground(Color.WHITE);
		turnoXO.setForeground(Color.WHITE);
		turno.setFont(new Font("Impact", Font.ITALIC, 28));
		turnoXO.setFont(new Font("Impact", Font.ITALIC, 48));
		
		sideMenu.setBackground(new Color(41, 41, 41));
		salir.setBackground(new Color(41, 41, 41));
		salir.setForeground(Color.WHITE);
		
		sideMenu.add(salir);
		sideMenu.add(Box.createVerticalStrut(180));
		sideMenu.add(turno);
		sideMenu.add(Box.createVerticalStrut(5));
		sideMenu.add(turnoXO);
		
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				WindowManagement.render("menu");
				DBConnection.deleteGame(DBConnection.gameID);
				
			}			
			
		});
		
		
		this.add(sideMenu, BorderLayout.WEST);
		
		// --	/END/ SIDE MENU	--
		
		// Actual game
		
		for(int i = 0; i < botones.length; i++) {
			
			for(int j = 0; j < botones.length; j++) {
				
				botones[i][j] = new Botones("");
				
				botones[i][j].setBounds(0, 0, 20, 20);
				botones[i][j].setBackground(new Color(47, 47, 47));
				botones[i][j].setForeground(Color.WHITE);
				botones[i][j].setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 140));
				game.add(botones[i][j]);
				botones[i][j].setEnabled(false);
				
				botones[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						for(int a = 0; a < botones.length; a++) {
							
							for(int b = 0; b < botones.length; b++) {
								
								if(e.getSource().equals(botones[a][b])) {
									
									Utilities.logs("Botón. X:" + b + " Y:" + a);
									
									botones[a][b].setEnabled(false);
									botones[a][b].setText(MultiplayerGameSelector.simbolo);
									DBConnection.pasarTurno(b, a);
									
								}
								
								
								
							}
								
						}
						enableButtons(false);
						checkWin();
						
					}	
					
				});
				
			}
			
		}
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					try {
						
						if(WindowManagement.modo.equals("Multiplayer")) {
							
							l.setText(" Jugando contra:  " + MultiplayerGameSelector.info.get(0) + "  ["+ MultiplayerGameSelector.info.get(3) +"] ");
							
							try {
								
								int rivalX = Integer.parseInt(MultiplayerGameSelector.info.get(4)), rivalY = Integer.parseInt(MultiplayerGameSelector.info.get(5));
								
								botones[rivalY][rivalX].setEnabled(false);
								botones[rivalY][rivalX].setText(MultiplayerGameSelector.info.get(1));
								
							} catch(Exception e) {}
							
							
							if(MultiplayerGameSelector.info.get(2).equals("JUEGA")) {

								enableButtons(false);
								turnoXO.setText("   " + MultiplayerGameSelector.info.get(1) + "     ");
								
								
							} else {
								
								enableButtons(true);
								turnoXO.setText("   " + MultiplayerGameSelector.simbolo + "     ");
							}
							
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {}
					checkWin();
				}
			}
			
		}).start();
		
	}
	
	public void enableButtons(boolean e) {
		
		for(int a = 0; a < botones.length; a++) {
			
			for(int b = 0; b < botones.length; b++) {
				
				if(botones[a][b].getText().equals("")) {
					
					botones[a][b].setEnabled(e);
					
				}
				
			}
				
		}
		
	}
	
	public void checkWin() {
		
		if(simboloRival == null) {
			
			if(MultiplayerGameSelector.simbolo.equals("X")) {
				simboloRival = "O";
			} else if(MultiplayerGameSelector.simbolo.equals("X")) {
				simboloRival = "X";
			}
			
		}
		
		//Check horizontal
		for(int i = 0; i < 3; i++) {
			int count = 0, countRival = 0;
			
			ArrayList<Botones> arr = new ArrayList<Botones>();
			ArrayList<Botones> arrRival = new ArrayList<Botones>();
			arr.removeAll(arr);
			arrRival.removeAll(arrRival);
			for(int j = 0; j < 3; j++) {
				
				if(botones[i][j].getText().equals(MultiplayerGameSelector.simbolo)) {
					count++;
					arr.add(botones[i][j]);
					
					if(count == 3) {
						
						for(int a = 0; a < arr.size(); a++) {
							
							arr.get(a).setBackground(new Color(66, 233, 62));
							win = "me";
							
						}
						
					}
				} else if(botones[i][j].getText().equals(simboloRival)) {
					countRival++;
					arrRival.add(botones[i][j]);
					
					if(countRival == 3) {
						
						for(int a = 0; a < arrRival.size(); a++) {
							
							arrRival.get(a).setBackground(new Color(249, 30, 30));
							win = "opponent";
							
						}
						
					}
				}
			}
			
		}
		
		//Check vertical
		for(int j = 0; j < 3; j++) {
			
			int count = 0, countRival = 0;
			ArrayList<Botones> arr = new ArrayList<Botones>();
			ArrayList<Botones> arrRival = new ArrayList<Botones>();
			arr.removeAll(arr);
			arrRival.removeAll(arrRival);
			
			for(int i = 0; i < 3; i++) {
				
				if(botones[i][j].getText().equals(MultiplayerGameSelector.simbolo)) {
					count++;
					arr.add(botones[i][j]);
					
					if(count == 3) {
						
						for(int a = 0; a < arr.size(); a++) {
							
							arr.get(a).setBackground(new Color(66, 233, 62));
							win = "me";
							
						}
						
					}
					
				} else if(botones[i][j].getText().equals(simboloRival)) {
					
					countRival++;
					arrRival.add(botones[i][j]);
					
					if(countRival == 3) {
						
						for(int a = 0; a < arrRival.size(); a++) {
							
							arrRival.get(a).setBackground(new Color(249, 30, 30));
							win = "opponent";
							
						}
						
					}
					
				}
				
			}
			
		}
		
		//Check de diagonales
		if(botones[0][0].getText().equals(MultiplayerGameSelector.simbolo) && botones[1][1].getText().equals(MultiplayerGameSelector.simbolo) && botones[2][2].getText().equals(MultiplayerGameSelector.simbolo)) {
			
			botones[0][0].setBackground(new Color(66, 233, 62));
			botones[1][1].setBackground(new Color(66, 233, 62));
			botones[2][2].setBackground(new Color(66, 233, 62));
			win = "me";
		}
		
		if(botones[0][0].getText().equals(simboloRival) && botones[1][1].getText().equals(simboloRival) && botones[2][2].getText().equals(simboloRival)) {
			
			botones[0][0].setBackground(new Color(249, 30, 30));
			botones[1][1].setBackground(new Color(249, 30, 30));
			botones[2][2].setBackground(new Color(249, 30, 30));
			win = "opponent";
		}
		if(botones[0][2].getText().equals(MultiplayerGameSelector.simbolo) && botones[1][1].getText().equals(MultiplayerGameSelector.simbolo) && botones[2][0].getText().equals(MultiplayerGameSelector.simbolo)) {
			
			botones[0][2].setBackground(new Color(66, 233, 62));
			botones[1][1].setBackground(new Color(66, 233, 62));
			botones[2][0].setBackground(new Color(66, 233, 62));
			win = "me";
		} 
		if(botones[0][2].getText().equals(simboloRival) && botones[1][1].getText().equals(simboloRival) && botones[2][0].getText().equals(simboloRival)) {
			
			botones[0][2].setBackground(new Color(249, 30, 30));
			botones[1][1].setBackground(new Color(249, 30, 30));
			botones[2][0].setBackground(new Color(249, 30, 30));
			win = "opponent";
		}
		
		//---------------
		if(win.equals("me")) {
			
			enableButtons(false);
			System.out.println("gane"); //testear glitch botones blinking en el perdedor.
			
		} else if(win.equals("opponent")) {
			
			enableButtons(false);
			
		}
		
	}
	
	
	
	private Botones[][] botones = new Botones[3][3];
	private JPanel game, sideMenu;
	private JButton salir = new JButton("Volver al menu");
	private JLabel turno = new JLabel("    Turno  ");
	private JLabel turnoXO;
	public static String simboloRival, win = "";

}

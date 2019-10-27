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
import game.window.multiplayer.serverConnection.ServerConnection;

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
		l = new JLabel();
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
				ServerConnection.deleteGame();
				WindowManagement.render("menu");
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
				int blockedButtonIterator = 0;
					@Override
					public void actionPerformed(ActionEvent e) {
						
						for(int a = 0; a < botones.length; a++) {
							
							for(int b = 0; b < botones.length; b++) {
								
								if(e.getSource().equals(botones[a][b])) {
									
									Utilities.logs("Botón. X:" + b + " Y:" + a);
									
									botones[a][b].setEnabled(false);
									botonesBlocked[blockedButtonIterator] = botones[a][b];
									blockedButtonIterator++;
									botones[a][b].setText(MultiplayerGameSelector.simbolo);

									ServerConnection.pasarTurno(b, a);
									
									
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
				
				while(!win.equals("me") || !win.equals("opponent")) {
					
					try {
					
						if(WindowManagement.modo.equals("Multiplayer") && win.equals("")) {
	
							l.setText(" Jugando contra:  " + ServerConnection.info.get(0) + "  ["+ ServerConnection.info.get(3) +"] ");
							try {
								int rivalX = Integer.parseInt(ServerConnection.info.get(4)), rivalY = Integer.parseInt(ServerConnection.info.get(5));
								
								botones[rivalY][rivalX].setEnabled(false);
								botones[rivalY][rivalX].setText(Multiplayer.simboloRival);
								
							} catch(Exception e) {}
							
							
							if(ServerConnection.info.get(2).equals("JUEGA")) {

								enableButtons(false);
								turnoXO.setText("   " + Multiplayer.simboloRival + "     ");
								
								
							} else {
								
								enableButtons(true);
								turnoXO.setText("   " + Multiplayer.simbolo + "     ");
							}
							
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {}
					try {
						checkWin();
					} catch(Exception e) {}
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				
				while(true) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(WindowManagement.modo.equals("Multiplayer")) {
						try {
							ArrayList<String> a = new ArrayList<String>();
							a = ServerConnection.getOtherPlayerInfo(ServerConnection.gameID);
							
							if(!a.get(1).equals("-") || !a.get(2).equals("-")) {
								ServerConnection.info = a;
								if(firstTimeSymbol) {
									Multiplayer.simboloRival = ServerConnection.info.get(1);
									firstTimeSymbol = false;
								}
							}
						
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}			
		}).start();
		
	}
	
	public void enableButtons(boolean e) {
		
		if(e == true && win.equals("me") || e == true && win.equals("opponent")) {
			return;
		}

		for(int a = 0; a < botones.length; a++) {
			
			for(int b = 0; b < botones.length; b++) {
				
				try {
					botonesBlocked[b].setEnabled(false);
				} catch(Exception e1) {}
				
				if(botones[a][b].getText().equals("")) {
					
					botones[a][b].setEnabled(e);
					
				}
				
			}
				
		}
		
	}
	
	public void checkWin() {
		if(simboloRival == null) {
			
			if(Multiplayer.simbolo.equals("X")) {
				simboloRival = "O";
			} else if(Multiplayer.simbolo.equals("X")) {
				simboloRival = "X";
			} else {
				simboloRival = null;
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
			l.setText(" ¡GANASTE! ");
			
			
		} else if(win.equals("opponent")) {
			
			enableButtons(false);
			l.setText(" GAME OVER. " + ServerConnection.info.get(0) + " ha ganado la partida.");
			
		}
		
	}
	
	
	
	private Botones[][] botones = new Botones[3][3];
	private Botones[] botonesBlocked = new Botones[3];
	private JPanel game, sideMenu;
	private JButton salir = new JButton("Volver al menu");
	private JLabel turno = new JLabel("    Turno  ");
	private JLabel turnoXO;
	public static String simboloRival, simbolo, win = "";
	private static boolean firstTimeSymbol = true;
	private JLabel l;

}

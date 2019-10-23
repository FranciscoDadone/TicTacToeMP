package game.window.singleplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.util.Utilities;
import game.window.Botones;
import game.window.WindowManagement;

public class Singleplayer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6657694132716643902L;
	public Singleplayer() {
		
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		
		//	JPanel: Game
		
		game = new JPanel();
		game.setBackground(Color.BLACK);
		game.setBackground(new Color(47, 47, 47));
		game.setLayout(new GridLayout(3, 3, 10, 10));
		this.add(game, BorderLayout.CENTER);
		//	--	/END/ Game	--
		
		
		
		//	SIDE MENU
		sideMenu = new JPanel();
		sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));

		turnoXO = new JLabel("    X  ");
		
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
				
				botones[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						for(int a = 0; a < botones.length; a++) {
							
							for(int b = 0; b < botones.length; b++) {
								
								if(e.getSource().equals(botones[a][b])) {
									
									botones[a][b].setXO(simboloJugador);
									botones[a][b].setEnabled(false);
									
									checkWin();
									
									IA_juega();
									
								}
								
							}
								
						}
						
					}	
					
				});
				
			}
			
		}
		
		randomizePlayers();
		
		
		
		
		// --	/END/ Actual game	--
		
	}
	
	public void checkWin() {
		
		for(int i = 0; i < 3; i++) {
			
			int count = 0, countIA = 0;
			ArrayList<Botones> arr = new ArrayList<Botones>();
			ArrayList<Botones> arrAI = new ArrayList<Botones>();
			arr.removeAll(arr);
			arrAI.removeAll(arrAI);
			
			for(int j = 0; j < 3; j++) {
				
				if(botones[i][j].getXO() == simboloJugador) {
					
					count++;
					arr.add(botones[i][j]);
					arrAI.add(botones[i][j]);
					
					if(count == 3) {
						
						for(int a = 0; a < arr.size(); a++) {
							
							arr.get(a).setBackground(new Color(20,20,20));
							
						}
						
					}
					
				} else if(botones[i][j].getXO() == simboloIA) {
					
					countIA++;
					if(countIA == 3) {
						
						for(int a = 0; a < arrAI.size(); a++) {
							
							arrAI.get(a).setBackground(new Color(20,20,20));
							
						}
						
					}
					
				}
				
			}
			
		}
		
		// vert
		
		for(int j = 0; j < 3; j++) {
			
			int count = 0, countIA = 0;
			ArrayList<Botones> arr = new ArrayList<Botones>();
			ArrayList<Botones> arrAI = new ArrayList<Botones>();
			arr.removeAll(arr);
			arrAI.removeAll(arrAI);
			
			for(int i = 0; i < 3; i++) {
				
				if(botones[i][j].getXO() == simboloJugador) {
					
					count++;
					arr.add(botones[i][j]);
					arrAI.add(botones[i][j]);
					
					if(count == 3) {
						
						for(int a = 0; a < arr.size(); a++) {
							
							arr.get(a).setBackground(new Color(20,20,20));
							
						}
						
					}
					
				} else if(botones[i][j].getXO() == simboloIA) {
					
					countIA++;
					arrAI.add(botones[i][j]);
					
					System.out.println(countIA);
					
					if(countIA == 3) {
						
						for(int a = 0; a < arrAI.size(); a++) {
							
							arrAI.get(a).setBackground(new Color(20,20,20));
							
						}
						
					}
					
				}
				
			}
			
		}
		
		//diagonales
		
		//if(botones[0][0] == simboloJugador)
		
	}
	
	public void randomizePlayers() {
		
		if(rand.nextBoolean() == true) {
			
			simboloJugador = "X";
			
		} else {
			
			simboloJugador = "O";
			
		}
		
		if(simboloJugador == "X") {
			
			simboloIA = "O";
			
		} else {
			
			simboloIA = "X";
			
		}
		
		if(rand.nextBoolean() == true) {
			
			consoleOrPlayer = "Console";
			
		} else {
			
			consoleOrPlayer = "Player";
			
		}
		
	}
	
	public void IA_juega() {
		
		consoleOrPlayer = "Console";
		Utilities.logs("Turno IA");
			
		if(consoleOrPlayer == "Console") {
			
			for(int i = 0; i < botones.length; i++) {
				
				for(int j = 0; j < botones.length; j++) {

					if(botones[i][j].getXO() == simboloIA) {
						//	---	/	ALGORITHM	/	---
						
						//	SI PUEDE BLOQUEAR
						
						
						
						//	--	/END/ SI PUEDE BLOQUEAR	--
						
						//	SI PUEDE GANAR
						
						try {
							
							if(botones[i+1][j].getXO() == "" && consoleOrPlayer == "Console" && botones[i-1][j].getXO() == simboloIA) {
								
								botones[i+1][j].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i][j+1].getXO() == "" && consoleOrPlayer == "Console" && botones[i][j-1].getXO() == simboloIA) {
								
								botones[i][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j].getXO() == "" && consoleOrPlayer == "Console" && botones[i+1][j].getXO() == simboloIA) {
								
								botones[i-1][j].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
						} catch(Exception e) {}
						
						try {
							
							if(botones[i][j-1].getXO() == "" && consoleOrPlayer == "Console" && botones[i][j+1].getXO() == simboloIA) {
								
								botones[i][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						// diagonales
						
						try {
							
							if(botones[i+1][j+1].getXO() == "" && consoleOrPlayer == "Console" && botones[i-1][j-1].getXO() == simboloIA) {
								
								botones[i+1][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j-1].getXO() == "" && consoleOrPlayer == "Console" && botones[i+1][j+1].getXO() == simboloIA) {
								
								botones[i-1][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i+1][j-1].getXO() == "" && consoleOrPlayer == "Console" && botones[i-1][j+1].getXO() == simboloIA) {
								
								botones[i+1][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j+1].getXO() == "" && consoleOrPlayer == "Console" && botones[i+1][j-1].getXO() == simboloIA) {
								
								botones[i-1][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						//	--	/END/ SI PUEDE GANAR	--
						
						
						
						
						try {
							
							if(botones[i+1][j].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i+1][j].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i][j+1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i-1][j].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i][j-1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						// diagonales
						
						try {
							
							if(botones[i+1][j+1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i+1][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j-1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i-1][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i+1][j-1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i+1][j-1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
						try {
							
							if(botones[i-1][j+1].getXO() == "" && consoleOrPlayer == "Console") {
								
								botones[i-1][j+1].setXO(simboloIA);
								consoleOrPlayer = "Player";
									
							}
							
						} catch(Exception e) {}
						
					}
						
				}
					
			}
				
			if(firstTime) {
				Utilities.logs("IA First time");
				firstTime = false;
					
				while(consoleOrPlayer == "Console") {
						
					int a = rand.nextInt(3);
					int b = rand.nextInt(3);
						
					if(botones[a][b].getXO().equals("")) {
							
						botones[a][b].setXO(simboloIA);
						consoleOrPlayer = "Player";
						break;
							
					}
					
				}
					
			}
							
		}
		
		consoleOrPlayer = "Player";
		
	}
	
	private Botones[][] botones = new Botones[3][3];
	private JPanel game, sideMenu;
	private JButton salir = new JButton("Volver al menu");
	private JLabel turno = new JLabel("    Turno  ");
	private JLabel turnoXO;
	private String consoleOrPlayer, simboloIA, simboloJugador;
	private boolean firstTime = true;
	private Random rand = new Random();
	
}

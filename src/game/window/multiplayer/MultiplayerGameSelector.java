package game.window.multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.util.Utilities;
import game.window.WindowManagement;
import game.window.multiplayer.mysqlConnection.DBConnection;
import game.window.multiplayer.serverConnection.ServerConnection;

public class MultiplayerGameSelector extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9200249265196896112L;

	public MultiplayerGameSelector() {
		
		this.setBackground(new Color(47, 47, 47));
		this.setLayout(new BorderLayout());
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(39, 39, 39));
		add(panelInfo, BorderLayout.PAGE_START);
		
		usuario = new JLabel(DBConnection.username);
		infoWins = new JLabel(" Partidas ganadas: 16 ");
		usuario.setForeground(Color.WHITE);
		infoWins.setForeground(Color.WHITE);
		usuario.setFont(new Font("Impact", Font.ITALIC, 50));
		infoWins.setFont(new Font("Impact", Font.ITALIC, 20));
		
		panelInfo.add(usuario);
		panelInfo.add(infoWins);
		
		// Creaci�n de la secci�n "Crear partida" "Unirse a partida"
		
		JPanel matchBuilder = new JPanel();
		this.add(matchBuilder, BorderLayout.CENTER);
		matchBuilder.setLayout(new BorderLayout(50,50));
		matchBuilder.setBackground(new Color(47, 47, 47));
		gameCode = new JTextField("00000000", 8);
		botonCrear = new JButton("Crear nueva partida");
		botonEntrar = new JButton("Entrar a la partida");
		botonCrear.setFont(new Font("Impact", Font.ITALIC, 20));
		botonEntrar.setFont(new Font("Impact", Font.ITALIC, 20));
		botonEntrar.setBackground(new Color(47, 47, 47));
		botonCrear.setBackground(new Color(47, 47, 47));
		botonEntrar.setForeground(Color.WHITE);
		botonCrear.setForeground(Color.WHITE);
		gameCode.setBackground(new Color(47, 47, 47));
		gameCode.setFont(new Font("Impact", Font.BOLD, 64));
		gameCode.setForeground(Color.WHITE);
		gameCode.setHorizontalAlignment(JTextField.CENTER);
		matchBuilder.add(gameCode, BorderLayout.CENTER);
		matchBuilder.add(botonCrear, BorderLayout.WEST);
		matchBuilder.add(botonEntrar, BorderLayout.EAST);
		
		spacer = new JLabel("Pegá un código o crea una nueva partida!");
		spacer.setBackground(new Color(47, 47, 47));
		spacer.setFont(new Font("Impact", Font.ITALIC, 24));
		spacer.setForeground(Color.WHITE);
		spacer.setHorizontalAlignment(JLabel.CENTER);
		spacer.setVerticalAlignment(JLabel.CENTER);
		matchBuilder.add(spacer, BorderLayout.SOUTH);
		spacer2 = new JLabel();
		spacer2.setBackground(new Color(47, 47, 47));
		spacer2.setFont(new Font("Impact", Font.ITALIC, 24));
		spacer2.setForeground(Color.WHITE);
		spacer2.setHorizontalAlignment(JLabel.CENTER);
		JPanel spacer2JPanel = new JPanel();
		spacer2JPanel.add(spacer2);
		spacer2JPanel.setBackground(new Color(47, 47, 47));
		matchBuilder.add(spacer2JPanel, BorderLayout.NORTH);
		
		// Clicks en los botones...
		botonCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ServerConnection.deleteGame();
				random = new Random();
				gameID = "" + (random.nextInt(90000000)+10000000);
				ServerConnection.createNewGame(gameID);
				gameCode.setText(""+gameID);
				spacer.setText("Copia el código y envíaselo a tu amigo para jugar con él! ");
				
				if(random.nextInt(2) == 0)
					simbolo = "X";
				else
					simbolo = "O";
				ServerConnection.cargarUserAPartida(gameID, ServerConnection.username, simbolo);
				MultiplayerGameSelector.playing = true;
			}
			
		});
		
		botonEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				gameID = gameCode.getText();
				ServerConnection.gameID = gameID;
				ServerConnection.getOtherPlayerInfo(gameID);
				info = ServerConnection.getOtherPlayerInfo(gameID);
				ServerConnection.close();
				MultiplayerGameSelector.playing = true;
				new Thread(new Runnable() {

					@Override
					public void run() {
						while(simbolo.equals("null") || Multiplayer.simbolo.equals("null")) {
							info = ServerConnection.getOtherPlayerInfo(gameID);
							ServerConnection.close();
							if(info.get(1).equals("O")) {
								
								simbolo = "X";
								Multiplayer.simbolo = "X";
								Multiplayer.simboloRival = "O";
								
							} else if(info.get(1).equals("X")) {
								
								simbolo = "O";
								Multiplayer.simbolo = "O";
								Multiplayer.simboloRival = "X";
								
							} else
								simbolo = "null";
							
							ServerConnection.cargarUserAPartida(gameID, ServerConnection.username, simbolo);

						}
						
					}

				}).start();
				
			}
			
		});
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) { //Loop infinito para cargar las stats del jugador.
						usuario.setText(ServerConnection.username + "  ");
						infoWins.setText(" Partidas ganadas: " + ServerConnection.wins + "  ");
						if(WindowManagement.modo.equals("MultiplayerGameSelector") && playing) {
							try {
								if(infoOn) {
									info = ServerConnection.getOtherPlayerInfo(gameID);
									ServerConnection.close();
								}
						
								ServerConnection.getReadyPlayers(gameID);
								readyPlayers = ServerConnection.getResponse();
								ServerConnection.close();
								
								spacer2.setText("Jugadores listos para que empiece la partida " + readyPlayers + "/2 ");
								if(readyPlayers.equals("2")) {
									infoOn = true;
									startGame();
								}
								
								Thread.sleep(1000);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						
					}
				} catch(Exception e) {}
				
				
			}
			
			
		}).start();
			
		
		
	}
	
	public void startGame() {

		if(!startGameLlamado) {
			startGameLlamado = true;
			//Countdown
			new Thread(new Runnable() {

				@Override
				public void run() {
					int timer = 5;
					while(timer > 0) {
						
						spacer.setText("Iniciando partida en "+timer+" segundos...");
						timer--;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {}
					}
					
					Utilities.logs("Partida '" + gameID + "' iniciada.");
					
					WindowManagement.render("Multiplayer");
					
					
					
					
				}
				
			}).start();
			
		}		
	}
	
	private JLabel usuario, infoWins, spacer, spacer2;
	private JTextField gameCode;
	private JButton botonCrear, botonEntrar;
	private Random random;
	public static String readyPlayers = "0";
	public static String gameID;
	public static String simbolo = "null";
	private boolean startGameLlamado = false;
	public static ArrayList<String> info;
	public static boolean playing = false, infoOn = false;
	
}

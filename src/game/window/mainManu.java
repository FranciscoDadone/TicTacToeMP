package game.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.util.Utilities;

class mainMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 844313111330627883L;
	public mainMenu() {
		
		this.setLayout(null);
		this.setBackground(new Color(47, 47, 47));
		
		title = new JLabel("# Tic Tac Toe #");
		title.setBounds((Utilities.width/2)-100, 10, 400, 50);
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(new Color(255,255,255));
		add(title);
		
		
		Singleplayer = new JButton("Singleplayer");
		Singleplayer.setBounds(30, (Utilities.height/2)-50, 200, 50);
		Singleplayer.setBackground(new Color(255,255,255));
		Singleplayer.setFont(new Font("Impact", Font.PLAIN, 30));
		Singleplayer.setForeground(new Color(47,47,47));
		add(Singleplayer);
		
		Multiplayer = new JButton("Multiplayer");
		Multiplayer.setBounds(30, (Utilities.height/2)+10, 200, 50);
		Multiplayer.setBackground(new Color(255,255,255));
		Multiplayer.setFont(new Font("Impact", Font.PLAIN, 30));
		Multiplayer.setForeground(new Color(47,47,47));
		add(Multiplayer);
		
		tictactoeImg = new JLabel("#");
		
		tictactoeImg.setBounds(500, 100, 300, 300);
		tictactoeImg.setForeground(new Color(255,255,255));
		tictactoeImg.setFont(new Font("Impact", Font.PLAIN, 300));
		
		add(tictactoeImg);
		
		// Listeners para botones
		
		Singleplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				WindowManagement.render("Singleplayer");
				
			}
			
		});
		
		Multiplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				WindowManagement.render("MultiplayerLogin");
				
			}
			
		});
		
	}
	
	private JLabel title, tictactoeImg;
	private JButton Singleplayer, Multiplayer;
	
}

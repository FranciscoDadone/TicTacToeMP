package game.window.multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import game.util.Utilities;
import game.window.WindowManagement;
import game.window.multiplayer.serverConnection.Packet;
import game.window.multiplayer.serverConnection.ServerConnection;

public class MultiplayerLogin extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296268066640427L;
	public MultiplayerLogin() {

		GridBagConstraints IPf = new GridBagConstraints(), svIP = new GridBagConstraints(), success = new GridBagConstraints(), regBut = new GridBagConstraints(), loginBut=new GridBagConstraints(), userFieldRegister= new GridBagConstraints(), passFieldRegisterLayout = new GridBagConstraints(), passReg = new GridBagConstraints(), titulo = new GridBagConstraints(), tituloRegister = new GridBagConstraints(), user= new GridBagConstraints(), userRegister = new GridBagConstraints(), userField= new GridBagConstraints(), pass= new GridBagConstraints(), passField1 = new GridBagConstraints();
		
		svIP.gridx = 0;
		svIP.gridy=-1;
		svIP.anchor = GridBagConstraints.EAST;
		
		IPf.gridx = 1;
		IPf.gridy=0;
		
		titulo.gridx = 0;
		titulo.gridy=1;
		titulo.anchor = GridBagConstraints.FIRST_LINE_START;
		
		tituloRegister.gridx = 1;
		tituloRegister.gridy=1;
		tituloRegister.anchor = GridBagConstraints.CENTER;
		
		titulo.gridx = 0;
		titulo.gridy=1;
		titulo.anchor = GridBagConstraints.CENTER;

		user.gridx = 0;
		user.ipady = 5;
		user.gridy=1;
		user.anchor = GridBagConstraints.WEST;
		
		userRegister.gridx = 1;
		userRegister.gridy=2;
		userRegister.anchor = GridBagConstraints.WEST;

		userField.gridx = 0;
		userField.gridy=3;
		userField.anchor = GridBagConstraints.WEST;
		
		userFieldRegister.gridx = 1;
		userFieldRegister.gridy=3;
		userFieldRegister.anchor = GridBagConstraints.WEST;

		pass.gridx = 0;
		pass.gridy=4;
		pass.anchor = GridBagConstraints.WEST;
		
		passReg.gridx = 1;
		passReg.gridy=4;
		passReg.anchor = GridBagConstraints.WEST;

		passField1.gridx = 0;
		passField1.gridy=5;
		passField1.anchor = GridBagConstraints.WEST;
		
		passFieldRegisterLayout.gridx = 1;
		passFieldRegisterLayout.gridy=5;
		passFieldRegisterLayout.anchor = GridBagConstraints.WEST;
		
		loginBut.gridx = 0;
		loginBut.gridy=6;
		loginBut.insets = new Insets(10,0,0,0);
		loginBut.anchor = GridBagConstraints.WEST;
		
		regBut.gridx = 1;
		regBut.gridy=6;
		regBut.insets = new Insets(10,0,0,0);
		regBut.anchor = GridBagConstraints.WEST;
		
		success.gridx = 0;
		success.gridy=8;
		success.anchor = GridBagConstraints.WEST;
		
		
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(47, 47, 47));
		
		serverIP = new JLabel("IP:PUERTO del servidor:   ");
		serverIP.setForeground(Color.WHITE);
		serverIP.setFont(new Font("Impact", Font.ITALIC, 28));
		add(serverIP, svIP);
		
		loginMainLabel = new JLabel("Inicia sesión / ");
		loginMainLabel.setForeground(Color.WHITE);
		loginMainLabel.setFont(new Font("Impact", Font.ITALIC, 58));
		add(loginMainLabel, titulo);
		
		registerMainLabel = new JLabel("Registrate ");
		registerMainLabel.setForeground(Color.WHITE);
		registerMainLabel.setFont(new Font("Impact", Font.ITALIC, 58));
		add(registerMainLabel, tituloRegister);
		
		LoginUsuarioLabel = new JLabel("Usuario: ");
		LoginUsuarioLabel.setForeground(Color.WHITE);
		LoginUsuarioLabel.setFont(new Font("Impact", Font.ITALIC, 28));
		add(LoginUsuarioLabel, user);
		
		RegisterUsuarioLabel = new JLabel(" Usuario: ");
		RegisterUsuarioLabel.setForeground(Color.WHITE);
		RegisterUsuarioLabel.setFont(new Font("Impact", Font.ITALIC, 28));
		add(RegisterUsuarioLabel, userRegister);
		
		usuarioField = new JTextField("", 24);
		usuarioField.setMaximumSize(usuarioField.getPreferredSize());
		add(usuarioField, userField);
		
		IPField = new JTextField("Ejemplo: 100.100.100.100:9999", 24);
		IPField.setMaximumSize(IPField.getPreferredSize());
		add(IPField, IPf);
		
		usuarioFieldRegister = new JTextField(24);
		usuarioFieldRegister.setMaximumSize(usuarioField.getPreferredSize());
		add(usuarioFieldRegister, userFieldRegister);
		
		passFieldRegister = new JPasswordField(24);
		passFieldRegister.setMaximumSize(usuarioField.getPreferredSize());
		add(passFieldRegister, passFieldRegisterLayout);
		
		passLabel = new JLabel("Contraseña: ");
		passLabel.setForeground(Color.WHITE);
		passLabel.setFont(new Font("Impact", Font.ITALIC, 28));
		add(passLabel, pass);
		
		registerPassLabel = new JLabel(" Contraseña: ");
		registerPassLabel.setForeground(Color.WHITE);
		registerPassLabel.setFont(new Font("Impact", Font.ITALIC, 28));
		add(registerPassLabel, passReg);
		
		passField = new JPasswordField("", 24);
		passField.setMaximumSize(passField.getPreferredSize());
		add(passField, passField1);
		
		loginButton = new JButton("Entrar");
		loginButton.setBackground(new Color(47, 47, 47));
		loginButton.setForeground(Color.WHITE);
		add(loginButton, loginBut);
		
		registerButton = new JButton("Registrarse");
		registerButton.setBackground(new Color(47, 47, 47));
		registerButton.setForeground(Color.WHITE);
		add(registerButton, regBut);
		
		successMessage = new JLabel("Cuenta creada correctamente! Inicia sesión");
		successMessage.setVisible(false);
		successMessage.setForeground(new Color(66, 233, 62));
		successMessage.setFont(new Font("Impact", Font.ITALIC, 18));
		add(successMessage, success);
		
		// FIN DE LA CREACI�N DE LA UI
		
		//BACKEND CONNECTION
		
		loginButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				Packet paquete = new Packet();
				paquete.setUsername(usuarioField.getText());
				paquete.setPassword(passField.getText());
				paquete.setDatabaseChange("USER AUTH: login");
				paquete.setGameID(ServerConnection.gameID);
				new ServerConnection(paquete);
				
				serverReply = ServerConnection.getResponse();
				if(!serverReply.equals("Success"))
					JOptionPane.showMessageDialog(null, serverReply, "Error en el login", JOptionPane.ERROR_MESSAGE);
				else {
					WindowManagement.render("MultiplayerGameSelector");
				}
				ServerConnection.close();
				
				ServerConnection.getWins();
				ServerConnection.wins = ServerConnection.getResponse();
				ServerConnection.close();
				
			}
			
		});
		
		registerButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Packet paquete = new Packet();
					paquete.setUsername(usuarioFieldRegister.getText());
					paquete.setPassword(passFieldRegister.getText());
					paquete.setDatabaseChange("USER AUTH: register");
					paquete.setGameID(ServerConnection.gameID);
					new ServerConnection(paquete);
					
					serverReply = ServerConnection.getResponse();
					if(serverReply.equals("Success"))
						successMessage.setVisible(true);
					else 
						JOptionPane.showMessageDialog(null, serverReply, "Error al crear la cuenta", JOptionPane.ERROR_MESSAGE);
					ServerConnection.close();
				} catch (Exception e1) {
					Utilities.logs("Error al registrar el usuario");
					e1.printStackTrace();
				}
			
			}
			
		});
		
	}
	
	public static String getUsername() {
		return usuarioField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public static String getPassword() {
		return passField.getText();
	}
	
	
	private JLabel loginMainLabel, serverIP, successMessage, registerPassLabel, registerMainLabel, LoginUsuarioLabel, RegisterUsuarioLabel, passLabel;
	public static JTextField usuarioField, usuarioFieldRegister, IPField;
	private static JPasswordField passField, passFieldRegister;
	private JButton loginButton, registerButton;
	private String serverReply;
	
}

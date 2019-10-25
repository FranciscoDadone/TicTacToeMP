package game.window.multiplayer.serverConnection;

import java.io.Serializable;

public class Packet implements Serializable {

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUsername() {
		return username;
	}

	public String getDatabaseChange() {
		return databaseChange;
	}

	public void setDatabaseChange(String databaseChange) {
		this.databaseChange = databaseChange;
	}



	private String username, password, databaseChange;
}

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
	
	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getSimboloRival() {
		return simboloRival;
	}

	public void setSimboloRival(String simboloRival) {
		this.simboloRival = simboloRival;
	}
	
	public String getOtherPlayerUSERNAME() {
		return otherPlayerUSERNAME;
	}

	public void setOtherPlayerUSERNAME(String otherPlayerUSERNAME) {
		this.otherPlayerUSERNAME = otherPlayerUSERNAME;
	}

	public String getOtherPlayerSIMBOLO() {
		return otherPlayerSIMBOLO;
	}

	public void setOtherPlayerSIMBOLO(String otherPlayerSIMBOLO) {
		this.otherPlayerSIMBOLO = otherPlayerSIMBOLO;
	}

	public String getOtherPlayerTURNO() {
		return otherPlayerTURNO;
	}

	public void setOtherPlayerTURNO(String otherPlayerTURNO) {
		this.otherPlayerTURNO = otherPlayerTURNO;
	}

	public String getOtherPlayerWIN() {
		return otherPlayerWIN;
	}

	public void setOtherPlayerWIN(String otherPlayerWIN) {
		this.otherPlayerWIN = otherPlayerWIN;
	}

	public String getOtherPlayerMOVE_X() {
		return otherPlayerMOVE_X;
	}

	public void setOtherPlayerMOVE_X(String otherPlayerMOVE_X) {
		this.otherPlayerMOVE_X = otherPlayerMOVE_X;
	}

	public String getOtherPlayerMOVE_Y() {
		return otherPlayerMOVE_Y;
	}

	public void setOtherPlayerMOVE_Y(String otherPlayerMOVE_Y) {
		this.otherPlayerMOVE_Y = otherPlayerMOVE_Y;
	}
	
	public String getWins() {
		return wins;
	}

	public void setWins(String wins) {
		this.wins = wins;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	private String username, password, databaseChange, gameID, simbolo, simboloRival, wins;
	private String otherPlayerUSERNAME, otherPlayerSIMBOLO, otherPlayerTURNO, otherPlayerWIN, otherPlayerMOVE_X, otherPlayerMOVE_Y;
	private int x, y;

}

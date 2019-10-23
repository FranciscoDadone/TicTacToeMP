package game.window;

import javax.swing.JButton;

public class Botones extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7431024899189977585L;

	public Botones(String XO) {
		super(XO);
		this.XO = XO;
		
	}
	
	public String getXO() {
		
		return this.XO;
		
	}
	
	public void setXO(String xo) {
		
		this.XO = xo;
		this.setText(xo);
		this.setEnabled(false);
		
	}
	
	public String txt, XO;
	
}

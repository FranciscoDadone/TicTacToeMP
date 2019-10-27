package game.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.time.LocalDateTime;

public class Utilities {
	
	public static void logs(String message) {
		
		System.out.println("[" + LocalDateTime.now().getHour() + ":" 
							+ LocalDateTime.now().getMinute() + ":" 
							+ LocalDateTime.now().getSecond() + "]" + " - " + message);
		
	}
	
	public static void font(String fontName) {		
		try {
			new fontLoader(fontName);
		} catch (Exception e) {
		     Utilities.logs("Error al cargar la fuente " + fontName);
		     e.printStackTrace();
		}
	}
	
	public static int width = 800;
	public static int height = 500;
	
}

class fontLoader {
	
	public fontLoader(String fontname) throws Exception {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("res/" + fontname + ".ttf")));
		     Utilities.logs("Fuente '" + fontname + "' cargada correctamente.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

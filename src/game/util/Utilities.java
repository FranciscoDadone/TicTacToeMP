package game.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class Utilities {
	
	public static void logs(String message) {
		
		System.out.println("[" + LocalDateTime.now().getHour() + ":" 
							+ LocalDateTime.now().getMinute() + ":" 
							+ LocalDateTime.now().getSecond() + "]" + " - " + message);
		
	}
	
	public static void font(String fontName) {
		
		String filename = "res//" + fontName + ".ttf";
		
		try {
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(filename)));
		     Utilities.logs("Fuente '" + fontName + "' cargada correctamente.");
		} catch (IOException|FontFormatException e) {
		     Utilities.logs("Error al cargar la fuente " + fontName);
		}

	}
	
	public static int width = 800;
	public static int height = 500;
	
}

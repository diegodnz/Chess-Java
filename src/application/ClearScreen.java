package application;

import java.io.IOException;

public class ClearScreen {
	
	public static void clear() {
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		try {			
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
}
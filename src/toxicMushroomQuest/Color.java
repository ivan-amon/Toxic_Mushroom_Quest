package toxicMushroomQuest;

public class Color {
	
	//VALORES DE COLORES PUBLICOS
	private String red = "\u001B[31m";
	private String orange = "\u001B[33m";
	private String yellow = "\u001B[33m";
	private String blue = "\u001B[34m";
	private String cyan = "\u001B[36m";
	private String lime = "\u001B[92m";
	private String purple = "\u001B[35m";
	private String resetarColor = "\u001B[0m";
	
	
	public Color() {
		
	}

	//GETTERS
	
	public String getRed() {
		return red;
	}

	public String getOrange() {
		return orange;
	}

	public String getYellow() {
		return yellow;
	}

	public String getResetarColor() {
		return resetarColor;
	}

	public String getBlue() {
		return blue;
	}

	public String getCyan() {
		return cyan;
	}

	public String getLime() {
		return lime;
	}

	public String getPurple() {
		return purple;
	}
}
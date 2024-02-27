package toxicMushroomQuest;

public class Jugador extends Personaje {
	
	//ATRIBUTOS
	final static int VIDA_INICIAL = 3;
	private int vida = VIDA_INICIAL;
	private int mochila = 0;

	private boolean llaveHabitacion = false;

	//Constructor
    public Jugador() {
    }

    //Getters && setters
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean getLlaveHabitacion() {
		return llaveHabitacion;
	}

	public void setLlaveHabitacion() {
		this.llaveHabitacion = true;
	}

	public int getMochila() {
		return this.mochila;
	}

	public void setMochila(int mochila) {
		this.mochila = mochila;
	}

}
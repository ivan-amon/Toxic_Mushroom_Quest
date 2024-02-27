package toxicMushroomQuest;

public class ObjetoJuego {
	
	//JUGADOR
	final static int JUGADOR = 0;

	//PUERTAS
	final static int PUERTA_IN = 1;
	
	//TODOS LOS HONGOS DEL JUEGO
	final static int HONGO_1 = 2;

	final static int HONGO_20 = 21;

	//OBJETOS
	final static int LLAVE_1 = 22;
	final static int LLAVE_3 = 24;
	final static int VIDA = 25;
	final static int PORTAL_SALIDA = 26;
	
	
	//ATRIBUTOS
	private String nombre;
	private Posicion pos;
	private int tipoObjeto = -1;
	private char letraMapa = ' ';

	public ObjetoJuego() {
		
	}

	
	//GETTERS & SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	public char getLetraMapa() {
		return letraMapa;
	}

	public void setLetraMapa(char letraMapa) {
		this.letraMapa = letraMapa;
	}
	
	public int getTipoObjeto() {
		return tipoObjeto;
	}
	
	public void setTipoObjeto(int tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}
}
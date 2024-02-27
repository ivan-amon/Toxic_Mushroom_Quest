package toxicMushroomQuest;

public class Habitacion {

    //CONSTANTES
   
    public static final int ALTO = 10;
    public static final int ANCHO = 30;

    //ATRIBUTOS
    private Posicion puertaEntrada;
    private Posicion puertaSalida;
    
    //ARRAY DE OBJETOS
    private ObjetoJuego[] objetosJ = new ObjetoJuego[30]; 
    private int numObjetos = 0;

    //MÉTODOS
    //Constructor
    public Habitacion() {

    }

    //hayObjeto: SI ENCUENTRA EN LA POSICION QUE ENTRA COMO PARAMETRO DEVUELVE LA POSICION, SI NO DEVUELVE -1
    public int hayObjeto(Posicion p) {

    	for(int i = 0; i < numObjetos; i++) {
    		
    		ObjetoJuego obj = objetosJ[i];
    		Posicion objPosicion = obj.getPos();
    		
    		if(p.esIgual(objPosicion) || p.esIgual(objPosicion)) return i;
    	}
    	return -1;
    }

    //eliminarObjeto: ELIMINA DEL ARRAY DE OBJETOS EL OBJETO CUYA POSICION ENTRA COMO PARAMETRO
	public void eliminarObjetoJ(int objPos) {
		int posObjetos=0;
        
        if(objPos != -1) {
		for (int i=0;i<numObjetos;i++) {
			if(objPos!=i) {
				objetosJ[posObjetos]=objetosJ[i];
				posObjetos++;
			}
		}
    }
		numObjetos--;
	}
       
    //hayJugadorPuertaIn: DICE SI HAY O NO HAY UN JUGADOR EN LA PUERTA DE ENTRADA
    public boolean hayJugadorPuertaIn(Posicion p) {
    	
    	if(p.esIgual(getPuertaEntrada())) {
    		return true;
    	} else
    		return false;
    }
    
    //Getters & Setters
    public Posicion getPuertaEntrada() {
        return puertaEntrada;
    }
    
    public void setPuertaEntrada(Posicion puertaEntrada) {
        this.puertaEntrada = puertaEntrada;
    }

    public Posicion getPuertaSalida() {
        return puertaSalida;
    }

    public void setPuertaSalida(Posicion puertaSalida) {
        this.puertaSalida = puertaSalida;
    }

    public void setObjetoJ(ObjetoJuego obj) {
    	this.objetosJ[this.numObjetos] = obj;
    	this.numObjetos++;
    }
    
    public ObjetoJuego getObjetoJuego(int objPosicion) {
    	return objetosJ[objPosicion];
    }

    public int getNumObjetos() {
        return numObjetos;
    }
}

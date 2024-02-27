package toxicMushroomQuest;

public class Personaje extends ObjetoJuego{

    public Personaje() {
    }
    
    //METODOS
    public void moverPosX(int movX) {
    	Posicion posicionPers = super.getPos();
    	posicionPers.setPosX(posicionPers.getPosX()+movX);
    }
    
    public void moverPosY(int movY) {
    	Posicion posicionPers = super.getPos();
    	posicionPers.setPosY(posicionPers.getPosY()+movY);
    }
        
    public int getPosX() {
    	Posicion posicionPers = super.getPos();
    	return posicionPers.getPosX();
    }
    
    public int getPosY() {
    	Posicion posicionPers = super.getPos();
    	return posicionPers.getPosY();
    }
}
package toxicMushroomQuest;

public class Posicion {

    //ATRIBUTOS
    private int posX;
    private int posY;

    //CONSTRUCTOR
    Posicion(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    //METODOS
    public boolean esIgual(Posicion pos) {
    	
        boolean correcto = false;
        
        if(pos.posX == this.posX && pos.posY == this.posY) {
            correcto = true;
        }

        return correcto;
    }

    //GETTERS & SETTERS
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
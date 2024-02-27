package toxicMushroomQuest;

public class Llave extends ObjetoJuego {

    //Atributos
    public static int MAX_NUM_LLAVES = 3;
    //Metodos
    public Llave() {

    }

    //Métodos
    public static boolean abrirPortal(int numLLaves) {

        boolean portalAbierto = false;

        if(numLLaves == MAX_NUM_LLAVES) 
            portalAbierto = true;

        return portalAbierto;
    }
}

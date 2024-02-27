package toxicMushroomQuest;

import java.util.concurrent.ThreadLocalRandom;

public class Hongo extends Personaje{

    //ATRIBUTOS
    boolean visible = true;

    //METODOS
    
    //CONSTRUCTOR
    public Hongo() {
    	
    }

    //cambioVisibilidadAleatorio: Esto permitira a los hongos que se hagan invisibles 
    public boolean cambioVisibilidadAleatorio() {

      boolean cambio;
      int numAleatorio = ThreadLocalRandom.current().nextInt(1, 4);

      if(numAleatorio == 1) {
        cambio = true;
      } else
        cambio = false;
      return cambio;
    }

	}


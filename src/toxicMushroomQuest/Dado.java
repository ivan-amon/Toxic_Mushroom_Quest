package toxicMushroomQuest;

import java.util.concurrent.ThreadLocalRandom;

public class Dado {


	
	//MÉTODOS

	//rand: FUNCION QUE DA UN NUMERO ALEATORIO ENTRE UN VALOR INF Y UNO SUP
	public static int rand(int numInf, int numSup) {

		if(numInf < numSup) {
	        int auxiliar = numInf;
	        numInf = numSup;
	        numSup = auxiliar;
		}
		
	    int randomNumber = ThreadLocalRandom.current().nextInt(numInf, numSup+1);
	   
	    return randomNumber;
	}
	
    //lanzarDado: DA UN VALOR INT ALEATORIO
    public static int lanzarDado(int numCaras) { 

        int dado = ThreadLocalRandom.current().nextInt(1,numCaras+1); 
        System.out.println("[DADO LANZADO]: "+dado);

        return dado;
    }
    
}

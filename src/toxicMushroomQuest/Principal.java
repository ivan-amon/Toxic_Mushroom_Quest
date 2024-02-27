package toxicMushroomQuest;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {

    //verificacionObjeto: DICE SI EN LA POSICION DEL JUGADOR HAY UN OBJETO
    public static int verificacionObjeto(Posicion posJ, Habitacion h) {

        int obj = hayObjetoSinJugador(posJ, h);

        return obj;

    }

    //hayObjetoSinJugador: SI ENCUENTRA EN LA POSICION QUE ENTRA COMO PARAMETRO DEVUELVE LA POSICION, (EXCLUYENDO LA DEL JUGADOR)
    public static int hayObjetoSinJugador(Posicion p, Habitacion h) {

        int posicionObjeto = -1;

    	for(int i=1; i < h.getNumObjetos(); i++) { //SE EMPIEZA POR EL UNO, EL JUEGADOR TIENE ASIGNADO EL 0: objetosJ[0];

    		ObjetoJuego obj =  h.getObjetoJuego(i);
    		Posicion objPosicion = obj.getPos();
    		
    		if(p.esIgual(objPosicion)) posicionObjeto = i; 
        }
    	return posicionObjeto;
    }
   
    //rand: DA UN VALOR RANDOM ENTRE EL NUM INF Y NUM SUP (DA IGUAL EL ORDEN EN EL QUE SE INTRODUZA)
	public static int rand(int numInf, int numSup) {
		
        int randomNumber = ThreadLocalRandom.current().nextInt(numInf, numSup+1);
       
        return randomNumber;
	}

    public static void main(String[] args) {

    	//VARIABLES/OBJETOS DEL JUEGO
        int opcionSeleccionada;
        int valorDado;
        int objeto = 0;
        int dificultadJuego;
        Scanner scn = new Scanner(System.in);
        Color colorMain = new Color();
        
        //CREACION DE LAS POSICIONES
        Posicion posicionIn = new Posicion(0, 2);
        Posicion posicionInJugador = new Posicion(posicionIn.getPosX(), posicionIn.getPosY());
        Posicion posVida = Juego.posAleatoria();
        Posicion posPortal = Juego.posAleatoria();
        
        //CREACION DE LA PUERTA DE ENTRADA
        ObjetoJuego puertaIn  = new ObjetoJuego();
        puertaIn.setPos(posicionIn);
        puertaIn.setTipoObjeto(ObjetoJuego.PUERTA_IN);
        puertaIn.setLetraMapa(' ');

        //mainCharacter
        Jugador mainCharacter = new Jugador();
        mainCharacter.setPos(posicionInJugador);
        mainCharacter.setTipoObjeto(ObjetoJuego.JUGADOR);
        mainCharacter.setLetraMapa('J');
        
        //CREAMOS 15 HONGOS
        Hongo[] hongos = new Hongo[15];
        
        //5 HONGOS EN LA HABITACION
        for(int i = 0; i < 15; i++) {
        hongos[i] = new Hongo();
        hongos[i].setPos(Juego.posAleatoria());  
        hongos[i].setTipoObjeto(ObjetoJuego.HONGO_1 + i);
        hongos[i].setLetraMapa('Ø');
        }

        //CREAMOS 3 LLAVES
        ObjetoJuego[] llaves = new ObjetoJuego[3];

        //3 LLAVES EN LA HABITACION
        for(int i = 0; i < 3; i++) {
        llaves[i] = new ObjetoJuego();
        llaves[i].setPos(Juego.posAleatoria());
        llaves[i].setTipoObjeto(ObjetoJuego.LLAVE_1 + i);
        llaves[i].setLetraMapa('X');
        }
        
        //VIDA
        ObjetoJuego vida = new ObjetoJuego();
        vida.setPos(posVida);
        vida.setTipoObjeto(ObjetoJuego.VIDA);
        vida.setLetraMapa('+');

        //PORTAL
        ObjetoJuego portal = new ObjetoJuego();
        portal.setPos(posPortal);
        portal.setTipoObjeto(ObjetoJuego.PORTAL_SALIDA);
        portal.setLetraMapa('█');
        
        //CREACION DE LA HABITACION CON LOS OBJETOS
        Habitacion habitacion = new Habitacion();
        habitacion.setObjetoJ(mainCharacter);
        habitacion.setObjetoJ(puertaIn);
        habitacion.setObjetoJ(vida);

        
        //SETEAMOS LOS HONGOS EN LA HABITACION
        for(int i = 0; i < hongos.length; i++) {
        	habitacion.setObjetoJ(hongos[i]);
        }

        //SETEAMOS LAS LLAVES EN LA HABITACION
        for(int i = 0; i < llaves.length; i++) {
            habitacion.setObjetoJ(llaves[i]);
        }

		//SE DESPLIEGA EL INICIO
		System.out.println();
		dificultadJuego = Juego.pintarInicio(scn, colorMain.getLime(), colorMain.getResetarColor());

        //INTERACCION CON EL USUARIO
        do {

        Juego.pintarMenu(colorMain.getLime(), colorMain.getResetarColor()); //SE DESPLIEGAN LAS OPCIONES
        Juego.pintarHabitacion(habitacion, mainCharacter); //SE PINTA LA HABITACION
        System.out.print("Seleccione una OPCION: ");
        opcionSeleccionada = scn.nextInt();
        
        //SWITCH PARA OPCIONES
        switch(opcionSeleccionada) {
            
            case Juego.LANZAR_DADO:
            	valorDado = Dado.lanzarDado(8); //SE LANZA EL DADO
            	
            	if(dificultadJuego == 0)
            	Juego.moverUsuarioNoob(valorDado, mainCharacter, habitacion, scn); //MODO FACIL
            	else Juego.moverUsuarioPro(valorDado, mainCharacter, habitacion, scn); //MODO DIFICIL

            	break; 
            
            case Juego.REGLAS_JUEGO:
            	Juego.reglasJuego(colorMain.getLime(), colorMain.getResetarColor()); //SE MUESTRAN LAS REGLAS E INTRUCCIONES DEL JUEGO
            	break;
            	
            case Juego.CONFIGURACION_JUEGO:
            	dificultadJuego = Juego.configuracionJuego(colorMain.getLime(), colorMain.getResetarColor(), scn); //SE PUEDE CAMBIAR LA DIFICULTAD DEL JUEGO
            	break;
            
            case Juego.SALIR_JUEGO:
            	System.out.println("\n¡Gracias por jugar! <3\n");

            	scn.close();
            	break;
            	
            default:
            	System.out.println("\nSeleccione una opcion existente... (._.)");
            	break;   	
            }

        //SE ASIGNA EL NUMERO DE OBJETO
        Posicion posActual = mainCharacter.getPos();
        objeto = verificacionObjeto(posActual, habitacion);
        
        //SI EL JUGADOR HA CHOCADO CON UN OBJETO, SE VERIFICA CON QUE OBJETO HA CHOCADO
        if(objeto > 0) {
        	
        	//SI CHOCA CONTRA UN HONGO
        	if(habitacion.getObjetoJuego(objeto).getTipoObjeto() >= ObjetoJuego.HONGO_1 && habitacion.getObjetoJuego(objeto).getTipoObjeto() <= ObjetoJuego.HONGO_20){
        		
        		int vidas = Dado.lanzarDado(4);
        		vidas = Juego.jugadorChocaHongo(vidas, objeto, colorMain.getLime(), colorMain.getResetarColor(), mainCharacter);
        		mainCharacter.setVida(vidas);
        		
        		if(vidas == 0) {
        			opcionSeleccionada = Juego.SALIR_JUEGO; //SE SALE DEL JUEGO
        		}
        	}
        	//SI ENCUENTRA LA LLAVE
        	else if(habitacion.getObjetoJuego(objeto).getTipoObjeto() >= ObjetoJuego.LLAVE_1 && habitacion.getObjetoJuego(objeto).getTipoObjeto() <= ObjetoJuego.LLAVE_3) {
                Juego.llaveEncontrada(colorMain.getOrange(), colorMain.getResetarColor(), mainCharacter);
        	}
        	//SI CONSIGUE UNA VIDA EXTRA
        	else if(habitacion.getObjetoJuego(objeto).getTipoObjeto() == ObjetoJuego.VIDA) {
        		Juego.vidaEncontrada(mainCharacter, colorMain.getRed(), colorMain.getResetarColor());
        	}
        	//SI APARECE EN LA PUERTA DE SALIDA
        	else if(habitacion.getObjetoJuego(objeto).getTipoObjeto() == ObjetoJuego.PORTAL_SALIDA) {
                Juego.habitacionSuperada(habitacion, mainCharacter, colorMain.getPurple(), colorMain.getResetarColor());
                opcionSeleccionada = 0; //SE SALE DEL JUEGO
        	}
        	else if(habitacion.getObjetoJuego(objeto).getTipoObjeto() == ObjetoJuego.VIDA)

        //SI EL JUGADOR HA DESBLOQUEADO 3 LLAVES SE ABRIRA EL PORTAL
        if(Llave.abrirPortal(mainCharacter.getMochila()))
        habitacion.setObjetoJ(portal);     
        	
        //SE ELIMINA EL OBJETO DEL MAPA
        habitacion.eliminarObjetoJ(objeto);
        objeto = -1;        

        }

        //SI EL JUGADOR HA DESBLOQUEADO 3 LLAVES SE ABRIRA EL PORTAL
        if(Llave.abrirPortal(mainCharacter.getMochila()))
        habitacion.setObjetoJ(portal);   
		
		//SE PONDRAN HONGOS ALEATORIAMENTE INVISIBLES
		for(int i = 0; i < hongos.length; i++) {
			hongos[i].visible = hongos[i].cambioVisibilidadAleatorio();
		}

		for(int i = 0; i < hongos.length; i++) {

			if(hongos[i].visible)
				hongos[i].setLetraMapa('Ø');
			else hongos[i].setLetraMapa(' ');
		}


        } while((opcionSeleccionada != Juego.SALIR_JUEGO));  
    }    
}

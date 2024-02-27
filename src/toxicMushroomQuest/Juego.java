package toxicMushroomQuest;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Juego {
	
	//CONSTANTES
	final static int LANZAR_DADO = 1;
	final static int REGLAS_JUEGO = 2;
	final static int CONFIGURACION_JUEGO = 3;
	final static int SALIR_JUEGO = 0;
	final static int DIFICULTAD_FACIL = 0;
	final static int DIFICULTAD_DIFICL = 1;
	final static int ELIMINAR_VIDA = 10;

	//ARRAY DE OBJETOS
	static Posicion[] posiciones = new Posicion[20];
	static int numPosiciones = 0;
	
    public Juego(){

    }

    //MÉTODOS
    
    //pintarHabitacion: PINTA LA HABITACION CON EL PERSONAJE, LAS PUERTAS Y LOS OBJETOS CORRESPONDIENTES
    public static void pintarHabitacion(Habitacion h, Jugador j) {
    
    	Color colorLocal = new Color();
    	
    	System.out.println("");
    	System.out.println("--------  INVENTARIO  --------");
		System.out.println();
    	Juego.pintarInventario(j.getVida(), j.getMochila(), colorLocal.getRed(), colorLocal.getOrange(), colorLocal.getResetarColor());
		System.out.println("\n\n-----------  MAPA  -----------");
    	
        for(int row=-1; row<Habitacion.ALTO; row++)
            for(int col=0; col<=Habitacion.ANCHO; col++) {

                //INDICE POSCION QUE VA RECORRIENDO LA HABITACION
                Posicion posActual = new Posicion(col, row);
                
                //INT QUE NOS DICE LA POSICION DE UN OBJETO SI HAY UN OBJETO
                int posObjeto = h.hayObjeto(posActual);
                
                if(posObjeto != -1) {
                	ObjetoJuego objJ = h.getObjetoJuego(posObjeto);
                	System.out.print(objJ.getLetraMapa()); //SE IMPRIME EL OBJETO
                }
                
                //SE IMPRIMEN LOS NUMEROS "GUIA"
                else if(row == -1 && col != Habitacion.ANCHO) {
                	if(col < 10) System.out.print(col);
                	else if(col < 20) System.out.print(col - 10);
                	else System.out.print(col - 20);
                }
                else if(col == Habitacion.ANCHO && row != -1) {
                	if(row < 10) System.out.print(row);
                	else if(row < 20) System.out.print(row - 10);
                	else System.out.println(row - 20);
                }
                
                //SE IMPRIMEN LAS ESQUINAS
                else if(row == 0 && col == 0) System.out.print("╔");
                else if(row == 0 && col == Habitacion.ANCHO-1) System.out.print("╗");
                else if(row == Habitacion.ALTO-1 && col == 0) System.out.print("╚");
                else if(row == Habitacion.ALTO-1 && col == Habitacion.ANCHO-1) System.out.print("╝");
                //SE IMPRIMEN LAS PAREDES
                else if(col == 0 || col == Habitacion.ANCHO-1) System.out.print("║");
                else if(row == 0 || row == Habitacion.ALTO-1) System.out.print("═");
                
                //SE IMPRIME EL MAPA
                else System.out.print(" ");
                
                //Line break
                if(col == Habitacion.ANCHO) System.out.println();
            }
        }
        
	//posicionAletoria: ASIGNA AL OBJETO EN UNA POSICION ALEATORIA Y LIBRE 
	public static Posicion posAleatoria() {
		
		int randomX = ThreadLocalRandom.current().nextInt(2, Habitacion.ANCHO-2);
		int randomY = ThreadLocalRandom.current().nextInt(2, Habitacion.ALTO-2);
		
		
		Posicion pos = new Posicion(randomX, randomY);
		
		if(numPosiciones > 0) {
			for(int i = 0; i < numPosiciones; i++) {
				if(pos.esIgual(posiciones[i])) {
					randomX=ThreadLocalRandom.current().nextInt(2, Habitacion.ANCHO-2);
					randomY=ThreadLocalRandom.current().nextInt(2, Habitacion.ALTO-2);
					pos.setPosX(randomX);
					pos.setPosY(randomY);
					i = 0;
				}
			}
		}
		
		posiciones[numPosiciones] = pos;
		numPosiciones++;
		return posiciones[numPosiciones-1];
	}

    //moverUsurarioNoob: MUEVE AL USUARIO CON LA DIFICULTAD FACIL
	public static void moverUsuarioNoob(int dado, Personaje jugador, Habitacion h, Scanner scnLocal) {

		//MOVER AL JUGADOR POR LAS COLUMNAS
		
		System.out.print("[MOVIMIENTO COLUMNAS (X)] ¿Cuantas columnas quieres moverte? Tienes "+dado+" movimientos: ");
    	int numColumnas = scnLocal.nextInt();
    	while(Math.abs(numColumnas) > dado) { //NO PUEDE MOVERSE MAS QUE EL VALOR DEL DADO
    		System.out.println("¡¡¡VALOR MAYOR AL DEL DADO!!! (._.)");
    		System.out.print("¿Cuantas columnas quieres moverte? Tienes "+dado+" movimientos: ");
    		numColumnas = 0;
    		numColumnas = scnLocal.nextInt();
    	}
    	
    	numColumnas = moverColumnasX(numColumnas, dado, jugador, h);
    	dado = dado - numColumnas;
    	
    	//MOVER AL JUGADOR POR LAS FILAS
	    
		if(jugador.getPosX() != 0) {

		if(dado != 0) {

		jugador.setPos(jugador.getPos());

    	System.out.print("[MOVIMIENTO FILAS (Y)] ¿Cuantas filas quieres moverte? Tienes "+dado+" movimientos: ");
    	int numFilas = scnLocal.nextInt();
    	
    	while(Math.abs(numFilas) > dado) { //NO PUEDE MOVERSE MAS QUE EL VALOR DEL DADO
    		System.out.println("¡¡¡VALOR MAYOR AL DEL DADO!!! (._.)");
    		System.out.print("¿Cuantas filas quieres moverte? Tienes "+dado+" movimientos: ");
    		numFilas = 0;
    		numFilas = scnLocal.nextInt();
    	}
    	
		if(numFilas < 0) {
		numFilas = 0 - Math.abs(numFilas);
    	moverFilasY(numFilas, jugador, h);
		}

		if(numFilas > 0) {
	    moverFilasY(numFilas, jugador, h);
		}

		}
   
    	} else {System.out.println("\n¡TIENES QUE SACAR AL PERSONAJE DE LA PUERTA!"); }
		
    System.out.println();
}
	
	//moverUsuarioPro: MUEVE AL USUARIO CON LA DIFICULTAD DIFICIL
	public static void moverUsuarioPro(int dado, Personaje jugador, Habitacion h, Scanner scnLocal) {
		

		//MOVER AL JUGADOR POR LAS COLUMNAS
		
		System.out.print("[MOVIMIENTO COLUMNAS (X)] ¿Cuantas columnas quieres moverte? Tienes "+dado+" movimientos: ");
    	int numColumnas = scnLocal.nextInt();
    	while(Math.abs(numColumnas) > dado) { //NO PUEDE MOVERSE MAS QUE EL VALOR DEL DADO
    		System.out.println("¡¡¡VALOR MAYOR AL DEL DADO!!! (._.)");
    		System.out.print("¿Cuantas columnas quieres moverte? Tienes "+dado+" movimientos: ");
    		numColumnas = 0;
    		numColumnas = scnLocal.nextInt();
    	}
    	
    	numColumnas = moverColumnasX(numColumnas, dado, jugador, h);
    	dado = dado - numColumnas;
    	
    	if(jugador.getPosX() != 0) {
    	
    	if(dado != 0) {
    		
    	int numFilas;
    	int preguntaSentido;
    	
        System.out.print("[MOVIMIENTO FILAS (Y)] Tienes "+dado+" movimientos ");
        preguntaSentido = Juego.preguntarUsuario("¿En que sentido quieres moverte? ↑ ARRIBA (U) ↑ / ↓ ABAJO ↓ (D) ¿[U/D]?: ", 'U', 'u', 'D', 'd', scnLocal);
        
        if(preguntaSentido == 0) {
			jugador.setPos(jugador.getPos());
        	numFilas = 0 - dado;
        	moverFilasY(numFilas, jugador, h);
        }
        
        if(preguntaSentido == 1) {
			jugador.setPos(jugador.getPos());
        	numFilas = dado;
        	moverFilasY(numFilas, jugador, h);
        }
    	}
        
    	} else System.out.println("\n¡TIENES QUE SACAR AL PERSONAJE DE LA PUERTA!");
    		
        System.out.println();
	}
   
	//moverColumnasX: FUNCION QUE MOVERA AL USUARIO POR LAS COLUMNAS, DEVUELVE EL NUMERO DE COLUMNAS QUE SE HA MOVIDO
    public static int moverColumnasX(int cols, int dice, Personaje jugador, Habitacion h) {
    	
    	
    	//EL USUARIO DECIDE MOVER AL PERSONAJE EN SENTIDO POSITIVO
    	if((cols> 0)) {
			
    		
    		if((cols > Habitacion.ANCHO - 1 - jugador.getPosX())) {
    		jugador.moverPosX(Habitacion.ANCHO - jugador.getPosX()-2); //PARA EVITAR QUE SE SALGA DE LA HABITACION (COULMNA DERECHA)
    		System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
    		cols = dice; //SE CHOCA CONTRA EL MURO Y NO PUEDE SEGUIR MOVIENDOSE
    		
    		} else if((cols + jugador.getPosX()) == (Habitacion.ANCHO - 1)) {
    		
    		jugador.moverPosX(Habitacion.ANCHO - jugador.getPosX()-2); //PARA EVITAR QUE SE SALGA DE LA HABITACION (COULMNA DERECHA)
        	System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");	
        	cols = dice; //SE CHOCA CONTRA EL MURO Y NO PUEDE SEGUIR MOVIENDOSE
        	
    		} else jugador.moverPosX(cols); //SE MUEVE EL JUGADOR SIN NINGUNA RESTRICCION
    	}
    	
    	
    	//EL USUARIO DECIDE MOVER AL PERSONAJE EN SENTIDO NEGATIVO
    	if((cols < 0)){ 
    		
    		if(Math.abs(cols) > jugador.getPosX()) {
    		jugador.moverPosX(1 - jugador.getPosX()); //PARA EVITAR QUE SE SALGA DE LA HABITACION (COULMNA IZQUIERDA)
    		System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
    		cols = dice; //SE CHOCA CONTRA EL MURO Y NO PUEDE SEGUIR MOVIENDOSE
    		
    		} else if((jugador.getPosX() - Math.abs(cols)) == 0) {
    		jugador.moverPosX(1 - jugador.getPosX()); //PARA EVITAR QUE SE SALGA DE LA HABITACION (COULMNA IZQUIERDA)
    		System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
    		cols = dice; //SE CHOCA CONTRA EL MURO Y NO PUEDE SEGUIR MOVIENDOSE
    		
    		} else jugador.moverPosX(cols); //SE MUEVE EL JUGADOR SIN NINGUNA RESTRICCION
    	}    	
 
    	return Math.abs(cols);	   	
    }

    //moverFilasY: FUNCION QUE MOVERA AL USUARIO POR LAS FILAS 
    public static void moverFilasY(int rows, Personaje jugador, Habitacion h) {

    	
    	//EL USUARIO DECIDE MOVER AL PERSONAJE EN SENTIDO POSITIVO
    	if(rows > 0) {

			if(rows > (Habitacion.ALTO + 1 - jugador.getPosY())) {
        	jugador.moverPosY(Habitacion.ALTO - jugador.getPosY()- 2); //PARA EVITAR QUE SE SALGA DE LA HABITACION (FILA INERIOR)
        	System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
        	
    		} else if(jugador.getPosY() + rows == Habitacion.ALTO - 1) { 
    			jugador.moverPosY(Habitacion.ALTO - jugador.getPosY() - 2); //PARA EVITAR QUE SE SALGA DE LA HABITACION (FILA INERIOR)
            	System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
            	
    		} else jugador.moverPosY(rows); //SE MUEVE EL JUGADOR SIN NINGUNA RESTRICCION
    	}
    	
    	//EL USUARIO DECIDE MOVER AL PERSONAJE EN SENTIDO NEGATIVO
    	if(rows < 0){ 
    		
    		if((Math.abs(rows) > jugador.getPosY())) {
    		jugador.moverPosY(1 - jugador.getPosY()); //PARA EVITAR QUE SE SAGA DE LA HABITACION (FILA SUPERIOR)
    		System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");
    		
    		} else if((Math.abs(rows) - jugador.getPosY()) == 0) {
    		jugador.moverPosY(1 - jugador.getPosY()); //PARA EVITAR QUE SE SAGA DE LA HABITACION (FILA SUPERIOR)
        	System.out.println("\n¡UY! Te pasaste de largo y te has chocado contra el muro... (>.<)");	
        	
    		} else jugador.moverPosY(rows); //SE MUEVE EL JUGADOR SIN NINGUNA RESTRICCION

    	}
    }
	//llaveEncontrada: SUMA UNA LLAVE AL INVENTARIO, SI EL JUGADOR ENCUENTRA 3 LLAVES APARECERÁ EL PORTAL DE SALIDA
	public static void llaveEncontrada(String color, String resetColor, Jugador j) {

		j.setMochila(j.getMochila()+1);

		System.out.println("");
		System.out.println("╔═══════════════════════════════════════════╗");
		System.out.printf("║                 %sLLAVE%s                     ║\n", color, resetColor);
		System.out.println("╠═══════════════════════════════════════════╣");
		System.out.println("║    ¡¡¡ HAS ENCONTRADO UNA LLAVE: X  !!!   ║");
		System.out.println("╚═══════════════════════════════════════════╝");
	}
	
	//vidaEncontrada: AÑADE UNA VIDA EXTRA AL JUGADOR
	public static void vidaEncontrada(Jugador j, String color, String resetColor) {
		
		System.out.println("");
		System.out.println("╔═══════════════════════════════════════════╗");
		System.out.printf("║               %sVIDA EXTRA%s                  ║\n", color, resetColor);
		System.out.println("╠═══════════════════════════════════════════╣");
		System.out.println("║    ¡¡¡ HAS CONSEGUIDO UNA VIDA: + !!!     ║");
		System.out.println("╚═══════════════════════════════════════════╝");
		
		j.setVida((j.getVida()+1));

	}
	
	//explotaHadron: ELIMINA EL HADRON DEL TABLERO Y QUITA VIDAS ALEATORIAS AL JUGADOR
	public static int jugadorChocaHongo(int vidaPerdida, int posObj, String color, String resetColor, Jugador j) {
		
		int vidasJugador = j.getVida() - vidaPerdida;
		
		System.out.println("");
		System.out.println("╔═══════════════════════════════════════════╗");
		System.out.printf("║                 %sHONGO%s                     ║\n", color, resetColor);
		System.out.println("╠═══════════════════════════════════════════╣");
		System.out.println("║  ¡¡¡ HAS CHOCADO CONTRA UN HONGO: ▲  !!!  ║");
		if(j.getVida() != 1) {
		     System.out.println("║           HAS PERDIDO "+vidaPerdida+" VIDAS             ║");
		} else {
			System.out.println("║           HAS PERDIDO "+vidaPerdida+" VIDA             ║");
		}
		
		if(vidaPerdida >= j.getVida()) {
			System.out.println("║  ---> (X_X) TE HAS ENVENENADO (X_X) <---  ║");
			System.out.println("╚═══════════════════════════════════════════╝");
			vidasJugador = 0;
		}
		else { System.out.println("║ Pero has tenido suerte, PUEDES CONTINUAR  ║");
		       System.out.println("╚═══════════════════════════════════════════╝");
		}
		return vidasJugador;	
	}
	
	//habitacionSuperada: NOS INDICA QUE YA HEMOS TERMINADO LA PARTIDA
	public static void habitacionSuperada(Habitacion h, Jugador j, String color, String resetColor) {
		System.out.println("╔═══════════════════════════════════════════╗");
		System.out.printf("║                  %sPORTAL%s                   ║\n", color, resetColor);
		System.out.println("╠═══════════════════════════════════════════╣");
		System.out.println("║    ¡¡¡ HAS ENCONTRADO EL PORTAL: █  !!!   ║");
		System.out.println("║                                           ║");
		System.out.println("║   HAS VUELTO A TU DIMENSION SANO Y SALVO  ║");
		System.out.println("║            ¡¡¡ ENHORABUENA !!!            ║");
		System.out.println("╚═══════════════════════════════════════════╝");
	}

	//pintarInicio: PINTA LA "PORTADA DEL JUEGO"
	public static int pintarInicio(Scanner sc, String green, String resetColor) {

		System.out.println("╔════════════════════════════════════════╗");
        System.out.printf("║          %sTOXIC MUSHROOM QUEST%s          ║\n", green, resetColor);
        System.out.println("╚════════════════════════════════════════╝");

		System.out.print("\nPulsa cualquier tecla y ENTER para continuar... ");
		sc.nextLine();
		System.out.println();

		int dificultad;
    	
    	System.out.println("╔════════════════════════════╗");
        System.out.printf("║         %sDIFICULTAD%s         ║\n", green, resetColor);
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ SELECCIONE UNA DIFICULTAD: ║");
        System.out.println("║                            ║");
        System.out.println("║  [N]: NOOB                 ║");
        System.out.println("║  [P]: PRO                  ║");
        System.out.println("║                            ║");
        System.out.println("╚════════════════════════════╝");
     
        
        dificultad = preguntarUsuario("Teclea la dificultad deseada (N/P): ", 'N', 'n', 'P', 'p', sc);
		System.out.println();
		
		return dificultad;
	}
	
	//pintarMenu: PINTA EL MENU DE OPCIONES CON EL TÍTULO DE COLOR NARANJA
    public static void pintarMenu(String green, String resetColor) {

        System.out.println("╔════════════════════════════════════════╗");
        System.out.printf("║          %sTOXIC MUSHROOM QUEST%s          ║\n", green, resetColor);
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║                OPCIONES                ║");
        System.out.println("║                                        ║");
        System.out.println("║  [1] JUGAR                             ║");
        System.out.println("║  [2] REGLAS DEL JUEGO                  ║");
        System.out.println("║  [3] CONFIGURACION                     ║");
        System.out.println("║  [0] SALIR DEL JUEGO                   ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
    } 
    
    //pintarVidas: IMPRIME LAS VIDAS QUE TIENE EL USUARIO DE COLOR ROJO
    public static void pintarInventario(int numVidas, int numLlaves, String rojo, String naranja, String resetColor) {
    	
    	System.out.print("VIDAS: ");
    	for(int i = 1; i <= numVidas; i++) {
    		System.out.printf("%s+%s", rojo, resetColor); //PINTA LAS VIDAS DE COLOR ROJO
    	}

		System.out.print("  ");
		if(numLlaves == 0) 
		System.out.print("LLAVES: "+numLlaves);
		else System.out.print("LLAVES: ");

		for(int i = 1; i <= numLlaves; i++) {
    		System.out.printf("%sX%s", naranja, resetColor); //PINTA LAS VIDAS DE COLOR ROJO
    	}
    }

    //reglasJuego: IMPRIME TODAS LAS REGLAS E INSTRUCCIONES NECESARIAS PARA JUGAR AL JUEGO
    public static void reglasJuego(String green, String resetColor) {

		System.out.println();
		System.out.println("╔════════════════════════════╗");
        System.out.printf("║          %sHISTORIA%s          ║\n", green, resetColor);
        System.out.println("╚════════════════════════════╝");
		System.out.println();

		System.out.printf("%s¡Te damos la bienvenida a TOXIC MUSHROOM QUEST, valiente viajero interdimensional!%s", green, resetColor); 
		System.out.println();
		System.out.println();
		System.out.println("Has llegado a una dimensión misteriosa y peligrosa, donde los inocentes hongos se han transformado ");
		System.out.println("en seres venenosos y malévolos, a un lugar donde la realidad se retuerce y los hongos cobran vida! ");
		System.out.println("En tu travesía interdimensional, te has extraviado en este extraño mundo donde los hongos acechan en ");
		System.out.println("cada esquina, esperando envenenar a los desprevenidos. Para escapar de esta pesadilla, debes encontrar ");
		System.out.println("las tres llaves místicas que abrirán el portal de salida hacia tu hogar.");
		System.out.println();
		System.out.println("Con cada paso que das, la maldad de esta extraña dimensión te rodea, y el tiempo corre en tu contra. ");
		System.out.println("Debes explorar cada rincón, enfrentarte a los peligros que acechan y recolectar las llaves para liberarte ");
		System.out.println("de este lugar maldito.");
		System.out.println();
		System.out.println("¡Adéntrate en este desafío épico, supera los obstáculos y demuestra tu valentía mientras buscas el ");
		System.out.println("camino de regreso a casa en Toxic Mushroom Quest!");
		

		System.out.println();
		System.out.println("╔════════════════════════════╗");
        System.out.printf("║        %sINSTRUCCIONES%s       ║\n", green, resetColor);
        System.out.println("╚════════════════════════════╝");
		System.out.println();
		System.out.printf("%s1) MOVIMIENTO DEL JUEGO:%s", green, resetColor);
		System.out.println(" En cada turno, lanzarás un dado de movimiento completamente aleatorio entre 1 y 8");
		System.out.println();
		System.out.println("   - DIFICULTAD NOOB: podrás moverte hasta el valor del dado en columnas y filas, ");
		System.out.println("     siempre que sea menor o igual al valor del dado.");
		System.out.println();
		System.out.println("   - DIFICULTAD PRO:  deberás moverte exactamente la cantidad de columnas y filas ");
		System.out.println("     que indique el dado.");
		
		System.out.println();
		System.out.printf("%s2) OBJETOS DEL JUEGO:%s", green, resetColor);
		System.out.println(" En el mapa del juego apareceran una serie de objetos: "); 
	    	System.out.println();
	    	System.out.println("   - JUGADOR J: (representado por 'J') debes moverlo por el mapa, para recolectar");
		System.out.println("     las 3 llaves, para finalmente salir por el portal. (SIEMPRE EVITANDO LOS HONGOS).");
		System.out.println();
		System.out.println("   - HONGOS Ø: Debes evitar los hongos (representados por 'Ø') que acechan en");
		System.out.println("     el mapa, ya que te envenenarán y reducirán tu salud ¡OJO: se hacen invisibles!.");
		System.out.println();
		System.out.println("   - LLAVES X: Encuentra las tres llaves místicas (representadas por 'X') esparcidas");
		System.out.println("     por el mapa para desbloquear el portal interdimensional de salida.");
		System.out.println();
		System.out.println("   - VIDA EXTRA +: Añade un punto de vida mas (representada por '+') ¡CUIDADO: ");
		System.out.println("     Despues de jugar 10 turnos, esta desaparecerá!.");
		System.out.println();
		System.out.println("   - PORTAL █: (representado por '█') aparecerá solo cuando tengas las tres llaves.");
		System.out.println("     ¡Sal del portal para escapar de esta dimensión maligna y regresar a tu hogar!");		
		System.out.println();
		
    
    }

    //configuracionJuego: IMPRIME LA CONFIGURACION DEL JUEGO Y PERMITE CAMBIARLA
    public static int configuracionJuego(String green, String resetColor, Scanner scnLocal) {
    	
    	int dificultad;
    	
    	System.out.println("╔═════════════════════════════╗");
        System.out.printf("║        %sCONFIGURACION%s        ║\n", green, resetColor);
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║  SELECCIONE UNA DIFICULTAD  ║");
        System.out.println("║                             ║");
        System.out.println("║  [N]: NOOB                  ║");
        System.out.println("║  [P]: PRO                   ║");
        System.out.println("║                             ║");
        System.out.println("╚═════════════════════════════╝");
     
        
        dificultad = preguntarUsuario("Teclea la dificultad deseada (N/P): ", 'N', 'n', 'P', 'p', scnLocal);
        
        if(dificultad == 1) {
        System.out.println("╔════════════════════════╗");
        System.out.println("║  ¡HAS CAMBIADO A PRO!  ║");
        System.out.println("╚════════════════════════╝");
        } else {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║  ¡HAS CAMBIADO A NOOB!  ║");
            System.out.println("╚═════════════════════════╝");
        }
   
        return dificultad;
    }

    //preguntarUsuario: FUNCION QUE PERMITE PEDIR AL USUARIO UN CARACTER Y NOS DEVUELVE UN INT (0 or 1)
    public static int preguntarUsuario(String mensaje, char a1, char a2, char b1, char b2, Scanner scnLocal) {

    	char opcionSelec;
    	int valorDevuelto;

    	do {
        	
    		System.out.print(mensaje);
            opcionSelec = scnLocal.next().charAt(0);
            
            if(opcionSelec != a1 && opcionSelec != a2 && opcionSelec != b1 && opcionSelec != b2) {
            	System.out.println("Seleccione una opcion existente... (._.)");
            }
            
            } while(opcionSelec != a1 && opcionSelec != a2 && opcionSelec != b1 && opcionSelec != b2);
            
            if(opcionSelec == a1 || opcionSelec == a2) 
            	valorDevuelto = 0;
            else valorDevuelto = 1;
            
       return valorDevuelto;
    	
    }
}

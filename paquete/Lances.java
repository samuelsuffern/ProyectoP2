package paquete;
import java.util.LinkedList;


public class Lances {
    private Salida salida;
	private Pareja pareja1;
	private Pareja pareja2;
    private Jugador jugMano;
    

    public void setSalida(Salida salida){
        this.salida =salida;

    }
    
    public void setData(Pareja pareja1, Pareja pareja2){
        this.pareja1 = pareja1;
        this.pareja2 = pareja2;
        
    }

    public void nextMano(Jugador jugador){
        this.jugMano = jugador;
    }
    

    public int piedrasJuegos(Integer juego) {
		if (juego == 1) {
			return 3;
		}else if (juego != 9) {
			return 2;
		}
		
		return 0;
	}
	
    

    public void resuelveGrande() {
		Baraja mano11 = pareja1.getJug1().getMano();
		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char grande = 'R';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano12, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano21, grande);
			par2.add(numero);
			numero = calculaNumCartas(mano22, grande);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de grande
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				salida.println("Gana J" + pareja1.getJug1().getId()+ "" + pareja1.getEquipo() +" por grande: " + grande + "(" + par1.get(0) + ")");

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				ganador = 1;

				salida.println("Gana J" + pareja1.getJug2().getId()+ "" + pareja1.getEquipo() +" por grande: " + grande + "(" + par1.get(1) + ")");

				//salida.println("Gana j12 por grande: " + par1.get(1) + "," + grande);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;
				salida.println("Gana J" + pareja2.getJug1().getId()+ "" + pareja2.getEquipo() +" por grande: " + grande + "(" + par2.get(0) + ")");

				//salida.println("Gana j21 por grande: " + par2.get(0) + "," + grande);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;

				salida.println("Gana J" + pareja2.getJug2().getId()+ "" + pareja2.getEquipo() +" por grande: " + grande + "(" + par2.get(1) + ")");

				//salida.println("Gana j22 por grande: " + par2.get(1) + "," + grande);
			} else { // empate
				switch (valor) {
				case 1:
					grande = 'C';
					break;
				case 2:
					grande = 'S';
					break;
				case 3:
					grande = '7';
					break;
				case 4:
					grande = '6';
					break;
				case 5:
					grande = '5';
					break;
				case 6:
					grande = '4';
					break;
				case 7:
					grande = '1';
					break;

				default:
					ganador = 1;
					// resPartida("Grande 0 0 ");
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

	}

	/*
	 * 
	 */

	public void resuelveChica() {
		Baraja mano11 = pareja1.getJug1().getMano();
		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char chica = '1';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano12, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano21, chica);
			par2.add(numero);
			numero = calculaNumCartas(mano22, chica);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de chica
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				salida.println("Gana J" + pareja1.getJug1().getId() + "" + pareja1.getEquipo() + " por chica: " + chica + "(" + par1.get(0) + ")");

				//salida.println("Gana j11 por chica: " + par1.get(0) + "," + chica);

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				ganador = 1;
				salida.println("Gana J" + pareja1.getJug2().getId() + "" + pareja1.getEquipo() + " por chica: " + chica + "(" + par1.get(1) + ")");

				//salida.println("Gana j12 por chica: " + par1.get(1) + "," + chica);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;
				salida.println("Gana J" + pareja2.getJug1().getId() + "" + pareja2.getEquipo() + " por chica: " + chica + "(" + par2.get(0) + ")");

				//salida.println("Gana j21 por chica: " + par2.get(0) + "," + chica);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;

				salida.println("Gana J" + pareja2.getJug2().getId() + "" + pareja2.getEquipo() + " por chica: " + chica + "(" + par2.get(1) + ")");

				//salida.println("Gana j22 por chica: " + par2.get(1) + "," + chica);
			} else { // empate
				switch (valor) {
				case 1:
					chica = '4';
					break;
				case 2:
					chica = '5';
					break;
				case 3:
					chica = '6';
					break;
				case 4:
					chica = '7';
					break;
				case 5:
					chica = 'S';
					break;
				case 6:
					chica = 'C';
					break;
				case 7:
					chica = 'R';
					break;

				default:
					ganador = 1;
					// resPartida("Chica 0 0\n");
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

		salida.print("\n");

	} // FIN RESUELVECHICA()

	public void resuelvePares() {
		int paresP1 = 0;
		int paresP2 = 0;
		int piedras = 0;
		piedras = cuentaPares(pareja1.getJug1().getMano().getBarajaList());
		paresP1 = paresP1 + piedras;
		piedras = cuentaPares(pareja1.getJug2().getMano().getBarajaList());
		paresP1 = paresP1 + piedras;
		piedras = cuentaPares(pareja2.getJug1().getMano().getBarajaList());
		paresP2 = paresP2 + piedras;
		piedras = cuentaPares(pareja2.getJug2().getMano().getBarajaList());
		paresP2 = paresP2 + piedras;
		
		salida.println("P1: " + paresP1 + " - P2: " + paresP2);
		pareja1.addPiedras(paresP1);
		pareja2.addPiedras(paresP2);

	} // fin resuelvePares()


    
	public void resuelveJuego(){
		int puntosP1J1 = 0,puntosP1J2,puntosP2J1 = 0,puntosP2J2=0;
		int juegoP1J1=0,juegoP1J2=0,juegoP2J1=0,juegoP2J2=0;

		puntosP1J1= pareja1.getJug1().getMano().getPuntos();
		juegoP1J1 = calculaJuego(puntosP1J1);
		
		puntosP1J2=pareja1.getJug2().getMano().getPuntos();
		juegoP1J2 = calculaJuego(puntosP1J2);
		puntosP2J1=pareja2.getJug1().getMano().getPuntos();
		juegoP2J1 = calculaJuego(puntosP2J1);
		puntosP2J2=pareja2.getJug2().getMano().getPuntos();
		juegoP2J2 = calculaJuego(puntosP2J2);
		
	
		if(juegoP1J1 < juegoP2J1 && juegoP1J1 < juegoP2J2) {
			pareja1.addPiedras(2);
			
			
			pareja1.addPiedras(piedrasJuegos(juegoP1J1));
			pareja1.addPiedras(piedrasJuegos(juegoP1J2));
			
			pareja2.addPiedras(piedrasJuegos(juegoP2J1));
			pareja2.addPiedras(piedrasJuegos(juegoP2J2));
			
		}else if(juegoP1J2 < juegoP2J1 && juegoP1J2 < juegoP2J2) {
			pareja1.addPiedras(2);
			
			
			pareja1.addPiedras(piedrasJuegos(juegoP1J1));
			pareja1.addPiedras(piedrasJuegos(juegoP1J2));
			
			pareja2.addPiedras(piedrasJuegos(juegoP2J1));
			pareja2.addPiedras(piedrasJuegos(juegoP2J2));
			
		
		}else if(juegoP2J1 < juegoP1J1 && juegoP2J1 < juegoP1J2) {
			pareja2.addPiedras(2);
			
			
			pareja1.addPiedras(piedrasJuegos(juegoP1J1));
			pareja1.addPiedras(piedrasJuegos(juegoP1J2));
			
			pareja2.addPiedras(piedrasJuegos(juegoP2J1));
			pareja2.addPiedras(piedrasJuegos(juegoP2J2));
			
		}else if(juegoP2J2 < juegoP1J1 && juegoP2J2 < juegoP1J2) {
			pareja2.addPiedras(2);
			
			
			pareja1.addPiedras(piedrasJuegos(juegoP1J1));
			pareja1.addPiedras(piedrasJuegos(juegoP1J2));
			
			pareja2.addPiedras(piedrasJuegos(juegoP2J1));
			pareja2.addPiedras(piedrasJuegos(juegoP2J2));
			
		} else {
			
			if(juegoP1J1 + juegoP1J2 < juegoP2J1 + juegoP2J2) {
				pareja1.addPiedras(2);
				
				pareja1.addPiedras(piedrasJuegos(juegoP1J1));
				pareja1.addPiedras(piedrasJuegos(juegoP1J2));
				
				pareja2.addPiedras(piedrasJuegos(juegoP2J1));
				pareja2.addPiedras(piedrasJuegos(juegoP2J2));
					
			} else if (juegoP2J1 + juegoP2J2 < juegoP1J1 + juegoP1J2) {
				pareja2.addPiedras(2);
				
				pareja1.addPiedras(piedrasJuegos(juegoP1J1));
				pareja1.addPiedras(piedrasJuegos(juegoP1J2));
				
				pareja2.addPiedras(piedrasJuegos(juegoP2J1));
				pareja2.addPiedras(piedrasJuegos(juegoP2J2));
								
			} else {
				jugMano.getPareja().addPiedras(2);
				
				pareja1.addPiedras(piedrasJuegos(juegoP1J1));
				pareja1.addPiedras(piedrasJuegos(juegoP1J2));
				
				pareja2.addPiedras(piedrasJuegos(juegoP2J1));
				pareja2.addPiedras(piedrasJuegos(juegoP2J2));
								
			}
			
		}
		
		
		salida.println("resuelveJuego() : P1 vs P2");
		salida.println(puntosP1J1 + "," + puntosP1J2 + " - "+puntosP2J1+ ","+puntosP2J2) ;
		salida.println("Piedras finales: " + pareja1.getPiedras() + " - " + pareja2.getPiedras());
		
	
	}
	
	
	private int calculaJuego(int puntos) {
		int juego=0;
		switch (puntos) {
		case 31:
			juego=1;
			break;
		case 32:
			juego=2;
			break;
		case 33:
			juego=8;
			break;
		case 34:
			juego=7;
			break;
		case 35:
			juego=6;
			break;
		case 36:
			juego=5;
			break;
		case 37:
			juego=4;
			break;
		case 40:
			juego=3;		
			break;
		default:
			juego=9;
			break;
		}		
		return juego;
	}
	

	/*
	 * Pares: 2 cartas iguales; Medias: 3 cartas iguales; Duplex: 2 parejas de
	 * cartas iguales o 4 cartas
	 */
	public int cuentaPares(LinkedList<Carta> lista) {
		int piedras = 0;
		int nParejas = 0;
		int par = 0; // 2 cartas iguales -> 1 piedra
		int duplex = 0; // 2 parejas o 4 cartas iguales -> 3 piedras
		int medias = 0; // 3 cartas iguales -> 2 piedras
		for (int i = 0; i < lista.size(); i++) {
			Carta carta = lista.get(i);
			char id = carta.getId();
			
			int aux = 1;
			for (int j = i+1 ; j < lista.size(); j++) {
				char id2 = lista.get(j).getId();
				
				if ((id2-id) == 0) {
					aux = aux + 1;
					
				}
			}
			switch (aux) {
			case 2:
				par = 1;
				nParejas++;
				if (nParejas == 2) {
					duplex = 1;
					piedras = 3;
					return piedras;
				}
				break;
			case 3:
				medias = 1;
				piedras = 2;
				return piedras;
			case 4:
				duplex = 1;
				piedras = 3;
				return piedras;
			default:
				break;
			}
			
			//salida.println("Par=" + par + ",nPar="+nParejas + ",Medias="+medias + ",Duplex="+duplex );
		}
		nParejas = 0;
		if (par == 1) {
			piedras = 1;
		}

		return piedras;
	}

	/*
	 * Calcula el numero de cartas en una lista de cartas conociendo el id.
	 * Devolvemos el numero
	 */
	public int calculaNumCartas(Baraja mano, char id) {
		int numero = 0;

		for (Carta carta : mano.getBarajaList()) {

			switch (id) {

			case 'R':
				if (carta.getId() == 'R' || carta.getId() == '3') {
					numero++;
				}
				break;

			case '1':
				if (carta.getId() == '1' || carta.getId() == '2') {
					numero++;
				}
				break;

			default:
				if (carta.getId() == id) {
					numero++;
				}
				break;

			}
		}
		return numero;
	}



}
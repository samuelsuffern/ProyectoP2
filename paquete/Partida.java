package paquete;

import java.util.LinkedList;
import java.util.Random;

public class Partida {
	private Baraja baraja = new Baraja();
	private Pareja pareja1 = new Pareja();
	private Pareja pareja2 = new Pareja();
	private Jugador jugMano;
	private Pareja ganadores;
	private int jugadas = 0;

	// Prueba mano
	
	public void generarMano() {
		LinkedList<Jugador> listaJug = new LinkedList<Jugador>();
		listaJug.addLast(pareja1.getJug1());
		listaJug.addLast(pareja1.getJug2());
		listaJug.addLast(pareja2.getJug1());
		listaJug.addLast(pareja2.getJug2());

		Jugador sigMano = null;
		jugadas = jugadas +1;
		
		
		if (jugadas == 1) {
			int mano =(int)(Math.random() * ((3) + 1)) + 0;	
			jugMano = listaJug.get(mano);
			System.out.println("Partida nº: " + jugadas + " - Primera mano: " + jugMano.getPareja() + " "+  jugMano.getId() );
			
		} else {
			
			Integer idMano = jugMano.getId();
			System.out.println("Partida nº: " + jugadas + " - Antigua mano: " + jugMano.getPareja() + " "+  jugMano.getId() );

			
			if ( jugadas % 2 == 0) {
				for(Jugador jug : listaJug){
					if(jug.getId() == idMano && !jug.getPareja().equals(jugMano.getPareja())){
						
						sigMano = jug;
						System.out.println("Nueva mano mismo id: " + sigMano.getPareja() + " "+  sigMano.getId() );
							
					}
				}
					//mapa.get(jugMano.getId())
			}else{

				for(Jugador jug : listaJug){
					if(jug.getId() != idMano && !jug.getPareja().equals(jugMano.getPareja())){							
							
						sigMano = jug;
						System.out.println("Nueva mano mismo id: " + sigMano.getPareja() + " "+  sigMano.getId() );
							
					}
				}
			}
			jugMano = sigMano;

		}
			
			
	}
	
		


	/*
	 * Getters & Setters
	 */
	public Baraja getBaraja() {
		return baraja;
	}

	public void setBaraja(Baraja baraja) {
		this.baraja = baraja;
	}

	public Pareja getPareja1() {
		return pareja1;
	}

	public void setPareja1(Pareja pareja1) {
		this.pareja1 = pareja1;
	}

	public Pareja getPareja2() {
		return pareja2;
	}

	public void setPareja2(Pareja pareja2) {
		this.pareja2 = pareja2;
	}

	public Jugador getJugMano() {
		return jugMano;
	}

	public void setJugMano(Jugador jugMano) {
		this.jugMano = jugMano;
	}

	public Pareja getGanadores() {
		return ganadores;
	}

	public void setGanadores(Pareja ganadores) {
		this.ganadores = ganadores;
	}

	/*
	 * Funcion que crea 2 parejas con 2 jugadores cada una.
	 */
	public void crearJugadores() {
		pareja1.setId(1);
		pareja2.setId(2);

		pareja1.setEquipo("PA");
		pareja2.setEquipo("PB");

		for (Integer i = 1; i < 5; i++) {
			Jugador jugador = new Jugador();

			switch (i) {
			case 1:
				jugador.setId(1);
				jugador.setNombre("J1");
				jugador.setPareja(pareja1);
				pareja1.setJug1(jugador);

				break;
			case 2:
				jugador.setId(2);

				jugador.setNombre("J2");
				jugador.setPareja(pareja1);
				pareja1.setJug2(jugador);
				break;

			case 3:
				jugador.setId(1);

				jugador.setNombre("J1");
				jugador.setPareja(pareja2);
				pareja2.setJug1(jugador);
				break;
			case 4:
				jugador.setId(2);
				jugador.setNombre("J2");
				jugador.setPareja(pareja2);
				pareja2.setJug2(jugador);
				break;
			}

		}

		/*
		 * Jugador jug1 = pareja1.getJug1(); Jugador jug2 = pareja1.getJug2();
		 * Jugador jug3 = pareja2.getJug1(); Jugador jug4 = pareja2.getJug2();
		 */

		/*
		 * String linea = pareja1.getEquipo() + ": " + jug1.getNombre() + " y "
		 * + jug2.getNombre() + ".\n" +pareja2.getEquipo() + ": " +
		 * jug3.getNombre() + " y " + jug4.getNombre() + ".\n";
		 * 
		 * resPartida(linea);
		 */

	} // fin crearJugadores()

	/*
	 * Genera el jugador mano dependiendo de en que jugada nos encontremos
	 */

	public void generarMano() {
		LinkedList<Jugador> listaJug = new LinkedList<Jugador>();
		listaJug.addLast(pareja1.getJug1());
		listaJug.addLast(pareja1.getJug2());
		listaJug.addLast(pareja2.getJug1());
		listaJug.addLast(pareja2.getJug2());

		Jugador sigMano = null;
		jugadas = jugadas + 1;

		if (jugadas == 1) {
			int mano = (int) (Math.random() * ((3) + 1)) + 0;
			jugMano = listaJug.get(mano);
			System.out.println("NULLPartida nº: " + jugadas
					+ " - Primera mano: " + jugMano.getPareja() + " "
					+ jugMano.getId());

		} else {

			Integer idMano = jugMano.getId();
			System.out.println("Partida nº: " + jugadas + " - Antigua mano: "
					+ jugMano.getPareja() + " " + jugMano.getId());

			if (jugadas % 2 == 0) {
				for (Jugador jug : listaJug) {
					if (jug.getId() == idMano
							&& !jug.getPareja().equals(jugMano.getPareja())) {

						sigMano = jug;
						System.out.println("Nueva mano mismo id: "
								+ sigMano.getPareja() + " " + sigMano.getId());

					}
				}
				// mapa.get(jugMano.getId())
			} else {

				for (Jugador jug : listaJug) {
					if (jug.getId() != idMano
							&& !jug.getPareja().equals(jugMano.getPareja())) {

						sigMano = jug;
						System.out.println("Nueva mano mismo id: "
								+ sigMano.getPareja() + " " + sigMano.getId());

					}
				}
			}
			jugMano = sigMano;

		}

	}

	/*
	 * Calculamos el numero de piedras de cada Pareja Calculamos primero el
	 * numero de Reyes (o 3), luego S,C,7,6,5,4,ASES
	 */

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
			 * Tenemos que comprobar que pareja gana en funcion del lance de
			 * grande
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana
																			// la
																			// pareja1

				pareja1.addPiedras(3);
				// resPartida("Grande 3 0 ");

				System.out.println("Gana j11 por grande: " + par1.get(0) + ","
						+ grande);

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
				// resPartida("Grande 3 0 ");

				ganador = 1;
				System.out.println("Gana j12 por grande: " + par1.get(1) + ","
						+ grande);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;
				System.out.println("Gana j21 por grande: " + par2.get(0) + ","
						+ grande);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;
				System.out.println("Gana j22 por grande: " + par2.get(1) + ","
						+ grande);
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
			 * Tenemos que comprobar que pareja gana en funcion del lance de
			 * chica
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana
																			// la
																			// pareja1

				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				System.out.println("Gana j11 por chica: " + par1.get(0) + ","
						+ chica);

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				ganador = 1;
				System.out.println("Gana j12 por chica: " + par1.get(1) + ","
						+ chica);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;
				System.out.println("Gana j21 por chica: " + par2.get(0) + ","
						+ chica);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;
				System.out.println("Gana j22 por chica: " + par2.get(1) + ","
						+ chica);
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

	} // FIN RESUELVECHICA()

	public void resuelvePares() {
		int pares = 0;
		int paresP1 = 0;
		int paresP2 = 0;
		pares = cuentaPares(pareja1.getJug1().getMano().getBarajaList());

		paresP1 = paresP1 + pares;
		System.out.println(paresP1);

	}

	/*
	 * Pares: 2 cartas iguales; Medias: 3 cartas iguales; Duplex: 2 parejas de
	 * cartas iguales o 4 cartas
	 */
	public int cuentaPares(LinkedList<Carta> lista) {
		int valor = 0;
		int cartas = 1;

		for (int i = 0; i < lista.size(); i++) {
			Carta c = lista.get(i);
			char id = c.getRepresentacion();
			lista.remove(i);
			System.out.println("Removemos " + id);
			for (int j = 0; j < lista.size(); j++) {
				if (cartas == 1) {
					if (id == lista.get(j).getRepresentacion()) {
						System.out.println(lista.get(j).getRepresentacion());

						cartas = cartas + 1;
						lista.remove(j);
					} else {
						System.out.println("no"
								+ lista.get(j).getRepresentacion());
					}
				}else{
					if (id == lista.get(j).getRepresentacion()) {
						cartas = 3;
						System.out.println(lista.get(j).getRepresentacion());

						cartas = cartas + 1;
						lista.remove(j);
					} else {
						System.out.println("no"
								+ lista.get(j).getRepresentacion());
					}
				}

			}
			

			i--;

		}
		return cartas;
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

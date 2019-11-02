package paquete;

import java.util.Collections;
import java.util.LinkedList;

public class Baraja {
	private LinkedList<Carta> baraja = new LinkedList<Carta>();

	// Getters & Setters
	public LinkedList<Carta> getBarajaList() {
		return baraja;
	}

	public void setBaraja(LinkedList<Carta> baraja) {
		this.baraja = baraja;
	}

	// Función crearBaraja(). Vamos añadiendo cartas a la baraja hasta tener las 40
	// cartas.
	public void crearBaraja() {
		for (Integer i = 0; i < 4; i++) {

			for (Integer j = 1; j < 11; j++) {

				Carta carta = new Carta();
				if (i == 0) {
					carta.setPalo('O');
				} else if (i == 1) {
					carta.setPalo('E');
				} else if (i == 2) {
					carta.setPalo('B');

				} else {
					carta.setPalo('C');
				}
				switch (j) {
				case 1:
				case 2:
					carta.setId((char) (j + '0')); // sumamos asci 48
					carta.setValor(1);
					break;
				case 3:
					carta.setId((char) (j + '0')); // modificacion p2,p3. Carta 3 --> valor = 10;
					carta.setValor(10);
					break;
				case 8:
					carta.setId('S');
					carta.setValor(10);
					break;
				case 9:
					carta.setId('C');
					carta.setValor(10);
					break;
				case 10:
					carta.setId('R');
					carta.setValor(10);
					break;

				default:
					carta.setId((char) (j + '0')); // sumamos asci 48
					carta.setValor(j);
					break;
				}

				baraja.addLast(carta);

			}

		}
	} // fin funcion crearBaraja()

	/*
	 * Funcion que desordena nuestra baraja
	 */
	public void barajarMazo(Partida partida) {
		if (!(baraja.size() < 1)) {
			Collections.shuffle(baraja);
			// mostrarBaraja();
			// partida.ficheroSalida("Barajar: Ejecución OK");

		} else {
			// partida.ficheroSalida("Barajar: No existe una baraja");
		}

	}

	/*
	 * Funcion que añade una carta pasada por argumentos a la lista de cartas del
	 * objeto
	 */
	public void añadirCarta(Carta carta) {
		baraja.addLast(carta);
	}

	/*
	 * Funcion que reparte n cartas y devuelve las cartas repartidas
	 */
	public Baraja repartir(int n, Partida partida) {
		Baraja barajajug = new Baraja();

		if (!(n >= 1)) {
			System.out.println(" n < 1");
			// partida.ficheroSalida("Obtener <" + n + "> cartas: Número no válido");
		} else if (baraja.size() < n) {

			// partida.ficheroSalida("Obtener <" + n + "> cartas: No hay suficientes
			// cartas");
		} else {
			for (Integer i = 0; i < n; i++) {
				Carta carta = baraja.getLast();

				// System.out.println("Carta: " + carta.getId() + "" + carta.getPalo() + " --
				// Valor: " + carta.getValor());
				barajajug.añadirCarta(baraja.getLast());
				baraja.removeLast();
			}

			// partida.ficheroSalida("Repartir <" + n + "> cartas: Ejecución OK");

		}

		return barajajug;

	}

	/*
	 * Funcion que se llama en cada jugada para repartir 4 cartas a los 4 jugadores
	 * de la mesa
	 */
	public void repartirJugada(Partida partida) {
		LinkedList<Jugador> jugadoresJugada = new LinkedList<Jugador>();
		jugadoresJugada.add(partida.getPareja1().getJug1());
		jugadoresJugada.add(partida.getPareja1().getJug2());
		jugadoresJugada.add(partida.getPareja2().getJug1());
		jugadoresJugada.add(partida.getPareja2().getJug2());
		Collections.shuffle(jugadoresJugada);

		for (Jugador j : jugadoresJugada) {
			j.setMano(repartir(4, partida));
			System.out.print("Jugador: ");
			for (Carta c : j.getMano().getBarajaList()) {
				System.out.print(c.getId() + "" + c.getPalo() + " - ");

			}
			System.out.print(j.getClave() + "\n");
			
		}
		 
	}

}

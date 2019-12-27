package paquete;

import java.util.LinkedList;
import java.util.Random;

public class Partida {
	
	private Lances lances = new Lances();
	private Salida salida;
	private Baraja baraja = new Baraja();
	private Pareja pareja1;
	private Pareja pareja2;
	private Jugador jugMano;
	private Pareja ganadores;
	private int jugadas = 0;

	Partida() {
		baraja.crearBaraja();
	}

	// Getters y Setters
	public void setSalida(Salida salida){
		this.salida = salida;
		Baraja.setSalida(salida);
	}

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
		return this.ganadores;
	}

	public void setGanadores(Pareja ganadores) {
		this.ganadores = ganadores;
	}

	public int getJugadas() {
		return this.jugadas;
	}


	// ----------------------------------------------- \\

	// Control de juego \\

	public void juegoPreestablecido(){
		lances.setSalida(salida);
		lances.setData(pareja1, pareja2);

		if (pareja1 == null && pareja2 == null) {
			crearJugadores();
		}

		
		jugadas++;
		Entrada.leerFichJugadas(this);

		salida.println(pareja1.getEquipo() + ": " + pareja1.getJug1().getNombre() + " y " + pareja1.getJug2().getNombre());
		salida.println(pareja2.getEquipo() + ": " + pareja2.getJug1().getNombre() + " y " + pareja2.getJug2().getNombre());
		salida.println("Mano: " + jugMano.getNombre() + ".\n");

		try {
			while (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40) {
				mostrarManosJugadores();
			
				lances.nextMano(jugMano);

				lances.resuelveGrande();
				lances.resuelveChica();
				lances.resuelvePares();
				lances.resuelveJuego();

				borrarManos();

				jugadas++;
				Entrada.leerFichJugadas(this);
			}
		} catch (Exception e) {
			jugadas--;
			salida.println("\nPartida incompleta. Número total de jugadas: " + jugadas);
		}

	}

	public void juegoComandos(){

	}

	public void juegoAutonomo(){
		if (pareja1 == null && pareja2 == null) {
			crearJugadores();
		}

		salida.println(pareja1.getEquipo() + ": " + pareja1.getJug1().getNombre() + " y " + pareja1.getJug2().getNombre());
		salida.println(pareja2.getEquipo() + ": " + pareja2.getJug1().getNombre() + " y " + pareja2.getJug2().getNombre());

		lances.setSalida(salida);
		lances.setData(pareja1, pareja2);


		baraja.barajarMazo(this);

		generarMano();
		lances.nextMano(jugMano);

		salida.println("Mano: " + jugMano.getNombre() + ".\n");


		while (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40){
			baraja.barajarMazo(this);
			baraja.repartirJugada(this);

			mostrarManosJugadores();

		

			lances.resuelveGrande();
			lances.resuelveChica();
			lances.resuelvePares();
			lances.resuelveJuego();

			generarMano();
			lances.nextMano(jugMano);

			baraja.restablecerBaraja();

		}

		if (pareja1.getPiedras() > pareja2.getPiedras()) {
			ganadores = pareja1;
		} else {
			ganadores = pareja2;
		}

		salida.println("Gana: " + ganadores.getEquipo() + ". Número total de jugadas: " + jugadas + ".");


		

	}


	public void mostrarManosJugadores() {
		if (pareja1.getJug1().equals(jugMano)) {
			salida.print("*");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();

		} else if (pareja1.getJug2().equals(jugMano)) {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("*");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();

		} else if (pareja2.getJug1().equals(jugMano)) {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("*");
			pareja2.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();
		} else {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug1().getMano().mostrarBaraja();
			salida.print("*");
			pareja2.getJug2().getMano().mostrarBaraja();

		}

		salida.print("\n");

	}

	// ------------------------------------------------ \\
	
	// Funciones Partida \\

	public void borrarManos() {
		pareja1.getJug1().getMano().getBarajaList().clear();
		pareja1.getJug2().getMano().getBarajaList().clear();
		pareja2.getJug1().getMano().getBarajaList().clear();
		pareja2.getJug2().getMano().getBarajaList().clear();

	}

	public void generarMano() {
		LinkedList<Jugador> listaJug = new LinkedList<Jugador>();
		listaJug.addLast(pareja1.getJug1());
		listaJug.addLast(pareja2.getJug1());
		listaJug.addLast(pareja1.getJug2());
		listaJug.addLast(pareja2.getJug2());

		jugadas = jugadas + 1;
		if (jugMano == null) {
			
			int mano = (int) (Math.random() * ((3) + 1)) + 0;
			jugMano = listaJug.get(mano);
			
		} else {
			
			for (int i = 0; i < listaJug.size(); i++) {
				if (jugMano.equals(listaJug.get(i)) && !jugMano.equals(listaJug.getLast()) ) {
					jugMano = listaJug.get(i+1);

					i = listaJug.size() +1;
				} else if (jugMano.equals(listaJug.getLast())) {
					jugMano = listaJug.get(0);

					i = listaJug.size() +1;
				}
				
			}


		}

		

	}


	public void crearJugadores() {
		this.pareja1 = new Pareja();
		this.pareja2 = new Pareja();
		pareja1.setId(1);
		pareja2.setId(2);

		pareja1.setEquipo("Pareja A");
		pareja2.setEquipo("Pareja B");

		for (Integer i = 1; i < 5; i++) {
			Jugador jugador = new Jugador();

			

			switch (i) {
			case 1:
				jugador.setNombre("Jugador 1A");
				jugador.setId(1);
				jugador.setPareja(pareja1);
				pareja1.setJug1(jugador);

				break;
			case 2:
				jugador.setNombre("Jugador 2A");
				jugador.setId(2);
				jugador.setPareja(pareja1);
				pareja1.setJug2(jugador);
				break;

			case 3:
				jugador.setId(1);
				jugador.setNombre("Jugador 1B");
				jugador.setPareja(pareja2);
				pareja2.setJug1(jugador);
				break;
			case 4:
				jugador.setId(2);
				jugador.setNombre("Jugador 2B");
				jugador.setPareja(pareja2);
				pareja2.setJug2(jugador);
				break;
			}

		}


	} // fin crearJugadores()

	// ---------------------------------------------- \\
}

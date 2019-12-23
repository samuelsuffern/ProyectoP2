package paquete;

import java.util.LinkedList;
import java.util.Random;

public class Partida {
	
	private Lances lances = new Lances();
	private Salida salida;
	private Baraja baraja = new Baraja();
	private Pareja pareja1 = new Pareja();
	private Pareja pareja2 = new Pareja();
	private Jugador jugMano;
	private Pareja ganadores;
	private int partidas = 0;


	// Getters y Setters
	public void setSalida(Salida salida){
		this.salida = salida;
		baraja.setSalida(salida);
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
		return ganadores;
	}

	public void setGanadores(Pareja ganadores) {
		this.ganadores = ganadores;
	}


	// ----------------------------------------------- \\

	// Control de juego \\

	public void juegoPreestablecido(){

	}

	public void juegoComandos(){

	}

	public void juegoAutonomo(){
		lances.setSalida(salida);
		crearJugadores();

		lances.setData(pareja1, pareja2);


		baraja.crearBaraja();
		baraja.barajarMazo(this);
		baraja.repartirJugada(this);



		while (pareja1.getPiedras() < 30 && pareja2.getPiedras() < 30){
			generarMano();
			lances.nextMano(jugMano);

			baraja.barajarMazo(this);
			baraja.repartirJugada(this);

			lances.resuelveGrande();
			lances.resuelveChica();
			lances.resuelvePares();
			lances.resuelveJuego();


		}


		

	}

	// ------------------------------------------------ \\
	
	// Funciones Partida \\
	public void generarMano() {
		LinkedList<Jugador> listaJug = new LinkedList<Jugador>();
		listaJug.addLast(pareja1.getJug1());
		listaJug.addLast(pareja1.getJug2());
		listaJug.addLast(pareja2.getJug1());
		listaJug.addLast(pareja2.getJug2());

		partidas = partidas + 1;
		if (jugMano == null) {
			
			int mano = (int) (Math.random() * ((3) + 1)) + 0;
			jugMano = listaJug.get(mano);
			
		} else {
			Integer idMano = jugMano.getId();

			if (!pareja1.equals(jugMano.getPareja())) {
				if (partidas % 2 != 0) {
					if (pareja1.getJug1().getId() != idMano) {
						jugMano = pareja1.getJug1();
					} else {
						jugMano = pareja1.getJug2();
					}
				} else {
					if (pareja1.getJug1().getId() == idMano) {
						jugMano = pareja1.getJug1();
					} else {
						jugMano = pareja1.getJug2();
					}
				}
			
			} else {
				if (partidas % 2 != 0) {
					if (pareja2.getJug1().getId() != idMano) {
						jugMano = pareja2.getJug1();
					} else {
						jugMano = pareja2.getJug2();
					}
				} else {
					if (pareja2.getJug1().getId() == idMano) {
						jugMano = pareja2.getJug1();
					} else {
						jugMano = pareja2.getJug2();
					}
				}
				
				
			}

		}

		salida.println("Jugador mano: J" + jugMano.getId()  + jugMano.getPareja().getEquipo() + "\n");
	}


	public void crearJugadores() {
		pareja1.setId(1);
		pareja2.setId(2);

		pareja1.setEquipo("PA");
		pareja2.setEquipo("PB");

		for (Integer i = 1; i < 5; i++) {
			Jugador jugador = new Jugador();

			

			switch (i) {
			case 1:
				jugador.setNombre("J1");
				jugador.setId(1);
				jugador.setPareja(pareja1);
				pareja1.setJug1(jugador);

				break;
			case 2:
				jugador.setNombre("J2");
				jugador.setId(2);
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


	} // fin crearJugadores()

	// ---------------------------------------------- \\
}

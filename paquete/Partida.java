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

	public void setJugadas(int jugadas) {
		this.jugadas = jugadas;
	}


	// ----------------------------------------------- \\


	public void completarEquipos(LinkedList<Pareja> parejasValidas) {
		if (parejasValidas.size() == 0) {
			crearJugadores();


		} else if (parejasValidas.size() == 1) {

			//System.out.println("Creamos pareja dos, solo habia");
			Pareja pareja = new Pareja();
			pareja.setEquipo("Pareja B");
			pareja.setId(2);

			Jugador jug1 = new Jugador();
			jug1.setId(125);
			jug1.setNombre("Jugador 1B");
			jug1.setPareja(pareja);
			pareja.setJug1(jug1);

			Jugador jug2 = new Jugador();
			jug2.setId(126);
			jug2.setNombre("Jugador 2B");
			jug2.setPareja(pareja);
			pareja.setJug2(jug2);
			
			parejasValidas.addLast(pareja);
			pareja2 = pareja;

		} else {
			//		System.out.println("Todo va correcto");

			
		}


	}


	// Control de juego \\

	public void juegoPreestablecido(){
		lances.setSalida(salida);

		if (pareja1 == null && pareja2 == null) {
			crearJugadores();
		}

		
		jugadas++;
		baraja.restablecerBaraja();
		Entrada.leerFichJugadas(this);

		salida.println(pareja1.getEquipo() + ": " + pareja1.getJug1().getNombre() + " y " + pareja1.getJug2().getNombre() + ".");
		salida.println(pareja2.getEquipo() + ": " + pareja2.getJug1().getNombre() + " y " + pareja2.getJug2().getNombre() + ".");
<<<<<<< HEAD
		salida.println("Mano: " + jugMano.getNombre() + ".");
=======
		salida.println("Mano: " + jugMano.getNombre() +".");
>>>>>>> master

		lances.setData(pareja1, pareja2);
		lances.nextMano(jugMano);


	
			while (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40) {
				mostrarManosJugadores();

				lances.resuelveGrande();
				salida.print(" ");
				lances.resuelveChica();
				salida.print(" ");
				lances.resuelvePares();
				salida.print(" ");
				lances.resuelveJuego();
				salida.print("- " + pareja1.getPiedras() + " " + pareja2.getPiedras() + "\n");

				
				if (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40) {
					borrarManos();
					generarMano();
					lances.nextMano(jugMano);
					Entrada.leerFichJugadas(this);
				}

			
			}

			if (pareja1.getPiedras() > pareja2.getPiedras()) {
				ganadores = pareja1;
			} else {
				ganadores = pareja2;
			}
	
			salida.println("Gana: " + ganadores.getEquipo() + ". Número total de jugadas: " + jugadas + ".");
		

	}

	public void juegoComandos(){
		Entrada.leerComandos(this,salida);
	}

	public void juegoAutonomo(){
		LinkedList<Pareja> parejas = new LinkedList<Pareja>(); 
		if (pareja1 != null) {
			parejas.addLast(pareja1);
		}
		if (pareja2 != null) {
			parejas.addLast(pareja2);
		}

		completarEquipos(parejas);

		salida.println(pareja1.getEquipo() + ": " + pareja1.getJug1().getNombre() + " y " + pareja1.getJug2().getNombre());
		salida.println(pareja2.getEquipo() + ": " + pareja2.getJug1().getNombre() + " y " + pareja2.getJug2().getNombre());

		lances.setSalida(salida);
		lances.setData(pareja1, pareja2);


		baraja.barajarMazo(this);

		generarMano();
		lances.nextMano(jugMano);

<<<<<<< HEAD
		salida.println("Mano: " + jugMano.getNombre() + ".");
=======
		salida.println("Mano: " + jugMano.getNombre()+".");
>>>>>>> master


		while (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40){
			

			baraja.restablecerBaraja();
			baraja.barajarMazo(this);
			baraja.repartirJugada(this);

			mostrarManosJugadores();

		

			lances.resuelveGrande();
			lances.resuelveChica();
			lances.resuelvePares();
			lances.resuelveJuego();
			salida.print("- " + pareja1.getPiedras() + " " + pareja2.getPiedras() + "\n");

			if (pareja1.getPiedras() < 40 && pareja2.getPiedras() < 40) {
				generarMano();
				lances.nextMano(jugMano);
			}
		

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
			pareja2.getJug1().getMano().mostrarBaraja();	
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();

		} else if (pareja1.getJug2().equals(jugMano)) {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug1().getMano().mostrarBaraja();	
			salida.print("*");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();
		} else if (pareja2.getJug1().equals(jugMano)) {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("*");
			pareja2.getJug1().getMano().mostrarBaraja();	
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug2().getMano().mostrarBaraja();
		} else {
			salida.print("-");
			pareja1.getJug1().getMano().mostrarBaraja();
			salida.print("-");
			pareja2.getJug1().getMano().mostrarBaraja();	
			salida.print("-");
			pareja1.getJug2().getMano().mostrarBaraja();
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

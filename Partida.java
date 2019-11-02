package paquete;

public class Partida {
	private Baraja baraja = new Baraja();
	private Pareja pareja1 = new Pareja();
	private Pareja pareja2 = new Pareja();
	private Jugador jugMano;
	private Pareja ganadores;
	
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

			jugador.setId(i);

			switch (i) {
			case 1:
				jugador.setNombre("J1");
				jugador.setParejaString("PA");
				jugador.setClave();
				pareja1.setJug1(jugador);
		
				break;
			case 2:
				jugador.setNombre("J2");
				jugador.setParejaString("PA");
				jugador.setClave();
				pareja1.setJug2(jugador);
				break;

			case 3:
				jugador.setNombre("J1");
				jugador.setParejaString("PB");
				jugador.setClave();
				pareja2.setJug1(jugador);
				break;
			case 4:
				jugador.setNombre("J2");
				jugador.setParejaString("PB");
				jugador.setClave();
				pareja2.setJug2(jugador);
				break;
			}

		}
		
		/*Jugador jug1 = pareja1.getJug1();
		Jugador jug2 = pareja1.getJug2();
		Jugador jug3 = pareja2.getJug1();
		Jugador jug4 = pareja2.getJug2();*/

		/*String linea = pareja1.getEquipo() + ": " + jug1.getNombre() + " y " 
			+ jug2.getNombre() + ".\n" +pareja2.getEquipo() + ": " 
			+ jug3.getNombre() + " y " + jug4.getNombre() + ".\n";
	
		resPartida(linea);*/

	} // fin crearJugadores()



}

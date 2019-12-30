package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Entrada {

	private Integer modoJuego;
	private static LinkedList<String> jugadas = new LinkedList<String>();
	private static LinkedList<String> comandosValidos = new LinkedList<String>();
	private static String ficheroJugadas;
	private static String[] comandos = { "NewPlayer", "DeletePlayer", "NewCouple", "DeleteCouple", "ResetPlayers",
			"LoadPlayers", "GenerateRandomDelivery", "PlayGame", "PlayHand", "ResolvePares", "ResolveJuego",
			"ResolveGrande", "ResolveChica","DumpPlayers" };
	private static String ficheroComandos;

	Entrada(String[] args, Salida salida, Partida partida) {
		modoJuego = leerArgs(args, salida, partida);
	}

	public static int leerArgs(String[] args, Salida salida, Partida partida) {

		try {
			// ------------Reparto preestablecido-------------//

			if (args[0].equals("-j")) {
				// Leer fichero jugadas \\
				LinkedList<String> auxArgs = new LinkedList<String>();

				for (int i = 2; i < args.length; i++) {
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() != 0) {
					opcionalArgs(auxArgs, salida, partida);
				} else {
					partida.crearJugadores();
				}

				Entrada.ficheroJugadas = args[1];

				guardarJugadas();

				return 0;

			} else if (args[0].equals("-c")) {
				// Leer fichero comandos \\
				LinkedList<String> auxArgs = new LinkedList<>();
				guardarComandos(args[1]);

				for (int i = 2; i < args.length; i++) {
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() != 0) {
					opcionalArgs(auxArgs, salida, partida);
				}

				return 1;
			} else {
				// Es modo autonomo \\
				// ------------Autonomo-------------//
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 0; i < args.length; i++) {
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() != 0) {
					opcionalArgs(auxArgs, salida, partida);
				} else {
					partida.crearJugadores();
				}

				return 2;
			}

		} catch (Exception e) {
			return 2;

		}

	}

	private static void guardarComandos(String fichero) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			String line = reader.readLine();
			int n = 0;
			while (line != null) {
				n++;
				for (String comando : comandos) {

					if (line.startsWith(comando)) {
						comandosValidos.addLast(line);
					}

				}

				line = reader.readLine();

			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static boolean isPlayer(LinkedList<Jugador> lista, int id) {

		for (Jugador jugador : lista) {
			if (jugador.getId() == id) {
				return true;
			}
		}

		return false;
	}

	public static void leerComandos(Partida partida, Salida salida) {
		LinkedList<Jugador> listaJugadores = new LinkedList<Jugador>();
		LinkedList<Pareja> listaParejas = new LinkedList<Pareja>();
		LinkedList<Pareja> listaParejasValidas = new LinkedList<Pareja>();

		for (String comandos : comandosValidos) {
			String[] split = comandos.split("\\s+");
			Integer id = 0;
			switch (split[0]) {
			case "NewPlayer":

				try {
					id = Integer.parseInt(split[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String nombre = "";
				for (int i = 2; i < split.length; i++) {
					if (i == split.length - 1) {
						nombre += split[i];
					} else {
						nombre += split[i] + " ";
					}
				}

				if (isPlayer(listaJugadores, id)) {
					salida.println(comandos + ": FAIL.");
				} else {
					Jugador jugador = new Jugador(id, nombre);
					listaJugadores.addLast(jugador);
					salida.println(comandos + ": OK.");
				}
				break;
			case "DeletePlayer":
				try {
					id = Integer.parseInt(split[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				int deleted = 0;
				for (Jugador jugador2 : listaJugadores) {
					if (jugador2.getId() == id) {
						listaJugadores.remove(jugador2);
						salida.println(comandos + ": OK.");
						deleted = 1;
					} else {

					}
				}

				if (deleted == 0) {
					salida.println(comandos + ": FAIL.");
				}
				break;
			case "NewCouple":
				int idJ1 = 0;
				int idJ2 = 0;
				Pareja pareja = new Pareja();
				try {
					id = Integer.parseInt(split[1]);
					idJ1 = Integer.parseInt(split[2]);
					idJ2 = Integer.parseInt(split[3]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				for (Jugador jugador2 : listaJugadores) {
					if (jugador2.getId() == idJ1) {
						pareja.setJug1(jugador2);
					} else if (jugador2.getId() == idJ2) {
						pareja.setJug2(jugador2);
					}
				}
				if (pareja.getJug1() == null || pareja.getJug2() == null) {
					salida.println(comandos + ": FAIL.");
				} else {
					pareja.setId(id);

					String equipo = "";
					for (int i = 4; i < split.length; i++) {
						if (i == split.length - 1) {
							equipo += split[i];
						} else {
							equipo += split[i] + " ";
						}
					}

					pareja.setEquipo(equipo);
					int error = 0;
					for (Pareja par : listaParejas) {
						if (par.getId() == pareja.getId()) {
							salida.println(comandos + ": FAIL.");
							error = 1;
						}
					}

					if (error == 0) {
						listaParejas.addLast(pareja);
						salida.println(comandos + ": OK.");

					}

				}
				break;
			case "DeleteCouple":
				try {
					id = Integer.parseInt(split[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				int error = 1;
				for (Pareja pareja2 : listaParejas) {
					if (pareja2.getId() == id) {
						listaParejas.remove(pareja2);
						error = 0;
					}
				}
				if (error == 1) {
					salida.println(comandos + ": FAIL.");
				} else {
					salida.println(comandos + ": OK.");
				}
				break;
			case "DumpPlayers":
				int error2 = 0;
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(split[1]));
				} catch (Exception e) {
					error2 = 1;
					salida.println(comandos + ": FAIL.");
					// TODO: handle exception
				}
				for (Jugador jugador2 : listaJugadores) {
					try {
						writer.write(jugador2.toString() + "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						salida.println(comandos + ": FAIL.");
						error2 = 1;
					}
				}
				for (Pareja pareja2 : listaParejas) {
					try {
						writer.write(pareja2.toString() + "\n");
						writer.flush();
					} catch (Exception e) {
						salida.println(comandos + ": FAIL.");
						error2 = 1;
					}
				}

				if (error2 == 0) {
					salida.println(comandos + ": OK.");
				}
				break;
			case "ResetPlayers":
				listaJugadores.clear();
				listaParejas.clear();
				listaParejasValidas.clear();
				partida.setPareja1(null);
				partida.setPareja2(null);
				partida.setJugMano(null);
				partida.setGanadores(null);

				salida.println(comandos + ": OK.");

				break;
			case "LoadPlayers":
				try {
					leerFichJugadoresComandos(split[1], listaJugadores, listaParejas, listaParejasValidas);
					salida.println(comandos + ": OK.");
				} catch (Exception e) {
					salida.println(comandos + ": FAIL.");
				}

				break;
			case "GenerateRandomDelivery":
				String njug = split[1];
				String idMano = split[2];
				String fichJug = split[3];
				if ((partida.getPareja1() == null || partida.getPareja2() == null)) {

				}
				String jugadas = partida.getBaraja().generarJugadas(idMano, njug);
				try {
					BufferedWriter writer2 = new BufferedWriter(new FileWriter(fichJug));
					writer2.write(jugadas);
					writer2.close();
					salida.println(comandos + ": OK.");

				} catch (Exception e) {
					salida.println(comandos + ": FAIL.");
				}
				// partida.mostrarManosJugadores();
				break;
			case "PlayHand":
				String jugada = comandos.substring(8).trim();
				Entrada.jugadas.addLast(jugada);
				partida.crearJugadores();
				partida.setJugadas(partida.getJugadas() + 1);

				partida.getBaraja().restablecerBaraja();
				Entrada.leerFichJugadas(partida);

				Lances lances = new Lances();
				lances.setSalida(salida);
				lances.setData(partida.getPareja1(), partida.getPareja2());

				lances.nextMano(partida.getJugMano());
				salida.print(comandos + ": ");
				lances.resuelveGrande();
				salida.print(", ");
				lances.resuelveChica();
				salida.print(", ");
				lances.resuelvePares();
				salida.print(", ");
				lances.resuelveJuego();

				salida.print("\n");
				break;
			case "ResolveGrande":
				String jugada2 = comandos.substring(14).trim();
				Entrada.jugadas.addLast(jugada2);
				partida.crearJugadores();
				partida.setJugadas(partida.getJugadas() + 1);

				partida.getBaraja().restablecerBaraja();
				Entrada.leerFichJugadas(partida);

				Lances lances2 = new Lances();
				lances2.setSalida(salida);
				lances2.setData(partida.getPareja1(), partida.getPareja2());

				lances2.nextMano(partida.getJugMano());
				salida.print(comandos + ": ");
				lances2.resuelveGrandeComandos();
				salida.print("\n");

				break;
			case "ResolveChica":
				String chica = comandos.substring(13).trim();
				Entrada.jugadas.addLast(chica);
				partida.crearJugadores();
				partida.setJugadas(partida.getJugadas() + 1);

				partida.getBaraja().restablecerBaraja();
				Entrada.leerFichJugadas(partida);

				Lances lanceChica = new Lances();
				lanceChica.setSalida(salida);
				lanceChica.setData(partida.getPareja1(), partida.getPareja2());

				lanceChica.nextMano(partida.getJugMano());
				salida.print(comandos + ": ");
				lanceChica.resuelveChicaComandos();
				salida.print("\n");

				break;
			case "ResolvePares":
				String pares = comandos.substring(13).trim();
				Entrada.jugadas.addLast(pares);
				partida.crearJugadores();
				partida.setJugadas(partida.getJugadas() + 1);

				partida.getBaraja().restablecerBaraja();
				Entrada.leerFichJugadas(partida);

				Lances lancePares = new Lances();
				lancePares.setSalida(salida);
				lancePares.setData(partida.getPareja1(), partida.getPareja2());

				lancePares.nextMano(partida.getJugMano());
				salida.print(comandos + ": ");
				lancePares.resuelveParesComandos();
				salida.print("\n");


				break;
			case "ResolveJuego":
				String juego = comandos.substring(13).trim();

				Entrada.jugadas.addLast(juego);
				partida.crearJugadores();
				partida.setJugadas(partida.getJugadas() + 1);

				partida.getBaraja().restablecerBaraja();
				Entrada.leerFichJugadas(partida);

				Lances lanceJuego = new Lances();
				lanceJuego.setSalida(salida);
				lanceJuego.setData(partida.getPareja1(), partida.getPareja2());

				lanceJuego.nextMano(partida.getJugMano());
				salida.print(comandos + ": ");
				lanceJuego.resuelveJuegoComandos();
				salida.print("\n");


				break;

			case "PlayGame":
				if (listaParejasValidas.size() >= 2) {
					partida.setPareja1(listaParejasValidas.get(0));
					partida.setPareja2(listaParejasValidas.get(1));

				} else {
					if (partida.getPareja1() == null || partida.getPareja2() == null) {
						partida.crearJugadores();
					}
				}

				BufferedWriter aux = salida.getWriter();
				String[] args = { "-j", split[1], "-o", split[2] };

				Entrada.jugadas.clear();
				partida.setJugadas(0);

				leerArgs(args, salida, partida);
				partida.juegoPreestablecido();
				salida.setWriter(aux);

				salida.println(comandos + ": OK.");

				break;

			default:
				if (comandos.charAt(0) == '#') {
					// nada
				} else {
					salida.println("Comando erroneo");
				}

				break;
			}
		}

	}

	public static void leerFichJugadores(String fich, Partida partida) throws Exception {
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		LinkedList<Pareja> parejas = new LinkedList<Pareja>();
		LinkedList<Pareja> parejasValidas = new LinkedList<Pareja>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(fich));
			String line = reader.readLine();

			while (line != null) {
				//// System.out.println(line);

				if (line.charAt(0) == 'J') {
					Jugador jug = new Jugador();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 2; i < array.length; i++) {
						if (i == array.length - 1) {
							nombre = nombre + "" + array[i];
						} else {
							nombre = nombre + "" + array[i] + " ";

						}
					}

					jug.setId(Integer.parseInt(array[1]));
					jug.setNombre(nombre);

					int añadido = 0;
					for (Jugador jugador : jugadores) {
						if (jugador.getId() == jug.getId()) {
							jugadores.remove(jugador);
							jugadores.addLast(jug);

							añadido = 1;
						}
					}

					if (añadido != 1) {

						jugadores.addLast(jug);
					}

				} else {
					Pareja pareja = new Pareja();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 4; i < array.length; i++) {
						if (i == array.length - 1) {
							nombre = nombre + "" + array[i];
						} else {
							nombre = nombre + "" + array[i] + " ";

						}
					}

					pareja.setId(Integer.parseInt(array[1]));
					pareja.setEquipo(nombre);

					for (Jugador jug : jugadores) {
						if (Integer.parseInt(array[2]) == jug.getId()) {
							jug.setPareja(pareja);

							pareja.setJug1(jug);
						} else if (Integer.parseInt(array[3]) == jug.getId()) {
							// System.out.println("Encontrado jug2");

							jug.setPareja(pareja);

							pareja.setJug2(jug);
						}
					}

					if (pareja.getJug1() == null || pareja.getJug2() == null) {
						// System.out.println("Pareja con al menos un jug null");

					} else {
						parejas.addLast(pareja);

					}

				}

				line = reader.readLine();

			}
			reader.close();

			for (int i = 0; i < parejas.size(); i++) {
				Pareja pareja = parejas.get(i);

				// Siempre válida la primera
				if (i == 0) {
					parejasValidas.addLast(pareja);
				} else {
					if (pareja.getJug1().equals(parejas.get(i - 1).getJug1())
							|| pareja.getJug2().equals(parejas.get(i - 1).getJug2())) {
						// Algún jugador repetido, pareja no válida
						// System.out.println("Jugador repetido, pareja no valida");
						parejas.remove(i);
						i--;
					} else {
						parejasValidas.addLast(pareja);
					}
				}
			}

			for (int i = 0; i < parejasValidas.size(); i++) {
				if (i == 0) {
					partida.setPareja1(parejasValidas.get(0));
				} else if (i == 1) {
					partida.setPareja2(parejasValidas.get(1));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.toString());
		}

	}

	public static void leerFichJugadoresComandos(String fich, LinkedList<Jugador> jugs, LinkedList<Pareja> pars,
			LinkedList<Pareja> parsValidas) throws Exception {
		LinkedList<Jugador> jugadores = jugs;
		LinkedList<Pareja> parejas = pars;
		LinkedList<Pareja> parejasValidas = parsValidas;

		try {

			BufferedReader reader = new BufferedReader(new FileReader(fich));
			String line = reader.readLine();

			while (line != null) {
				//// System.out.println(line);

				if (line.charAt(0) == 'J') {
					Jugador jug = new Jugador();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 2; i < array.length; i++) {
						if (i == array.length - 1) {
							nombre = nombre + "" + array[i];
						} else {
							nombre = nombre + "" + array[i] + " ";

						}
					}

					jug.setId(Integer.parseInt(array[1]));
					jug.setNombre(nombre);

					int añadido = 0;
					for (Jugador jugador : jugadores) {
						if (jugador.getId() == jug.getId()) {
							jugadores.remove(jugador);
							jugadores.addLast(jug);

							añadido = 1;
						}
					}

					if (añadido != 1) {

						jugadores.addLast(jug);
					}

				} else {
					Pareja pareja = new Pareja();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 4; i < array.length; i++) {
						if (i == array.length - 1) {
							nombre = nombre + "" + array[i];
						} else {
							nombre = nombre + "" + array[i] + " ";

						}
					}

					pareja.setId(Integer.parseInt(array[1]));
					pareja.setEquipo(nombre);

					for (Jugador jug : jugadores) {
						if (Integer.parseInt(array[2]) == jug.getId()) {
							jug.setPareja(pareja);

							pareja.setJug1(jug);
						} else if (Integer.parseInt(array[3]) == jug.getId()) {
							// System.out.println("Encontrado jug2");

							jug.setPareja(pareja);

							pareja.setJug2(jug);
						}
					}

					if (pareja.getJug1() == null || pareja.getJug2() == null) {
						// System.out.println("Pareja con al menos un jug null");

					} else {
						parejas.addLast(pareja);

					}

				}

				line = reader.readLine();

			}
			reader.close();

			for (int i = 0; i < parejas.size(); i++) {
				Pareja pareja = parejas.get(i);

				// Siempre válida la primera
				if (i == 0) {
					parejasValidas.addLast(pareja);
				} else {
					if (pareja.getJug1().equals(parejas.get(i - 1).getJug1())
							|| pareja.getJug2().equals(parejas.get(i - 1).getJug2())) {
						// Algún jugador repetido, pareja no válida
						// System.out.println("Jugador repetido, pareja no valida");
						parejas.remove(i);
						i--;
					} else {
						parejasValidas.addLast(pareja);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.toString());
		}

	}

	public static void opcionalArgs(LinkedList<String> auxArgs, Salida salida, Partida partida) {

		if (auxArgs.get(0).equals("-p")) {
			try {
				leerFichJugadores(auxArgs.get(1), partida);

			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				if (auxArgs.get(2).equals("-o")) {
					try {
						salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(3))));

					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			} catch (Exception e) {
			}

		} else {
			if (auxArgs.get(0).equals("-o")) {
				try {
					salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(1))));

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	}

	public static void guardarJugadas() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ficheroJugadas));

			String linea = reader.readLine();
			while (linea != null) {
				jugadas.addLast(linea);

				linea = reader.readLine();
			}
			reader.close();
			
		} catch (Exception e) {
		}


	}

	public static void leerFichJugadas(Partida partida) {

		Jugador jugMano = partida.getJugMano();
		Pareja pareja1 = partida.getPareja1();
		Pareja pareja2 = partida.getPareja2();
		String linea = jugadas.get(partida.getJugadas() -1);
		//System.out.println(linea);
		char idCarta;
		char palo;
		Baraja barajaJug = new Baraja();


		String mano1 = linea.substring(0, 17); // -(30, R0, 1C, 2B)

		String mano3 = linea.substring(17, 34); // -(4C, 4B, 4E, SB)
		String mano2 = linea.substring(34, 51); // *(5E, 4C, 3E, 3B)
		String mano4 = linea.substring(51); // -(6B, 6E, RB, RB, CC)
		String[] manos = {mano1,mano3,mano2,mano4};
		for(int j = 0; j < manos.length ; j++){
			
			char mano = manos[j].charAt(0);
			manos[j] = manos[j].substring(2, manos[j].length() -1); 
		
			String[] cartas = manos[j].split("\\,\\s");
		
			linea = "" ;
			for  (int i = 0; i < cartas.length; i++){
				idCarta = cartas[i].charAt(0);
				palo = cartas[i].charAt(1);
				//System.out.print("("+idCarta+","+palo+")");
				Carta carta = partida.getBaraja().findCarta(palo, idCarta);
				
				//System.out.print(" --> "+carta.getId()+"\n");
				//pareja1.getJug1().getMano().añadirCarta(carta);
				switch(j){
					case 0:
					if(mano == '*'){
						jugMano = pareja1.getJug1();
						partida.setJugMano(jugMano);
					}
					pareja1.getJug1().getMano().añadirCarta(carta);
					break;
					case 1:
					
					if(mano == '*'){
						jugMano = pareja2.getJug1();
						partida.setJugMano(jugMano);

					}
					pareja2.getJug1().getMano().añadirCarta(carta);
					break;
					case 2:
					
					if(mano == '*'){
						jugMano = 	pareja1.getJug2();
						partida.setJugMano(jugMano);
					}
					pareja1.getJug2().getMano().añadirCarta(carta);
					break;
					case 3:
					
					if(mano == '*'){
						jugMano = pareja2.getJug2();
						partida.setJugMano(jugMano);
					}
					pareja2.getJug2().getMano().añadirCarta(carta);
					break;
				}
		
			}
	
			


		}
		

		partida.setPareja1(pareja1);
		partida.setPareja2(pareja2);
		


		partida.getBaraja().restablecerBaraja();


		/*
		
		linea = linea + "" + Salida.ordenarJugada(pareja1.getJug1(), partida);
		linea = linea + "" + Salida.ordenarJugada(pareja1.getJug2(), partida);
		linea = linea + "" + Salida.ordenarJugada(pareja2.getJug1(), partida);
		linea = linea + "" + Salida.ordenarJugada(pareja2.getJug2(), partida);

		String linea2 = "Mano: " + jugMano.getNombre()+"\n"+linea+"\n";

		System.out.println(linea2);
		
		*/
		
	}

	

	public Integer getModoJuego() {
		return modoJuego;
	}

	public void setModoJuego(Integer modoJuego) {
		this.modoJuego = modoJuego;
	}


	
	
	
}

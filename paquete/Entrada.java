package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


public class Entrada {
	
	private Integer modoJuego;
	private static LinkedList<String> jugadas = new LinkedList<String>();
	private static String ficheroJugadas;

	Entrada (String[] args, Salida salida, Partida partida){
		leerArgs(args,salida,partida);		
	}
	
	public void leerArgs(String[] args,Salida salida, Partida partida){

		try {
			//------------Reparto preestablecido-------------//

			if (args[0].equals("-j")){
				// Leer fichero jugadas \\
				LinkedList<String> auxArgs = new LinkedList<String>();

				for (int i = 2; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() != 0) {
					opcionalArgs(auxArgs,salida,partida);
				} else {
					partida.crearJugadores();
				}


				Entrada.ficheroJugadas = args[1];

				guardarJugadas();

				modoJuego = 0;

			} else if (args[0].equals("-c")) {
				// Leer fichero comandos \\
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 2; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}
				
				if (auxArgs.size() > 1) opcionalArgs(auxArgs,salida,partida);

				modoJuego = 1;
			} else {
				// Es modo autonomo \\
				//------------Autonomo-------------//
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 0; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() != 0){
					opcionalArgs(auxArgs,salida,partida);
				} else {
					partida.crearJugadores();
				}


				modoJuego = 2;
			}

		} catch (Exception e) {
			modoJuego = 2;

		}

	}

	public void leerFichJugadores(String fich, Partida partida) {
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		LinkedList<Pareja> parejas = new LinkedList<Pareja>();
		LinkedList<Pareja> parejasValidas = new LinkedList<Pareja>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(fich));
			String line = reader.readLine();

			while (line != null) {
				////System.out.println(line);

				if (line.charAt(0) == 'J') {
					Jugador jug = new Jugador();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 2; i < array.length; i++) {
						if( i == array.length -1) {
							nombre = nombre + "" + array[i];
						} else {
							nombre = nombre + "" + array[i] + " ";

						}
					}
					

					jug.setId(Integer.parseInt(array[1]));
					jug.setNombre(nombre);

					jugadores.addLast(jug);

				} else {
					Pareja pareja = new Pareja();
					String[] array = line.split("\\s+");
					String nombre = "";

					for (int i = 4; i < array.length; i++) {
						if( i == array.length -1) {
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
							//System.out.println("Encontrado jug2");

							jug.setPareja(pareja);

							pareja.setJug2(jug);
						}		
					}

					if (pareja.getJug1() == null || pareja.getJug2() == null) {
						//System.out.println("Pareja con al menos un jug null");


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
					if (pareja.getJug1().equals(parejas.get(i-1).getJug1()) || pareja.getJug2().equals(parejas.get(i-1).getJug2())) {
						// Algún jugador repetido, pareja no válida
						//System.out.println("Jugador repetido, pareja no valida");
						parejas.remove(i);
						i--;
					} else {
						parejasValidas.addLast(pareja);
					}
				}
			}

			if (parejasValidas.size() == 0) {
				partida.crearJugadores();


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

			} else {
				//System.out.println("Todo va correcto");

				
			}
	


			partida.setPareja1(parejasValidas.get(0));
			partida.setPareja2(parejasValidas.get(1));
			
		} catch (Exception e) {
			//TODO: handle exception
		}
	

	}

	public void opcionalArgs(LinkedList<String> auxArgs, Salida salida, Partida partida){

		if (auxArgs.get(0).equals("-p")){
			leerFichJugadores(auxArgs.get(1), partida);

			try {
				if (auxArgs.get(2).equals("-o")){
					try {
						salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(3))));

					} catch (Exception e) {
						//TODO: handle exception
					}
				} 
				
			} catch (Exception e) {
			}
			

		} else {
			if (auxArgs.get(0).equals("-o")){
				try {
					salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(1))));

				} catch (Exception e) {
					//TODO: handle exception
				}
			} 
	
		}
	}

	public void guardarJugadas() {
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
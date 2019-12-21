package paquete;

import java.io.BufferedWriter;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.OutputStreamWriter;



public class Probando{
	
	public static void main(String[] args) {

		
	
		
		Salida salida = new Salida();
		Entrada entrada = new Entrada(args,salida);		
		
		Partida partida = new Partida();
		partida.setSalida(salida);
		

		
		int modo = entrada.getModoJuego();

		switch(modo){
			case 0:
				partida.juegoPreestablecido();

				break;
			case 1:
				partida.juegoComandos();

				break;
			case 2:
				partida.juegoAutonomo();

				break;
		}
		
	
	}


	// ---- 0 -> reparto preestablecido
	// ---- 1 -> juego por comandos
	// ---- 2 -> juego autonomo
	
	
}
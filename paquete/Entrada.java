package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


public class Entrada {
	

	
	private Integer modoJuego;

	Entrada (String[] args, Salida salida){
		leerArgs(args,salida);
		
	}
	
	public void leerArgs(String[] args,Salida salida){

		try {
			//------------Reparto preestablecido-------------//
			if (args[0].equals("-j")){
				// Leer fichero jugadas \\
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 2; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() > 1) opcionalArgs(auxArgs,salida);

				modoJuego = 0;

			} else if (args[0].equals("-c")) {
				// Leer fichero comandos \\
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 2; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}
				
				if (auxArgs.size() > 1) opcionalArgs(auxArgs,salida);

				modoJuego = 1;
			} else {
				// Es modo autonomo \\
				//------------Autonomo-------------//
				LinkedList<String> auxArgs = new LinkedList<>();

				for (int i = 0; i < args.length; i++){
					auxArgs.addLast(args[i]);
				}

				if (auxArgs.size() > 1) opcionalArgs(auxArgs,salida);


				modoJuego = 2;
			}

		} catch (Exception e) {
			modoJuego = 2;
		}

	}

	public void opcionalArgs(LinkedList<String> auxArgs, Salida salida){
		

		if (auxArgs.get(0).equals("-p")){
			//leerFichJug(auxArgs.get(i+1));

			try {
				if (auxArgs.get(2).equals("-o")){
					try {
						salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(3), true)));

					} catch (Exception e) {
						//TODO: handle exception
					}
				} 
				
			} catch (Exception e) {
			}
			

		} else {
			if (auxArgs.get(0).equals("-o")){
				try {
					salida.setWriter(new BufferedWriter(new FileWriter(auxArgs.get(1), true)));

				} catch (Exception e) {
					//TODO: handle exception
				}
			} 
	
		}
	}

	public Integer getModoJuego() {
		return modoJuego;
	}

	public void setModoJuego(Integer modoJuego) {
		this.modoJuego = modoJuego;
	}


	
	
	
}
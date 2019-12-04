package paquete;

import javax.net.ssl.SSLContext;

public class Probando{
	
	public static void main(String[] args) {
		Partida partida = new Partida();
		partida.crearJugadores();
		partida.getBaraja().crearBaraja();
		partida.getBaraja().barajarMazo(partida); // pasamos partida para poder acceder a los metodos de partida ( ficheroSalida )
		partida.getBaraja().repartirJugada(partida); // aunque de momento no lo usamos
		System.out.println();
		partida.resuelvePares();
		
		
		
		/*partida.generarMano();
		partida.generarMano();
		partida.generarMano();
		partida.generarMano();
		partida.generarMano();
		partida.generarMano();
		partida.generarMano();*/
	}
	
}
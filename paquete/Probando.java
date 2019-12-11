package paquete;

public class Probando{
	
	public static void main(String[] args) {
		Partida partida = new Partida();
		
		// ---------------- QUIEN JUEGA ------------- \\
		partida.crearJugadores();	
		partida.generarMano();
		
		
		// ----------------- PREPARACION --------------\\
		partida.getBaraja().crearBaraja();
		partida.getBaraja().barajarMazo(partida); // pasamos partida para poder acceder a los metodos de partida ( ficheroSalida ), tambien haciendolo public static se podría acceder
		partida.getBaraja().repartirJugada(partida); // aunque de momento no lo usamos
		


		// ------------------  JUGAMOS ----------------- \\
		partida.resuelveGrande();
		partida.resuelveChica();
		partida.resuelvePares();
		partida.resuelveJuego();


		// lo siguiente es añadir el orden de jugador mano. 1º aleatorio y después secuencia
		
	}
	
}
package paquete;

public class Probando{
	
	public static void main(String[] args) {
		Partida partida = new Partida();
		partida.crearJugadores();
		for (int i = 0; i < 10; i++) {
			
			partida.generarMano();
			
		}
		partida.getBaraja().crearBaraja();
		partida.getBaraja().barajarMazo(partida); // pasamos partida para poder acceder a los metodos de partida ( ficheroSalida )
		partida.getBaraja().repartirJugada(partida); // aunque de momento no lo usamos
		//partida.resuelvePares();
		partida.resuelveJuego();
		// lo siguiente es añadir el orden de jugador mano. 1º aleatorio y después secuencia
		
	}
	
}
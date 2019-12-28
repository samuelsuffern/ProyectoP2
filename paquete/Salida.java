package paquete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;


public class Salida{
    private BufferedWriter writer;

    
    
    public void println(String string){
        if (writer == null){
            System.out.println(string);
        } else {
            try {
                this.writer.write(string + "\n");
                this.writer.flush();
    
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        
    }

    public void print(String string){
        if (writer == null){
            System.out.print(string);
        } else {
            try {
                this.writer.write(string);
                this.writer.flush();
    
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        
    }


    public static String ordenarJugada(Jugador jugador, Partida partida){
        Jugador jugMano = partida.getJugMano();
		String linea = "";
        LinkedList<Carta> lista = jugador.getMano().getBarajaList();
        try {
            Collections.sort(lista, new OrdenaJugada());

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
		
		if (jugador.equals(jugMano)){
			linea = linea + "*";
		} else {
			linea = linea + "-";
        }


		for(int i = 0; i < lista.size(); i++){
			Carta carta = lista.get(i);
			if (i == 0){
				linea = linea + "(" + carta.getId() + "" + carta.getPalo() +", ";
			}else if ( i != lista.size() -1){
				linea = linea + carta.getId() + "" + carta.getPalo() +", "; 
			} else {
				linea = linea + carta.getId() + "" + carta.getPalo() +")";
			}
		}



		return linea;

	}


   

  
    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
       
      
    }

    
	

	
}
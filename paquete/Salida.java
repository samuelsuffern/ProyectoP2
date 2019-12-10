package paquete;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Salida{

    public static void ficheroPartida(String line,File file){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            writer.close();
        } catch (Exception e) {
            //TODO: handle exception
        }


    }	
	

	
}
	
	
	
	
}
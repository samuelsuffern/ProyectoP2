package paquete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;


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
                System.out.println(e);
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
                System.out.println(e);
            }
        }
        
    }


   

  
    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
       
      
    }

    
	

	
}
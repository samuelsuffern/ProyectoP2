package paquete;
import java.util.Comparator;

public class OrdenaJugada implements Comparator<Carta>{

    
    public int compare(Carta c1,Carta c2){
        
         if(c1.getValor() < c2.getValor()){

             return -1;
         } else if(c1.getValor() > c2.getValor()){
             return 1;
         } else{
            //valor 1 --> 1,2
            // valor 4,...,7 -> 4,...,7
            // valor 10 --> S,C,R,3

            if(c1.getId() == c2.getId()){
                if(c1.getPalo() < c2.getPalo() ) {
                    return -1;
                }else{
                    return 1;
                }
            }else{
                if(c1.getId() == '2' && c2.getId() == '1'){
                    return 1;
                }else if(c1.getId() == '3'){
                    return 1;
                }else if(c1.getId() == 'R' && (c2.getId()=='S' || c2.getId() == 'C' )) {
                    return 1;
                }else if(c1.getId() == 'C' && c2.getId() == 'S'){
                    return 1;
                }else{
                    return -1;
                }
            }
         }

    }


}
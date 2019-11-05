package paquete;

public class Carta {
	  private char id;
	    private Integer valor;
	    private char palo;
	    private char representacion;

	    public char getRepresentacion() {
			return representacion;
		}

		

		public char getId() {
	        return id;
	    }

	    public void setId(char id) {
	        this.id = id;
	        if(id == '1' || id == '2'){
	        	this.representacion='A'; 
	        }else if(id == '3'){
	        	this.representacion = 'R';
	        	
	        }else{
	        	this.representacion = id;
	        }

	    }

	    public char getPalo() {
	        return palo;
	    }

	    public void setPalo(char palo) {
	        this.palo = palo;
	    }

	    public void setValor(Integer valor){
	        this.valor = valor;
	    }

	    public Integer getValor(){
	        return valor;
	    }

}

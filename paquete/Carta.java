package paquete;

public class Carta {
	  private char id;
	    private Integer valor;
	    private char palo;

	    public char getId() {
	        return id;
	    }

	    public void setId(char id) {
	        this.id = id;

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

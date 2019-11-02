package paquete;

public class Jugador {
	 private Integer id;
	    private String nombre;
	    private Baraja mano = new Baraja(); 
	    private String parejaString; 
	    private String clave;

	    
	    public String getClave() {
			return clave;
		} 
	    public void setClave() {
	    	this.clave = nombre + "" +parejaString;
	    }

		/*
	     * Getters & Setters
	     */
	    public String getParejaString() {
			return parejaString;
		}

		public void setParejaString(String parejaString) {
			this.parejaString = parejaString;
		}

		public void setId(Integer id){
	        this.id = id;
	    }

	    public Integer getId(){
	        return id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public Baraja getMano() {
	        return mano;
	    }

	    public void setMano(Baraja mano) {
	        this.mano = mano;
	    }

}

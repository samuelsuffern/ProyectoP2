package paquete;

public class Jugador {
	private Integer id;
	private String nombre;
	private Baraja mano = new Baraja(); 
	private Pareja miPareja; 
	
	
	public Jugador(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	
	}
	public Jugador(){}
		/*
	     * Getters & Setters
	     */
	    public Pareja getPareja() {
			return miPareja;
		}

		public void setPareja(Pareja pareja) {
			this.miPareja = pareja;
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
		@Override
		public String toString() {
			return "J "+id+" "+nombre;
		}

}
